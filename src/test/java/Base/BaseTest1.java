package Base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest1 {

    protected static AppiumDriver driver;
    protected WebDriverWait wait;
    // private static final String APP = "D:\\Devopsify\\NativeApp_Testing\\proverbial_android.apk";
    private static final String APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";
    private static final String APPIUM =  "http://localhost:4723/wd/hub";
    private static final String SITE = "https://appiumpro.com";


    @Parameters({"appType"})
    @BeforeTest
    public void setUp(String appType) throws MalformedURLException {
        try{
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion", "9");
            capabilities.setCapability("deviceName", "Appium");
            capabilities.setCapability("automationName", "UiAutomator2");
            switch (appType.toUpperCase()){
                case "NATIVE":
                    capabilities.setCapability("app", APP);     //Enter your app url
                    driver = new AndroidDriver(new URL(APPIUM), capabilities);

                    break;
                case "WEB":
                    capabilities.setCapability("browserName", "Chrome");
                    driver = new AndroidDriver(new URL(APPIUM), capabilities);
                    driver.get(SITE);
                    break;
                // case:
                default:
                    System.out.println("incorrect app type. Please input corectly app type");
            }

            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Thread.sleep(1000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @AfterTest
    public void tearDown(){
        if (driver!=null){
            driver.quit();
        }
    }
}
