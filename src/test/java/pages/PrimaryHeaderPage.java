package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class PrimaryHeaderPage {

    WebDriver driver;

    By shoppingCartBadge = By.xpath("//*[@class='shopping_cart_badge']");

    public PrimaryHeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка наличия иконки с количеством товаров в корзине")
    public boolean isShoppingCartBadge() {
        try {
            driver.findElement(shoppingCartBadge).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Получение количества товаров в корзине")
    public String getShoppingCartBadge() {
        return driver.findElement(shoppingCartBadge).getText();
    }
}
