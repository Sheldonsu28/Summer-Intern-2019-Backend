package com.mmm.weixin.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmm.weixin.common.ResultValidate;
import com.mmm.weixin.common.TokenValidate;
import com.mmm.weixin.constants.CommodityConstants;
import com.mmm.weixin.dao.CommodityImageMapper;
import com.mmm.weixin.dao.CommodityMapper;
import com.mmm.weixin.dao.ContestInfoMapper;
import com.mmm.weixin.dao.ContestTeamInfoMapper;
import com.mmm.weixin.dao.ContestTeamMemberMapper;
import com.mmm.weixin.dao.HolidayMapper;
import com.mmm.weixin.dao.TeamInfoMapper;
import com.mmm.weixin.dao.TeamMemberMapper;
import com.mmm.weixin.dao.UserCommodityMapper;
import com.mmm.weixin.dto.BookContestDetailDto;
import com.mmm.weixin.dto.CommodityDto;
import com.mmm.weixin.dto.ContestInfoDetailDto;
import com.mmm.weixin.dto.ContestInfoDto;
import com.mmm.weixin.dto.ContestInfoParamDto;
import com.mmm.weixin.dto.ContestOrderNumDto;
import com.mmm.weixin.dto.FlashSaleCommodityParamDto;
import com.mmm.weixin.dto.GolfCommodityDto;
import com.mmm.weixin.dto.GolfDetailDto;
import com.mmm.weixin.dto.GolfShopParamDto;
import com.mmm.weixin.dto.NewTeamMemberDto;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.dto.ShopDetailDto;
import com.mmm.weixin.dto.ShopDetailParamDto;
import com.mmm.weixin.dto.TeamInfoAddDto;
import com.mmm.weixin.dto.TeamInfoQueryDto;
import com.mmm.weixin.dto.TeamMemberAddDto;
import com.mmm.weixin.dto.TeamAndMemberDto;
import com.mmm.weixin.dto.TeamMemberDto;
import com.mmm.weixin.dto.param.CommodityImgParamDto;
import com.mmm.weixin.dto.param.CommodityParamDto;
import com.mmm.weixin.dto.param.ContestInfoQueryParamDto;
import com.mmm.weixin.dto.param.ContestTeamMemberParamDto;
import com.mmm.weixin.dto.param.GolfDetailParamDto;
import com.mmm.weixin.dto.param.TeamInfoParamDto;
import com.mmm.weixin.dto.param.TeamMemberParamDto;
import com.mmm.weixin.exception.ServiceException;
import com.mmm.weixin.feign.SellerClient;
import com.mmm.weixin.service.ICommodityService;
import com.mmm.weixin.utils.base.ValidateHelper;
import com.mmm.weixin.utils.clone.CloneUtils;
import com.mmm.weixin.utils.jwt.JwtUtil;
import com.mmm.weixin.vo.Commodity;
import com.mmm.weixin.vo.CommodityImage;
import com.mmm.weixin.vo.ContestInfo;
import com.mmm.weixin.vo.ContestTeamInfo;
import com.mmm.weixin.vo.ContestTeamMember;
import com.mmm.weixin.vo.ContestTeamMemberDetail;
import com.mmm.weixin.vo.ContestTeamMemberDto;
import com.mmm.weixin.vo.Holiday;
import com.mmm.weixin.vo.TeamInfo;
import com.mmm.weixin.vo.TeamMember;
import com.mmm.weixin.vo.UserCommodity;

import io.jsonwebtoken.Claims;

@Service
public class CommodityServiceImpl implements ICommodityService{

	@Autowired
	private CommodityMapper mapper;
	
	@Autowired
	private CommodityImageMapper imgMapper;
	
	@Autowired
	private UserCommodityMapper userCommodityMapper;
	
	@Autowired
	private ContestInfoMapper contestInfoMapper;
	
	@Autowired
	public RestTemplate restTemplate;
	
	@Value("${rest_template_prefix}")
	public String urlPrefix;
	
	@Resource
	private JwtUtil jwtUtil;
	
	@Autowired
	private TokenValidate tokenValidate;
	
