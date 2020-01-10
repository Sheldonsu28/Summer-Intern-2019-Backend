package com.mmm.weixin.dao;

import com.mmm.weixin.vo.OrderFormItems;
import java.util.List;

public interface OrderFormItemsMapper {
    int deleteByPrimaryKey(Integer itemid);

    int insert(OrderFormItems record);

    OrderFormItems selectByPrimaryKey(Integer itemid);

    List<OrderFormItems> selectAll();

    int updateByPrimaryKey(OrderFormItems record);
}