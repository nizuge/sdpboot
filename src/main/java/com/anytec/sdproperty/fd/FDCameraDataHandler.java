package com.anytec.sdproperty.fd;

import com.anytec.sdproperty.config.GeneralConfig;
import com.anytec.sdproperty.pojo.FDCameraData;
import com.anytec.sdproperty.service.inf.BusinessService;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Scope("singleton")
public class FDCameraDataHandler {
    private static final Logger logger = LoggerFactory.getLogger(FDCameraDataHandler.class);

    private ExecutorService faceReconPool = null;

    @Autowired
    private GeneralConfig config;

    ThreadLocal<Integer> counts = new ThreadLocal<>();
    @Autowired
    private BusinessService businessService;

    public void init() {
        faceReconPool = Executors.newFixedThreadPool(config.getDataThread(), (runnable)-> {
            Thread thread = new Thread(runnable);
            thread.setPriority(5);
            thread.setDaemon(true);
            return thread;
        });
    }


    public void onCameraData(FDCameraData data) {

        Integer count = counts.get();
        if (count == null) {
            count = 1;
        } else {
            count++;
        }
        counts.set(count);

        if ((count % config.getDataThreshold()) == 0) {
            logger.debug("Put to pool -" + data.toString());
            counts.set(0);
            //faceReconPool.execute(new FDThread(data.mJpgData, data.mStrMac,businessService));
            faceReconPool.execute(()->
                    businessService.analyse(data.mJpgData,data.mStrMac)
            );
        }
    }


}

