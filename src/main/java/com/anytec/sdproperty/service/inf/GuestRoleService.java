package com.anytec.sdproperty.service.inf;


import com.anytec.sdproperty.model.TbGuestRole;
import com.anytec.sdproperty.service.impl.HException;

public interface GuestRoleService extends BaseService{
    TbGuestRole getByName(String name) throws HException;
    TbGuestRole getById(Integer id);
}
