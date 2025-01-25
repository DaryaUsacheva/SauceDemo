package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

    WebDriver driver;

    String buttonAddOrRemovePattern = "//*[text()='%s']//ancestor::div[@class='inventory_item']//button";

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getButtonAddOrRemove(String productName) {
        return driver.findElement(By.xpath(String.format(buttonAddOrRemovePattern,productName))).getText();
    }

    public void clickButtonAddOrRemove(String productName) {
        driver.findElement(By.xpath(String.format(buttonAddOrRemovePattern,productName))).click();
    }
}
