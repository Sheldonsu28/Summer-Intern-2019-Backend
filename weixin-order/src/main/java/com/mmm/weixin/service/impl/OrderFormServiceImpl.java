package com.mmm.weixin.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mmm.weixin.dto.param.OrderDto;
import com.mmm.weixin.dto.param.OrderFormFieldParamDto;
import com.mmm.weixin.dto.param.OrderFormFieldValueParamDto;
import com.mmm.weixin.dto.param.UserOrderListDto;
import com.mmm.weixin.exception.ServiceException;
import com.mmm.weixin.feign.CommodityClient;
import com.mmm.weixin.utils.base.ValidateHelper;
import com.mmm.weixin.utils.jwt.JwtUtil;
import com.mmm.weixin.utils.sequence.GenerateSequenceUtil;
import com.mmm.weixin.vo.ContestInfo;
import com.mmm.weixin.vo.ContestTeamInfo;
import com.mmm.weixin.vo.ContestTeamMember;
import com.mmm.weixin.vo.ContestTeamMemberDetail;
import com.mmm.weixin.vo.DetailVo;
import com.mmm.weixin.vo.OrderFormCustom;
import com.mmm.weixin.vo.OrderFormField;
import com.mmm.weixin.vo.OrderFormFieldValue;
import com.mmm.weixin.vo.OrderFormItems;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmm.weixin.common.ResultValidate;
import com.mmm.weixin.common.TokenValidate;
import com.mmm.weixin.constants.OrderConstants;
import com.mmm.weixin.dao.OrderFormFieldMapper;
import com.mmm.weixin.dao.OrderFormFieldValueMapper;
import com.mmm.weixin.dao.OrderFormItemsMapper;
import com.mmm.weixin.dao.OrderFormMapper;
import com.mmm.weixin.dto.AddContestOrderResultDto;
import com.mmm.weixin.dto.BaseDto;
import com.mmm.weixin.dto.ContestOrderNumDto;
import com.mmm.weixin.dto.ContestOrderParamDto;
import com.mmm.weixin.dto.GolfOrderParamDto;
import com.mmm.weixin.dto.OrderItemParamDto;
import com.mmm.weixin.dto.OrderParamDto;
import com.mmm.weixin.dto.Result;
import com.mmm.weixin.dto.TeamMemberDto;
import com.mmm.weixin.dto.UpdateContestOrderParamDto;
import com.mmm.weixin.service.OrderFormService;
import com.mmm.weixin.vo.OrderForm;

import javax.annotation.Resource;

@Service
public class OrderFormServiceImpl implements OrderFormService {

    @Autowired
    private OrderFormMapper orderMapper;

    @Autowired
    private OrderFormFieldMapper orderFormFieldMapper;

    @Autowired
    private OrderFormFieldValueMapper orderFormFieldValueMapper;

    @Autowired
    private OrderFormItemsMapper orderItemsMapper;

    @Resource
    private JwtUtil jwtUtil;

    @Autowired
    private TokenValidate tokenValidate;

    @Autowired
    private CommodityClient commodityClient;
    
    @Override
    public PageInfo<OrderForm> list(BaseDto baseDto) {
        PageHelper.startPage(baseDto.getCurrentPage(), baseDto.getPageSize());
        List<OrderForm> items = orderMapper.selectAll();
        PageInfo<OrderForm> result = new PageInfo<OrderForm>(items);
        return result;
    }