	@Autowired
	private TeamInfoMapper teamMapper;
	
	@Autowired
	private TeamMemberMapper teamMemberMapper;
	
	@Autowired
	private ContestTeamInfoMapper contestTeamMapper;
	
	@Autowired
	private ContestTeamMemberMapper contestMemberMapper;
	
	@Autowired
	private HolidayMapper holidayMapper;
	
	@Autowired
	private SellerClient sellerClient;
	
	private static Logger logger = LoggerFactory.getLogger(CommodityServiceImpl.class);
	
	
	@Override
	public GolfDetailDto getGolfDetail(GolfDetailParamDto golfDetailParam,String authorization){
		Integer userId = tokenValidate.validateToken(authorization);
		Integer commodityId = golfDetailParam.getCommodityId();
		//查询商品对应的shopId,再查询shopId下所有商品
		GolfDetailDto result = new GolfDetailDto();
		Commodity commodity = this.getById(commodityId);
		CommodityParamDto param = new CommodityParamDto();
		Integer shopId = commodity.getShopId();
		param.setShopId(shopId);
		List<CommodityDto> commodities = this.listCommodityDtoByCondition(param);
		checkSpecialPrice(golfDetailParam.getDate(),commodities);
		result.setCommodities(commodities);
		//查询商家信息
		ShopDetailParamDto detailParam = new ShopDetailParamDto();
		detailParam.setShopId(shopId);
		Result shopResult = sellerClient.getShopDetail(detailParam);
		ResultValidate.validateResult(shopResult);
		ShopDetailDto shopDetailDto = JSON.parseObject(JSON.toJSONString(shopResult.getData()), new TypeReference<ShopDetailDto>() {});
		result.setShopDetail(shopDetailDto);	
		//是否收藏过该商品
		UserCommodity userCommodity = new UserCommodity();
		userCommodity.setUid(userId);
		userCommodity.setCid(commodityId);
		UserCommodity collectGolf = this.getCollectGolf(userCommodity);
		result.setIsCollect(collectGolf==null?false:true);
		return result;
	}
	
	/**
	 * 检查是否
	 * @param date
	 * @param commodities
	 */
	private void checkSpecialPrice(String date,List<CommodityDto> commodities) {
		Holiday holiday = this.selectHolidayByDate(date);
		DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd").toFormatter();
		LocalDate localDate = LocalDate.parse(date, formatter);
		boolean isHolidayOrWeekend = false;
		DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		if(null != holiday || dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY ) {
			isHolidayOrWeekend = true;
		}
		Iterator<CommodityDto> iterator = commodities.iterator();
		while(iterator.hasNext()) {
			CommodityDto commodityDto = iterator.next();
			commodityDto.setIsSpecial(isHolidayOrWeekend?false:true);
		}
	}
	
	public Holiday selectHolidayByDate(String date) {
		Holiday holiday = new Holiday();
		holiday.setHoliday(date);
		return holidayMapper.selectByHoliday(holiday);
	}
	

	@Override
	public List<Commodity> listByCondition(CommodityParamDto param) {
		return mapper.selectByCondition(param);
	}
	
	public List<CommodityDto> listCommodityDtoByCondition(CommodityParamDto param){
		return mapper.selectCommDtoByCondition(param);
	}

	@Override
	public Commodity getById(Integer commodityId) {
		return mapper.selectByPrimaryKey(commodityId);
	}

	@Override
	public void collectGolf(GolfDetailParamDto param, String authorization) {
		Integer uid = tokenValidate.validateToken(authorization);
		Integer commodityId = param.getCommodityId();
		if(null == commodityId) {
			throw new ServiceException("商品ID不能为空");
		}
		Integer type = param.getType();
		UserCommodity vo = new UserCommodity(); 
		vo.setCid(commodityId);
		vo.setUid(uid); 
		UserCommodity userCommodity = this.getCollectGolf(vo);
		 
		//查询用户是否收藏商品
		if(null != userCommodity) {
			if(CommodityConstants.COLLECT_TYPE_COLLECT==type) {
				throw new ServiceException("您已收藏过该球场");
			}
			if(CommodityConstants.COLLECT_TYPE_CANCEL==type) {
				userCommodityMapper.deleteByCondition(userCommodity);
			}
		}else {
			if(CommodityConstants.COLLECT_TYPE_COLLECT==type) {
				userCommodityMapper.insert(vo);
			}
			if(CommodityConstants.COLLECT_TYPE_CANCEL==type) {
				throw new ServiceException("您没有收藏该球场");
			}
		}
		
	}
	
