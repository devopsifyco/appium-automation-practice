import com.google.common.annotations.VisibleForTesting;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;

public class Mobile_Web {
    private static final  String APPIUM =  "http://localhost:4723/wd/hub";;
    private static final String DEVICE_ID = "bb19ac3a";
    private static AndroidDriver driver;


    public static void main (String[] args) throws Exception {
      /*  DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9");
        caps.setCapability("deviceName", "Appium");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("browserName", "Chrome");*/

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "12");
        capabilities.setCapability("deviceName", "OPPO Reno4");
        capabilities.setCapability("automationName", "UiAutomator2");
        //capabilities.setCapability("appWaitForLaunch ", false);
       // capabilities.setCapability("autoGrantPermissions ", false);
        capabilities.setCapability("ignoreHiddenApiPolicyError" , true);
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("udid", DEVICE_ID);

        driver = new AndroidDriver(new URL(APPIUM), capabilities);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://arras.io");

        /*WebElement  e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='toggleMenu']")));
        System.out.println(e.getLocation().getX() + " "+ e.getLocation().getX());
        e.click();*/

      /*  Actions actions = new Actions(driver);
        actions.moveToElement(e);
        actions.click();
        actions.perform();*/
        //e.click();
     /*   JavascriptExecutor js = driver;
        js.executeScript("arguments[0].click();",e);*/


       /* driver.findElement(By.linkText("All Editions")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".editionList")));*/
    //    tearDown();
    }


    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
