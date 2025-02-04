package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CartPage {

    WebDriver driver;

    String productNamePattern = "//*[text()='%s']";

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы \"корзина\"")
    public void open() {
        driver.get("https://www.saucedemo.com/cart.html");
    }

    @Step("Проверка наличия блока товара с именем \"{productName}\" на странице корзины")
    public boolean isProductInPage(String productName) {
        try {
            driver.findElement(By.xpath(String.format(productNamePattern,productName))).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