	public UserCommodity getCollectGolf(UserCommodity userCommodity) {
		return userCommodityMapper.selectByCondition(userCommodity);
	}

	/**
	 * 根据条件查询默认商品
	 */
	@Override
	public PageInfo<GolfCommodityDto> listDefaultCommodityByCondition(GolfShopParamDto param) {
		List<GolfCommodityDto> golfList = new ArrayList<GolfCommodityDto>();
		PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
		List<Commodity> commodities = mapper.selectDefaultCommodityByCondition(param);
		Iterator<Commodity> iterator = commodities.iterator();
		GolfCommodityDto commodityDto = null;
		while(iterator.hasNext()) {
			commodityDto = new GolfCommodityDto();
			Commodity commodity = iterator.next();
			BeanUtils.copyProperties(commodity, commodityDto);
			//设置商品对应的商家信息
			setCommodityShopProperty(commodityDto);
			//设置商品图片
			CommodityImgParamDto imgParam = new CommodityImgParamDto();
			imgParam.setCommodityId(commodityDto.getCommodityId());
			imgParam.setImgType(CommodityConstants.IMG_TYPE_SHOW);
			List<CommodityImage> imgs = this.listCommodityImgByCondition(imgParam);
			commodityDto.setImage(ValidateHelper.isNotEmptyCollection(imgs)?imgs.get(0).getImgUrl():"");
			golfList.add(commodityDto);
		}
		PageInfo<GolfCommodityDto> result = new PageInfo<GolfCommodityDto>(golfList);
		return result;
	}

	private void setCommodityShopProperty(GolfCommodityDto commodityDto) {
		ShopDetailParamDto shopParam = new ShopDetailParamDto();
		shopParam.setShopId(commodityDto.getShopId());
		Result shopDetailResult = sellerClient.getShopDetail(shopParam);
		ResultValidate.validateResult(shopDetailResult);
		ShopDetailDto shopDetailDto = JSON.parseObject(JSON.toJSONString(shopDetailResult.getData()), new TypeReference<ShopDetailDto>() {});
		commodityDto.setShopName(shopDetailDto.getShopName());
		commodityDto.setAddressInfo(shopDetailDto.getAddressInfo());
		commodityDto.setTagsText(shopDetailDto.getTagsText());
	}
	
	@Override
	public List<CommodityImage> listCommodityImgByCondition(CommodityImgParamDto param){
		List<CommodityImage> result = imgMapper.selectByCondition(param);
		return result;
	}

	@Override
	public List<ContestInfoDto> listContestInfoByCondition(){
		List<ContestInfo> list = new ArrayList<ContestInfo>();
		ContestInfoParamDto cParam = new ContestInfoParamDto();
		cParam.setPropertyCode(CommodityConstants.CONTEST_PROPERTYCODE_RECOMMEND);
		list = contestInfoMapper.selectByCondition(cParam);
		List<ContestInfoDto> result = new ArrayList<ContestInfoDto>();
		try {
			CloneUtils.copyProperties(list,result,ContestInfo.class,ContestInfoDto.class);
		} catch (Exception e) {
			logger.error("listContestInfoByCOndition异常:",e);
			throw new RuntimeException();
		}
		return result;
	}

	@Override
	public PageInfo<ContestInfoDetailDto> listAllContest(ContestInfoQueryParamDto param){
		PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
		ContestInfoParamDto infoParamDto = new ContestInfoParamDto();
		infoParamDto.setAreaId(param.getAreaId());
		List<ContestInfo> list = contestInfoMapper.selectByCondition(infoParamDto);
		List<ContestInfoDetailDto> dtoList = new ArrayList<ContestInfoDetailDto>();
		try {
			CloneUtils.copyProperties(list, dtoList, ContestInfo.class, ContestInfoDetailDto.class);
		}catch(Exception e) {
			logger.error("listAllContest异常:",e);
			throw new RuntimeException();
		}
		resolveContestTimeAndShopName(dtoList);
		return new PageInfo<ContestInfoDetailDto>(dtoList);
	}

