package tests;

import base.BaseTest;
import io.qameta.allure.testng.Tag;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductPage;

public class HomeTest extends BaseTest {

    @Test()
    @Tag("first")
    public void verifyPricesAreTheSameAfterAddingProductToCart() {
        homePage.open();
        homePage.acceptCookies();
        homePage.chooseSmartwatchesPage();
        smartwatchesPage.clickOnFirstProduct();
        productPage.captureProductPrice();
        productPage.captureFee();
        productPage.clickAddToCartButton();
        cartPage.captureCartPrice();
        cartPage.captureCartFee();
        Assert.assertEquals(ProductPage.productPrice, CartPage.cartPrice);
        Assert.assertEquals(CartPage.cartFee, ProductPage.productFee);
    }
}