    @Override
    public PageInfo<OrderFormCustom> findUserOrderList(UserOrderListDto dto,String authorization) {

        OrderFormCustom orderFormCustom = new OrderFormCustom();
        Integer userId = tokenValidate.validateToken(authorization);
        orderFormCustom.setUserId(userId);
        orderFormCustom.setStateCode(dto.getStateCode());
        orderFormCustom.setOrderFormType(dto.getOrderType());
        PageHelper.startPage(dto.getCurrentPage(), dto.getPageSize());
        List<OrderFormCustom> list = orderMapper.findUserOrderList(orderFormCustom);

        for (OrderFormCustom order : list) {
            order.setImgUrls(orderMapper.getShopImgs(order.getShopId()));
            if (dto.getOrderType() == 1) {
                order.setPlayDate(orderMapper.getOrderExtraValue(order.getOrderFormId(), 1).getValuetext());
                order.setPlayTime(orderMapper.getOrderExtraValue(order.getOrderFormId(), 2).getValuetext());
                order.setPlayUserCount(Integer.parseInt(orderMapper.getOrderExtraValue(order.getOrderFormId(), 3).getValuetext()));
            } else if (dto.getOrderType() == 2) {
                order.setPlayDate(orderMapper.getOrderExtraValue(order.getOrderFormId(), 8).getValuetext());
                order.setPlayUserCount(Integer.parseInt(orderMapper.getOrderExtraValue(order.getOrderFormId(), 9).getValuetext()));
            } else if(dto.getOrderType() == 3) {
            	String timeStr = orderMapper.getOrderExtraValue(order.getOrderFormId(), 14).getValuetext();
            	order.setPlayDate(timeStr);
            	order.setPlayUserCount(Integer.parseInt(orderMapper.getOrderExtraValue(order.getOrderFormId(), 16).getValuetext()));
            	order.setDepositAmount(this.getContestInfo(order.getOrderFormId()).getDepositAmount());
            }
        }
        return new PageInfo<>(list);
    }

	private ContestInfo getContestInfo(Integer orderFormId) {
		ContestTeamInfo team = new ContestTeamInfo();
		team.setOrderFormId(orderFormId);
		Result result = commodityClient.getContestTeamByCondition(team);
		ResultValidate.validateResult(result);
		List<ContestTeamInfo> contestTeams = JSON.parseObject(JSON.toJSONString(result.getData()), new TypeReference<List<ContestTeamInfo>>() {});
		ContestTeamInfo contestTeamInfo = contestTeams.get(0);
		Result contestInfoResult = commodityClient.getContestInfoById(contestTeamInfo.getContestId());
		return JSON.parseObject(JSON.toJSONString(contestInfoResult.getData()),new TypeReference<ContestInfo>() {});
	}

    
    
    @Override
    public DetailVo getOrderInfoById(OrderDto dto) {

        DetailVo detailVo = orderMapper.getOrderInfoById(dto.getOrderId());
        detailVo.setPrice(orderMapper.getCommodityPriceByOrderId(dto.getOrderId()));
        if (dto.getOrderType() == 1) {
            detailVo.setPlayDate(orderMapper.getOrderExtraValue(dto.getOrderId(), 1).getValuetext());
            detailVo.setPlayTime(orderMapper.getOrderExtraValue(dto.getOrderId(), 2).getValuetext());
            detailVo.setPlayCount(Integer.parseInt(orderMapper.getOrderExtraValue(dto.getOrderId(), 3).getValuetext()));
            detailVo.setPlayPeople(orderMapper.getOrderExtraValue(dto.getOrderId(), 4).getValuetext());
            detailVo.setPlayPeoplePhone(orderMapper.getOrderExtraValue(dto.getOrderId(), 5).getValuetext());
            detailVo.setContactName(orderMapper.getOrderExtraValue(dto.getOrderId(), 6).getValuetext());

        } else if (dto.getOrderType() == 2) {
            detailVo.setTravelTitle(orderMapper.getOrderExtraValue(dto.getOrderId(), 7).getValuetext());
            detailVo.setPlayDate(orderMapper.getOrderExtraValue(dto.getOrderId(), 8).getValuetext());
            detailVo.setPlayCount(Integer.parseInt(orderMapper.getOrderExtraValue(dto.getOrderId(), 9).getValuetext()));
            detailVo.setPlayPeople(orderMapper.getOrderExtraValue(dto.getOrderId(), 10).getValuetext());
            detailVo.setPlayPeoplePhone(orderMapper.getOrderExtraValue(dto.getOrderId(), 11).getValuetext());
            detailVo.setContactName(orderMapper.getOrderExtraValue(dto.getOrderId(), 12).getValuetext());
        } else if (dto.getOrderType() == 3) {
        	detailVo.setTeamName(orderMapper.getOrderExtraValue(dto.getOrderId(), 15).getValuetext());
        	detailVo.setPlayDate(orderMapper.getOrderExtraValue(dto.getOrderId(), 14).getValuetext());
        	detailVo.setPlayCount(Integer.parseInt(orderMapper.getOrderExtraValue(dto.getOrderId(), 16).getValuetext()));
        	detailVo.setPlayPeople(orderMapper.getOrderExtraValue(dto.getOrderId(), 17).getValuetext());
        	detailVo.setPlayPeoplePhone(orderMapper.getOrderExtraValue(dto.getOrderId(), 18).getValuetext());
        	detailVo.setContactName(orderMapper.getOrderExtraValue(dto.getOrderId(), 19).getValuetext());
        	detailVo.setDepositAmount(this.getContestInfo(dto.getOrderId()).getDepositAmount());
        }

        return detailVo;
    }

