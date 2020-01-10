package com.mmm.weixin.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.mmm.weixin.dao.*;
import com.mmm.weixin.dto.*;
import com.mmm.weixin.dto.param.*;
import com.mmm.weixin.exception.ServiceException;
import com.mmm.weixin.vo.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmm.weixin.common.ResultValidate;
import com.mmm.weixin.common.TokenValidate;
import com.mmm.weixin.constants.ToolConstants;
import com.mmm.weixin.dao.AreaHoleMapper;
import com.mmm.weixin.dao.GolfAreaMapper;
import com.mmm.weixin.dao.MatchDetailMapper;
import com.mmm.weixin.dao.MatchMapper;
import com.mmm.weixin.dao.MatchPlayerMapper;
import com.mmm.weixin.dto.param.FinishMatchParamDto;
import com.mmm.weixin.dto.param.MatchAreaQueryParamDto;
import com.mmm.weixin.dto.param.MatchDetailParamDto;
import com.mmm.weixin.dto.param.MatchPlayerParamDto;
import com.mmm.weixin.dto.param.MatchResultParamDto;
import com.mmm.weixin.dto.param.MiniProgramPlayerParamDto;
import com.mmm.weixin.dto.param.UserMatchParamDto;
import com.mmm.weixin.feign.SellerClient;
import com.mmm.weixin.feign.UserClient;
import com.mmm.weixin.feign.WebSocketClient;
import com.mmm.weixin.service.IMatchService;

@Service
public class MatchServiceImpl implements IMatchService{

	@Autowired
	private SellerClient sellerClient;
	
	@Autowired
	private TokenValidate tokenValidate;
	
	@Autowired
	private MatchMapper mapper;
	
	@Autowired
	private MatchDetailMapper detailMapper;
	
	@Autowired
	private GolfAreaMapper areaMapper;

	
	@Autowired
	private MatchPlayerMapper playerMapper;

	@Autowired
	private MatchUserMapper userMapper;	

	@Autowired
	private AreaHoleMapper holeMapper;
	
	@Autowired
	private UserClient userClient;
	
	@Autowired
	private MatchResultMapper resultMapper;
	
