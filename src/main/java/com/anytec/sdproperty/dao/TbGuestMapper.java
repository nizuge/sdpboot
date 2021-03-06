package com.anytec.sdproperty.dao;

import com.anytec.sdproperty.model.TbGuest;
import com.anytec.sdproperty.model.TbGuestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbGuestMapper {
    int countByExample(TbGuestExample example);

    int deleteByExample(TbGuestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbGuest record);

    int insertSelective(TbGuest record);

    List<TbGuest> selectByExample(TbGuestExample example);

    TbGuest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbGuest record, @Param("example") TbGuestExample example);

    int updateByExample(@Param("record") TbGuest record, @Param("example") TbGuestExample example);

    int updateByPrimaryKeySelective(TbGuest record);

    int updateByPrimaryKey(TbGuest record);
}