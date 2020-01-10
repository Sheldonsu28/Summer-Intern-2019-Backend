package com.mmm.weixin.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.mmm.weixin.dto.CommentDto;
import com.mmm.weixin.dto.GolfCommodityDto;
import com.mmm.weixin.dto.GolfShopParamDto;
import com.mmm.weixin.dto.MatchShopDto;
import com.mmm.weixin.dto.MatchShopParamDto;
import com.mmm.weixin.dto.ShopCommentQueryDto;
import com.mmm.weixin.dto.ShopDetailDto;
import com.mmm.weixin.dto.ShopDetailParamDto;
import com.mmm.weixin.dto.ShopDto;
import com.mmm.weixin.dto.param.CommentParamDto;
import com.mmm.weixin.dto.param.CommentReplyParamDto;
import com.mmm.weixin.dto.param.ShopImageParamDto;
import com.mmm.weixin.vo.Comments;
import com.mmm.weixin.vo.CommentsReply;
import com.mmm.weixin.vo.Shop;
import com.mmm.weixin.vo.ShopImage;

public interface IShopService {

	ShopDetailDto getShopDetail(ShopDetailParamDto param);
	
	ShopDto getShopById(ShopDetailParamDto param);
	
	List<ShopImage> listShopImageByCondition(ShopImageParamDto param);
	
	List<Comments> listCommentsByCondition(CommentParamDto param);
	
	PageInfo<CommentDto> listCommentDtoByShopId(ShopCommentQueryDto shopCommentQueryDto);
	
	List<CommentDto> listCommentDtoByCondition(CommentParamDto param);
	
	public List<CommentsReply> listCommentReplyByCondition(CommentReplyParamDto param);
	
	PageInfo<GolfCommodityDto> listGolfCommodityByCondition(GolfShopParamDto param);
	
	PageInfo<MatchShopDto> listMatchShopByCondition(MatchShopParamDto param);
	
}