	private Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);

	@Autowired
	private WebSocketClient webClient;

	@Override
	public PageInfo<MatchShopDto> listMatchShop(MatchShopParamDto param, String authorization) {
		param.setAuthorization(authorization);
		Result<PageInfo<MatchShopDto>> resultJson = sellerClient.getMatchShopList(param);
		ResultValidate.validateResult(resultJson);
		return JSON.parseObject(JSON.toJSONString(resultJson.getData()),new TypeReference<PageInfo<MatchShopDto>>() {});
	}

	@Override
	public PageInfo<MatchDto> listMatchByUser(UserMatchParamDto param, String authorization) {
		List<MatchDto> result = new ArrayList<MatchDto>();
		Integer userId = tokenValidate.validateToken(authorization);
		PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
		MatchPlayerParamDto playerMatchParam = new MatchPlayerParamDto();
		playerMatchParam.setUserId(userId);
		playerMatchParam.setStatus(param.getStatus());
		//根据用户ID查询参加过的比赛
		List<MatchPlayer> matchPlayers = playerMapper.selectByCondition(playerMatchParam);
		MatchDto matchDto = null;
		Iterator<MatchPlayer> iterator = matchPlayers.iterator();
		while(iterator.hasNext()) {
			MatchPlayer matchPlayer = iterator.next();
			Match match = mapper.selectByPrimaryKey(matchPlayer.getMid());
			if(matchPlayer.getRole()==ToolConstants.MATCH_ROLE_CADDIE && match.getStatus()==ToolConstants.MATCH_STATUS_FINISH) {
				continue;
			}
			matchDto = new MatchDto();
			BeanUtils.copyProperties(match, matchDto);
			resolveShopNameAndTime(match,matchDto);
			matchDto.setIsQuit(matchPlayer.getIsQuit());
			result.add(matchDto);
		}
		return new PageInfo<MatchDto>(result);
	}

	//解析球场名称和开球时间
	private void resolveShopNameAndTime(Match match,MatchDto matchDto) {
		Integer mid = matchDto.getMid();
		List<MatchDetail> details = this.detailMapper.selectMatchArea(mid);
		//查询球场原名称
		ShopDetailParamDto shopDetailParam = new ShopDetailParamDto();
		shopDetailParam.setShopId(matchDto.getShopId());
		Result result = sellerClient.getShopById(shopDetailParam);
		ResultValidate.validateResult(result);
		Shop shop = JSON.parseObject(JSON.toJSONString(result.getData()), new TypeReference<Shop>() {});
		//拼接球场原名称+球场区域名称
		StringBuilder sb = new StringBuilder();
		Iterator<MatchDetail> iterator = details.iterator();
		sb.append(shop.getShopName()).append("(");
		while(iterator.hasNext()) {
			MatchDetail matchDetail = iterator.next();
			Integer aid = matchDetail.getAid();
			GolfArea golfArea = areaMapper.selectByPrimaryKey(aid);
			sb.append(golfArea.getName()).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
		matchDto.setAreaName(sb.toString());
		//解析开球时间
		SimpleDateFormat sdf = new SimpleDateFormat("E",Locale.CHINA);
		String day = sdf.format(match.getTime());
		sdf = new SimpleDateFormat("yyyy-MM-dd "+day+" HH:mm:ss",Locale.CHINA);
		String timeResult = sdf.format(match.getTime());
		matchDto.setTime(timeResult);
	}

	@Override
	public MatchDetailDto getMatchDetail(MatchDetailParamDto param,String authorization) {
		Integer uid = tokenValidate.validateToken(authorization);
		MatchDetailDto result = new MatchDetailDto();
		Integer mid = param.getMid();
		Match match = mapper.selectByPrimaryKey(mid);
		if(null == match) {
			throw new ServiceException("比赛不存在");
		}
		List<MatchDetail> matchDetails = detailMapper.selectMatchArea(mid);
		Map<String, Object> resolveAreaResult = resolveArea(matchDetails);
	    List<MatchAreaDto> areas = (List<MatchAreaDto>) resolveAreaResult.get("areas");
	    Integer allAreasBar = (Integer) resolveAreaResult.get("allBar");
	    List<PlayerResultDto> allPlayerResults = resolveResults(allAreasBar,mid,areas);
	    AllMatchAreaDto allMatchArea = new AllMatchAreaDto();
	    allMatchArea.setAllAreasBar(allAreasBar);
	    allMatchArea.setAllAreasDifference(0);
	    allMatchArea.setAreas(areas);
	    result.setAllMatchArea(allMatchArea);
	    result.setAllPlayerResult(allPlayerResults);
	    result.setStatus(match.getStatus());
	    result.setMid(mid);
	    result.setMatchName(match.getName());
	    result.setCreatorId(match.getUserId());
	    MatchDto matchDto = new MatchDto();
	    matchDto.setMid(mid);
	    matchDto.setShopId(match.getShopId());
	    this.resolveShopNameAndTime(match, matchDto);
	    result.setAreaName(matchDto.getAreaName());
	    result.setTime(matchDto.getTime());
		MatchPlayerParamDto playerCondition = new MatchPlayerParamDto();
		playerCondition.setMid(mid);
		playerCondition.setRole(ToolConstants.MATCH_ROLE_CADDIE);
		List<MatchPlayer> caddieList = playerMapper.selectByMatchID(playerCondition);
		//判断是否有球童
		result.setHasCaddie(CollectionUtils.isEmpty(caddieList)?ToolConstants.MATCH_HAS_NOT_CADDIE:ToolConstants.MATCH_HAS_CADDIE);
		//查询球童信息以及判断当前用户是否球童
		if(result.getHasCaddie()==ToolConstants.MATCH_HAS_CADDIE) {
			Integer caddieUid = caddieList.get(0).getUserId();
			CaddieDto caddie = resolveCaddies(caddieUid);
			result.setCaddie(caddie);
			result.setIsCaddie(uid == caddieUid?true:false);
		}
		return result;
	}

	private CaddieDto resolveCaddies(Integer userId) {
		ListUserParamDto param = new ListUserParamDto();
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(userId);
		param.setUserIds(ids);
		Result result = userClient.listUserByUserId(param);
		List<User> users = JSON.parseObject(JSON.toJSONString(result.getData()), new TypeReference<List<User>>() {});
		CaddieDto caddie = new CaddieDto();
		User user = users.get(0);
		BeanUtils.copyProperties(user, caddie);
		return caddie;
	}

	//解析区域和球洞
	private Map<String,Object> resolveArea(List<MatchDetail> matchDetails) {
		Map<String,Object> result = new HashMap<String,Object>();
		Integer allBar = 0;
		List<MatchAreaDto> matchAreaDtos = new ArrayList<MatchAreaDto>();
		Iterator<MatchDetail> iterator = matchDetails.iterator();
		MatchAreaDto area = null;
		while(iterator.hasNext()) {
			area = new MatchAreaDto();
			MatchDetail matchDetail = iterator.next();
			Integer aid = matchDetail.getAid();
			GolfArea golfArea = areaMapper.selectByPrimaryKey(aid);
			area.setAid(aid);
			area.setAreaName(golfArea.getName());
			Map<String, Object> holeResult = resolveHole(aid);
			List<MatchHoleDto> holes = (List<MatchHoleDto>) holeResult.get("holes");
			area.setHoles(holes);
			Integer allStandardBar = (Integer) holeResult.get("standardBarVal");
			area.setAllHoleStandardBar(allStandardBar);
			allBar+=allStandardBar;
			matchAreaDtos.add(area);
		}
		result.put("areas", matchAreaDtos);
		result.put("allBar", allBar);
		return result;
	}
	
	private Map<String,Object> resolveHole(Integer aid) {
		Map<String,Object> result = new HashMap<String,Object>();
		Integer standardBarVal = 0;
		List<MatchHoleDto> holeDtos = new ArrayList<MatchHoleDto>();
		List<AreaHole> holes = holeMapper.selectByAid(aid);
		Iterator<AreaHole> iterator = holes.iterator();
		MatchHoleDto holeDto = null;
		while(iterator.hasNext()) {
			holeDto = new MatchHoleDto();
			AreaHole next = iterator.next();
			BeanUtils.copyProperties(next, holeDto);
			holeDto.setHoldName(next.getName());
			//累计每个区域所有球洞的总标准杆
			standardBarVal+=next.getStandardBar();
			holeDtos.add(holeDto);
		}
		result.put("holes", holeDtos);
		result.put("standardBarVal", standardBarVal);
		return result;
	}

	private List<PlayerResultDto> resolveResults(Integer allAreasBar,Integer mid,List<MatchAreaDto> areas) {
		List<PlayerResultDto> result = new ArrayList<PlayerResultDto>();
		//设置相同的区域和球洞
		List<PlayerAreaDto> playerAreasDto = null;
		MatchPlayerParamDto playerCondition = new MatchPlayerParamDto();
		playerCondition.setMid(mid);
		playerCondition.setRole(ToolConstants.MATCH_ROLE_PLAYER);
		List<MatchPlayer> players = playerMapper.selectByMatchID(playerCondition);
		//遍历选手，设置ID，名字和成绩
		PlayerResultDto playerResultDto = null;
		List<User> users = convertToUsers(players);
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			//每个选手各有一份比赛球洞列表用以记录成绩,没有成绩的球洞值为null
			playerAreasDto = new ArrayList<PlayerAreaDto>();
			playerAreasDto = convertToPlayerArea(areas);
			playerResultDto = new PlayerResultDto();
			//设置选手信息
			User next = iterator.next();
			Integer userId = next.getUserId();
			playerResultDto.setUserId(userId);
			playerResultDto.setUserName(next.getNickName());
			playerResultDto.setHeadurl(next.getHeadUrl());
			//设置选手成绩
			Integer resultAllDifference = setPlayerResult(playerAreasDto,mid,userId);
			Integer resultAllBar = allAreasBar+resultAllDifference;
			playerResultDto.setAreas(playerAreasDto);
			playerResultDto.setResultAllDifference(resultAllDifference);
			playerResultDto.setResultAllBar(resultAllBar);
			result.add(playerResultDto);
		}
		return result;
	}	
	
	private Integer setPlayerResult(List<PlayerAreaDto> playerAreasDto, Integer mid, Integer userId) {
		MatchResultParamDto param = new MatchResultParamDto();
		param.setMid(mid);
		param.setUserId(userId);
		List<MatchResult> matchResults = resultMapper.selectByMidAndUserId(param);
		//holeMap存储球洞映射，用于查找有成绩的球洞
		Map<Integer,PlayerHoleDto> holeMap = new HashMap<Integer,PlayerHoleDto>();
		Iterator<PlayerAreaDto> areaIter = playerAreasDto.iterator();
		while(areaIter.hasNext()) {
			PlayerAreaDto next = areaIter.next();
			List<PlayerHoleDto> holes = next.getHoles();
			Iterator<PlayerHoleDto> holeIter = holes.iterator();
			while(holeIter.hasNext()) {
				PlayerHoleDto hole = holeIter.next();
				//添加每个球场区域的所有球洞
				holeMap.put(hole.getHid(), hole);x
			}
		}
		//取参赛球洞和成绩表球洞交集
		if(!CollectionUtils.isEmpty(matchResults)) {
			setResultUnionSet(matchResults,holeMap);
		}
		return setPlayerAreaResult(playerAreasDto);
	}

	//设置每个区域总成绩
	private Integer setPlayerAreaResult(List<PlayerAreaDto> playerAreasDto) {
		Integer resultAllDifference = 0;
		Iterator<PlayerAreaDto> areaIter = playerAreasDto.iterator();
		while(areaIter.hasNext()) {
			Integer eachAreaAllBar = 0;
			PlayerAreaDto area = areaIter.next();
			List<PlayerHoleDto> holes = area.getHoles();
			Iterator<PlayerHoleDto> holeIter = holes.iterator();
			while(holeIter.hasNext()) {
				PlayerHoleDto hole = holeIter.next();
				Integer result = hole.getResult();
				eachAreaAllBar+=result;
				//成绩默认是null值，需要判断
				//if(null != result) {
				//}
			}
			area.setResultAllAreaBar(eachAreaAllBar);
			resultAllDifference+=eachAreaAllBar;
		}
		return resultAllDifference;
	}

	private void setResultUnionSet(List<MatchResult> matchResults, Map<Integer, PlayerHoleDto> holeMap) {
		Iterator<MatchResult> iterator = matchResults.iterator();
		while(iterator.hasNext()) {
			MatchResult next = iterator.next();
			Integer hid = next.getHid();
			//球洞有成绩纪录
			if(holeMap.containsKey(hid)) {
				PlayerHoleDto playerHoleDto = holeMap.get(hid);
				playerHoleDto.setResult(next.getResult());
			}
		}
	}

	private List<User> convertToUsers(List<MatchPlayer> players) {
		List<Integer> list = new ArrayList<Integer>();
		Iterator<MatchPlayer> iterator = players.iterator();
		while(iterator.hasNext()) {
			MatchPlayer matchUser = iterator.next();
			list.add(matchUser.getUserId());
		}
		ListUserParamDto listUserParamDto = new ListUserParamDto();
		listUserParamDto.setUserIds(list);
		Result result = userClient.listUserByUserId(listUserParamDto);
		ResultValidate.validateResult(result);
		List<User> users = JSON.parseObject(JSON.toJSONString(result.getData()), new TypeReference<List<User>>() {});
		return users;
	}

	private List<PlayerAreaDto> convertToPlayerArea(List<MatchAreaDto> areas) {
		List<PlayerAreaDto> result = new ArrayList<PlayerAreaDto>();
		Iterator<MatchAreaDto> iterator = areas.iterator();
		PlayerAreaDto playerArea = null;
		while(iterator.hasNext()) {
			playerArea = new PlayerAreaDto();
			MatchAreaDto matchArea = iterator.next();
			playerArea.setAid(matchArea.getAid());
			List<PlayerHoleDto> playerHoles = convertToPlayerHoles(matchArea.getHoles());
			playerArea.setHoles(playerHoles);
			result.add(playerArea);
		}
		return result;
	}

	private List<PlayerHoleDto> convertToPlayerHoles(List<MatchHoleDto> holes) {
		List<PlayerHoleDto> result = new ArrayList<PlayerHoleDto>();
		Iterator<MatchHoleDto> iterator = holes.iterator();
		PlayerHoleDto playerHole = null; 
		while(iterator.hasNext()) {
			playerHole = new PlayerHoleDto();
			MatchHoleDto matchHole = iterator.next();
			playerHole.setHid(matchHole.getHid());
			playerHole.setResult(0);
			playerHole.setHoleInfo(matchHole.getHoldName()+"("+matchHole.getStandardBar()+")");
			result.add(playerHole);
		}
		return result;
	}


	/**
	 * 查询用户UID
	 * @return 用户UID
	 */
	private int getPlayerUID(HttpServletRequest request) {
		String authorization = request.getHeader("authorization");
		return tokenValidate.validateToken(authorization);
	}

	/**
	 * 通过用户UID查找用户创建过的比赛并返回查找到的所有比赛
	 * @param UID:          用户UID
	 * @return List:        用户创建过的比赛的MID
	 */
	private List<Integer> getMatchID(Integer UID){
		List<Integer> mIds = new ArrayList<>();
		UserMatchParamDto user = new UserMatchParamDto();
		user.setUserId(UID);
		List<Match> matches  = mapper.selectByUserID(UID);
		for (Match x:matches){
			mIds.add(x.getMid());
		}
		return mIds;
	}

	/**
	 * 根据MID找到每一个参加过比赛的用户，返回HashMap(UID,比赛次数）。
	 * @param uids          比赛过的用户ID
	 * @param UID           用户UID
	 * @return              HashMap，UID作为Key，值为参赛次数。
	 */
	private HashMap<Integer,Integer> getUsersMatchNum(List<MatchPlayer> uids,int UID){

		HashMap<Integer,Integer>details = new HashMap<>();
		//根据输入的比赛ID与用户Id查询所有参与过同一场比赛的用户并统计一起比赛过的次数
			for (MatchPlayer uid : uids){
				if(details.containsKey(uid.getUserId())){
					details.replace(uid.getUserId(),details.get(uid.getUserId()) + 1);
				}else if (!details.containsKey(uid.getUserId()) && !uid.getUserId().equals(UID)){
					details.put(uid.getUserId(),1);
				}
			}

		return details;
	}


	/**
	 * 获取用户资料
	 * @param userIds      用户名单，key用户名，值为参赛次数
	 * @return              List，存储MatchUserDto，包含用户头像url与参赛次数。
	 * @throws Exception    出错向外抛
	 */
	private List<MatchUserDto> getUserList(HashMap<Integer,Integer> userIds,String Username) throws Exception {
		UserSearchParam searchParam = new UserSearchParam();
		searchParam.setUsername(Username);
		List<MatchUserDto> result = new ArrayList<>();
		//对HashMap里的userId查找对应的用户信息
		for (Integer x : userIds.keySet()) {
			searchParam.setUid(x);
			MatchUser player = userMapper.selectByPrimaryKey(searchParam);
			MatchUserDto userDto = MatchUserDto.class.newInstance();
			if (player != null) {
				userDto.setName(player.getNickName());
				userDto.setThumbnailUrl(player.getHeadUrl());
				userDto.setMatchHistory(userIds.get(x));
				userDto.setID(x);
				result.add(userDto);
			}
		}
		return result;
	}

    /**
     * 创建比赛
     * @param info 比赛信息
     */
    @Override
    public Map<String,Object> createMatch(MatchInfoDto info,HttpServletRequest request) {
    	int userId = this.getPlayerUID(request);
        int areaNum = info.getAreaId().size();
        UserRoleDto self = new UserRoleDto();
        List<UserRoleDto> players = info.getUserIdsAndRoles();
        //判断邀请的用户是否已加入别的比赛
		if(!CollectionUtils.isEmpty(players)) {
			if(this.isJoining(info.getUserIdsAndRoles())){
				throw new ServiceException("您选择的用户正在参与别的比赛");
			}
		}
        self.setUserID(userId);
        self.setRole(1);
        info.getUserIdsAndRoles().add(self);
        Integer createdMID;
        if(info.getContestantNumber() == 0){
            throw new ServiceException("您还没有设置参赛人数");
        }else if(info.getUserIdsAndRoles().size() > info.getContestantNumber()){
            throw new ServiceException("您选择的人数已超过最大比赛人数");
        } else if (areaNum < 2){
            throw new ServiceException("您没有选择足够的半场");

        }else if(areaNum> 2){
            throw new ServiceException("选择的球洞已超出2个半场的上限");
        }else {
            Date startTime = new Date();
            try {
                SimpleDateFormat startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                startTime = startDate.parse(info.getStartTime());
            }catch(Exception e){
                e.printStackTrace();
                throw new ServiceException("内部错误");
            }
            Match matchInfo = new Match();
            matchInfo.setTime(startTime);
            matchInfo.setShopId(info.getShopId());
            matchInfo.setName(info.getMatchName());
            matchInfo.setContestantNumber(info.getContestantNumber());
            matchInfo.setActive(true);
            matchInfo.setStatus(1);
            matchInfo.setUserId(this.getPlayerUID(request));
            mapper.createMatch(matchInfo);
            Integer matchId = mapper.selectLatestMatchByUserId(matchInfo.getUserId());
            createdMID = matchId;

            HashMap<Integer,List<AreaHole>> holes = new HashMap<>();
            for (int area:info.getAreaId()){
            	List<AreaHole> areaHoles = this.getHoleIdByAreaIDAndShopID(area,info.getShopId());
                holes.put(area,areaHoles);
            }
            //创建用户比赛结果的记录，真正更新记录时就可以直接查找了
            if ((holes.keySet().size() == 2)) {
				for (int area : holes.keySet()) {
						for (AreaHole hole : holes.get(area)) {
							MatchDetail matchDetail = new MatchDetail();
							matchDetail.setMid(matchId);
							matchDetail.setHid(hole.getHid());
							matchDetail.setAid(area);
							matchDetail.setActive(true);
							detailMapper.createDetail(matchDetail);

						}
				}
				//将用户加入到比赛中
				for (UserRoleDto user : info.getUserIdsAndRoles()) {
					if (user.getRole() == 1) {
						MatchPlayer player = new MatchPlayer();
						player.setUserId(user.getUserID());
						player.setMid(matchId);
						player.setRole(user.getRole());
						player.setIsQuit(false);
						playerMapper.createPlayer(player);
					}
				}
			}else{
            	throw new ServiceException("场地不存在！");
			}
        }

        int UID = this.getPlayerUID(request);
		MsgDto msg = new MsgDto();
		msg.setMessageType(ToolConstants.WEBSOCKET_MESSAGE_TYPE_ADD_MEMEBER);
		msg.setSendUserId(UID);
		int mid = mapper.selectLatestMatchByUserId(UID);
		msg.setChatRoomId(String.valueOf(mid));
		msg.setMessageBody(String.valueOf(UID));
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("matchId", createdMID);
		result.put("msg", msg);
		return result;
    }

    public MsgDto updateResult(ResultDto resultDto,String authorization){
    	Integer userId = tokenValidate.validateToken(authorization);
        if(mapper.selectByPrimaryKey(resultDto.getMatchId()) == null){
            throw new ServiceException("找不到该比赛！");
        } else if(mapper.selectByPrimaryKey(resultDto.getMatchId()).getStatus() != 2){
            throw new ServiceException("比赛还未开始或已经结束，您现在无法修改分数。");
        }else{
            //对每一个在resultDto里的用户更新比赛成绩
        	for(MatchResultDto playerResult : resultDto.getResults()){
        		if(!this.notLowerThanStandardBar(playerResult.getResult(),resultDto.getHoleId())){
        			throw new ServiceException("您的杆数不能小于0");
				}
				MatchResult pack = new MatchResult();
				pack.setMid(resultDto.getMatchId());
				pack.setHid(resultDto.getHoleId());
				pack.setUserId(playerResult.getUserId());
				MatchResult result = resultMapper.selectByParameter(pack);
				//判断是否创建了结果记录，没有的话自行创建
				if (result != null){
					pack.setResult(playerResult.getResult());
					pack.setRid(result.getRid());
					resultMapper.updateByRid(pack); 
				}else{
					pack.setResult(playerResult.getResult());
					resultMapper.createResult(pack);
				}
			}

        }
		MsgDto msg = new MsgDto();
		msg.setMessageType(ToolConstants.WEBSOCKET_MESSAGE_TYPE_MARK_RESULT);
		msg.setSendUserId(userId);
		msg.setChatRoomId(String.valueOf(resultDto.getMatchId()));
		String msgBody = resultDto.getResults().toString();
		msg.setMessageBody(String.valueOf(msgBody));
		return msg;
    }

	/**
	 * 更新玩家的职位与状态，infoDto.PlayerIdAndRoles里面key为userId值为角色
	 * @param infoDto           MatchInfoDto类，包含更新信息
	 * @throws ServiceException 不满足条件抛出ServiceException错误
	 */
	@Transactional
    public void updateMatchInfo(MatchInfoDto infoDto,HttpServletRequest request) throws ServiceException{
        int userId = this.getPlayerUID(request);
        Match thisMatch = mapper.selectByPrimaryKey(infoDto.getMatchId());
        List<UserRoleDto> players = infoDto.getUserIdsAndRoles();
		if(!CollectionUtils.isEmpty(players)) {
			if(this.isJoining(infoDto.getUserIdsAndRoles())){
				throw new ServiceException("您邀请的用户正在参加别的比赛！");
			}
		}
        if(infoDto.getMatchId() == 0){
            throw new ServiceException("赛事id不能为空！");
        } else if(thisMatch.getName() == null){
            throw new ServiceException("未找到比赛数据！请重试。");
        } else if (!thisMatch.getUserId().equals(userId)) {
            throw new ServiceException("您无权修改比赛信息！");
        } else if(thisMatch.getStatus() != 1){
            throw new ServiceException("比赛已经开始或已经结束，您已无法对该比赛做出修改！");
        }else{
            Date startTime = new Date();
            try {
                SimpleDateFormat startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                startTime = startDate.parse(infoDto.getStartTime());
            }catch(Exception e){
                e.printStackTrace();
            }
        	Match match = new Match();
        	match.setMid(infoDto.getMatchId());
        	match.setName(infoDto.getMatchName());
        	match.setTime(startTime);
        	mapper.updateNameAndTimeByMid(match);
            for(UserRoleDto user:infoDto.getUserIdsAndRoles()){
				int role = user.getRole();
				MatchPlayer player = new MatchPlayer();
				player.setMid(infoDto.getMatchId());
				player.setUserId(user.getUserID());
				player.setRole(role);
				player.setIsQuit(false);
				if(player.getRole() == 1) {
                    if (playerMapper.selectByMidAndUidAndIsQuit(player) != null) {
                        throw new ServiceException("您添加的用户已在比赛中");
                    } else if (mapper.selectByPrimaryKey(infoDto.getMatchId()).getContestantNumber()
                            <= playerMapper.selectPlayersByMatchIdAndIsQuit(infoDto.getMatchId()).size()) {
                        throw new ServiceException("比赛位置已满，你无法再添加新的用户！");
                    } else {
                        playerMapper.insert(player);
                    }
                }else{
					throw new ServiceException("您无法对球童做更改！");
                }
            }
        }
    }

	@Override
	public MsgDto addPlayerFromMiniProgramCode(MiniProgramPlayerParamDto param,String authorization) {
		Integer userId = tokenValidate.validateToken(authorization);
		Integer mid = param.getMid();
		Match match = mapper.selectByPrimaryKey(mid);
		if(null == match) {
			throw new ServiceException("比赛不存在");
		}
		//根据比赛ID和用户ID查询比赛用户
		MatchPlayerParamDto mPlayerParam = new MatchPlayerParamDto();
		mPlayerParam.setUserId(userId);
		mPlayerParam.setMid(mid);
		List<MatchPlayer> players = playerMapper.selectByCondition(mPlayerParam);
		//判断用户之前是否曾经参赛
		if(CollectionUtils.isEmpty(players)) {
			MatchPlayer player = new MatchPlayer();
			player.setMid(param.getMid());
			player.setUserId(userId);
			player.setIsQuit(false);
			player.setRole(param.getType());
			player.setCreated(new Date());
			playerMapper.insert(player);
		}else {
			MatchPlayer matchPlayer = players.get(0);
			Integer playerRole = matchPlayer.getRole();
			Integer requestRole = param.getType();
			if(playerRole==ToolConstants.MATCH_ROLE_PLAYER && requestRole==ToolConstants.MATCH_ROLE_CADDIE) {
				throw new ServiceException("您之前已经参加过比赛,无法添加为球童");
			} 
			matchPlayer.setIsQuit(false);
			matchPlayer.setUpdated(new Date());
			playerMapper.updateByPrimaryKey(matchPlayer);
		}
		MsgDto msg = new MsgDto();
		msg.setMessageType(ToolConstants.WEBSOCKET_MESSAGE_TYPE_ADD_MEMEBER);
		msg.setSendUserId(userId);
		msg.setChatRoomId(String.valueOf(mid));
		msg.setMessageBody("");
		return msg;
	}

    @Override
	public void addPlayersFromMatchHistory(MatchInfoDto infoDto, HttpServletRequest request){
        int userId = this.getPlayerUID(request);
        if(!CollectionUtils.isEmpty(infoDto.getUserIdsAndRoles())){
			if(this.isJoining(infoDto.getUserIdsAndRoles())){
				throw new ServiceException("您邀请的用户正在参加别的比赛！");
			}
		}
		if(infoDto.getMatchId() == 0){
			throw new ServiceException("赛事id不能为空！");
		}
		else if(null == mapper.selectByPrimaryKey(infoDto.getMatchId()).getName()){
			throw new ServiceException("未找到比赛数据！请重试。");
		}else if(mapper.selectByPrimaryKey(infoDto.getMatchId()).getStatus() != 1){
			throw new ServiceException("比赛已经开始或已经结束，您已无法对该比赛做出修改！");
		}else{
			for(UserRoleDto user:infoDto.getUserIdsAndRoles()){ //对每一个
				int role = user.getRole();
				MatchPlayer player = new MatchPlayer();
				player.setMid(infoDto.getMatchId());
				player.setUserId(user.getUserID());
				player.setRole(role);
				player.setIsQuit(false);
				MatchPlayer matchPlayer = playerMapper.selectByMidAndUid(player);
				List<MatchPlayer> matchPlayers = playerMapper.selectByUserId(player.getUserId());
				for (MatchPlayer Player: matchPlayers ){
				    if(!Player.getIsQuit()) {
                        Match joinedMatch = mapper.selectByPrimaryKey(Player.getMid());
                        if (joinedMatch != null) {
                            if (joinedMatch.getStatus() == 2) {
                                throw new ServiceException("您邀请的玩家已加入别的比赛");
                            }
                        }
                    }
				}
                if(player.getRole() == 1) {
                    if (matchPlayer != null) {
                    	if (matchPlayer.getIsQuit()){
                    		playerMapper.updateIsQuitToFalseByUserId(matchPlayer);
                    		playerMapper.updateByParam(player);
						}else {
							throw new ServiceException("用户已经存在！");
						}
                    } else if (mapper.selectByPrimaryKey(infoDto.getMatchId()).getContestantNumber() <= playerMapper.selectPlayersByMatchIdAndIsQuit(infoDto.getMatchId()).size()) {
                        throw new ServiceException("比赛位置已满，你无法再添加新的用户！");
                    } else {
                        playerMapper.insert(player);
                    }
                }else{
                    if (playerMapper.selectByMidAndUid(player) != null ) {
						if (matchPlayer.getIsQuit()){
							playerMapper.updateIsQuitToFalseByUserId(matchPlayer);
							playerMapper.updateByParam(player);
						}else {
							throw new ServiceException("用户已经存在！");
						}
                    }else{
                        playerMapper.insert(player);
                    }
                }
			}
		}
	}

	@Override
	public Map<String, Object> finishMatch(FinishMatchParamDto param, String authorization) {
		Integer uid = this.tokenValidate.validateToken(authorization);
		Integer mid = param.getMid();
		Match checkMatch = mapper.selectByPrimaryKey(mid);
		if(null == checkMatch) {
			throw new ServiceException("比赛不存在");
		}
		if(checkMatch.getStatus()==ToolConstants.MATCH_STATUS_FINISH) {
			throw new ServiceException("比赛已结束");
		}
		Match match = new Match();
		match.setMid(param.getMid());
		match.setStatus(ToolConstants.MATCH_STATUS_FINISH);
		match.setUpdated(new Date());
		mapper.updateByPrimaryKey(match);
		List<SinglePlayerResultDto> matchResult = getMatchResult(mid);
		MsgDto msg = new MsgDto();
		msg.setMessageType(ToolConstants.WEBSOCKET_MESSAGE_TYPE_FINISH_MATCH);
		msg.setSendUserId(uid);
		msg.setChatRoomId(String.valueOf(mid));
		msg.setMessageBody("");
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("matchResult", matchResult);
		result.put("msg", msg);
		return result;
	}
	
	public List<SinglePlayerResultDto> getfinishMatchDetail(FinishMatchParamDto param){
		Integer mid = param.getMid();
		Match checkMatch = mapper.selectByPrimaryKey(mid);
		if(null == checkMatch) {
			throw new ServiceException("比赛不存在");
		}
		if(checkMatch.getStatus()!=ToolConstants.MATCH_STATUS_FINISH) {
			throw new ServiceException("比赛尚未结束");
		}
		return getMatchResult(mid);
	}

	private List<SinglePlayerResultDto> getMatchResult(Integer mid) {
		List<SinglePlayerResultDto> result = new ArrayList<SinglePlayerResultDto>();
		MatchPlayerParamDto param = new MatchPlayerParamDto();
		param.setMid(mid);
		param.setRole(ToolConstants.MATCH_ROLE_PLAYER);
		List<MatchPlayer> matchPlayers = playerMapper.selectByCondition(param);
		Set<Integer> playerIds = new HashSet<Integer>();
		Iterator<MatchPlayer> iterator = matchPlayers.iterator();
		while(iterator.hasNext()) {
			MatchPlayer matchPlayer = iterator.next();
			playerIds.add(matchPlayer.getUserId());
		}
		Iterator<Integer> playerIter = playerIds.iterator();
		SinglePlayerResultDto singleResult = null;
		while(playerIter.hasNext()) {
			Integer uid = playerIter.next();
			param.setUserId(uid);
			singleResult = new SinglePlayerResultDto();
			singleResult = resultMapper.selectPlayerResult(param);
			result.add(singleResult);
		}
		return result;
	}

	@Override
	public MsgDto quitMatch(MatchDetailParamDto param, String authorization) {
		Integer userId = this.tokenValidate.validateToken(authorization);
		Integer mid = param.getMid();
		Match match = mapper.selectByPrimaryKey(mid);
		if(null == match) {
			throw new ServiceException("比赛不存在");
		}
		MatchPlayerParamDto matchPlayerParam = new MatchPlayerParamDto();
		matchPlayerParam.setMid(mid);
		matchPlayerParam.setUserId(userId);
		MatchPlayer matchPlayer = playerMapper.selectByCondition(matchPlayerParam).get(0);
		if(null == matchPlayer) {
			throw new ServiceException("选手没有参加比赛");
		}
		matchPlayer.setIsQuit(true);
		matchPlayer.setUpdated(new Date());
		playerMapper.updateByPrimaryKey(matchPlayer);
		MsgDto msg = new MsgDto();
		msg.setMessageType(ToolConstants.WEBSOCKET_MESSAGE_TYPE_REMOVE_MEMEBER);
		msg.setSendUserId(userId);
		msg.setChatRoomId(String.valueOf(mid));
		msg.setMessageBody(String.valueOf(userId));
		return msg;
	}

	@Override
	public void updateStatus2Playing() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<Match> matches = mapper.selectReadyMatch(sdf.format(new Date()));
		Iterator<Match> iterator = matches.iterator();
		while(iterator.hasNext()) {
			Match match = iterator.next();
			match.setStatus(ToolConstants.MATCH_STATUS_PLAYING);
			match.setUpdated(new Date());
			mapper.updateByPrimaryKey(match);
		}
	}

	@Override
	public List<MatchUserDto> userListResult(String username,HttpServletRequest request){
		try {
			int uid = this.getPlayerUID(request);
			List<MatchPlayer> userIds = playerMapper.selectHistoryPlayer(uid);
			HashMap<Integer,Integer> userMatchNumber = getUsersMatchNum(userIds,uid);
			return this.getUserList(userMatchNumber,username);
		}catch (Exception e){
			e.printStackTrace();
			throw new ServiceException("获取好友列表时出错！");
		}
	}

	@Override
	public List<GolfArea> getMatchArea(MatchAreaParamDto param) {
		MatchAreaQueryParamDto queryParam = new MatchAreaQueryParamDto();
		queryParam.setShopId(param.getShopId());
		return areaMapper.selectByCondition(queryParam);
	}

	private List<AreaHole> getHoleIdByAreaIDAndShopID(int AreaID,int shopId){
        AreaHole hole = new AreaHole();
        hole.setShopId(shopId);
        hole.setAid(AreaID);
        List<AreaHole> result = holeMapper.selectByAidAndShopID(hole);
        return result;
    }

    private boolean notLowerThanStandardBar(int hit,int holeID){
		AreaHole hole = holeMapper.selectByPrimaryKey(holeID);
		return hole.getStandardBar() - hit >= 0;
	}

	private boolean isJoining(List<UserRoleDto> players){
		for (UserRoleDto player : players) {
			List<MatchPlayer> matchPlayers = playerMapper.selectByUserId(player.getUserID());
			for (MatchPlayer matchPlayer : matchPlayers) {
				if (!matchPlayer.getIsQuit()) {
					Match joinedMatch = mapper.selectByPrimaryKey(matchPlayer.getMid());
					if (joinedMatch != null) {
						if (joinedMatch.getStatus() == 2) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
