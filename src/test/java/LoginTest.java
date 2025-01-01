import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void checkPositiveLogin(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String pageUrl = driver.getCurrentUrl();
        Assert.assertEquals(pageUrl, "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void checkNegativeLoginNoPass(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']/h3")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Password is required");
    }

    @Test
    public void checkNegativeLoginNoUserName(){
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']/h3")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required");
    }

    @Test
    public void checkNegativeLoginPassNotCorrect(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("pass");
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']/h3")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void checkNegativeLoginLockedUser(){
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']/h3")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Sorry, this user has been locked out.");
    }

    @AfterMethod(alwaysRun = true)
    public void quit(){
        driver.quit();
    }
}
