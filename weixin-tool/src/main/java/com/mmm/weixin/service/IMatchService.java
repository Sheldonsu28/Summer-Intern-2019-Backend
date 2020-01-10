package com.mmm.weixin.service;

import javax.servlet.http.HttpServletRequest;

import com.mmm.weixin.dto.*;
import com.mmm.weixin.exception.ServiceException;
import com.mmm.weixin.vo.GolfArea;
import com.mmm.weixin.vo.Match;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.pagehelper.PageInfo;
import com.mmm.weixin.dto.param.FinishMatchParamDto;
import com.mmm.weixin.dto.param.MatchDetailParamDto;
import com.mmm.weixin.dto.param.MiniProgramPlayerParamDto;
import com.mmm.weixin.dto.param.UserMatchParamDto;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IMatchService {

	public PageInfo<MatchShopDto> listMatchShop(MatchShopParamDto param,String authorization);
	
	public PageInfo<MatchDto> listMatchByUser(UserMatchParamDto param,String authorization);
	
	/**
	 * 获取比赛详情
	 * @param param		MatchDetailParamDto类，包含所需信息。
	 * @param authorization 
	 * @return			符合条件的比赛信息
	 */
	public MatchDetailDto getMatchDetail(MatchDetailParamDto param, String authorization);

	/**
	 * 通过用户id表获取用户信息列表
	 * @param request 		Http请求
	 * @return				用户信息表
	 * @throws Exception 	出错向外抛
	 */
	public List<MatchUserDto> userListResult(String username,HttpServletRequest request);



	/**
	 * 通过MatchInfoDtoc创建比赛
	 * @param info MatchInfoDto实例
	 */
	public Map<String, Object> createMatch(MatchInfoDto info,HttpServletRequest request);

	/**
	 * 更新比赛结果信息
	 * @param info	MatchResultDto类，包含所需信息
	 * @param authorization 
	 * @return 
	 */
	public MsgDto updateResult(ResultDto info, String authorization);

	/**
	 * 更新比赛信息
	 * @param infoDto				ResultDto类，包含需要更新的信息
	 * @return						更新成功消息
	 * @throws ServiceException		出错向外抛
	 */

    void updateMatchInfo(MatchInfoDto infoDto,HttpServletRequest request) throws ServiceException;


	/**
	 * 通过小程序码添加球手
	 * @param param
	 * @return 
	 */
	public MsgDto addPlayerFromMiniProgramCode(MiniProgramPlayerParamDto param,String authorization);

	/**
	 * 结束比赛
	 * @param param
	 * @param authorization
	 */
	public Map<String, Object> finishMatch(FinishMatchParamDto param, String authorization);
	
	/**
	 * 查询已结束比赛详情
	 * @param param
	 * @param authorization
	 * @return
	 */
	public List<SinglePlayerResultDto> getfinishMatchDetail(FinishMatchParamDto param);

	/**
	 * 退出比赛
	 * @param param
	 * @param authorization
	 * @return 
	 */
	public MsgDto quitMatch(MatchDetailParamDto param, String authorization);
	
	/**
	 * 设置开始时间等于当前时间yyyy-MM-dd HH:mm格式的比赛为进行中
	 * @return
	 */
	public void updateStatus2Playing();

	/**
	 * 从以前一起比赛过的玩家里挑选并加入玩家
	 * @param info
	 */
	public void addPlayersFromMatchHistory(MatchInfoDto info,HttpServletRequest request);
	
	/**
	 * 根据球场查询球场区域
	 * @param param
	 * @return 
	 */
	public List<GolfArea> getMatchArea(MatchAreaParamDto param);
}
