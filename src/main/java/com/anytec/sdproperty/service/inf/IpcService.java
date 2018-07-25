package com.anytec.sdproperty.service.inf;




import com.anytec.sdproperty.model.TbIpc;
import com.anytec.sdproperty.service.impl.HException;

import java.util.List;

/**
 *
 * @author door 相关的service
 *
 */
public interface IpcService extends BaseService{

    public TbIpc getByName(String name) throws HException;//根据名称返回门岗对象
    public TbIpc getById(Integer id) throws HException;
    public TbIpc getByAddress(String mac);//mac address
    public List<TbIpc> getAllIpc();
}
