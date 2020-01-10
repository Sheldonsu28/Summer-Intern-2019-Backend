package com.mmm.weixin.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.httpclient.HTTPException;
import com.google.gson.Gson;
import com.mmm.weixin.consts.PhoneType;
import com.mmm.weixin.dto.param.*;
import com.mmm.weixin.dto.qrcode.QRCodeDto;
import com.mmm.weixin.vo.*;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmm.weixin.common.GenerateAccessToken;
import com.mmm.weixin.common.ResultValidate;
import com.mmm.weixin.common.TokenValidate;
import com.mmm.weixin.config.redis.RedisClient;
import com.mmm.weixin.constants.UserConstants;
import com.mmm.weixin.dao.CommentsMapper;
import com.mmm.weixin.dao.CommentsReplyMapper;
import com.mmm.weixin.dao.UserMapper;
import com.mmm.weixin.dto.AccessTokenDto;
import com.mmm.weixin.dto.AddressParamDto;
import com.mmm.weixin.dto.CommentDto;
import com.mmm.weixin.dto.CommentParamDto;
import com.mmm.weixin.dto.CommentReplyDto;
import com.mmm.weixin.dto.CommentReplyParamDto;
import com.mmm.weixin.dto.CommentReplyQueryDto;
import com.mmm.weixin.dto.LoginDto;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.dto.ShopDetailParamDto;
import com.mmm.weixin.dto.ShopDto;
import com.mmm.weixin.dto.UserDto;
import com.mmm.weixin.dto.UserParamDto;
import com.mmm.weixin.exception.ServiceException;
import com.mmm.weixin.exception.WxLoginException;
import com.mmm.weixin.feign.SellerClient;
import com.mmm.weixin.service.IUserService;
import com.mmm.weixin.utils.base.ValidateHelper;
import com.mmm.weixin.utils.jwt.JwtUtil;

@Service
public class UserServiceImpl implements IUserService,ApplicationRunner  {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RestTemplate restTmp;

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private CommentsReplyMapper replyMapper;

    @Autowired
    private SellerClient sellerClient;

    @Resource
    private JwtUtil jwtUtil;

    @Autowired
    private TokenValidate tokenValidate;

    @Value("${code2Session}")
    private String code2Session;

    @Value("${baidu.geocoder}")
    private String baiduGeocoder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisClient redisClient;

	@Value("${qiniu_server_ip_address}")
	private String serverIpAddress;
	
	@Value("${qiniu_access_key}")
	private String accessKey;
	
	@Value("${qiniu_secret_key}")
	private String secretKey;
	
	@Value("${qiniu_bucket_name}")
	private String bucketName;
	
	@Value("${weixin_code_url}")
	private String getWxCodeUrl;
	
	@Value("${golf_tool_wxapp_page}")
	private String golfToolPage;
	
	@Value("${appointment_wxapp_page}")
	private String appointmentPage;
	
	@Value("${qq_sms_appkey}")
	private String smsAppkey;
	
	@Value("${qq_smsSign}")
	private String smsSign;
	
	@Value("${qq_sms_appid}")
	private int smsAppid;
	
    public static String ACCESS_TOKEN;
    public static long LASTTOKENTIME;
	
	@Autowired
	private GenerateAccessToken generator;
	
    @Override
    public LoginDto login(UserParamDto param) {
        LoginDto loginDto = new LoginDto();
        String jwt = "";
        String openId = getOpenId(param.getCode());
        //查询指定openId的用户
        User queryUser = userMapper.selectByOpenId(openId);
        loginDto.setIsNew(false);
        User user = new User();
        if(!ValidateHelper.isEmpty(queryUser)) {
        	BeanUtils.copyProperties(queryUser, user);
        }
        user.setOpenId(openId);
        user.setNickName(param.getNickName());
        user.setHeadUrl(param.getHeadUrl());
        user.setLastLoginTime(new Date());
        user.setSex(param.getSex());
        user.setAddressInfo(param.getAddressInfo());
        user.setStateCode(1);
        //没有此openId则新增用户
        if (ValidateHelper.isEmpty(queryUser)) {
            userMapper.insert(user);
            loginDto.setIsNew(true);
            //loginDto.setToken(jwt);
        } else {
            //openId存在则判断用户资料是否更新
            boolean equals = queryUser.equals(user);
            if (!equals) {
                userMapper.updateByPrimaryKey(user);
            }
        }
        jwt = jwtUtil.createJWT(user.getUserId());
        loginDto.setToken(jwt);
        return loginDto;
    }


