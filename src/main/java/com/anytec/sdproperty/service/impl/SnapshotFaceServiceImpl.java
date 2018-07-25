package com.anytec.sdproperty.service.impl;

import com.anytec.sdproperty.dao.TbSnapshotFaceMapper;
import com.anytec.sdproperty.model.TbSnapshotFace;
import com.anytec.sdproperty.service.inf.BaseService;
import com.anytec.sdproperty.service.inf.SnapshotFaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("SnapshotFaceService")
public class SnapshotFaceServiceImpl implements SnapshotFaceService {
	private static final Logger logger = LoggerFactory.getLogger(SnapshotFaceServiceImpl.class);

	@Autowired
	TbSnapshotFaceMapper tbSnapshotFaceMapper;

	@Override
	public int insert(TbSnapshotFace tbSnapshotFace) {
		return tbSnapshotFaceMapper.insert(tbSnapshotFace);
	}
}
