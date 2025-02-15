package tests;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddRemoveProductsInProductsPageTest extends BaseTest {

    @BeforeMethod
    public void login(){
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");
    }

    @Test(testName = "Добавление одного продукта",
            groups = {"smoke","cart","ui"},
            timeOut = 5000,
            description = "Добавление одного продукта с общей страницы")
    @Description("Добавление одного продукта с общей страницы")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("SauseDemo-1.0")
    @Feature("Cart in SauseDemo")
    @Story("Добавление товаров в корзину")
    public void addOneProduct() {
        SoftAssert softAssert = new SoftAssert();
        String productName = productsPage.getProductName();
        softAssert.assertEquals(productsPage.getButtonAddOrRemove(productName),"Add to cart");
        productsPage.clickButtonAddOrRemove(productName);
        softAssert.assertEquals(primaryHeaderPage.getShoppingCartBadge(),"1");
        cartPage.open();
        softAssert.assertTrue(cartPage.isProductInPage(productName));
        softAssert.assertAll();
    }

    @Test(testName = "Добавление двух продуктов",
            groups = {"regress","cart","ui"},
            timeOut = 5000,
            description = "Добавление двух продуктов с общей страницы")
    @Description("Добавление двух продуктов с общей страницы")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("SauseDemo-1.0")
    @Feature("Cart in SauseDemo")
    @Story("Добавление товаров в корзину")
    @TmsLink("ссылка на жиру")
    @Issue("ссылка на баг")
    @Flaky
    public void addTwoProducts() {
        SoftAssert softAssert = new SoftAssert();
        String productName1 = productsPage.getProductName();
        String productName2 = productsPage.getProductName();
        softAssert.assertEquals(productsPage.getButtonAddOrRemove(productName1),"Add to cart");
        productsPage.clickButtonAddOrRemove(productName1);
        softAssert.assertEquals(primaryHeaderPage.getShoppingCartBadge(),"1");
        softAssert.assertEquals(productsPage.getButtonAddOrRemove(productName2),"Add to cart");
        productsPage.clickButtonAddOrRemove(productName2);
        softAssert.assertEquals(primaryHeaderPage.getShoppingCartBadge(),"2");
        cartPage.open();
        softAssert.assertTrue(cartPage.isProductInPage(productName1));
        softAssert.assertTrue(cartPage.isProductInPage(productName2));
        softAssert.assertAll();
    }

    @Test(testName = "Удаление одного продукта с общей страницы",
            groups = {"regress","cart","ui"},
            description = "Удаление одного продукта из корзины с общей страницы")
    @Description("Удаление одного продукта из корзины с общей страницы")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("SauseDemo-1.0")
    @Feature("Cart in SauseDemo")
    @Story("Удаление товаров")
    public void removeProductFromCart() {
        SoftAssert softAssert = new SoftAssert();
        String productName = productsPage.getProductName();
        softAssert.assertEquals(productsPage.getButtonAddOrRemove(productName),"Add to cart");
        productsPage.clickButtonAddOrRemove(productName);
        softAssert.assertEquals(primaryHeaderPage.getShoppingCartBadge(),"1");
        softAssert.assertEquals(productsPage.getButtonAddOrRemove(productName),"Remove");
        productsPage.clickButtonAddOrRemove(productName);
        softAssert.assertFalse(primaryHeaderPage.isShoppingCartBadge());
        cartPage.open();
        softAssert.assertFalse(cartPage.isProductInPage(productName));
        softAssert.assertAll();
    }
}