	private void resolveContestTimeAndShopName(List<ContestInfoDetailDto> dtoList) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Iterator<ContestInfoDetailDto> iterator = dtoList.iterator();
		while(iterator.hasNext()) {
			ContestInfoDetailDto contest = iterator.next();
			contest.setContestBeginTimeStr(sdf.format(contest.getContestBeginTime()));
			contest.setContestEndTimeStr(sdf.format(contest.getContestEndTime()));
			Integer shopId = contest.getShopId();
			ShopDetailDto shopDetailDto = getShopDetailDto(shopId);
			contest.setShopName(shopDetailDto.getShopName());
		}
	}

	private ShopDetailDto getShopDetailDto(Integer shopId) {
		ShopDetailParamDto detailParam = new ShopDetailParamDto();
		detailParam.setShopId(shopId);
		Result shopResult = sellerClient.getShopDetail(detailParam);
		ResultValidate.validateResult(shopResult);
		ShopDetailDto shopDetailDto = JSON.parseObject(JSON.toJSONString(shopResult.getData()), new TypeReference<ShopDetailDto>() {});
		return shopDetailDto;
	}

	@Override
	public BookContestDetailDto getBookContestDetail(Integer contestId) {
		ContestInfo contestInfo = contestInfoMapper.selectByPrimaryKey(contestId);
		if(null == contestInfo) {
			throw new ServiceException("该团队赛事不存在");
		}
		BookContestDetailDto detailDto = new BookContestDetailDto();
		BeanUtils.copyProperties(contestInfo, detailDto);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		detailDto.setContestBeginTimeStr(sdf.format(detailDto.getContestBeginTime()));
		detailDto.setContestEndTimeStr(sdf.format(detailDto.getContestEndTime()));
		return detailDto;
	}

	@Transactional
	@Override
	public void addTeam(TeamInfoAddDto param,String authorization) {
		Integer userId = tokenValidate.validateToken(authorization);
		param.setUserId(userId);
		String teamName = param.getTeamName();
		TeamInfoParamDto paramDto = new TeamInfoParamDto();
		paramDto.setTeamName(teamName);
		TeamInfo teamNameInfo = teamMapper.selectByNameAndId(paramDto);
		//查询球队名是否存在 
		if(!ValidateHelper.isEmpty(teamNameInfo)) {
			throw new ServiceException("球队名称已存在,请重新输入");			
		}
		TeamInfo teamInfo = new TeamInfo();
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(teamInfo, param);
		} catch (Exception e) {
			logger.error("addTeam异常:",e);
			throw new RuntimeException();
		}
		teamMapper.insert(teamInfo);
		Integer teamId = teamInfo.getTeamId();
		//添加队员
		addTeamMemberByTeamId(param, teamId);
	}

	@Override
	public List<TeamAndMemberDto> getTeamInfoByUserId(String authorization) {
		Integer userId = tokenValidate.validateToken(authorization);
		TeamInfoParamDto paramDto = new TeamInfoParamDto();
		paramDto.setUserId(userId);
		//根据userId查询球队
		List<TeamInfo> teamList = teamMapper.selectByCondition(paramDto);
		return resolveTeamList(teamList);
	}

	//解析队伍集合
	private List<TeamAndMemberDto> resolveTeamList(List<TeamInfo> teamList) {
		List<TeamAndMemberDto> result = new ArrayList<TeamAndMemberDto>();
		try {
			CloneUtils.copyProperties(teamList, result, TeamInfo.class, TeamAndMemberDto.class);
		} catch (Exception e) {
			logger.error("resolveTeamList异常:",e);
			throw new RuntimeException();
		}
		Iterator<TeamAndMemberDto> iterator = result.iterator();
		List<TeamMemberDto> membersDto = new ArrayList<TeamMemberDto>();
		while(iterator.hasNext()) {
			membersDto = new ArrayList<TeamMemberDto>();
			TeamAndMemberDto team = iterator.next();
			TeamMemberParamDto memberParam = new TeamMemberParamDto();
			memberParam.setTeamId(team.getTeamId());
			List<TeamMember> members = teamMemberMapper.selectByCondition(memberParam);
			membersDto = getTeamMember(members);
			team.setMembers(membersDto);
		}
		return result;
	}

	//查询每个队伍的队员
	private List<TeamMemberDto> getTeamMember(List<TeamMember> members) {
		List<TeamMemberDto> result = new ArrayList<TeamMemberDto>();
		Iterator<TeamMember> iterator = members.iterator();
		TeamMemberDto memberDto = null;
		while(iterator.hasNext()) {
			memberDto = new TeamMemberDto();
			TeamMember member = iterator.next();
			try {
				CloneUtils.copyProperties(member,memberDto);
				result.add(memberDto);
			} catch (Exception e) {
				logger.error("getTeamMemberName异常:",e);
				throw new RuntimeException();
			}
		}
		return result;
	}

	@Override
	public ContestInfo getContestInfoByid(Integer contestId) {
		return contestInfoMapper.selectByPrimaryKey(contestId);
	}

	@Override
	@Transactional
	public Integer addContestTeam(ContestTeamInfo param) {
		contestTeamMapper.insert(param);
		return param.getCtId();
	}

	@Override
	@Transactional
	public void addContestTeamMember(List<ContestTeamMember> param) {
		Iterator<ContestTeamMember> iterator = param.iterator();
		while(iterator.hasNext()) {
			ContestTeamMember member = iterator.next();
			contestMemberMapper.insert(member);
		}
	}

	@Override
	public List<ContestTeamInfo> getContestTeamByCondition(ContestTeamInfo param) {
		return contestTeamMapper.selectByCondition(param);
	}

	@Override
	public PageInfo<GolfCommodityDto> listCommodityByProperty(FlashSaleCommodityParamDto param) {
		List<GolfCommodityDto> golfList = new ArrayList<GolfCommodityDto>();
		PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
		List<Commodity> comList = mapper.selectCommodityByProperty(param);
		Iterator<Commodity> iterator = comList.iterator();
		GolfCommodityDto commodityDto = null;
		while(iterator.hasNext()) {
			commodityDto = new GolfCommodityDto();
			Commodity commodity = iterator.next();
			BeanUtils.copyProperties(commodity, commodityDto);
			setCommodityShopProperty(commodityDto);
			//查询商品图片
			CommodityImgParamDto imgParam = new CommodityImgParamDto();
			imgParam.setCommodityId(commodityDto.getCommodityId());
			imgParam.setImgType(CommodityConstants.IMG_TYPE_SHOW);
			List<CommodityImage> imgs = this.listCommodityImgByCondition(imgParam);
			commodityDto.setImage(ValidateHelper.isNotEmptyCollection(imgs)?imgs.get(0).getImgUrl():"");
			golfList.add(commodityDto);
		}
		PageInfo<GolfCommodityDto> result = new PageInfo<GolfCommodityDto>(golfList);
		return result;
	}

	@Override
	@Transactional
	public void udpateTeam(TeamInfoAddDto param, String authorization) {
		Integer userId = tokenValidate.validateToken(authorization);
		param.setUserId(userId);
		String teamName = param.getTeamName();
		TeamInfoParamDto paramDto = new TeamInfoParamDto();
		paramDto.setTeamName(teamName);
		paramDto.setTeamId(param.getTeamId());
		TeamInfo teamNameInfo = teamMapper.selectByNameAndId(paramDto);
		//查询球队名是否存在 
		if(!ValidateHelper.isEmpty(teamNameInfo)) {
			throw new ServiceException("球队名称已存在,请重新输入");			
		}
		TeamInfo teamInfo = new TeamInfo();
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(teamInfo, param);
		} catch (Exception e) {
			logger.error("addTeam异常:",e);
			throw new RuntimeException();
		}
		teamMapper.updateByPrimaryKey(teamInfo);
		Integer teamId = teamInfo.getTeamId();
		//删除队伍的所有队员
		deleteTeamMemberByTeamId(teamId);
		//新增队员
		addTeamMemberByTeamId(param, teamId);
	}

	private void addTeamMemberByTeamId(TeamInfoAddDto param, Integer teamId) {
		String[] memberNames = param.getMemberNames();
		TeamMember member = new TeamMember();
		for(String name:memberNames) {
			member = new TeamMember();
			member.setNickName(name);
			member.setMemberLevel(CommodityConstants.TEAM_MEMBER_LEVEL_NORMAL);
			member.setTeamId(teamId);
			teamMemberMapper.insert(member);
		}
	}

	private void deleteTeamMemberByTeamId(Integer teamId) {
		TeamMemberParamDto memberParam = new TeamMemberParamDto();
		memberParam.setTeamId(teamId);
		List<TeamMember> memberList = teamMemberMapper.selectByCondition(memberParam);
		Iterator<TeamMember> iterator = memberList.iterator();
		while(iterator.hasNext()) {
			TeamMember member = iterator.next();
			teamMemberMapper.deleteByPrimaryKey(member.getMemberId());
		}
	}

	@Override
	public TeamAndMemberDto getTeamInfoById(TeamInfoQueryDto param) {
		TeamInfoParamDto paramDto = new TeamInfoParamDto();
		paramDto.setTeamId(param.getTeamId());
		//根据userId查询球队
		List<TeamInfo> teamList = teamMapper.selectByCondition(paramDto);
		return resolveTeamList(teamList).get(0);
	}

	@Transactional
	@Override
	public void deleteTeam(TeamInfoQueryDto param,String authorization) {
		tokenValidate.validateToken(authorization);
		Integer teamId = param.getTeamId();
		teamMapper.deleteByPrimaryKey(teamId);
		deleteTeamMemberByTeamId(teamId);
	}

	@Transactional
	public void updateContestTeamMember(List<ContestTeamMember> param) {
		//先删除对应ctId的所有ContestTeamMember
		ContestTeamMember contestTeamMember = param.get(0);
		ContestTeamMemberParamDto teamMemberParam = new ContestTeamMemberParamDto();
		teamMemberParam.setCtId(contestTeamMember.getCtId());
		List<ContestTeamMember> teamMemberList = contestMemberMapper.selectByCondition(teamMemberParam);
		Iterator<ContestTeamMember> iterator = teamMemberList.iterator();
		while(iterator.hasNext()) {
			ContestTeamMember next = iterator.next();
			contestMemberMapper.deleteByPrimaryKey(next.getCtmId());
		}
		//添加ContestTeamMember
		this.addContestTeamMember(param);
	}

	@Override
	public ContestTeamMemberDetail getContestTeamMember(ContestOrderNumDto param) {
		ContestTeamMemberDetail result = new ContestTeamMemberDetail();
		//查询参赛球队ContestTeam信息
		ContestTeamInfo contestParam = new ContestTeamInfo();
		contestParam.setOrderFormId(param.getOrderId());
		List<ContestTeamInfo> contestTeams= contestTeamMapper.selectByCondition(contestParam);
		ContestTeamInfo contestTeamInfo = contestTeams.get(0);
		result.setCtId(contestTeamInfo.getCtId());
		result.setContestId(contestTeamInfo.getContestId());
		Integer teamId = contestTeamInfo.getTeamId();
		result.setTeamId(teamId);
		TeamInfo teamInfo = teamMapper.selectByPrimaryKey(teamId);
		result.setTeamName(teamInfo.getTeamName());
		//查询参赛球队所有球员
		TeamMemberParamDto memberParam = new TeamMemberParamDto();
		memberParam.setTeamId(teamId);
		List<TeamMember> teamMembers = teamMemberMapper.selectByCondition(memberParam);
		Map<Integer,ContestTeamMemberDto> allTeamMembers = parseMemberMap(teamMembers);
		//查询参赛队员
		ContestTeamMemberParamDto teamMemberParam = new ContestTeamMemberParamDto();
		teamMemberParam.setCtId(contestTeamInfo.getCtId());
		List<ContestTeamMember> contestTeamMembers = contestMemberMapper.selectByCondition(teamMemberParam);
		Iterator<ContestTeamMember> iterator = contestTeamMembers.iterator();
		while(iterator.hasNext()) {
			ContestTeamMember contestTeamMember = iterator.next();
			Integer memberId = contestTeamMember.getMemberId();
			ContestTeamMemberDto teamMemberDto = allTeamMembers.get(memberId);
			if(null == teamMemberDto) {
				teamMemberDto = new ContestTeamMemberDto();
				teamMemberDto.setMemberId(memberId);
				teamMemberDto.setName(contestTeamMember.getMemberName());
				teamMemberDto.setHasJoin(true);
				allTeamMembers.put(memberId, teamMemberDto);
			}else {
				teamMemberDto.setHasJoin(true);
			}
		}
		List<ContestTeamMemberDto> teamMemberDtos = new ArrayList<ContestTeamMemberDto>(allTeamMembers.values());
		result.setMembers(teamMemberDtos);
		return result;
	}

	private Map<Integer, ContestTeamMemberDto> parseMemberMap(List<TeamMember> teamMembers) {
		Map<Integer, ContestTeamMemberDto> result = new HashMap<Integer, ContestTeamMemberDto>();
		ContestTeamMemberDto memberDto = null;
		Iterator<TeamMember> iterator = teamMembers.iterator();
		while(iterator.hasNext()) {
			memberDto = new ContestTeamMemberDto();
			TeamMember teamMember = iterator.next();
			Integer memberId = teamMember.getMemberId();
			memberDto.setMemberId(memberId);
			memberDto.setName(teamMember.getNickName());
			result.put(memberId, memberDto);
		}
		return result;
	}

	@Override
	public List<NewTeamMemberDto> addTeamMember(TeamMemberAddDto param,String authorization) {
		tokenValidate.validateToken(authorization);
		String name = param.getName();
		Integer teamId = param.getTeamId();
		String[] names = name.split(",");
		TeamMemberParamDto memberParam = new TeamMemberParamDto();
		for(String n:names) {
			memberParam = new TeamMemberParamDto();
			memberParam.setName(n);
			List<TeamMember> members = teamMemberMapper.selectByCondition(memberParam);
			if(!CollectionUtils.isEmpty(members)) {
				throw new ServiceException("球员名称'"+n+"'已存在,请修改后提交");
			}
		}
		Set<Integer> newIds = new HashSet<Integer>();
		TeamMember teamMember = null;
		for(String n:names) {
			teamMember = new TeamMember();
			teamMember.setNickName(n);
			teamMember.setTeamId(param.getTeamId());
			teamMember.setMemberLevel(CommodityConstants.TEAM_MEMBER_LEVEL_NORMAL);
			teamMemberMapper.insert(teamMember);
			newIds.add(teamMember.getMemberId());
		}
		memberParam = new TeamMemberParamDto();
		memberParam.setTeamId(teamId);
		List<TeamMember> teamMembers = teamMemberMapper.selectByCondition(memberParam);
		return this.getNewTeamMember(teamMembers,newIds);
	}

	private List<NewTeamMemberDto> getNewTeamMember(List<TeamMember> members, Set<Integer> newIds) {
		List<NewTeamMemberDto> result = new ArrayList<NewTeamMemberDto>();
		Iterator<TeamMember> iterator = members.iterator();
		NewTeamMemberDto memberDto = null;
		while(iterator.hasNext()) {
			memberDto = new NewTeamMemberDto();
			TeamMember member = iterator.next();
			try {
				CloneUtils.copyProperties(member,memberDto);
				if(newIds.contains(memberDto.getMemberId())) {
					memberDto.setIsNew(true);
				}
				result.add(memberDto);
			} catch (Exception e) {
				logger.error("getTeamMemberName异常:",e);
				throw new RuntimeException();
			}
		}
		return result;
	}
}