    @Override
    public void cancelOrderById(OrderDto dto) {
        orderMapper.cancelOrderById(dto.getOrderId());
    }

    @Override
    public void deleteOrderById(OrderDto dto) {
        orderMapper.deleteOrderById(dto.getOrderId());
    }

    /**
     * 添加高尔夫球场订单
     */
    @Transactional
    public void addGolfOrder(GolfOrderParamDto golfOrderParamDto, String authorization) {
        Integer userId = tokenValidate.validateToken(authorization);
        validateOrderPrice(new BigDecimal(golfOrderParamDto.getUnitPrice()), golfOrderParamDto.getPlayerNum(), new BigDecimal(golfOrderParamDto.getTotalPrice()));
        OrderParamDto orderParamDto = new OrderParamDto();
        OrderItemParamDto orderItemParamDto = new OrderItemParamDto();
        BeanUtils.copyProperties(golfOrderParamDto, orderParamDto);
        BeanUtils.copyProperties(golfOrderParamDto, orderItemParamDto);
        Map<String, Object> addOrderResultMap = addOrderForm(orderParamDto, userId, OrderConstants.ORDER_TYPE_GOLF);
        Integer orderFormId = (Integer) addOrderResultMap.get("orderformid");
        addGolfOrderFormFieldValue(golfOrderParamDto, orderFormId);
        addOrderItems(orderItemParamDto, orderFormId);
    }

    /**
     * 添加订单
     *
     * @param param
     * @param userId
     */
    private Map<String,Object> addOrderForm(OrderParamDto param, Integer userId, Integer orderType) {
        Map<String,Object> result = new HashMap<String,Object>();
    	OrderForm orderForm = new OrderForm();
        String orderformcode = GenerateSequenceUtil.generateSequenceNo();
        orderForm.setOrderFormCode(orderformcode);
        Date current = new Date();
        orderForm.setAppendTime(current);
        orderForm.setUserId(userId);
        orderForm.setOrderFormType(orderType);
        orderForm.setLastTime(current);
        orderForm.setTotalAmount(BigDecimal.valueOf(param.getTotalPrice()));
        orderForm.setShouldPayAmount(BigDecimal.valueOf(param.getTotalPrice()));
        orderForm.setActualPayAmount(BigDecimal.valueOf(0));
        orderForm.setPayMethod(OrderConstants.PAY_METHOD_WX);
        orderForm.setStateCode(OrderConstants.ORDER_TYPE_GOLF==orderType?OrderConstants.STATE_CODE_NOT_CONFIRM:OrderConstants.STATE_CODE_NOT_PAY);
        orderForm.setRemark(param.getRemark());
        orderForm.setIsDelete(false);
        orderForm.setShopId(param.getShopId());
        orderMapper.insert(orderForm);
        Integer orderformid = orderForm.getOrderFormId();
        result.put("orderformid", orderformid);
        result.put("orderformcode", orderformcode);
        return result;

    }

