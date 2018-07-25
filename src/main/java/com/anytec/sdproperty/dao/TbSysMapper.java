package com.anytec.sdproperty.dao;

import com.anytec.sdproperty.model.TbSys;
import com.anytec.sdproperty.model.TbSysExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSysMapper {
    int countByExample(TbSysExample example);

    int deleteByExample(TbSysExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbSys record);

    int insertSelective(TbSys record);

    List<TbSys> selectByExample(TbSysExample example);

    TbSys selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbSys record, @Param("example") TbSysExample example);

    int updateByExample(@Param("record") TbSys record, @Param("example") TbSysExample example);

    int updateByPrimaryKeySelective(TbSys record);

    int updateByPrimaryKey(TbSys record);
}