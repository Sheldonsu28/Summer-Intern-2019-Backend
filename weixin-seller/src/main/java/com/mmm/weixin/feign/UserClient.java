package com.mmm.weixin.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mmm.weixin.dto.Result;
import com.mmm.weixin.dto.ShopCommentQueryDto;

@FeignClient("user")
public interface UserClient {

	@PostMapping("/listCommentByShop")
	Result listCommentByShop(@RequestBody ShopCommentQueryDto shopCommentQueryDto);
	
}
