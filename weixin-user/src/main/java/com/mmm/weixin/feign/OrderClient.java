package com.mmm.weixin.feign;

import com.mmm.weixin.dto.Result;
import com.mmm.weixin.vo.PayVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("order")
public interface OrderClient {
    @PostMapping("/payOrderUnion")
    Result payOrderUnion(@RequestBody PayVo payVo);
}
