package com.mmm.weixin.dto.param;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class SaveTripOrderDto {

    @ApiModelProperty("用户token")
    private String authorization;
    @ApiModelProperty("商品ID")
    private Integer commodityid;
    @ApiModelProperty("球场ID")
    private Integer shopid;
    @ApiModelProperty("套餐名称")
    private String commodityname;
    @ApiModelProperty("商品单价")
    private BigDecimal price;
    @ApiModelProperty("套餐总额")
    private BigDecimal totalamount;
    @ApiModelProperty("联系人名字")
    private String contactname;
    @ApiModelProperty("联系人电话")
    private String contactphone;
    @ApiModelProperty("备注信息")
    private String remark;
    @ApiModelProperty("出行日期")
    private String traveldate;
    @ApiModelProperty("出行人数")
    private Integer travelcount;
    @ApiModelProperty("出行人名字")
    private String[] travelfullName;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public Integer getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(Integer commodityid) {
        this.commodityid = commodityid;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public String getCommodityname() {
        return commodityname;
    }

    public void setCommodityname(String commodityname) {
        this.commodityname = commodityname;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTraveldate() {
        return traveldate;
    }

    public void setTraveldate(String traveldate) {
        this.traveldate = traveldate;
    }

    public Integer getTravelcount() {
        return travelcount;
    }

    public void setTravelcount(Integer travelcount) {
        this.travelcount = travelcount;
    }

    public String[] getTravelfullName() {
        return travelfullName;
    }

    public void setTravelfullName(String[] travelfullName) {
        this.travelfullName = travelfullName;
    }
}
