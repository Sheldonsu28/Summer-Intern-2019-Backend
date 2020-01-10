package com.mmm.weixin.service;

import java.util.List;

import com.mmm.weixin.dto.param.AreaDataParamDto;
import com.mmm.weixin.vo.AreaData;

public interface IAreaDataService {

	public List<AreaData> getAreaDataByCondition(AreaDataParamDto param);

}