    /**
     * 添加高尔夫订单额外属性值
     *
     * @param param
     * @param orderformid
     */
    private void addGolfOrderFormFieldValue(GolfOrderParamDto param, Integer orderformid) {
        // 查询高尔夫类型的订单属性
        List<OrderFormField> orderFormFieds = this.listOrderFormFieldByOrderType(OrderConstants.ORDER_TYPE_GOLF);
        Iterator<OrderFormField> iterator = orderFormFieds.iterator();
        OrderFormFieldValue offv = null;
        while (iterator.hasNext()) {
            OrderFormField next = iterator.next();
            String fieldenname = next.getFieldenname();
            Integer fieldid = next.getFieldid();
            offv = new OrderFormFieldValue();
            offv.setFieldid(fieldid);
            offv.setOrderformid(orderformid);
            // 根据FieldEnName设置属性值
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String playDate = sdf.format(param.getPlayDate()).substring(0, 9);
            String playTime = sdf.format(param.getPlaytime()).substring(11, 19);
            if (OrderConstants.ORDER_FIELD_PLAYING_DATE.equals(fieldenname)) {
                offv.setValuetext(playDate);
            } else if (OrderConstants.ORDER_FIELD_PLAYING_TIME.equals(fieldenname)) {
                offv.setValuetext(playTime);
            } else if (OrderConstants.ORDER_FIELD_PLAYING_USER_COUNT.equals(fieldenname)) {
                offv.setValuetext(String.valueOf(param.getPlayerNum()));
            } else if (OrderConstants.ORDER_FIELD_FULL_NAME.equals(fieldenname)) {
                String[] playerName = param.getPlayerName();
                // 把打球人姓名转换成逗号分隔的字符串
                String playerNameStr = String.join(",", playerName);
                offv.setValuetext(playerNameStr);
            } else if (OrderConstants.ORDER_FIELD_CONTACT_PHONE.equals(fieldenname)) {
                offv.setValuetext(String.valueOf(param.getMobile()));
            } else if (OrderConstants.ORDER_FIELD_CONTACT_NAME.equals(fieldenname)) {
                offv.setValuetext(String.valueOf(param.getContactName()));
            }
            orderFormFieldValueMapper.insert(offv);
        }
    }

    /**
     * 添加订单项目
     *
     * @param param
     */
    private void addOrderItems(OrderItemParamDto param, Integer orderFormId) {
        Integer commdityId = param.getCommodityId();
        Integer playerNum = param.getPlayerNum();
        OrderFormItems ofi = new OrderFormItems();
        ofi.setCommodityid(commdityId);
        ofi.setSellcount(playerNum);
        ofi.setSellprice(BigDecimal.valueOf(param.getUnitPrice()));
        ofi.setActualsellprice(BigDecimal.valueOf(param.getUnitPrice()));
        ofi.setTotalamount(BigDecimal.valueOf(param.getTotalPrice()));
        ofi.setOrderformid(orderFormId);
        orderItemsMapper.insert(ofi);
    }

    @Override
    public List<OrderFormField> listOrderFormFieldByOrderType(Integer orderFormType) {
        return orderFormFieldMapper.selectByOderType(orderFormType);
    }

    /**
     * 添加团队赛事订单
     */
    @Override
    @Transactional
    public AddContestOrderResultDto addContestOrder(ContestOrderParamDto param, String authorization) {
        AddContestOrderResultDto result = new AddContestOrderResultDto();
    	Integer teamId = param.getTeamId();
        Integer contestId = param.getContestId();
        validateContestTeam(teamId, contestId);
        Integer userId = tokenValidate.validateToken(authorization);
        OrderItemParamDto itemParam = new OrderItemParamDto();
        BeanUtils.copyProperties(param, itemParam);
        //团队赛订单项目的商品ID为0
        itemParam.setCommodityId(0);
        Map<String,Object> addContestOrderResult = addContestOrderForm(param, userId);
        Integer orderFormId = (Integer) addContestOrderResult.get("orderformid");
        String orderFormCode = String.valueOf(addContestOrderResult.get("orderformcode"));
        result.setOrderformid(orderFormId);
        result.setOrderformcode(orderFormCode);
        addContestOrderFormFieldValue(param, orderFormId);
        addOrderItems(itemParam, orderFormId);
        Integer contestTeamId = addContestTeamInfo(teamId, contestId, orderFormId);
        addContestTeamMember(param.getMembers(), contestTeamId);
        return result;
    }

