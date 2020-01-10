package com.mmm.weixin.vo.tripvo;

import com.mmm.weixin.vo.Commodity;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class FindTripPackageListVo extends Commodity {

    @ApiModelProperty("商品图片")
    private List<String> imgUrl;
    @ApiModelProperty("店铺名称")
    private String shopName;
    @ApiModelProperty("店铺Logo")
    private String shopLogo;

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }

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
