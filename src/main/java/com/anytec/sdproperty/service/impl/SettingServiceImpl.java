package com.anytec.sdproperty.service.impl;


import com.anytec.sdproperty.dao.TbSysMapper;
import com.anytec.sdproperty.jedis.RedisService;
import com.anytec.sdproperty.model.TbSys;
import com.anytec.sdproperty.model.TbSysExample;
import com.anytec.sdproperty.service.inf.SettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


@Service("SettingService")
public class SettingServiceImpl implements SettingService {
	private static final Logger logger = LoggerFactory.getLogger(SettingServiceImpl.class);
	@Autowired
	private TbSysMapper mTbSysMapper;
	@Autowired
	private RedisService redisService;


	@Override
	public TbSys getByKeyName(String name){

		TbSysExample example = new TbSysExample();
		TbSysExample.Criteria c = example.createCriteria();
		c.andKeynameEqualTo(name);
		List<TbSys> list = mTbSysMapper.selectByExample(example);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Boolean checkConfidenceEnough(double confidence) {
		try {
			String confidenceValue;
			if(redisService.get("sysConfidence") == null){
				TbSys sys = getByKeyName(SettingService.KEY_CONFIDENCE);
				confidenceValue = sys.getValue();
				redisService.set("sysConfidence",confidenceValue);
			}else {
				confidenceValue = redisService.get("sysConfidence");
			}
			double sysconfidence = Double.parseDouble(confidenceValue);
			confidence = confidence * 100;
			if (confidence > sysconfidence) {
				return TRUE;
			} else {
				return FALSE;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return TRUE;
	}


}
