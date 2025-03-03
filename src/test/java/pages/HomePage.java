package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import lombok.Getter;

public class HomePage {
    private final Page page;
    private final String coockieAcceptButton = "#didomi-notice-agree-button";
    private final String devicesDropdownMenuButton = "//button[text()=\"Urządzenia\"]";
    private final String smartwatchItemInDevicesDropdownMenu = "//*[@data-ga-ea=\"nav-links - Urządzenia/Bez abonamentu/Smartwatche\"]";
    @Getter private final String numberOfProductsInCart = "//div[@class=\"ml-auto flex lg:mt-auto group-[.shrank-header]/header:lg:mt-0\"]//a[@data-ma=\"menu-basket\"]//div";

    public HomePage(Page page) {
        this.page = page;
    }

    public void open(){
        page.navigate("https://www.t-mobile.pl");
    }

    public void acceptCookies() {
            try {
                page.waitForSelector(coockieAcceptButton, new Page.WaitForSelectorOptions()
                        .setTimeout(10000)
                        .setState(WaitForSelectorState.VISIBLE));

                page.click("#didomi-notice-agree-button");
                System.out.println("cookies accepted");
            } catch (Exception e) {
                System.out.println("cookies not found or accepted" + e.getMessage());
            }
    }
    public void chooseSmartwatchesPage() {
        page.locator(devicesDropdownMenuButton).click();
        page.locator(smartwatchItemInDevicesDropdownMenu).click();
    }

}