    private String getOpenId(String code) {
        String code2SessionUrl = MessageFormat.format(code2Session, code);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String body = restTmp.exchange(code2SessionUrl, HttpMethod.GET, entity, String.class).getBody();
        JSONObject jsonObj = JSON.parseObject(body);
        String errorCode = jsonObj.getString("errcode");
        if (null != errorCode) {
            if (errorCode.equals("-1")) {
                throw new ServiceException("系统繁忙,请稍后再试");
            } else if (errorCode.equals("40029") || errorCode.equals("40163")) {
                throw new WxLoginException();
            }
        }
        logger.info("wx.login返回值:" + jsonObj);
        String openId = jsonObj.getString("openid");
        return openId;
    }


    @Override
    public PageInfo<CollectVo> getUserCollectList(GetUserInfoDto getUserInfoDto,String authorization) {

        Integer userId = tokenValidate.validateToken(authorization);
        PageHelper.startPage(getUserInfoDto.getCurrentPage(), getUserInfoDto.getPageSize());
        List<CollectVo> collectLists = userMapper.getUserCollectList(userId);
        for (CollectVo collect : collectLists) {
            collect.setCommodityImg(userMapper.getProdImgs(collect));
        }
        return new PageInfo<>(collectLists);
    }

    @Override
    public void saveFeedBack(Feedback feedback,String authorization) {
        Integer userId = tokenValidate.validateToken(authorization);
        feedback.setUserid(userId);
        userMapper.saveFeedBack(feedback);
    }

    @Override
    public void saveComment(Comments comments,String authorization) {
        Integer userId = tokenValidate.validateToken(authorization);
        comments.setUserid(userId);
        comments.setShopid(userMapper.getShopIdByOrderId(comments.getOrderid()));
        userMapper.saveComment(comments);
    }


    @Override
    public PageInfo<CommentDto> listCommentByCondition(CommentParamDto commentParamDto) {
        PageHelper.startPage(commentParamDto.getCurrentPage(), commentParamDto.getPageSize());
        List<Comments> list = commentsMapper.selectByCondition(commentParamDto);
        List<CommentDto> dtoList = new ArrayList<CommentDto>();
        Iterator<Comments> iterator = list.iterator();
        CommentDto commentDto = null;
        while (iterator.hasNext()) {
            commentDto = new CommentDto();
            Comments next = iterator.next();
            BeanUtils.copyProperties(next, commentDto);
            String[] imgUrls = next.getImgurl().split(",");
            commentDto.setImgUrls(Arrays.asList(imgUrls));
            Integer shopId = next.getShopid();
            //查询评价的商家名称
            ShopDetailParamDto param = new ShopDetailParamDto();
            param.setShopId(shopId);
            Result sellerResult = sellerClient.getShopById(param);
            ResultValidate.validateResult(sellerResult);
            String shopName = JSON.parseObject(JSON.toJSONString(sellerResult.getData()),new TypeReference<ShopDto>() {}).getShopName();
            commentDto.setShopName(shopName);
            //查询回复数量
            Integer commentId = next.getCommentid();
            CommentReplyParamDto replyParam = new CommentReplyParamDto();
            replyParam.setCommentId(commentId);
            List<CommentsReply> replyList = this.listCommentsReplyByCondition(replyParam);
            List<CommentReplyDto> replyDtoList = parseCommentReplyDto(replyList);
            commentDto.setReplyNums(replyDtoList.size());
            commentDto.setReplyList(replyDtoList);
            dtoList.add(commentDto);
        }
        return new PageInfo<CommentDto>(dtoList);
    }

