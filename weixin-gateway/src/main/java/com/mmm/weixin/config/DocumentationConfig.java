package com.mmm.weixin.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider{

	private SwaggerResource swaggerResource(String name,String location,String version){
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}
	
	@Override
	public List<SwaggerResource> get() {
		List resources = new ArrayList<>();
		resources.add(swaggerResource("商品系统", "/commodity/v1/api-docs", "2.0"));
		resources.add(swaggerResource("商家系统", "/seller/v1/api-docs", "2.0"));
		resources.add(swaggerResource("订单系统", "/order/v1/api-docs", "2.0"));
		resources.add(swaggerResource("用户系统", "/user/v1/api-docs", "2.0"));
		resources.add(swaggerResource("计分工具", "/tool/v1/api-docs", "2.0"));
		return resources;
	}

}
