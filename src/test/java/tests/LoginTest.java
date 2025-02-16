package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    WebDriver driver;

    @BeforeMethod
    public void openPage() {
        loginPage.open();
    }

    @Test(testName = "Аутентификация в системе",
            groups = {"smoke","auth","ui"},
            timeOut = 2000,
            description = "Аутентификация в системе с валидными логином и паролем стандартного пользователя")
    @Description("Аутентификация в системе с валидными логином и паролем стандартного пользователя")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("SauseDemo-1.0")
    @Feature("Login in SauseDemo")
    @Story("Система авторизации")
    public void checkPositiveLogin(){
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.getPageUrl(),
                "https://www.saucedemo.com/inventory.html");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"standard_user_name", "", "Epic sadface: Password is required"},
                {"", "secret_password", "Epic sadface: Username is required"},
                {"standard_user", "pass", "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}
        };
    }

    @Test(testName = "Неуспешная аутентификация",
            groups = {"smoke","auth","ui"},
            timeOut = 2000,
            description = "Аутентификация в системе с различными ошибками",
            dataProvider = "loginData")
    @Description("Аутентификация в системе с различными ошибками")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("SauseDemo-1.0")
    @Feature("Login in SauseDemo")
    @Story("Система авторизации")
    public void checkNegativeLogin(String userName, String password, String errorMessage){
        loginPage.login(userName, password);
        Assert.assertEquals(loginPage.getErrorMessage(), errorMessage);
    }

}
