package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddRemoveProductsInProductPageTest extends BaseTest {

    @BeforeMethod
    public void login(){
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");
    }

    @Test
    public void addOneProduct() {
        SoftAssert softAssert = new SoftAssert();
        String productName = "Sauce Labs Backpack";
        softAssert.assertEquals(productsPage.getButtonAddOrRemove(productName),"Add to cart");
        productsPage.clickButtonAddOrRemove(productName);
        softAssert.assertEquals(primaryHeaderPage.getShoppingCartBadge(),"1");
        cartPage.open();
        softAssert.assertTrue(cartPage.isProductInPage(productName));
        softAssert.assertAll();
    }

    @Test
    public void addTwoProducts() {
        SoftAssert softAssert = new SoftAssert();
        String productName1 = "Sauce Labs Backpack";
        String productName2 = "Sauce Labs Bolt T-Shirt";
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

    @Test
    public void removeProductFromCart() {
        SoftAssert softAssert = new SoftAssert();
        String productName = "Sauce Labs Onesie";
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
