package com.anytec.sdproperty.service.inf;


import com.anytec.sdproperty.model.TbIpc;

import java.util.Map;

/**
 * 
 * @author door 相关的service
 *
 */
public interface SDKService extends BaseService{
    public Map<String, Object> addFace(String image) throws Exception;

    public boolean updateSet(String keyname, String value);

    //闪开门
    public void flashOpenDoor(String ip, Integer port, Integer line, Integer time);
}
