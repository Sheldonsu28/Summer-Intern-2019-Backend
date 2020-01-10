package com.mmm.weixin.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mmm.weixin.dto.GolfShopParamDto;
import com.mmm.weixin.dto.Result;

@FeignClient("commodity")
public interface CommodityClient {

	@PostMapping("/listDefaultCommodity")
	public Result getDefaultCommodityList(@RequestBody GolfShopParamDto param);
}
