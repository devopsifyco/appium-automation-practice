import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

public class touch_android {
    //private static AppiumDriver driver;
    private static AndroidDriver driver;
   // private static final String APP = "D:\\Devopsify\\NativeApp_Testing\\proverbial_android.apk";
   private static final String APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";
    private static final String APPIUM =  "http://localhost:4723/wd/hub";

    public static void main(String args[]) throws Exception {
        /*MalformedURLException, InterruptedException*/


        try {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion", "9");
            capabilities.setCapability("deviceName", "Appium");
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("app", APP);     //Enter your app url

            driver = new AndroidDriver(new URL(APPIUM), capabilities);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement eleDemo =  wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            MobileBy.accessibilityId("List Demo")));
            eleDemo.click();

            /*WebElement eleAltostratus =*/
            wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.accessibilityId("Altostratus")));

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
            Interaction moveToStart = finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),520,1530);
            Interaction pressDown = finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
            Interaction moveToEnd = finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 520,490);
            Interaction pressUp = finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());

            Sequence swipe = new Sequence(finger, 0);
            swipe.addAction(moveToStart);
            swipe.addAction(pressDown);
            swipe.addAction(moveToEnd);
            swipe.addAction(pressUp);

            driver.perform(Arrays.asList(swipe));

            driver.findElement(MobileBy.accessibilityId("Stratus"));

        } catch (AssertionError a) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
            a.printStackTrace();
        }
// The driver.quit statement is required, otherwise the test continues to execute, leading to a timeout.
        driver.quit();
    }
}
