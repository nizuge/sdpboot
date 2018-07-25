package com.anytec.sdproperty.dao;

import com.anytec.sdproperty.model.TbGuestRole;
import com.anytec.sdproperty.model.TbGuestRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbGuestRoleMapper {
    int countByExample(TbGuestRoleExample example);

    int deleteByExample(TbGuestRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbGuestRole record);

    int insertSelective(TbGuestRole record);

    List<TbGuestRole> selectByExample(TbGuestRoleExample example);

    TbGuestRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbGuestRole record, @Param("example") TbGuestRoleExample example);

    int updateByExample(@Param("record") TbGuestRole record, @Param("example") TbGuestRoleExample example);

    int updateByPrimaryKeySelective(TbGuestRole record);

    int updateByPrimaryKey(TbGuestRole record);
}