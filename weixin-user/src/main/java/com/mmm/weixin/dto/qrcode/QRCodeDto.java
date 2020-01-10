package com.mmm.weixin.dto.qrcode;

import io.swagger.annotations.ApiModelProperty;

public class QRCodeDto {

    @ApiModelProperty("需要传递的参数")
    private String scene;
    
    @ApiModelProperty("小程序码类型：1计分工具 2约球圈")
    private Integer type;

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }
    
    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
    public String toString() {
        return "QRCodeDto{" +
                "scene=\"" + scene + '\"' +
                '}';
    }
}
