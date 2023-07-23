package simple_practice;

import Base.BaseTest1;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class Touch_android extends BaseTest1 {
    @Test
    public void testAndroid(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#toggleMenu"))).click();
        //System.out.println(e.getLocation().getX() + " "+ e.getLocation().getX());
        driver.findElement(By.linkText("Contact")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#contactEmail"))).sendKeys("foo@bar.com");
        driver.findElement(By.cssSelector("#contactText")).sendKeys("hello");
        driver.findElement(By.xpath("//input[@value='Send']")).click();
        String response = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".contactResponse"))).getText();
        assert(response.contains("Captcha"));
    }
}
