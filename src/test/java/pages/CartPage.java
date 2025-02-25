package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CartPage  {
    private final Page page;
    public static String cartPrice;
    public static String cartFee;
    private final String continueButton = "(//button[@data-qa=\"PRD_AddToBasket\"])[2]";
    private final String productPriceOnCart = "//*[@data-qa=\"BKT_TotalupFront\"]";
    private final String feeOnCart = "//*[@data-qa=\"BKT_TotalMonthly\"]";

    public CartPage(Page page) {
        this.page = page;
    }

    public void captureCartPrice() {
        cartPrice = page.locator(productPriceOnCart).innerText().replaceAll("[^\\d.]", "");
    }

    public void captureCartFee() {
        cartFee = page.locator(feeOnCart).innerText().replaceAll("[^\\d.]", "");
    }
    public void clickBackToHomeButton(){
        page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("T-Mobile - przejdź na stronę")).click();
    }
    public void clickOnAddToCartButton(){
        page.click(continueButton);
    }
}
