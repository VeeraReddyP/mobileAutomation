package com.cognizantTechno.test.pages;

import com.cognizantTechno.test.commonUtils.AppiumActions;
import com.cognizantTechno.test.data.TestData;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

public class LoginPage extends AppiumActions {

    private By signIn = By.id("button_sign_in");
    private By username = By.id("edit_text_username");
    private By password = By.xpath("//*[contains(@text,'Password')]");
    private  By mayBeBtn = By.id("bt_maybe_later");

    @Step
    public void login(String userName, String passWord) {
        waitForVisible(signIn, TestData.timeout);
        tap(signIn);
        waitForVisible(username, TestData.timeout);
        type(username, userName);
        type(password, passWord);
        tap(signIn);
        waitForVisible(mayBeBtn, TestData.timeout);
        tap(mayBeBtn);
    }
}
