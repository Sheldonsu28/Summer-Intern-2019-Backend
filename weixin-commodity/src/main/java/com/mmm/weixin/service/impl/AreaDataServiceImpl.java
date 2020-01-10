package com.mmm.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmm.weixin.dao.AreaDataMapper;
import com.mmm.weixin.dto.param.AreaDataParamDto;
import com.mmm.weixin.service.IAreaDataService;
import com.mmm.weixin.vo.AreaData;

@Service
public class AreaDataServiceImpl implements IAreaDataService{

	@Autowired
	private AreaDataMapper areaMapper;
	
	@Override
	public List<AreaData> getAreaDataByCondition(AreaDataParamDto param) {
		return areaMapper.selectByCondition(param);
	}

}
