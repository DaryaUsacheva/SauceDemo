package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class PrimaryHeaderPage {

    WebDriver driver;

    By shoppingCartBadge = By.xpath("//*[@class='shopping_cart_badge']");

    public PrimaryHeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isShoppingCartBadge() {
        try {
            driver.findElement(shoppingCartBadge);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getShoppingCartBadge() {
        return driver.findElement(shoppingCartBadge).getText();
    }
}
