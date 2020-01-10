package com.mmm.weixin.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mmm.weixin.dto.MsgDto;

@FeignClient(url="${websocket_url}",name="websocket")
public interface WebSocketClient {

	@PostMapping("/api/Notice/PostNotice")
	String invokeWebSocket(@RequestBody MsgDto param);
}
