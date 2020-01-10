package com.mmm.weixin.service;

import com.github.pagehelper.PageInfo;
import com.mmm.weixin.dto.AddContestOrderResultDto;
import com.mmm.weixin.dto.BaseDto;
import com.mmm.weixin.dto.ContestOrderNumDto;
import com.mmm.weixin.dto.ContestOrderParamDto;
import com.mmm.weixin.dto.GolfOrderParamDto;
import com.mmm.weixin.dto.UpdateContestOrderParamDto;
import com.mmm.weixin.dto.param.OrderDto;
import com.mmm.weixin.dto.param.UserOrderListDto;
import com.mmm.weixin.vo.ContestTeamMember;
import com.mmm.weixin.vo.ContestTeamMemberDetail;
import com.mmm.weixin.vo.DetailVo;
import com.mmm.weixin.vo.OrderForm;
import com.mmm.weixin.vo.OrderFormCustom;
import com.mmm.weixin.vo.OrderFormField;

import java.util.List;

public interface OrderFormService {

	public PageInfo<OrderForm> list(BaseDto baseDto);

    PageInfo<OrderFormCustom> findUserOrderList(UserOrderListDto dto, String authorization);

    DetailVo getOrderInfoById(OrderDto dto);

    void cancelOrderById(OrderDto dto);

    void deleteOrderById(OrderDto dto);

	void addGolfOrder(GolfOrderParamDto orderParamDto,String authorization);
	
	List<OrderFormField> listOrderFormFieldByOrderType(Integer orderFormType);

	AddContestOrderResultDto addContestOrder(ContestOrderParamDto param, String authorization);

	void updateContestOrder(UpdateContestOrderParamDto param, String authorization);
	
	ContestTeamMemberDetail getContestMember(ContestOrderNumDto param,String authorization);
}
