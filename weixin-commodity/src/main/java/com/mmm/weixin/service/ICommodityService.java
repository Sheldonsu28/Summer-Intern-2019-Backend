package com.mmm.weixin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.mmm.weixin.dto.BookContestDetailDto;
import com.mmm.weixin.dto.ContestInfoDetailDto;
import com.mmm.weixin.dto.ContestInfoDto;
import com.mmm.weixin.dto.ContestOrderNumDto;
import com.mmm.weixin.dto.FlashSaleCommodityParamDto;
import com.mmm.weixin.dto.GolfCommodityDto;
import com.mmm.weixin.dto.GolfDetailDto;
import com.mmm.weixin.dto.GolfShopParamDto;
import com.mmm.weixin.dto.NewTeamMemberDto;
import com.mmm.weixin.dto.TeamInfoAddDto;
import com.mmm.weixin.dto.TeamInfoQueryDto;
import com.mmm.weixin.dto.TeamMemberAddDto;
import com.mmm.weixin.dto.TeamMemberDto;
import com.mmm.weixin.dto.TeamAndMemberDto;
import com.mmm.weixin.dto.param.CommodityImgParamDto;
import com.mmm.weixin.dto.param.CommodityParamDto;
import com.mmm.weixin.dto.param.ContestInfoQueryParamDto;
import com.mmm.weixin.dto.param.ContestTeamMemberParamDto;
import com.mmm.weixin.dto.param.GolfDetailParamDto;
import com.mmm.weixin.vo.Commodity;
import com.mmm.weixin.vo.CommodityImage;
import com.mmm.weixin.vo.ContestInfo;
import com.mmm.weixin.vo.ContestTeamInfo;
import com.mmm.weixin.vo.ContestTeamMember;
import com.mmm.weixin.vo.ContestTeamMemberDetail;

public interface ICommodityService {

	GolfDetailDto getGolfDetail(GolfDetailParamDto param,String authorization);
	
	Commodity getById(Integer commodityId);
	
	List<Commodity> listByCondition(CommodityParamDto param);

	void collectGolf(GolfDetailParamDto param, String authorization);
	
	PageInfo<GolfCommodityDto> listDefaultCommodityByCondition(GolfShopParamDto param);
	
	List<CommodityImage> listCommodityImgByCondition(CommodityImgParamDto param);
	
	List<ContestInfoDto> listContestInfoByCondition();
	
	PageInfo<ContestInfoDetailDto> listAllContest(ContestInfoQueryParamDto param);
	
	BookContestDetailDto getBookContestDetail(Integer contestId);
	
	void addTeam(TeamInfoAddDto param,String authorization);
	
	void udpateTeam(TeamInfoAddDto param,String authorization);
	
	void deleteTeam(TeamInfoQueryDto param,String authorization);
	
	List<TeamAndMemberDto> getTeamInfoByUserId(String authorization);
	
	TeamAndMemberDto getTeamInfoById(TeamInfoQueryDto param);
	
	ContestInfo getContestInfoByid(Integer contestId);

	Integer addContestTeam(ContestTeamInfo param);

	void addContestTeamMember(List<ContestTeamMember> param);

	List<ContestTeamInfo> getContestTeamByCondition(ContestTeamInfo param);

	PageInfo<GolfCommodityDto> listCommodityByProperty(FlashSaleCommodityParamDto param);

	void updateContestTeamMember(List<ContestTeamMember> param);
	
	ContestTeamMemberDetail getContestTeamMember(ContestOrderNumDto param);
	
	List<NewTeamMemberDto> addTeamMember(TeamMemberAddDto param,String authorization);
}
