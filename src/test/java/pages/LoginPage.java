package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    By userField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    By errorMessage = By.xpath("//div[@class='error-message-container error']/h3");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие сайта SauceDemo")
    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    @Step("Вход в систему с логином: \"{userName}\" и паролем: \"{password}\"")
    public void login(String userName, String password) {
        driver.findElement(userField).sendKeys(userName);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
