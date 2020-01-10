package com.mmm.weixin.dao;

import com.mmm.weixin.dto.param.OrderDto;
import com.mmm.weixin.vo.DetailVo;
import com.mmm.weixin.vo.OrderForm;

import java.math.BigDecimal;
import java.util.List;

import com.mmm.weixin.vo.OrderFormCustom;
import com.mmm.weixin.vo.OrderFormFieldValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderFormMapper {
    int deleteByPrimaryKey(Integer orderformid);

    int insert(OrderForm record);

    OrderForm selectByPrimaryKey(Integer orderformid);

    List<OrderForm> selectAll();

    int updateByPrimaryKey(OrderForm record);

    List<OrderFormCustom> findUserOrderList(OrderFormCustom orderFormCustom);

    OrderFormFieldValue getOrderExtraValue(@Param("orderFormId") Integer orderFormId, @Param("fieldId") Integer fieldId);

    DetailVo getOrderInfoById(Integer orderId);

    void cancelOrderById(Integer orderId);

    void deleteOrderById(Integer orderId);

    //    void updateOrderState(@Param("orderFormCode") String orderFormCode, @Param("totalFee") BigDecimal fee);
    void updateOrderState(@Param("orderFormId") Integer orderFormId, @Param("totalFee") BigDecimal totalFee, @Param("stateCode") Integer stateCode);

    Integer getPayResult(OrderDto orderDto);

    String[] getShopImgs(Integer shopId);

    Integer selectIdByCode(String orderFormCode);

    BigDecimal getCommodityPriceByOrderId(Integer orderFormId);

    Integer getStateCodeByOrderId(Integer orderFromId);
}