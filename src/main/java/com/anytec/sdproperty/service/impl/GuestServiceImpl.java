package com.anytec.sdproperty.service.impl;


import com.anytec.sdproperty.dao.TbGuestMapper;
import com.anytec.sdproperty.model.TbGuest;
import com.anytec.sdproperty.model.TbGuestExample;
import com.anytec.sdproperty.service.inf.GuestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("GuestService")
public class GuestServiceImpl implements GuestService {
    private static final Logger logger = LoggerFactory.getLogger(GuestServiceImpl.class);

    @Autowired
    private TbGuestMapper tbGuestMapper;

    @Override
    public TbGuest getByCode(String code) {
        try {
            TbGuestExample ue = new TbGuestExample();
            ue.createCriteria().andCodeEqualTo(code);
            List<TbGuest> listGuest =  tbGuestMapper.selectByExample(ue);
            if (listGuest != null && listGuest.size() > 0) {
                return listGuest.get(0);
            } else {
                logger.info("has no Guest code:" + code);
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public TbGuest checkCode(String code) {
        TbGuestExample ue = new TbGuestExample();
        ue.createCriteria().andCodeEqualTo(String.valueOf(code));
        List<TbGuest> listGuest =  tbGuestMapper.selectByExample(ue);
        if (listGuest != null && listGuest.size() > 0) {
            return listGuest.get(0);
        }
        return null;
    }
}
