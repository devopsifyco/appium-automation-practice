package Base;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.annotation.Nullable;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;




public class BaseTest2 {
    private static final String APP_ANDROID = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";
    private static final String APP_IOS = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.app.zip";
    private static final String APPIUM = "http://localhost:4723/wd/hub";
    private static final String DEVICE_ID = "bb19ac3a";


    protected AppiumDriver driver;
    protected WebDriverWait wait;



    private void setUpAndroid() throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "12");
        caps.setCapability("deviceName", "OPPO Reno4");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("ignoreHiddenApiPolicyError" , true);
        caps.setCapability("app", APP_ANDROID);
        caps.setCapability("udid", DEVICE_ID);

        driver = new AndroidDriver(new URL(APPIUM), caps);

    }

    private void setUpIOS() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "12.0");
        caps.setCapability("deviceName", "iPhone X");
        caps.setCapability("app", APP_IOS);
        driver = new IOSDriver(new URL(APPIUM), caps);
    }

    @BeforeTest
    public void setUp() throws Exception {
        setUpAndroid();
        //setUpIOS();
        driver.manage().timeouts().implicitlyWait (Duration.ofSeconds(3));
        wait= new WebDriverWait(driver, Duration.ofSeconds(20));
    }

  //  @AfterTest
    public void tearDown() {
        if (driver != null) {
        }
            driver.quit();
        }

    @Nullable
    protected String getWebContext(AppiumDriver driver) {
        //driver.getContextHandles() //not support -> it is replace by (SupportsContextSwitching) driver).getContextHandles()
        ArrayList<String> contexts = new ArrayList(((SupportsContextSwitching) driver).getContextHandles());
        for (String context : contexts) {
            if (!context.equals("NATIVE_APP")) {

                return context;
            }
        }
        return null;
    }

}
