package com.mmm.weixin.feign;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mmm.weixin.dto.MatchShopParamDto;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.dto.ShopDetailParamDto;

@FeignClient("seller")
public interface SellerClient {

	@PostMapping("/getMatchShopList")
	public Result getMatchShopList(@RequestBody MatchShopParamDto param);
	
	@PostMapping("/getShopById")
	public Result getShopById(@RequestBody ShopDetailParamDto param);
}
