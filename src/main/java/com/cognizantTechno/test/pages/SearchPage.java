package com.cognizantTechno.test.pages;


import com.cognizantTechno.test.commonUtils.AppiumActions;
import com.cognizantTechno.test.data.TestData;
import com.cognizantTechno.test.driverUtils.DriverManager;
import io.appium.java_client.MobileElement;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

import java.util.List;

public class SearchPage extends AppiumActions {


    private By search = By.id("search_box");
    private By searchItem =  By.xpath("//*[contains(@text,'Search for anything')]");
    private By suggestionItem = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.TextView");
    private By listSearchItems = By.xpath("//androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout");
    private By upArrow = By.id("up_arrow");
    public By listItem(int number){
        return By.xpath("//androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout["+number+"]");
    }
    private  By price = By.id("textview_item_price");
    private  By productName = By.id("textview_item_price");
    private By okBtn = By.xpath("//*[contains(@text,'OK')]");
    private By buyingOptions = By.xpath("//android.widget.Button[@content-desc='Buying options']");


    @Step
    public void clickSearch(){
        waitForVisible(search, TestData.timeout);
        tap(search);
    }

    @Step
    public void enterSearchItem(String itemName){
        waitForVisible(searchItem,TestData.timeout);
        type(searchItem,itemName);
    }

    @Step
    public void clickSuggested(){
        waitForVisible(suggestionItem,TestData.timeout);
        tap(suggestionItem);
    }

    @Step
    public int getItemNames(){
        waitForVisible(upArrow,TestData.timeout);
        tap(upArrow);
        List<MobileElement> searchItems = DriverManager.getAppiumDriver().findElements(listSearchItems);
        return searchItems.size();
    }

    @Step
    public void clickRandomItemInList(int randomNumber){
        waitForVisible(listItem(randomNumber),TestData.timeout);
        tap(listItem(randomNumber));
    }

    @Step
    public void clickAddToCart(){
        waitForVisible(buyingOptions,TestData.timeout);
        tap(buyingOptions);
        waitForVisible(okBtn,TestData.timeout);
        tap(okBtn);
    }

    @Step
    public String getPrice(){
        waitForVisible(price,TestData.timeout);
        return getText(price);
    }

    @Step
    public String getProduct(){
        waitForVisible(productName,TestData.timeout);
        return getText(productName);
    }
}
