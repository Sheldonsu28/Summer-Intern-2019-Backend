package com.mmm.weixin.dao;

import java.util.List;
import java.util.Map;

import com.mmm.weixin.vo.*;
import org.apache.ibatis.annotations.Mapper;

import com.mmm.weixin.dto.UserParamDto;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    User selectByPrimaryKey(Integer userid);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByCondition(UserParamDto param);

    List<CollectVo> getUserCollectList(Integer userId);

    List<String> getProdImgs(CollectVo collectVo);

    List<String> getShopImgs(CollectVo collectVo);

    void saveFeedBack(Feedback feedback);

    void saveComment(Comments commentsDto);

    Integer getShopIdByOrderId(Integer commodityId);

    OrderFormFieldValue getOrderDate(@Param("orderFormId") Integer orderFormId, @Param("fieldId") Integer fieldId);

    String getShopNameById(Integer shopId);

    String getShopLogoById(Integer shopId);

    void saveMessageInfo(Map<String, Object> map);

    String getPhoneCodeByIdPhone(@Param("userId") Integer userId, @Param("smsPhone") String smsPhone);

    void updatePhoneNum(@Param("userId") Integer userId, @Param("phoneNum") String phoneNum);

    User selectByPhoneNumber(Long phoneNum);

    User selectByOpenId(String openId);

    User getUserInfo(Integer userId);

    void saveUserInfo(User user);

    String getAboutUs();

    List<User> selectUserListByUserId(List<Integer> list);

    void updateBySexByUserId(User user);

    void updateThumbnailByUserId(User user);
}