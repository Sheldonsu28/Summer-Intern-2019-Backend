package com.mmm.weixin.vo.tripvo;


import io.swagger.annotations.ApiModelProperty;

public class GetPackageDetailVo extends FindTripPackageListVo {

    @ApiModelProperty("球场名称")
    private String shopName;
    @ApiModelProperty("球场Logo")
    private String shopLogo;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }
}