    /**
     * 校验队伍是否已经报名对应的赛事
     */
    private void validateContestTeam(Integer teamId, Integer contestId) {
        ContestTeamInfo team = new ContestTeamInfo();
        team.setTeamId(teamId);
        team.setContestId(contestId);
        Result result = commodityClient.getContestTeamByCondition(team);
        List<ContestTeamInfo> contestTeams = JSON.parseObject(JSON.toJSONString(result.getData()), new TypeReference<List<ContestTeamInfo>>() {
        });
        if (contestTeams.size() > 0) {
            throw new ServiceException("队伍已经报名该赛事");
        }
    }

    /**
     * 添加赛事队伍纪录
     *
     * @param teamId
     * @param contestId
     * @param orderFormId
     */
    private Integer addContestTeamInfo(Integer teamId, Integer contestId, Integer orderFormId) {
        ContestTeamInfo team = new ContestTeamInfo();
        team.setTeamId(teamId);
        team.setContestId(contestId);
        team.setOrderFormId(orderFormId);
        Result result = commodityClient.addContestTeam(team);
        ResultValidate.validateResult(result);
        Integer contestTeamId = JSON.parseObject(JSON.toJSONString(result.getData()), Integer.class);
        return contestTeamId;
    }

    /**
     * 添加参赛队伍成员
     *
     * @param members
     * @param teamId
     */
    private void addContestTeamMember(List<TeamMemberDto> members, Integer contestTeamId) {
        List<ContestTeamMember> memberList = getContestTeamMemberList(members, contestTeamId);
        Result result = commodityClient.addContestTeamMember(memberList);
        ResultValidate.validateResult(result);
    }

	private List<ContestTeamMember> getContestTeamMemberList(List<TeamMemberDto> members, Integer ctId) {
		if (members.isEmpty() || members.size() == 0) {
            throw new ServiceException("参赛队员不能为空");
        }
        List<ContestTeamMember> memberList = new ArrayList<ContestTeamMember>();
        ContestTeamMember member = null;
        Iterator<TeamMemberDto> iterator = members.iterator();
        while (iterator.hasNext()) {
            member = new ContestTeamMember();
            TeamMemberDto memberDto = iterator.next();
            member.setMemberId(memberDto.getMemberId());
            member.setCtId(ctId);
            member.setMemberName(memberDto.getName());
            memberList.add(member);
        }
		return memberList;
	}

    /**
     * 添加团队赛订单
     *
     * @param param
     * @param userId
     * @return
     */
    private Map<String,Object> addContestOrderForm(ContestOrderParamDto param, Integer userId) {
        BigDecimal totalPrice = new BigDecimal(param.getTotalPrice());
        Integer contestId = param.getContestId();
        Result result = commodityClient.getContestInfoById(contestId);
        ContestInfo contestInfo = JSON.parseObject(JSON.toJSONString(result.getData()), new TypeReference<ContestInfo>() {
        });
        BigDecimal unitPrice = contestInfo.getPrice();
        //校验订单金额
        this.validateOrderPrice(unitPrice, param.getMembers().size(), totalPrice);
        //添加团队赛事订单
        OrderParamDto orderParamDto = new OrderParamDto();
        BeanUtils.copyProperties(param, orderParamDto);
        return this.addOrderForm(orderParamDto, userId, OrderConstants.ORDER_TYPE_CONTEST);
    }

    /**
     * 校验金额
     *
     * @param unitPrice
     * @param number
     * @param totalPrice
     */
    private void validateOrderPrice(BigDecimal unitPrice, int number, BigDecimal totalPrice) {
        int result = unitPrice.multiply(new BigDecimal(number)).compareTo(totalPrice);
        if (result != 0) {
            throw new ServiceException("订单金额有误，请刷新页面重新提交");
        }
    }

