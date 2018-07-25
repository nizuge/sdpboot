package com.anytec.sdproperty.dao;

import com.anytec.sdproperty.model.TbSnapshot;
import com.anytec.sdproperty.model.TbSnapshotExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSnapshotMapper {
    int countByExample(TbSnapshotExample example);

    int deleteByExample(TbSnapshotExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbSnapshot record);

    int insertSelective(TbSnapshot record);

    List<TbSnapshot> selectByExample(TbSnapshotExample example);

    TbSnapshot selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbSnapshot record, @Param("example") TbSnapshotExample example);

    int updateByExample(@Param("record") TbSnapshot record, @Param("example") TbSnapshotExample example);

    int updateByPrimaryKeySelective(TbSnapshot record);

    int updateByPrimaryKey(TbSnapshot record);
}