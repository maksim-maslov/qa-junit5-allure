package org.example.qa.pageobject.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.example.qa.pageobject.page.LoginPage;
import org.example.qa.pageobject.page.SignUpPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginPageTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {

        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.mail.ru/");
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Epic("TESTING FOR https://www.mail.ru/ authorization")
    @Feature(value = "Tests for authorization")
    @Severity(SeverityLevel.BLOCKER)
    @Description("LogIn")
    @Story(value = "Tests for login with incorrect credentials")


    @Test
    public void signUp() {
        SignUpPage signUpPage = loginPage.createAccount();
        String heading = signUpPage.getHeadingText();
        assertEquals("Создание аккаунта", heading);
    }

    @Test
    public void loginEmptyUsername() {
        LoginPage newLoginPage = loginPage.submitUsername("");
        String error = newLoginPage.getErrorText();
        assertEquals("Введите имя ящика", error);
    }

    @Test
    public void loginInvalidUsername() {
        LoginPage newLoginPage = loginPage.submitUsername("qwerty=");
        String error = newLoginPage.getErrorText();
        assertEquals("Неверное имя ящика", error);
    }

    @Test
    public void loginEmptyPassword() {
        LoginPage newLoginPage = loginPage.submitInvalidPassword("mksm.m", "");
        String error = newLoginPage.getErrorText();
        assertEquals("Введите пароль", error);
    }
}