    //添加团队赛事订单额外属性
    public void addContestOrderFormFieldValue(ContestOrderParamDto param, Integer orderformid) {
        Result result = commodityClient.getContestInfoById(param.getContestId());
        ContestInfo contestInfo = JSON.parseObject(JSON.toJSONString(result.getData()), new TypeReference<ContestInfo>() {
        });
        // 查询高尔夫类型的订单属性
        List<OrderFormField> orderFormFieds = this.listOrderFormFieldByOrderType(OrderConstants.ORDER_TYPE_CONTEST);
        Iterator<OrderFormField> iterator = orderFormFieds.iterator();
        OrderFormFieldValue offv = null;
        while (iterator.hasNext()) {
            OrderFormField next = iterator.next();
            String fieldenname = next.getFieldenname();
            Integer fieldid = next.getFieldid();
            offv = new OrderFormFieldValue();
            offv.setFieldid(fieldid);
            offv.setOrderformid(orderformid);
            // 根据FieldEnName设置属性值
            if (OrderConstants.ORDER_FIELD_CONTEST_TITLE.contentEquals(fieldenname)) {
                offv.setValuetext(contestInfo.getTitle());
            } else if (OrderConstants.ORDER_FIELD_CONTEST_TIME.equals(fieldenname)) {
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	String beginTime = sdf.format(contestInfo.getContestBeginTime());
            	String endTime = sdf.format(contestInfo.getContestEndTime());
                offv.setValuetext(beginTime + "," + endTime);
            } else if (OrderConstants.ORDER_FIELD_TEAM_NAME.equals(fieldenname)) {
                offv.setValuetext(param.getTeamName());
            } else if (OrderConstants.ORDER_FIELD_PLAYING_USER_COUNT.equals(fieldenname)) {
                offv.setValuetext(String.valueOf(param.getMembers().size()));
            } else if (OrderConstants.ORDER_FIELD_FULL_NAME.equals(fieldenname)) {
                String fullNames = resolveFullName(param.getMembers());
                offv.setValuetext(fullNames);
            } else if (OrderConstants.ORDER_FIELD_CONTACT_PHONE.equals(fieldenname)) {
                offv.setValuetext(param.getContactPhone());
            } else if (OrderConstants.ORDER_FIELD_CONTACT_NAME.equals(fieldenname)) {
                offv.setValuetext(param.getContactName());
            } else if (OrderConstants.ORDER_FIELD_CONTEST_ADDRESS.equals(fieldenname)) {
                offv.setValuetext(contestInfo.getAddress());
            }
            orderFormFieldValueMapper.insert(offv);
        }
    }

    //解析用户名数组字符串
    private String resolveFullName(List<TeamMemberDto> members) {
        List<String> namesList = new ArrayList<String>();
        Iterator<TeamMemberDto> iterator = members.iterator();
        while (iterator.hasNext()) {
            TeamMemberDto teamMember = iterator.next();
            namesList.add(teamMember.getName());
        }
        if (namesList.size() == 0) {
            return "";
        }
        String[] namesArray = namesList.toArray(new String[namesList.size()]);
        return String.join(",", namesArray);
    }

    @Transactional
	@Override
	public void updateContestOrder(UpdateContestOrderParamDto param, String authorization) {
        Integer userId = tokenValidate.validateToken(authorization);
        //总金额不能小于订金
        checkTotalAmount(param);
        //修改OrderFormFieldValue的PlayingUserCount和FullName
        updateContestOrderFormFieldValue(param);
        //修改ContestTeamMember
        updateContestTeamMember(param);
        //修改OrderForm的TotalAmount
        BigDecimal newTotalAmount = new BigDecimal(param.getUnitPrice()).multiply(new BigDecimal(param.getMembers().size()));
        OrderForm order = orderMapper.selectByPrimaryKey(param.getOrderId());
        order.setTotalAmount(newTotalAmount);
        order.setShouldPayAmount(newTotalAmount);
        orderMapper.updateByPrimaryKey(order);
	}

