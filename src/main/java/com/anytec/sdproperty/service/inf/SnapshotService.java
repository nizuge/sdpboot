package com.anytec.sdproperty.service.inf;




import com.anytec.sdproperty.model.TbSnapshot;

import java.util.Map;

/**
 * 
 * @author door 相关的service
 *
 */
public interface SnapshotService extends BaseService{

    public TbSnapshot getById(Integer id);

//    public List<OutputSnapshotVo> getListPage(ParamSnapshot param, int pageNum, int count, String order, String orderRule);
    public void delete(Integer id);
//    public Map<String, Object> add(InputSnapshotVo data) throws Exception;
//    public int getCount(ParamSnapshot param);

    public void updateSnapshot(Integer snapshotId, String guest_code);

//    public List<OutputSnapshotVo> getLastSnapshot(Integer ipcid, Integer lastsnapid, Integer forward, Integer count);

     int insert(TbSnapshot snapshot);

    public void openDoorCheck(int doorId, String person_id, float confidence);
}
