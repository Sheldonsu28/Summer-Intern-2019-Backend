package com.mmm.weixin.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmm.weixin.common.ResultValidate;
import com.mmm.weixin.common.TokenValidate;
import com.mmm.weixin.constants.SellerConstants;
import com.mmm.weixin.constants.ToolConstants;
import com.mmm.weixin.dao.CommentsMapper;
import com.mmm.weixin.dao.CommentsReplyMapper;
import com.mmm.weixin.dao.ShopImageMapper;
import com.mmm.weixin.dao.ShopMapper;
import com.mmm.weixin.dao.ShopTypeFieldMapper;
import com.mmm.weixin.dao.ShopTypeFieldValueMapper;
import com.mmm.weixin.dto.CommentDto;
import com.mmm.weixin.dto.GolfCommodityDto;
import com.mmm.weixin.dto.GolfShopParamDto;
import com.mmm.weixin.dto.MatchShopDto;
import com.mmm.weixin.dto.MatchShopParamDto;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.dto.ShopCommentQueryDto;
import com.mmm.weixin.dto.ShopDetailDto;
import com.mmm.weixin.dto.ShopDetailParamDto;
import com.mmm.weixin.dto.ShopDto;
import com.mmm.weixin.dto.ShopTypeFieldDto;
import com.mmm.weixin.dto.param.CommentParamDto;
import com.mmm.weixin.dto.param.CommentReplyParamDto;
import com.mmm.weixin.dto.param.PlayedMatchShopParamDto;
import com.mmm.weixin.dto.param.ShopImageParamDto;
import com.mmm.weixin.exception.ServiceException;
import com.mmm.weixin.feign.CommodityClient;
import com.mmm.weixin.feign.UserClient;
import com.mmm.weixin.service.IShopService;
import com.mmm.weixin.utils.location.LocationUtils;
import com.mmm.weixin.vo.Comments;
import com.mmm.weixin.vo.CommentsReply;
import com.mmm.weixin.vo.Shop;
import com.mmm.weixin.vo.ShopImage;
import com.mmm.weixin.vo.ShopTypeField;
import com.mmm.weixin.vo.ShopTypeFieldValue;

@Service
public class ShopServiceImpl implements IShopService{

	@Autowired
	private ShopMapper shopMapper;
	
	@Autowired
	private ShopImageMapper imageMapper;
	
	@Autowired
	private CommentsMapper commentMapper;
		
	@Autowired
	private CommentsReplyMapper comReplyMapper;
	
	@Autowired
	private UserClient userClient;
	
	@Autowired
	private CommodityClient commodityClient;
	
	@Autowired
	private ShopTypeFieldMapper typeFieldMapper;
	
	@Autowired
	private ShopTypeFieldValueMapper typeFieldValueMapper;
	
	@Autowired
	private TokenValidate tokenValidate;
	
	@Override
	public ShopDetailDto getShopDetail(ShopDetailParamDto param) {
		Integer shopId = param.getShopId();
		ShopDetailDto result = new ShopDetailDto();
		//查询商家基本资料
		Shop shop = this.getShopById(param);
		BeanUtils.copyProperties(shop, result);
		//查询商家图片
		ShopImageParamDto imgParam = new ShopImageParamDto();
		imgParam.setShopId(shopId);
		List<ShopImage> imgList = this.listShopImageByCondition(imgParam);
		result.setImages(imgList);
		return result;
	}
	
	//解析评价
	/*
	 * private void resolveCommentDto(List<CommentDto> commentDtos) {
	 * Iterator<CommentDto> iterator = commentDtos.iterator();
	 * while(iterator.hasNext()){ CommentDto commentDto = iterator.next();
	 * CommentReplyParamDto replyParam = new CommentReplyParamDto();
	 * replyParam.setCommentId(commentDto.getCommentId()); List<CommentsReply>
	 * replyComments = this.listCommentReplyByCondition(replyParam);
	 * commentDto.setCommentReplies(replyComments); //评价图片 String[] imgUrls =
	 * commentDto.getImgUrl().split(",");
	 * commentDto.setImgUrls(Arrays.asList(imgUrls)); } }
	 */

	@Override
	public ShopDto getShopById(ShopDetailParamDto param) {
		ShopDto result = new ShopDto();
		Shop shop = shopMapper.selectByPrimaryKey(param.getShopId());
		if(null == shop) {
			throw new ServiceException("无效的shopId");
		}
		BeanUtils.copyProperties(shop, result);
		setShopExternalField(result);
		return result;
	}

	//设置额外属性值
	private void setShopExternalField(ShopDto result) {
		Integer shopId = result.getShopId();
		List<ShopTypeFieldValue> fieldValues = typeFieldValueMapper.selectByCondition(shopId);
		Map<Integer,Object> valuesMap = convertToShopTypeFieldValueMap(fieldValues);
		List<ShopTypeField> allFields = typeFieldMapper.selectAll();
		Iterator<ShopTypeField> iterator = allFields.iterator();
		ShopTypeFieldDto sfd = new ShopTypeFieldDto();;
		while(iterator.hasNext()) {
			ShopTypeField field = iterator.next();
			Integer fieldId = field.getFieldId();
			sfd.setFieldId(fieldId);
			String fieldValue = String.valueOf(valuesMap.get(fieldId));
			String fieldEnName = field.getFieldEnName();
			if(SellerConstants.SHOP_FIELD_CREATE_DATE.equals(fieldEnName)) {
				sfd.setCreateDate(fieldValue);
			}else if(SellerConstants.SHOP_FIELD_ACREAGE_COUNT.equals(fieldEnName)) {
				sfd.setAcreageCount(fieldValue);
			}else if(SellerConstants.SHOP_FIELD_FAIRWAY_LEN.equals(fieldEnName)) {
				sfd.setFairwayLen(fieldValue);
			}else if(SellerConstants.SHOP_FIELD_FAIRWAY_GRASS.equals(fieldEnName)) {
				sfd.setFairwayGrass(fieldValue);
			}else if(SellerConstants.SHOP_FIELD_TEL.equals(fieldEnName)) {
				sfd.setTel(fieldValue);
			}else if(SellerConstants.SHOP_FIELD_CHARACTERISTIC.equals(fieldEnName)) {
				sfd.setCharacteristic(fieldValue);
			}
		}
		result.setShopTypeField(sfd);
	}

