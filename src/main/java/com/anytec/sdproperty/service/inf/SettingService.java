package com.anytec.sdproperty.service.inf;


import com.anytec.sdproperty.model.TbSys;
import com.anytec.sdproperty.service.impl.HException;

import java.util.List;

/**
 * 
 * @author 系统设置 相关的service
 *
 */
public interface SettingService extends BaseService{

    public final static String KEY_CONFIDENCE = "confidence";
    public final static String KEY_STORAGE_MAX = "storage_max";
    public final static String KEY_SYSNAME ="sysname";

    TbSys getByKeyName(String name);

    Boolean checkConfidenceEnough(double confidence);
}