	private void updateContestTeamMember(UpdateContestOrderParamDto param) {
		ContestTeamInfo contestTeamParam = new ContestTeamInfo();
        contestTeamParam.setOrderFormId(param.getOrderId());
        Result contestTeamInfoResult = commodityClient.getContestTeamByCondition(contestTeamParam);
        ResultValidate.validateResult(contestTeamInfoResult);
        List<ContestTeamInfo> contestTeamInfos = JSON.parseObject(JSON.toJSONString(contestTeamInfoResult.getData()),new TypeReference<List<ContestTeamInfo>>() {});
        //根据ContestTeamInfo的ID查询ContestTeamMember列表
        List<ContestTeamMember> contestTeamMemberList = this.getContestTeamMemberList(param.getMembers(), contestTeamInfos.get(0).getCtId());
        commodityClient.updateContestTeamMember(contestTeamMemberList);
	}

	private void updateContestOrderFormFieldValue(UpdateContestOrderParamDto param) {
		OrderFormField orderFormField = new OrderFormField();
		OrderFormFieldParamDto fieldParam = new OrderFormFieldParamDto();
		OrderFormFieldValueParamDto fieldValueParam = new OrderFormFieldValueParamDto();
		OrderFormFieldValue orderFormFieldValue = new OrderFormFieldValue();
		//更新PlayingUserCount
		Integer orderId = param.getOrderId();
		fieldParam.setFieldName(OrderConstants.ORDER_FIELD_PLAYING_USER_COUNT);
		fieldParam.setOrderFormType(OrderConstants.ORDER_TYPE_CONTEST);
		orderFormField = orderFormFieldMapper.selectByCondition(fieldParam);
		fieldValueParam.setFieldId(orderFormField.getFieldid());
		fieldValueParam.setOrderformId(param.getOrderId());
		orderFormFieldValue = orderFormFieldValueMapper.selectByCondition(fieldValueParam);
		orderFormFieldValue.setValuetext(String.valueOf(param.getMembers().size()));
		orderFormFieldValueMapper.updateByPrimaryKey(orderFormFieldValue);
		//更新FullName
		String resolveFullName = this.resolveFullName(param.getMembers());
		fieldParam.setFieldName(OrderConstants.ORDER_FIELD_FULL_NAME);
		fieldParam.setOrderFormType(OrderConstants.ORDER_TYPE_CONTEST);
		orderFormField = orderFormFieldMapper.selectByCondition(fieldParam);
		fieldValueParam.setFieldId(orderFormField.getFieldid());
		fieldValueParam.setOrderformId(param.getOrderId());
		orderFormFieldValue = orderFormFieldValueMapper.selectByCondition(fieldValueParam);
		orderFormFieldValue.setValuetext(resolveFullName);
		orderFormFieldValueMapper.updateByPrimaryKey(orderFormFieldValue);
	}

	private void checkTotalAmount(UpdateContestOrderParamDto param) {
		Integer orderId = param.getOrderId();
		OrderForm order = orderMapper.selectByPrimaryKey(orderId);
		if(null == order) {
			throw new ServiceException("订单不存在");
		}
		BigDecimal totalAmount = new BigDecimal(param.getMembers().size()*param.getUnitPrice());
		if(totalAmount.compareTo(order.getActualPayAmount())==-1) {
			throw new ServiceException("订单总金额不能小于订金");
		}
	}

	@Override
	public ContestTeamMemberDetail getContestMember(ContestOrderNumDto param, String authorization) {
		tokenValidate.validateToken(authorization);
		Result result = commodityClient.getContestTeamMember(param);
		ResultValidate.validateResult(result);
		ContestTeamMemberDetail members = JSON.parseObject(JSON.toJSONString(result.getData()), new TypeReference<ContestTeamMemberDetail>() {});
		return members;
	}

}
