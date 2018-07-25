package com.anytec.sdproperty.service.impl;


import com.anytec.sdproperty.dao.TbSnapshotMapper;
import com.anytec.sdproperty.model.TbSnapshot;
import com.anytec.sdproperty.service.inf.SnapshotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("SnapshotService")
public class SnapshotServiceImpl implements SnapshotService {
    private static final Logger logger = LoggerFactory.getLogger(SnapshotServiceImpl.class);

    @Autowired
    private TbSnapshotMapper tbSnapshotMapper;

    @Override
    public TbSnapshot getById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void updateSnapshot(Integer snapshotId, String guest_code) {

    }

    @Override
    public int insert(TbSnapshot snapshot) {
        tbSnapshotMapper.insert(snapshot);
        return snapshot.getId();
    }

    @Override
    public void openDoorCheck(int doorId, String person_id, float confidence) {

    }
}