    private List<CommentReplyDto> parseCommentReplyDto(List<CommentsReply> replyList) {
        List<CommentReplyDto> result = new ArrayList<CommentReplyDto>();
        Iterator<CommentsReply> iterator = replyList.iterator();
        CommentReplyDto replyDto = null;
        while (iterator.hasNext()) {
            replyDto = new CommentReplyDto();
            CommentsReply next = iterator.next();
            BeanUtils.copyProperties(next, replyDto);
            Integer userId = next.getUserId();
            Integer touserId = next.getToUserId();
            User fromUser = userMapper.selectByPrimaryKey(userId);
            User toUser = userMapper.selectByPrimaryKey(touserId);
            replyDto.setFromUserName(fromUser.getNickName());
            replyDto.setToUserName(toUser.getNickName());
            result.add(replyDto);
        }
        return result;
    }

    @Override
    public List<CommentsReply> listCommentsReplyByCondition(CommentReplyParamDto param) {
        return replyMapper.selectByCondition(param);
    }

    @Override
    public void addCommentReply(CommentReplyQueryDto param, String authorization) {
        param.getCommentId();
        Integer userId = tokenValidate.validateToken(authorization);
        param.setUserId(userId);
        CommentsReply record = new CommentsReply();
        BeanUtils.copyProperties(param, record);
        replyMapper.insert(record);
    }

    @Override
    public CommentsVo toSaveComments(CommentsVo commentsVo) {

        commentsVo.setShopId(userMapper.getShopIdByOrderId(commentsVo.getOrderId()));
        commentsVo.setShopLogo(userMapper.getShopLogoById(commentsVo.getShopId()));
        commentsVo.setPlayDate(userMapper.getOrderDate(commentsVo.getOrderId(), 1).getValuetext());
        commentsVo.setPlayTime(userMapper.getOrderDate(commentsVo.getOrderId(), 2).getValuetext());
        commentsVo.setShopName(userMapper.getShopNameById(commentsVo.getShopId()));
        return commentsVo;
    }

