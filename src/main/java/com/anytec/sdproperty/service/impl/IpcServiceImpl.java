package com.anytec.sdproperty.service.impl;


import com.anytec.sdproperty.dao.TbDoorMapper;
import com.anytec.sdproperty.dao.TbIpcMapper;
import com.anytec.sdproperty.model.TbIpc;
import com.anytec.sdproperty.model.TbIpcExample;
import com.anytec.sdproperty.service.inf.IpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("IpcService")
public class IpcServiceImpl implements IpcService {
    private static final Logger logger = LoggerFactory.getLogger(IpcServiceImpl.class);
    @Autowired
    private TbIpcMapper mTbIpcMapper;


    @Override
    public TbIpc getByName(String name) throws HException {
        try {
            TbIpcExample ue = new TbIpcExample();
            ue.createCriteria().andNameEqualTo(name);
            List<TbIpc> listIpc = mTbIpcMapper.selectByExample(ue);
            if (listIpc != null && listIpc.size() > 0) {
                TbIpc ret = listIpc.get(0);
                return ret;
            } else {
                logger.info("has no Ipc:" + name);
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new HException("没有当前名称的门岗");
        }
    }

    @Override
    public TbIpc getById(Integer id) throws HException {
        return mTbIpcMapper.selectByPrimaryKey(id);
    }


    public void delete(Integer id) {
        TbIpc ipc = mTbIpcMapper.selectByPrimaryKey(id);
        if (ipc != null) {
            mTbIpcMapper.deleteByPrimaryKey(id);
        }
    }

//	public TbIpc addOrUpdate(TbIpc Ipc){
//		Ipc.setCreateTime(new Date());
//		if(Ipc.getId() == null){
//			mTbIpcMapper.insert(Ipc);
//		}else{
//			TbIpc oldIpc = mTbIpcMapper.selectByPrimaryKey(Ipc.getId());
//			try{
//				TbIpc sameIpcname = getByName(oldIpc.getName());
//				if(sameIpcname != null){
//					throw new Exception("名称: " + Ipc.getName() + " 已存在");
//				}
//			}catch(Exception e){
//
//			}
//			mTbIpcMapper.updateByPrimaryKey(Ipc);
//		}
//
//
//		return Ipc;
//	}

//	public int getCount(TbIpc param){
//		TbIpcExample example = new TbIpcExample();
//		if(param != null){
//			TbIpcExample.Criteria c = example.createCriteria();
//			if(param.getName() != null && param.getName().length() > 0){
//				c.andNameLike("%" + param.getName() + "%");
//			}
//			if(param.getAddress() != null && param.getAddress().length() > 0){
//				c.andAddressLike("%" + param.getAddress() + "%");
//			}
//		}
//		return (int)(mTbIpcMapper.countByExample(example));
//	}

    public List<TbIpc> getAllIpc() {
        TbIpcExample example = new TbIpcExample();
        example.setOrderByClause("id desc");
        List<TbIpc> listData = mTbIpcMapper.selectByExample(example);
        return listData;
    }


    @Override
    public TbIpc getByAddress(String mac) {
        try {
            TbIpcExample ue = new TbIpcExample();
            ue.createCriteria().andAddressEqualTo(mac.trim());
            List<TbIpc> listIpc = mTbIpcMapper.selectByExample(ue);
            if (listIpc != null && listIpc.size() > 0) {
                TbIpc ret = listIpc.get(0);
                return ret;
            }
            logger.info("has no Ipc:address=" + mac);
            return null;

        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
//			throw new Exception("没有当前名称的门岗");
        }
    }
}
