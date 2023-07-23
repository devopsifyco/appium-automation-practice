package simple_practice;

import Base.BaseTest1;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class firstTest extends BaseTest1 {
    @Test
    public void login(){
        WebElement eleScreen =  wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        MobileBy.accessibilityId("Login Screen")));
        eleScreen.click();

        WebElement username = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        MobileBy.accessibilityId("username")));
        username.sendKeys("alice");

        WebElement psw = driver.findElement(MobileBy.accessibilityId("password"));
        psw.sendKeys("mypassword");

        WebElement loginbtn = driver.findElement(MobileBy.accessibilityId("loginBtn"));
        loginbtn.click();

        WebElement loginText = wait.until(ExpectedConditions.presenceOfElementLocated(
                MobileBy.xpath("//android.widget.TextView[contains(@text, 'You are logged in')]")));

        Assert.assertTrue(loginText.getText().contains("alice"));
    }
}
