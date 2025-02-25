package base;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.SmartwatchesPage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {
    protected ThreadLocal<Playwright> playwright = ThreadLocal.withInitial(Playwright::create);
    protected ThreadLocal<Browser> browser = new ThreadLocal<>();
    protected ThreadLocal<Page> page = new ThreadLocal<>();
    protected HomePage homePage;
    protected SmartwatchesPage smartwatchesPage;
    protected ProductPage productPage;
    protected CartPage cartPage;
    private ThreadLocal<BrowserContext> context = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() {
        Playwright pw = playwright.get();
        browser.set(pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));

        context.set(browser.get().newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080)));

        context.get().tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        page.set(context.get().newPage());

        homePage = new HomePage(page.get());
        smartwatchesPage = new SmartwatchesPage(page.get());
        productPage = new ProductPage(page.get());
        cartPage = new CartPage(page.get());
    }
    @AfterMethod()
    public void clearCookiesAndCache() {context.get().clearCookies();}

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
            String formattedDateTime = formatter.format(currentDateTime);

            String resultName = result.getName() + "_" + "_" + formattedDateTime;
            Path screenshotPath = Paths.get("./target/screenshots/" + resultName + ".png");

            page.get().screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));
            attachScreenshotToAllureReport(resultName, screenshotPath);
            context.get().tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));

        }
        context.get().close();
        browser.get().close();
    }

    @Attachment(type = "image/png")
    private void attachScreenshotToAllureReport(String resultName, Path screenshotPath) throws IOException {
        Allure.addAttachment(resultName, new ByteArrayInputStream(Files.readAllBytes(screenshotPath)));
    }
}