    @Override
    public void sendMsg(SendMsgDto sendMsgDto,String authorization) {
        int templateId = 0;
        String[] params = new String[1];
        String random = String.valueOf(Math.random()).substring(2, 8);

        Map<String, Object> map = new HashMap<>();
        if (PhoneType.typeOf(sendMsgDto.getType()) == PhoneType.USER_REGISTER_TYPE || PhoneType.typeOf(sendMsgDto.getType()) == PhoneType.USER_UPDATETEL_TYPE) {
            templateId = 310824;
            params[0] = random;
            map.put("messageType", 0);
            map.put("contents", random);
        } else if (PhoneType.typeOf(sendMsgDto.getType()) == PhoneType.ORDER_SUCCESS_TYPE) {
            templateId = 310834;
            params[0] = sendMsgDto.getOrderFormCode();
            map.put("messageType", 1);
            map.put("contents", sendMsgDto.getOrderFormCode());
        }

        map.put("userId", tokenValidate.validateToken(authorization));
        map.put("smsPhone", sendMsgDto.getPhoneNum());
        userMapper.saveMessageInfo(map);
        SmsSingleSender ssender = new SmsSingleSender(smsAppid, smsAppkey);
        try {
            ssender.sendWithParam("86", sendMsgDto.getPhoneNum(), templateId, params, smsSign, "", "");
        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePhoneNum(UpdatePhoneDto updatePhoneDto,String authorization) {
        Integer userId = tokenValidate.validateToken(authorization);
        String smsCoded = userMapper.getPhoneCodeByIdPhone(userId, updatePhoneDto.getPhoneNum());
        if (updatePhoneDto.getSmsCode().equals(smsCoded)) {
            userMapper.updatePhoneNum(userId, updatePhoneDto.getPhoneNum());
        } else {
            throw new ServiceException("验证码错误!");
        }
    }

    @Override
    public UserDto getUserInfo(String authorization) {
        Integer userId = tokenValidate.validateToken(authorization);
        return convert2UserDto(userId);
    }


	private UserDto convert2UserDto(Integer userId) {
		User user = userMapper.getUserInfo(userId);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        String tagsStr = user.getTags();
        List<String> tags = new ArrayList<String>();
        if(ValidateHelper.isNotEmptyString(tagsStr)) {
        	tags = Arrays.asList(tagsStr.split(","));
        }
        userDto.setTags(tags);
        return userDto;
	}

    @Override
    public Integer saveUserInfo(SaveUserInfoDto saveUserInfoDto,String authorization) {
        User user = new User();
        Integer uid = tokenValidate.validateToken(authorization);
        user.setUserId(uid);
        user.setHeadUrl(saveUserInfoDto.getHeadUrl());
        user.setNickName(saveUserInfoDto.getNickName());
        user.setSex(saveUserInfoDto.getSex());
        userMapper.saveUserInfo(user);
        return uid;
    }

    @Override
    public void updatePhoneNext(UpdatePhoneDto updatePhoneDto,String authorization) {
        Integer userId = tokenValidate.validateToken(authorization);
        String smsCoded = userMapper.getPhoneCodeByIdPhone(userId, updatePhoneDto.getPhoneNum());
        if (!updatePhoneDto.getSmsCode().equals(smsCoded)) {
            throw new RuntimeException("验证码错误!");
        }
    }


    @Override
    public String getUserAddress(AddressParamDto param) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        //根据经纬度查询地址信息
        String baiduMap = MessageFormat.format(baiduGeocoder, param.getLat(), param.getLng());
        String body = restTmp.exchange(baiduMap, HttpMethod.GET, entity, String.class).getBody();
        JSONObject jsonObj = JSON.parseObject(body);
        return String.valueOf(jsonObj.getJSONObject("result").getJSONObject("addressComponent").get("city"));
    }

    @Override
    public String getAboutUs() {
        return userMapper.getAboutUs();
    }


    @Override
    public List<User> listUserByUserId(List<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new ServiceException("用户ID不能为空");
        }
        return userMapper.selectUserListByUserId(list);
    }

    
    @Override
    public String getQRCode(QRCodeDto dto) {

        try {
			
			  logger.info("传入参数：" + dto); 
			  UploadManager uploadManager = new UploadManager(); 
			  //获取七牛授权 
			  Auth auth = Auth.create(accessKey, secretKey);
			  String key = UUID.randomUUID() + ".png";
			  //从redis获取微信access_token，如果为空则主动请求微信api生成access_token 
			  AccessTokenDto tokenDto = JSON.parseObject(redisClient.get("access_token_map"),AccessTokenDto.class);
			  Long previousTime = Long.parseLong(String.valueOf(tokenDto.getTime()));
			  Long currentTime = new Date().getTime();
			  boolean isExpired = (currentTime-previousTime)/1000>3600;
			  String accessToken = String.valueOf(tokenDto.getAccessToken());
			  if(null == previousTime || isExpired) {
				  generator.generateAccessToken(); 
				  accessToken = redisClient.get("access_token"); 
			  }
			  //params为微信小程序码的参数 
			  Map<String, Object> params = new HashMap<>();
			  params.put("scene", dto.getScene()); 
			  String page = dto.getType()==UserConstants.QRCODE_TYPE_GOLF_TOOL?golfToolPage:appointmentPage; 
			  params.put("page", page); 
			  CloseableHttpClient httpClient = HttpClientBuilder.create().build(); 
			  String wxCodeUrl = MessageFormat.format(getWxCodeUrl, accessToken); 
			  HttpPost httpPost = new HttpPost(wxCodeUrl); 
			  httpPost.addHeader(HTTP.CONTENT_TYPE,"application/json");
			  
			  StringEntity entity = new StringEntity(JSON.toJSONString(params));
			  entity.setContentType("image/png"); 
			  httpPost.setEntity(entity); 
			  HttpResponse response = httpClient.execute(httpPost);
			  InputStream is = response.getEntity().getContent();
			  
			  ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
			  byte[] buffer = new byte[1024]; 
			  int len = -1; 
			  while ((len = is.read(buffer)) != -1) {
				  bos.write(buffer, 0, len); 
			  }
			  byte[] uploadBytes = bos.toByteArray();
			  //上传小程序码图片到七牛
			  Response response1 = uploadManager.put(uploadBytes, key,auth.uploadToken(bucketName)); 
			  DefaultPutRet putRet = new Gson().fromJson(response1.bodyString(), DefaultPutRet.class); 
			  //返回图片地址 
			  return serverIpAddress + putRet.key;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


	@Override
	public void run(ApplicationArguments args) throws Exception {
		generator.generateAccessToken();
	}


	@Override
	public UserDto getUserInfo(Integer userId) {
		return this.convert2UserDto(userId);
	}

}
