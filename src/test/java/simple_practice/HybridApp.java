package simple_practice;

import Base.BaseTest1;
import Base.BaseTest2;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class HybridApp extends BaseTest2 {
    private static final String APPIUM_PRO = "https://appiumpro.com";
    private static final String OTHER_SITE = "test"; //"https://cloudgrey.io";
    private static final By WEBVIEW_PAGE = AppiumBy.accessibilityId("Webview Demo"); // MobileBy.AccessibilityId("Webview Demo");
    private static final By URL_FIELD = AppiumBy.accessibilityId("urlInput");
    //By.xpath("//XCUIElementTypeTextField[@name=\"urlInput\"]");
    private static final By GO_BUTTON = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"navigateBtn\"]/android.widget.TextView");
    //AppiumBy.accessibilityId("navigateBtn"); // MobileBy.AccessibilityId("navigateBtn");
    @Test
    public void testHybridApp() throws InterruptedException {
        // 1. Navigate to the webview page
        driver.findElement(WEBVIEW_PAGE).click(); //webview-demo

        // 2. Attempt to navigate to an incorrect site
        driver.findElement(URL_FIELD).sendKeys(OTHER_SITE);
        driver.findElement(GO_BUTTON).click();

        // 3. Assert that an error message pops up
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        assert alert.getText().contains("Sorry");
        alert.accept();

        // 4. assert that the webview did not actually go anywhere
        System.out.println(getWebContext(driver));
        ((SupportsContextSwitching) driver).context(getWebContext(driver));
        //driver.context(getWebContext(driver));
        String bodyText = driver.findElement(By.xpath("/html/body/center/h1")).getText();
        assert bodyText.equals("Please navigate to a webpage");

        // 5. attempt to navigate to the correct site
        ((SupportsContextSwitching) driver).context("NATIVE_APP");
        WebElement urlField = driver.findElement(URL_FIELD);
        urlField.clear();
        urlField.sendKeys(APPIUM_PRO);
        driver.findElement(GO_BUTTON).click();

        // 6. assert that the webview went to the right place
        ((SupportsContextSwitching) driver).context(getWebContext(driver));
        wait.until(ExpectedConditions.titleContains("Appium Pro"));


        WebElement e = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("toggleMenu")));
        System.out.println(e.getLocation().getX()+" "+ e.getLocation().getY());
        // e.click();

    }
}
