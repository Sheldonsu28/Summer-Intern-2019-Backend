package com.mmm.weixin.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mmm.weixin.dto.ListUserParamDto;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.dto.UserParamDto;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@FeignClient("user")
public interface UserClient {

	@PostMapping("listUserByUserId")
    public Result listUserByUserId(@RequestBody ListUserParamDto param);

    @PostMapping("/login")
    public Result login(@RequestBody UserParamDto param);
}
