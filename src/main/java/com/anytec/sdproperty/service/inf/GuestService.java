package com.anytec.sdproperty.service.inf;


import com.anytec.sdproperty.model.TbGuest;

public interface GuestService extends BaseService{
    TbGuest getByCode(String code);
    TbGuest checkCode(String code);
}
