package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CartPage {

    WebDriver driver;

    String productNamePattern = "//*[text()='%s']";

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.saucedemo.com/cart.html");
    }

    //  Наличие блока с продуктом на странице
    public boolean isProductInPage(String productName) {
        try {
            driver.findElement(By.xpath(String.format(productNamePattern,productName)));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
