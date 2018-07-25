package com.anytec.sdproperty.service.inf;



import com.anytec.sdproperty.model.TbDoorLock;
import com.anytec.sdproperty.model.TbGuest;
import com.anytec.sdproperty.model.TbGuestRole;


public interface DoorLockService extends BaseService{

//    public List<TbDoorLockVo> getListPage(TbDoorLock param, int pageNum, int count, String order, String orderRule);
    public void delete(Integer id);
//    public TbDoorLock addOrUpdate(TbDoorLock user);
//    public int getCount(TbDoorLock param);

    public TbDoorLock getByDoorId(Integer id, boolean dangerFlag);//获取某个门的门禁

    public TbDoorLock getByDoorId(Integer id);//获取某个门的门禁

    public boolean openDoor(Integer id);

    void openDoorCheck(int doorId, TbGuest guest);

    void alarmDangerLock(TbDoorLock dangerLock);

    void openDoorLock(TbDoorLock openLock , TbGuest guest, TbGuestRole role);
}
