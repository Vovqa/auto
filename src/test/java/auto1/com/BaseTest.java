package auto1.com;

import com.codeborne.selenide.Configuration;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext testContext) {
        configureSelenide();

    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite(ITestContext testContext) {
    }

    private void configureSelenide() {
        Configuration.browser = "chrome";
        Configuration.screenshots = false;
        Configuration.timeout = 15000;
        Configuration.collectionsTimeout = 12000;

    }
}
