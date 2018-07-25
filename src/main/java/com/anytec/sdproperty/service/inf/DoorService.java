package com.anytec.sdproperty.service.inf;


import com.anytec.sdproperty.model.TbDoor;
import com.anytec.sdproperty.model.TbUser;
import com.anytec.sdproperty.service.impl.HException;

import java.util.List;

/**
 * 
 * @author door 相关的service
 *
 */
public interface DoorService extends BaseService{

    public TbDoor getDoorByName(String name) throws HException;//根据名称返回门岗对象
    public TbDoor getDoorById(Integer id) throws HException;

    /**
     * @param self 当前登陆用户
     * @param param 前端页面搜索传递来的参数对象
     * @param pageNum
     * @param count
     * @param order
     * @param orderRule
     * @return
     */
//    public List<TbDoor> getListPage(TbUser self, TbDoor param, int pageNum, int count, String order, String orderRule);
    public void delete(Integer id);
    public TbDoor addOrUpdate(TbDoor user);
    public int getCount(TbDoor param);
}
