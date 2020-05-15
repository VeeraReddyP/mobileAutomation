package com.cognizantTechno.test.testScripts;

import com.cognizantTechno.test.data.TestData;
import com.cognizantTechno.test.helpers.ConfigReader;
import com.cognizantTechno.test.pages.LoginPage;
import com.cognizantTechno.test.pages.SearchPage;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class EbayTests extends BaseTestRun {

    @Steps
    SearchPage searchPage;
    @Steps
    LoginPage loginPage;
    Random random = new Random();
    private int searchItems;

    @Test
    public void tc1_SearchProduct() {

        loginPage.login(ConfigReader.getProperty("uname"),ConfigReader.getProperty("pwd"));
        searchPage.clickSearch();
        searchPage.enterSearchItem(TestData.searchItem);
        searchPage.clickSuggested();
        searchItems = searchPage.getItemNames();
        Assert.assertTrue(searchItems > 0);
        int randomNum = random.ints(searchItems - 1, searchItems).limit(1).findFirst().getAsInt();
        searchPage.clickRandomItemInList(randomNum);
        searchPage.clickAddToCart();
        String productValue[]= searchPage.getPrice().split("$");
        Assert.assertTrue(Integer.valueOf(productValue[1])!=0);
        Assert.assertTrue(!searchPage.getProduct().isEmpty());

    }

}
