package com.anytec.sdproperty.dao;

import com.anytec.sdproperty.model.TbDoorLock;
import com.anytec.sdproperty.model.TbDoorLockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbDoorLockMapper {
    int countByExample(TbDoorLockExample example);

    int deleteByExample(TbDoorLockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbDoorLock record);

    int insertSelective(TbDoorLock record);

    List<TbDoorLock> selectByExample(TbDoorLockExample example);

    TbDoorLock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbDoorLock record, @Param("example") TbDoorLockExample example);

    int updateByExample(@Param("record") TbDoorLock record, @Param("example") TbDoorLockExample example);

    int updateByPrimaryKeySelective(TbDoorLock record);

    int updateByPrimaryKey(TbDoorLock record);
}