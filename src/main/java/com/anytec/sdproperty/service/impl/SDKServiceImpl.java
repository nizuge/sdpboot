package com.anytec.sdproperty.service.impl;


import com.anytec.sdproperty.service.inf.SDKService;
import com.anytec.sdproperty.service.inf.SettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("SDKService")
public class SDKServiceImpl implements SDKService {
    private static final Logger logger = LoggerFactory.getLogger(SDKServiceImpl.class);
    @Autowired
    private SettingService mSettingService;

    /*
    添加人脸特征（face）
    Uri：/v0/face POST
    */
    public Map<String, Object> addFace(String image) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
//
//        try {
//            logger.info("addFace:" + image);
//            String urlRoot = getSDKRootUrl();
//            if (HTextUtils.isEmpty(urlRoot)) {
//                return null;
//            }
////            String url = urlRoot + "/v0/face";
//            String url = "http://"+Constant.SDK_IP + ":8000/v0/face";
//
//            JSONObject obj = new JSONObject();
//            obj.put("photo", image);
//
//            HttpResponse response = Request.Post(url)
//                    .connectTimeout(10000)
//                    .socketTimeout(30000)
//                    .addHeader("Authorization", "Token " + Constant.TOKEN)//Token 4BBj-6pjv
//                    .body(MultipartEntityBuilder
//                            .create()
////                            .addTextBody("mf_selector", "all")
//                            .addTextBody("photo", image)
//                            .build())
//                    .execute().returnResponse();
//            String result= EntityUtils.toString(response.getEntity());
//            logger.info(result);
////            ZtHttpUtilResult result = ZtHttpUtil.sendHttpRequest(url, obj.toJSONString(), HttpMethod.POST);
//            if (result != null) {
////                logger.info(result.content);
//                if (response.getStatusLine().getStatusCode() == 200) {
//                    JSONObject outObj = JSON.parseObject(result);
//                    JSONArray array = outObj.getJSONArray("results");
//                    JSONObject jsonObj = array.getJSONObject(0);
//                    String image1 = jsonObj.getString("normalized");
//                    String code = jsonObj.getString("person_id");
//                    map.put("image", image1);
//                    map.put("code", code);
//                    logger.info("image=" + image + "  code=" + code);
//                }
//            }
////            if (result != null) {
////                logger.info(result.content);
////                if (result.code == KConst.RESULT_OK) {
////                    JSONObject outObj = JSON.parseObject(result.content);
////                    JSONArray array = outObj.getJSONArray("results");
////                    JSONObject jsonObj = array.getJSONObject(0);
////                    String image1 = jsonObj.getString("normalized");
////                    String code = jsonObj.getString("person_id");
////                    map.put("image", image1);
////                    map.put("code", code);
////                    logger.info("image=" + image + "  code=" + code);
////                }
////            }
//        }catch (Exception e){
//            logger.error(e.getMessage());
//        }

        return map;
    }

    @Override
    public boolean updateSet(String keyname, String value) {
        return false;
    }

//


    /**
     *
     * @param ip
     * @param port
     * @param line 第几录
     * @param time 持续几秒
     */
    public void flashOpenDoor(String ip, Integer port, Integer line, Integer time){
        logger.info("flashOpenDoor1：");
        Runnable r = new OpenDoorThread(ip, port, line, time);
        Thread t = new Thread(r);
        t.start();
        logger.info("flashOpenDoor2：" );
    }

    public  synchronized  void flashOpenDoorThreadFun(String ip, Integer port, Integer line, Integer time){
        try {
            logger.info("flashOpenDoorThreadFun 2：" );
//            String urlRoot = getSDKRootUrl();
//            if (HTextUtils.isEmpty(urlRoot)) {
//                logger.warn("flashOpenDoorThreadFun 33：" );
//                return ;
//            }
//            String url = urlRoot + "/switch/on_off_ex";
//            String url =  Constant.SWITCH_IP_PORT;
//            JSONObject obj = new JSONObject();
//            obj.put("ip", ip);
//            obj.put("port", port);
//            obj.put("line", line);
//            obj.put("on_off", 1);
//            obj.put("time", time);

//					String url = "http://192.168.10.208:8888/";

            logger.info("flashOpenDoorThreadFun: " + ip + " port:" + port + " line:" + line);
//            HttpResponse response = Request.Post(Constant.SWITCH_IP_PORT)
//                    .connectTimeout(10000)
//                    .socketTimeout(30000)
////							.addHeader("Authorization", "Token " + ntechToken)
//                    .body(MultipartEntityBuilder
//                            .create()
//                            .addTextBody("ip", ip)
//                            .addTextBody("port", String.valueOf(port))
//                            .addTextBody("line", String.valueOf(line))
//                            .addTextBody("on_off", "1")
//                            .addTextBody("time", String.valueOf(time))
//                            .build())
//                    .execute().returnResponse();


//            ZtHttpUtilResult result = ZtHttpUtil.sendHttpRequest(url, obj.toJSONString(), HttpMethod.POST);
//            logger.info("flashOpenDoorThreadFun result:" + response.getStatusLine().getStatusCode()+ "|content=" + response.getEntity().toString());
        }catch (Exception e){
            logger.error(e.getMessage());
            return ;
        }
    }

    private class OpenDoorThread implements Runnable {
        public String ip;
        public Integer port;
        public Integer line;
        public Integer time;

        public OpenDoorThread(String ip, Integer port, Integer line, Integer time){
            this.ip = ip;
            this.port = port;
            this.line = line;
            this.time = time;
        }

        @Override
        public void run() {
            logger.info("OpenDoorThread 11：" + Thread.currentThread().getName());
            flashOpenDoorThreadFun(ip, port, line, time);
            logger.info("OpenDoorThread 22：" + Thread.currentThread().getName());
        }
    }
}
