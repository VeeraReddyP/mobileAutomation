package com.cognizantTechno.test.helpers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileReader;

public class DesiredCapabilityBuilder {

    DesiredCapabilities capabilities = new DesiredCapabilities();

    public DesiredCapabilityBuilder() {
    }

    public DesiredCapabilities getCapabilities() {
        return this.capabilities;
    }

    public void setCapabilities(DesiredCapabilities desiredCapabilties) {
        this.capabilities = desiredCapabilties;
    }


    public void setCapabilities(String platform) throws Exception {
        JSONParser jsonParser = new JSONParser();
        String fileName = System.getProperty("user.dir") + "/src/test/capabilities/capability.json";
        FileReader reader = new FileReader(fileName);
        JSONObject caps = (JSONObject) jsonParser.parse(reader);
        JSONObject capability = (JSONObject) caps.get(platform);
        capability.keySet().forEach((key) -> {
            capabilities.setCapability(key.toString(), capability.get(key));
        });
    }

    public void buildCapabilities() throws Exception {
        this.setCapabilities("android");
    }
}

