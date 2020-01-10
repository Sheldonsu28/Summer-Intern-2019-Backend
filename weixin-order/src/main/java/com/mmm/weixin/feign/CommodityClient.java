package com.mmm.weixin.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mmm.weixin.dto.ContestOrderNumDto;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.vo.ContestTeamInfo;
import com.mmm.weixin.vo.ContestTeamMember;

import springfox.documentation.annotations.ApiIgnore;

@FeignClient("commodity")
public interface CommodityClient {

	@PostMapping("/getContestInfoById")
	public Result getContestInfoById(@RequestParam("contestId") Integer contestId);
	
	@PostMapping("/addContestTeam")
	public Result addContestTeam(@RequestBody ContestTeamInfo param);
	
	@PostMapping("/getContestTeam")
	public Result getContestTeamByCondition(@RequestBody ContestTeamInfo param);
	
	@PostMapping("/addContestTeamMember")
	public Result addContestTeamMember(@RequestBody List<ContestTeamMember> param);
	
	@PostMapping("/updateContestTeamMember")
	public Result updateContestTeamMember(@RequestBody List<ContestTeamMember> param);
	
	@PostMapping("/getContestTeamMember")
	public Result getContestTeamMember(@RequestBody ContestOrderNumDto param);
}
