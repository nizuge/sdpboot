package com.anytec.sdproperty.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GeneralConfig {

    //FindFace SDK
    @Value("${sdk.host_ip}")
    private String hostIp;

    @Value("${sdk.port}")
    private short sdkPort;

    @Value("${sdk.token}")
    private String token;

    @Value("${sdk.version}")
    private String sdkVersion;

    //图片流控制
    @Value("${data.threshold}")
    private int dataThreshold;

    @Value("${data.thread}")
    private int dataThread;

    @Value("${data.draw}")
    private int dataDraw;
    //识别控制
    @Value("${identify.type}")
    private String identifyType;

    @Value("${identify.size}")
    private int identifySize;

    //阔展摄像头分辨率
    @Value("${camera.resolution.width}")
    private int cameraWidth;
    @Value("${camera.resolution.height}")
    private int cameraHeight;

    //本地存储路径
    @Value("${data.storage}")
    private String dataStorage;

    @Value("${door.switch_address}")
    private String switchAddress;

    @Value("{door.type}")
    private String doorType;


    public String getDataStorage() {
        return dataStorage;
    }

    public String getHostIp() {
        return hostIp;
    }

    public short getSdkPort() {
        return sdkPort;
    }

    public String getToken() {
        return token;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public int getDataThreshold() {
        return dataThreshold;
    }

    public int getDataThread() {
        return dataThread;
    }

    public int getDataDraw() {
        return dataDraw;
    }

    public String getIdentifyType() {
        return identifyType;
    }

    public int getIdentifySize() {
        return identifySize;
    }

    public String getSwitchAddress() {
        return switchAddress;
    }

    public String getDoorType() {
        return doorType;
    }

    public int getCameraWidth() {
        return cameraWidth;
    }

    public int getCameraHeight() {
        return cameraHeight;
    }
}
