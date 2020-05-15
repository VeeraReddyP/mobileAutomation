package com.cognizantTechno.test.commonUtils;

import com.cognizantTechno.test.driverUtils.DriverManager;
import com.google.common.base.Function;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AppiumActions {
    private final static Logger LOGGER = LoggerFactory.getLogger(AppiumActions.class);

    public MobileElement waitForEnabled(By locator, long timeInSec) {
        Wait<By> wait = (new FluentWait(locator)).withTimeout(Duration.ofSeconds(timeInSec)).pollingEvery(Duration.ofMillis(1000L)).ignoring(WebDriverException.class).withMessage("'" + locator + "' locator is not visible.");
        wait.until(new Function<By, Boolean>() {
            public Boolean apply(By locator) {
                return ((MobileElement) DriverManager.getAppiumDriver().findElement(locator)).isEnabled();
            }
        });
        return (MobileElement) DriverManager.getAppiumDriver().findElement(locator);
    }
    public MobileElement waitForEnabled(By locator) {
        return this.waitForEnabled(locator, 10L);
    }

    public MobileElement waitForVisible(By locator, long timeInSec) {
        Wait<By> wait = (new FluentWait(locator)).withTimeout(Duration.ofSeconds(timeInSec)).pollingEvery(Duration.ofMillis(1000L)).ignoring(WebDriverException.class).withMessage("'" + locator + "' locator is not visible.");
        wait.until(new Function<By, Boolean>() {
            public Boolean apply(By locator) {
                return ((MobileElement)DriverManager.getAppiumDriver().findElement(locator)).isDisplayed();
            }
        });
        return (MobileElement)DriverManager.getAppiumDriver().findElement(locator);
    }

    public void waitForVisible(WebElement element, long timeInSec) {
        (new FluentWait(element)).withTimeout(Duration.ofSeconds(timeInSec)).pollingEvery(Duration.ofMillis(1000L)).ignoring(WebDriverException.class).withMessage(element + "' element is not visible.").until(new Function<MobileElement, Boolean>() {
            public Boolean apply(MobileElement element) {
                return element.isDisplayed();
            }
        });
    }

    public MobileElement waitForVisible(By locator) {
        return this.waitForVisible(locator, 10L);
    }

    public void tap(By locator) {
        waitForVisible(locator).click();
    }

    public void tap(WebElement element) {
        this.waitForVisible(element,10l);
        element.click();
    }

    public String getText(By locator) {
        MobileElement element = this.waitForVisible(locator);
        return element.getText();
    }

    public void type(By locator, String text) {
        //this.waitForVisible(locator).clear();
        this.waitForVisible(locator).sendKeys(text);
    }

    public void setValue(By locator, String text) {
        //this.waitForVisible(locator).clear();
        this.waitForVisible(locator).setValue(text);
    }

    public void scrollUpToText(MobileElement fromElement, String text) {
        Integer startX = fromElement.getLocation().x;
        Integer startY = fromElement.getLocation().y;
        String currText = fromElement.getText();
        if (currText.equals(text))
            return;
        while (Integer.valueOf(currText) > Integer.valueOf(text)) {
            currText = fromElement.getText();
            if (currText.equals(text))
                break;
            scrollUp(startX, startY, 15, 120);

        }
    }

    public void scrollUp(Integer startX, Integer startY, int yoffset, Integer duration) {
        Integer endX = startX;
        Integer endY = startY - yoffset;
        this.scroll(startX, startY, endX, endY, duration);
    }

    protected void scroll(Integer startX, Integer startY, Integer endX, Integer endY, Integer duration) {
        (new TouchAction(DriverManager.getAppiumDriver())).press((new PointOption()).withCoordinates(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis((long)duration))).moveTo((new PointOption()).withCoordinates(endX, endY)).release().perform();
    }

    public void wait_for_while(long timeout){
        DriverManager.getAppiumDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }
}
