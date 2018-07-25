package com.anytec.sdproperty.service.inf;



import com.anytec.sdproperty.model.TbSnapshotFace;

import java.util.List;

/**
 * 
 * @author SnapshotFace 相关的service
 *
 */
public interface SnapshotFaceService extends BaseService{

    int insert(TbSnapshotFace tbSnapshotFace);
//    public List<OutputSnapshotFaceVo> queryListBySnapshotId(int snapshotId);
//    public OutputSnapshotFaceVo queryOutputSnapshotFaceVoBySnapshotId(int snapshotId);

}
