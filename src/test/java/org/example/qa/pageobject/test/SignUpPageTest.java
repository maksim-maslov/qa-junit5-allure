package org.example.qa.pageobject.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.example.qa.pageobject.page.SignUpPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SignUpPageTest {

    private WebDriver driver;
    private SignUpPage signUpPage;

    @BeforeEach
    public void setUp() {

//        StringBuilder pathToDriver = new StringBuilder(Paths.get(".").toAbsolutePath().normalize().toString());
//        String fileSeparator = File.separator;
//        pathToDriver.append(fileSeparator).append("drivers").append(fileSeparator).append("geckodriver.exe");
//        System.setProperty("webdriver.gecko.driver", pathToDriver.toString());

//        try {
//            System.setProperty("webdriver.gecko.driver",
//                Paths.get(getClass().getProtectionDomain().getCodeSource().getLocation().toURI())
//                    .resolve(Paths.get("geckodriver.exe"))
//                    .toFile()
//                    .getAbsolutePath()
//            );
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }

        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://account.mail.ru/signup/");
        signUpPage = new SignUpPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Epic("TESTING FOR https://www.mail.ru/ register")
    @Feature(value = "Tests for register")
    @Severity(SeverityLevel.MINOR)
    @Description("SignUp")
    @Story(value = "Tests for register with incorrect credentials")

    @Test
    public void signUpEmptyUsername() {
        SignUpPage newSignUpPage = signUpPage.registeredWithInvalidCreds("", "");
        String error = newSignUpPage.getErrorEmptyUsernameText();
        assertEquals("Укажите желаемое имя аккаунта", error);
    }

    @Test
    public void signUpShortUsername() {
        SignUpPage newSignUpPage = signUpPage.registeredWithInvalidCreds("bmw", "");
        String error = newSignUpPage.getErrorTooShortUsernameText();
        assertEquals("Введено некорректное имя аккаунта. Имя аккаунта должно быть длиной от 5 до 31 символов.", error);
    }

    @Test
    public void signUpIncorrectUsername() {
        SignUpPage newSignUpPage = signUpPage.registeredWithInvalidCreds("example.qa.pageobject.example.qa.pageobject.test****", "");
        String error = newSignUpPage.getErrorInvalidUsernameText();
        assertEquals("Введено некорректное имя аккаунта. Допустимо использовать только латинские буквы, цифры, знак подчеркивания, точку и минус.", error);
    }

    @Test
    public void signUpReservedUsername() {
        SignUpPage newSignUpPage = signUpPage.registeredWithInvalidCreds("tests", "");
        String error = newSignUpPage.getErrorReservedUsernameText();
        assertEquals("Аккаунт с таким именем уже существует", error);
    }

    @Test
    public void signUpEmptyPassword() {
        SignUpPage newSignUpPage = signUpPage.registeredWithInvalidCreds("", "");
        String error = newSignUpPage.getErrorEmptyPasswordText();
        assertEquals("Укажите пароль", error);
    }

    @Test
    public void signUpShortPassword() {
        SignUpPage newSignUpPage = signUpPage.registeredWithInvalidCreds("", "example.qa.pageobject/example.qa.pageobject.test");
        String error = newSignUpPage.getErrorTooShortPasswordText();
        assertEquals("Используйте не менее 8 символов", error);
    }

    @Test
    public void signUpIncorectPassword() {
        SignUpPage newSignUpPage = signUpPage.registeredWithInvalidCreds("", "example.qa.pageobject.example.qa.pageobject.test example.qa.pageobject.example.qa.pageobject.test");
        String error = newSignUpPage.getErrorInvalidPasswordText();
        assertEquals("Пароль не должен содержать пробелы", error);
    }

    @Test
    public void signUpLikeUsernamePassword() {
        SignUpPage newSignUpPage = signUpPage.registeredWithInvalidCreds("example.qa.pageobject.example.qa.pageobject.test****", "********");
        String error = newSignUpPage.getErrorLikeUsernamePasswordText();
        assertEquals("Не используйте имя аккаунта и другие личные данные", error);
    }

    @Test
    public void signUpEasyPassword() {
        SignUpPage newSignUpPage = signUpPage.registeredWithInvalidCreds("", "password");
        String error = newSignUpPage.getErrorEasyPasswordText();
        assertEquals("Не используйте личные данные, последовательности (123456, qwerty) и популярные пароли (password).", error);
    }
}
