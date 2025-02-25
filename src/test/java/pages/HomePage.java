package pages;

import com.microsoft.playwright.Page;
import lombok.Getter;

public class HomePage {
    private final Page page;
    private final String coockieAcceptButton = "#didomi-notice-agree-button";
    private final String devicesDropdownMenuButton = "//button[text()=\"Urządzenia\"]";
    private final String smartwatchItemInDevicesDropdownMenu = "//*[@data-ga-ea=\"nav-links - Urządzenia/Bez abonamentu/Smartwatche\"]";
    @Getter
    private final String numberOfProductsInCart = "//div[@class=\"ml-auto flex lg:mt-auto group-[.shrank-header]/header:lg:mt-0\"]//a[@data-ma=\"menu-basket\"]//div";

    public HomePage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate("https://www.t-mobile.pl");
    }

    public void acceptCookies() {
        if (page.isVisible(coockieAcceptButton)) {
        page.locator(coockieAcceptButton).click();
        } else {
            System.out.println("There are no cookies on the website.");
        }
    }
    public void chooseSmartwatchesPage() {
        page.locator(devicesDropdownMenuButton).click();
        page.locator(smartwatchItemInDevicesDropdownMenu).click();
    }

}
