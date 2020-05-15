package com.cognizantTechno.test.driverUtils;


import com.cognizantTechno.test.helpers.CommandLineExecutor;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class AppiumManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppiumManager.class);
    private ThreadLocal<AppiumDriverLocalService> service = new ThreadLocal();
    static String host = "127.0.0.1";
    static int port = 4723;

    public AppiumManager() {
    }

    public String getHubURL() {
        String hub = ((AppiumDriverLocalService)this.service.get()).getUrl().toString();
        System.out.println("::::::url"+hub);
        return hub;
    }

    public void startAppiumServer() {
        this.service.set(AppiumDriverLocalService.buildService((new AppiumServiceBuilder()).withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js")).withIPAddress(host).usingPort(port).withArgument(GeneralServerFlag.SESSION_OVERRIDE)));
        ((AppiumDriverLocalService)this.service.get()).start();
        if (this.service.get() == null || !((AppiumDriverLocalService)this.service.get()).isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
        }
    }

    public void stopAppiumServer() {
        if (((AppiumDriverLocalService)this.service.get()).isRunning() || this.service.get() != null) {
            LOGGER.info("Stopping appium server: " + this.getHubURL());
            ((AppiumDriverLocalService)this.service.get()).stop();
        }

    }

    public void stopAppiumServerFromCmd() {
        String cmd = "lsof -n -i:" + port + " | grep LISTEN | awk  '{print $2}'";
        String pid = CommandLineExecutor.execute(cmd, 1);
        if (pid != null) {
            CommandLineExecutor.execute("kill -9 " + pid, 1);
        }

    }
}
