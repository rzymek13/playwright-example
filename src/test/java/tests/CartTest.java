package tests;

import base.BaseTest;
import com.microsoft.playwright.Page;
import io.qameta.allure.testng.Tag;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigLoader;

public class CartTest extends BaseTest {

    @Test()
    @Tag("second")
    public void addSmartwatchToCartTest() throws InterruptedException {

        homePage.open();
        homePage.acceptCookies();
        homePage.chooseSmartwatchesPage();
        smartwatchesPage.clickOnFirstProduct();
        productPage.clickAddToCartButton();
        cartPage.clickBackToHomeButton();
        page.get().waitForSelector(homePage.getNumberOfProductsInCart(), new Page.WaitForSelectorOptions().setTimeout(2000));
        ConfigLoader.getAllProperties();
        Assert.assertEquals(page.get().locator(homePage.getNumberOfProductsInCart()).textContent(),"1");
    }
}
