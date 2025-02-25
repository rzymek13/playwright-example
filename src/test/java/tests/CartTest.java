package tests;

import base.BaseTest;
import com.microsoft.playwright.Page;
import io.qameta.allure.testng.Tag;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test()
    @Tag("second")
    public void addSmartwatchToCartTest() {
        homePage.open();
        homePage.acceptCookies();
        homePage.chooseSmartwatchesPage();
        smartwatchesPage.clickOnFirstProduct();
        productPage.clickAddToCartButton();
        cartPage.clickBackToHomeButton();
        page.get().waitForSelector(homePage.getNumberOfProductsInCart(), new Page.WaitForSelectorOptions().setTimeout(2000));
        Assert.assertEquals(page.get().locator(homePage.getNumberOfProductsInCart()).textContent(),"1");
    }
}
