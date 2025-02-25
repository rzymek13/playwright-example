package pages;

import com.microsoft.playwright.Page;

public class SmartwatchesPage{
    private final Page page;
    private final String firstProduct = "//*[@data-qa='LST_ProductCard0']";

    public SmartwatchesPage(Page page) {
        this.page = page;
    }

    public void clickOnFirstProduct(){
        page.click(firstProduct);
    }


}
