package com.cognizantTechno.test.driverUtils;




import com.cognizantTechno.test.helpers.DesiredCapabilityBuilder;
import com.cognizantTechno.test.helpers.GetScreenshot;
import org.junit.Rule;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestInitializer.class);


    public AppiumManager appiumManager = new AppiumManager();



    @Rule
    public GetScreenshot getScreenshot = new GetScreenshot();


    public void beforeClass() throws Exception {
        appiumManager.startAppiumServer();
        DesiredCapabilityBuilder capBuilder = new DesiredCapabilityBuilder();
        capBuilder.buildCapabilities();
        DesiredCapabilities caps = capBuilder.getCapabilities();
        DriverManager.createDriverInstances("http://127.0.0.1:4723/wd/hub", caps);
    }


    public void afterClass() {
        DriverManager.stopDriverInstances();
        //appiumManager.stopAppiumServer();
        appiumManager.stopAppiumServerFromCmd();
    }
}