	//转换为属性值映射,以fieldId为key,valueText为value
	private Map<Integer,Object> convertToShopTypeFieldValueMap(List<ShopTypeFieldValue> fieldValues) {
		Map<Integer,Object> result = new HashMap<Integer,Object>();
		Iterator<ShopTypeFieldValue> iterator = fieldValues.iterator();
		while(iterator.hasNext()) {
			ShopTypeFieldValue fieldValue = iterator.next();
			result.put(fieldValue.getFieldId(), fieldValue.getValueText());
		}
		return result;
	}

	@Override
	public List<ShopImage> listShopImageByCondition(ShopImageParamDto param) {
		return imageMapper.selectByCondition(param);
	}

	@Override
	public List<Comments> listCommentsByCondition(CommentParamDto param) {
		return commentMapper.selectByCondition(param);
	}
	
	public List<CommentDto> listCommentDtoByCondition(CommentParamDto param){
		return commentMapper.selectCommentDtoByCondition(param);
	}
	
	@Override
	public List<CommentsReply> listCommentReplyByCondition(CommentReplyParamDto param) {
		return comReplyMapper.selectByCondition(param);
	}

	@Override
	public PageInfo<CommentDto> listCommentDtoByShopId(ShopCommentQueryDto shopCommentQueryDto) {
		Result<PageInfo<CommentDto>> result = userClient.listCommentByShop(shopCommentQueryDto);
		ResultValidate.validateResult(result);
		PageInfo<CommentDto> list = JSON.parseObject(JSON.toJSONString(result.getData()),new TypeReference<PageInfo<CommentDto>>() {});
		return list;
	}

	@Override
	public PageInfo<GolfCommodityDto> listGolfCommodityByCondition(GolfShopParamDto param) {
		Result commodityResult = commodityClient.getDefaultCommodityList(param);
		ResultValidate.validateResult(commodityResult);
		PageInfo<GolfCommodityDto> result = JSON.parseObject(JSON.toJSONString(commodityResult.getData()),new TypeReference<PageInfo<GolfCommodityDto>>() {});
		if(SellerConstants.SORT_TYPE_DISTANCE==param.getSortType()) {
			sortByDistance(param, result);
		}
		return result;
	}

	private void sortByDistance(GolfShopParamDto param, PageInfo<GolfCommodityDto> result) {
		List<GolfCommodityDto> golfList = result.getList();
		Iterator<GolfCommodityDto> iterator = golfList.iterator();
		while(iterator.hasNext()) {
			GolfCommodityDto golf= iterator.next();
			Integer shopId = golf.getShopId();
			Shop shop = shopMapper.selectByPrimaryKey(shopId);
			String[] latAndLng = shop.getPositionData().split(",");
			double distance = LocationUtils.getDistance(param.getLng(),param.getLat(),Double.valueOf(latAndLng[0]),Double.valueOf(latAndLng[1]));
			golf.setDistance(distance/1000);
		}
		Collections.sort(golfList);
	}
	
	@Override
	public PageInfo<MatchShopDto> listMatchShopByCondition(MatchShopParamDto param) {
		Integer currentPage = param.getCurrentPage();
		Integer pageSize = param.getPageSize();
		PageHelper.startPage(currentPage, pageSize);
		//判断查询条件是否为经常
		Boolean isSearchPlayed=param.getSortType()==ToolConstants.SORT_TYPE_PLAYED?true:false;
		List<MatchShopDto> matchShops = null;
		if(isSearchPlayed) {
			Integer userId = tokenValidate.validateToken(param.getAuthorization());
			matchShops = this.listPlayedMatchShop(param, userId);
		}else {
			matchShops = shopMapper.selectMatchShopByCondition(param);
			Iterator<MatchShopDto> iterator = matchShops.iterator();
			while(iterator.hasNext()) {
				MatchShopDto shop = iterator.next();
				String[] latAndLng = shop.getPositionData().split(",");
				//计算每个球场距用户的距离
				double distance = LocationUtils.getDistance(param.getLng(), param.getLat(), Double.valueOf(latAndLng[0]),Double.valueOf(latAndLng[1]));
				shop.setDistance(distance/1000);
			}
			Collections.sort(matchShops);			
		}
		return new PageInfo<MatchShopDto>(matchShops);
	}

	//查询经常光顾的球场
	private List<MatchShopDto> listPlayedMatchShop(MatchShopParamDto param,Integer userId) {
		Integer currentPage = param.getCurrentPage();
		Integer pageSize = param.getPageSize();
		PageHelper.startPage(currentPage, pageSize);
		PlayedMatchShopParamDto playedShop = new PlayedMatchShopParamDto();
		playedShop.setShopName(param.getShopName());
		playedShop.setUserId(userId);
		List<MatchShopDto> matchShops = shopMapper.selectPlayedMatchShop(playedShop);
		return matchShops;
	}


}
