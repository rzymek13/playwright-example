package pages;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ProductPage {


    public static String productPrice;
    public static String productFee;
    private final Page page;
    private final String productPriceOnProductPage = "(//*[text()=\"Do zapłaty na start\"]/following::div[contains(text(),'zł')])[3]";
    private final String feeOnProductPage = "(//*[text()=\"Do zapłaty miesięcznie\"]/following::div[contains(text(),'zł')])[3]";
    private final String productLabel = "//*[@data-qa=\"PRD_ProductName\"]";
    private final String addToCartButton = "(//button[@data-qa=\"PRD_AddToBasket\"])[2]";

    public ProductPage(Page page) {
        this.page = page;
    }
    public void clickAddToCartButton(){
        page.click(addToCartButton);
    }

    public void captureProductPrice() {
        productPrice = page.locator(productPriceOnProductPage).textContent().replaceAll("[^\\d.]", "");
        log.info("Captured product price on product page: " + productPrice);
    }

    public void captureFee() {
        productFee = page.locator(feeOnProductPage).innerText().replaceAll("[^\\d.]", "");
        log.info("Captured fee on product page: " + productFee);
    }
}
