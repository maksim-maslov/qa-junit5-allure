package test;

import page.LoginPage;
import page.SignUpPage;

import java.io.File;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import io.qameta.allure.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginPageTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        StringBuilder pathToDriver = new StringBuilder(Paths.get(".").toAbsolutePath().normalize().toString());
        String fileSeparator = File.separator;
        pathToDriver.append(fileSeparator).append("drivers").append(fileSeparator).append("geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", pathToDriver.toString());

        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.mail.ru/");
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
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
