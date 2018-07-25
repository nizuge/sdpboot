package com.anytec.sdproperty.service.impl;


import com.anytec.sdproperty.config.GeneralConfig;
import com.anytec.sdproperty.dao.TbDoorLockMapper;
import com.anytec.sdproperty.dao.TbDoorMapper;
import com.anytec.sdproperty.model.TbDoorLock;
import com.anytec.sdproperty.model.TbDoorLockExample;
import com.anytec.sdproperty.model.TbGuest;
import com.anytec.sdproperty.model.TbGuestRole;
import com.anytec.sdproperty.service.inf.DoorLockService;
import com.anytec.sdproperty.service.inf.DoorService;
import com.anytec.sdproperty.service.inf.GuestRoleService;
import com.anytec.sdproperty.service.inf.SDKService;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class DoorLockServiceImpl implements DoorLockService {

    private static final Logger logger = LoggerFactory.getLogger(DoorLockServiceImpl.class);

    @Autowired
    private TbDoorLockMapper mTbDoorLockMapper;

    @Autowired
    private DoorService doorService;

    @Autowired
    private GuestRoleService guestRoleService;

    @Autowired
    private SDKService mSDKService;

    @Autowired
    private GeneralConfig generalConfig;

    public void delete(Integer id) {
        mTbDoorLockMapper.deleteByPrimaryKey(id);
    }


    public TbDoorLock getByDoorId(Integer id, boolean dangerFlag) {
        TbDoorLockExample example = new TbDoorLockExample();
        TbDoorLockExample.Criteria c = example.createCriteria();
        c.andDoorIdEqualTo(id);
        if (dangerFlag) {
            c.andSecurityLevelEqualTo(1);
        } else {
            c.andSecurityLevelNotEqualTo(3);
        }
        List<TbDoorLock> listDoorLock = mTbDoorLockMapper.selectByExample(example);
        if (listDoorLock != null && listDoorLock.size() > 0) {
            return listDoorLock.get(0);
        } else {
            return null;
        }
    }

    @Override
    public TbDoorLock getByDoorId(Integer id) {
        TbDoorLockExample example = new TbDoorLockExample();
        TbDoorLockExample.Criteria c = example.createCriteria();
        c.andDoorIdEqualTo(id);
        List<TbDoorLock> listDoorLock = mTbDoorLockMapper.selectByExample(example);
        if (listDoorLock != null && listDoorLock.size() > 0) {
            return listDoorLock.get(0);
        } else {
            return null;
        }
    }

    public boolean openDoor(Integer id) {
        TbDoorLock lock = mTbDoorLockMapper.selectByPrimaryKey(id);
        mSDKService.flashOpenDoor(lock.getIp(), lock.getPort(), lock.getLine(), lock.getTime());
        return true;
    }

    @Override
    public void openDoorCheck(int doorId, TbGuest guest) {
        //是否开门
        TbDoorLock lock = getByDoorId(doorId, false);
        if (lock != null) {
            //该门岗装了门禁

            logger.info("check user code id:" + guest.getCode());
            TbGuestRole role = guestRoleService.getById(guest.getGuestRoleId());
            if (role != null) {
                if (role.getSecurityLevel().equals(generalConfig.getDoorType())) {
                    TbDoorLock dangerLock = getByDoorId(doorId, true);
                    if (dangerLock != null) {
                        logger.info("识别危险人员，开启警示器");
                        logger.info("ip:" + dangerLock.getIp().toString());
                        logger.info("Port:" + dangerLock.getPort().toString());
                        logger.info("line:" + dangerLock.getLine().toString());
                        logger.info("time:" + dangerLock.getTime().toString());
//					String url = "http://192.168.10.208:8888/";
                        try {
                            Request.Post(generalConfig.getSwitchAddress())
                                    .connectTimeout(10000)
                                    .socketTimeout(30000)
                                    .body(MultipartEntityBuilder
                                            .create()
                                            .addTextBody("ip", dangerLock.getIp())
                                            .addTextBody("port", dangerLock.getPort().toString())
                                            .addTextBody("line", dangerLock.getLine().toString())
                                            .addTextBody("on_off", "1")
                                            .addTextBody("time", dangerLock.getTime().toString())
                                            .build())
                                    .execute().returnResponse();
                        } catch (Exception e) {
                            logger.error("开始请求异常：" + e.getMessage());
                        }
                        logger.info("警示器已开启");
                    } else {
                        logger.info("doorId:" + doorId + "未配置警示器doorlock");
                    }
                } else if (role.getAutoOpenDoor() == 1 && !role.getSecurityLevel().equals(generalConfig.getDoorType())) {
                    boolean needOpen = true;
                    if (role.getLimitTime() != null) {//不为null
                        if (role.getLimitTime()) {//不为false，则进入限制时间模式
                            //如果限制开门时间
                            Date now = new Date();
                            if (guest.getLockStartTime() != null) {
                                if (now.getTime() < guest.getLockStartTime().getTime()) {
                                    logger.info("failopendoor当前时间小于允许开门的最小时间，不开门");
                                    needOpen = false;//当前时间小于允许开门的最小时间，不开门
                                }
                            }
                            if (guest.getLockEndTime() != null) {
                                if (now.getTime() > guest.getLockEndTime().getTime()) {
                                    //当前时间大于允许开门的最大时间， 不开门
                                    logger.info("failopendoor当前时间大于允许开门的最大时间， 不开门");
                                    needOpen = false;
                                }
                            }
                        }
                    }
                    if (needOpen) {
                        logger.info("识别业主，准备开锁");
                        logger.info("ip:" + lock.getIp().toString());
                        logger.info("Port:" + lock.getPort().toString());
                        logger.info("line:" + lock.getLine().toString());
//                                logger.info("on_off:"+"1");
                        logger.info("time:" + lock.getTime().toString());
//					String url = "http://192.168.10.208:8888/";
                        try {
                            Request.Post(generalConfig.getSwitchAddress())
                                    .connectTimeout(10000)
                                    .socketTimeout(30000)
                                    //							.addHeader("Authorization", "Token " + ntechToken)
                                    .body(MultipartEntityBuilder
                                            .create()
                                            .addTextBody("ip", lock.getIp())
                                            .addTextBody("port", lock.getPort().toString())
                                            .addTextBody("line", lock.getLine().toString())
                                            .addTextBody("on_off", "1")
                                            .addTextBody("time", lock.getTime().toString())
                                            .build())
                                    .execute().returnResponse();
                        } catch (Exception e) {
                            logger.error("开始请求异常：" + e.getMessage());
                        }
                        logger.info("完成开锁");
                    }
//                            logger.info("lockresult:" + resultMap.toString());
                } else {
                    logger.info("该用户不再允许开门的时间段内，不开门");
                }
            }
        } else {
            logger.info("doorId:" + doorId + "未配置开门doorlock");
        }
    }

    @Override
    public void alarmDangerLock(TbDoorLock dangerLock) {
        logger.info("ip:" + dangerLock.getIp().toString());
        logger.info("Port:" + dangerLock.getPort().toString());
        logger.info("line:" + dangerLock.getLine().toString());
//                                logger.info("on_off:"+"1");
        logger.info("time:" + dangerLock.getTime().toString());
//					String url = "http://192.168.10.208:8888/";
        try {
            Request.Post(generalConfig.getSwitchAddress())
                    .connectTimeout(10000)
                    .socketTimeout(30000)
                    //							.addHeader("Authorization", "Token " + ntechToken)
                    .body(MultipartEntityBuilder
                            .create()
                            .addTextBody("ip", dangerLock.getIp())
                            .addTextBody("port", dangerLock.getPort().toString())
                            .addTextBody("line", dangerLock.getLine().toString())
                            .addTextBody("on_off", "1")
                            .addTextBody("time", dangerLock.getTime().toString())
                            .build())
                    .execute().returnResponse();
        } catch (Exception e) {
            logger.error("请求警报继电器异常：" + e.getMessage());
        }
    }

    @Override
    public void openDoorLock(TbDoorLock openLock,TbGuest guest,TbGuestRole role) {
        boolean needOpen = true;
        if (role.getLimitTime() != null) {//不为null
            if (role.getLimitTime()) {//不为false，则进入限制时间模式
                //如果限制开门时间
                Date now = new Date();
                if (guest.getLockStartTime() != null) {
                    if (now.getTime() < guest.getLockStartTime().getTime()) {
                        logger.info("fail opendoor当前时间小于允许开门的最小时间，不开门");
                        needOpen = false;//当前时间小于允许开门的最小时间，不开门
                    }
                }
                if (guest.getLockEndTime() != null) {
                    if (now.getTime() > guest.getLockEndTime().getTime()) {
                        //当前时间大于允许开门的最大时间， 不开门
                        logger.info("fail opendoor当前时间大于允许开门的最大时间， 不开门");
                        needOpen = false;
                    }
                }
            }
        }
        if (needOpen) {
            logger.info("ip:" + openLock.getIp().toString());
            logger.info("Port:" + openLock.getPort().toString());
            logger.info("line:" + openLock.getLine().toString());
//                                logger.info("on_off:"+"1");
            logger.info("time:" + openLock.getTime().toString());
//					String url = "http://192.168.10.208:8888/";
            try {
                Request.Post(generalConfig.getSwitchAddress())
                        .connectTimeout(10000)
                        .socketTimeout(30000)
                        //							.addHeader("Authorization", "Token " + ntechToken)
                        .body(MultipartEntityBuilder
                                .create()
                                .addTextBody("ip", openLock.getIp())
                                .addTextBody("port", openLock.getPort().toString())
                                .addTextBody("line", openLock.getLine().toString())
                                .addTextBody("on_off", "1")
                                .addTextBody("time", openLock.getTime().toString())
                                .build())
                        .execute().returnResponse();
            } catch (Exception e) {
                logger.error("请求开门继电器异常：" + e.getMessage());
            }
        }
//
    }


}
