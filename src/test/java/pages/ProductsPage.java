package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class ProductsPage {

    WebDriver driver;

    By productsName = By.xpath("");
    String buttonAddOrRemovePattern = "//*[text()='%s']//ancestor::div[@class='inventory_item']//button";

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    @Step("Получение имени рандомного товара для дальнейших действий")
    public String getProductName() {
        List<WebElement> productsList = driver.findElements(By.xpath("//*[@class='inventory_item_label']/a/div"));
        int sizeProductsList = productsList.size();
        Random random = new Random();
        int randomNumberProduct = random.nextInt(sizeProductsList);
        return productsList.get(randomNumberProduct).getText();
    }

    @Step("Получение названия кнопки в блоке товара с именем {productName} - кнопка удалить или добавить")
    public String getButtonAddOrRemove(String productName) {
        return driver.findElement(By.xpath(String.format(buttonAddOrRemovePattern,productName))).getText();
    }

    @Step("Нажатие на кнопку добавления/удаления товара именем {productName}")
    public void clickButtonAddOrRemove(String productName) {
        driver.findElement(By.xpath(String.format(buttonAddOrRemovePattern,productName))).click();
    }
}
