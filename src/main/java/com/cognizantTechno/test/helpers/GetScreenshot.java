package com.cognizantTechno.test.helpers;

import com.cognizantTechno.test.driverUtils.DriverManager;
import net.serenitybdd.core.Serenity;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileOutputStream;

public class GetScreenshot implements MethodRule {
    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } catch (Throwable t) {
                    captureScreenshot(frameworkMethod.getName());
                    throw t;
                }
            }

            public void captureScreenshot(String fileName) {
                try {
                    new File("target/site/serenity/").mkdirs();
                    FileOutputStream out = new FileOutputStream("target/site/serenity/screenshot-" + fileName + ".png");
                    out.write(((TakesScreenshot) DriverManager.getAppiumDriver()).getScreenshotAs(OutputType.BYTES));
                    out.close();
                    File file = new File("target/site/serenity/screenshot-" + fileName + ".png");
                    Serenity.recordReportData().withTitle("FailureScreenShot").downloadable().fromFile(file.toPath());
                } catch (Exception e) {

                }
            }
        };
    }
}
