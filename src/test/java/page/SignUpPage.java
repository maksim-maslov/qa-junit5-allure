package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SignUpPage {

    WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    private By heading = By.xpath("//*[@class='base-0-2-63 h2-0-2-66 auto-0-2-85']");
    private By usernameField = By.cssSelector("#aaa__input");
    private By passwordField = By.cssSelector("#password");
    private By errorEmptyUsername = By.xpath("//*[@data-test-id='required']//*[@class='base-0-2-1 auto-0-2-5']//*[@class='base-0-2-63 small-0-2-70 auto-0-2-85 error-0-2-76']");
    private By errorTooShortUsername = By.xpath("//*[@data-test-id='invalid_length']//*[@class='base-0-2-1 auto-0-2-5']//*[@class='base-0-2-63 small-0-2-70 auto-0-2-85 error-0-2-76']");
    private By errorInvalidUsername = By.xpath("//*[@data-test-id='invalid_symbols']//*[@class='base-0-2-1 auto-0-2-5']//*[@class='base-0-2-63 small-0-2-70 auto-0-2-85 error-0-2-76']");
    private By errorReservedUsername = By.xpath("//*[@data-test-id='exists']//*[@class='base-0-2-1 auto-0-2-5']//*[@class='base-0-2-63 small-0-2-70 auto-0-2-85 error-0-2-76']");
    private By errorEmptyPassword = By.xpath("//*[@data-test-id='password-field-error-required']");
    private By errorTooShortPassword = By.xpath("//*[@data-test-id='password-field-error-too_short']");
    private By errorInvalidPassword = By.xpath("//*[@data-test-id='password-field-error-invalid_whitespaces']");
    private By errorLikeUsernamePassword = By.xpath("//*[@data-test-id='password-field-error-like_username']");
    private By errorEasyPassword = By.xpath("//*[@data-test-id='password-field-error-like_dictionary_word']");
    private By submitButton = By.xpath("//*[@data-test-id='first-step-submit']");


    public String getHeadingText() {
        return driver.findElement(heading).getText();
    }

    public String getErrorEmptyUsernameText() {
        return driver.findElement(errorEmptyUsername).getText();
    }

    public String getErrorTooShortUsernameText() {
        return driver.findElement(errorTooShortUsername).getText();
    }

    public String getErrorInvalidUsernameText() {
        return driver.findElement(errorInvalidUsername).getText();
    }

    public String getErrorReservedUsernameText() {
        return driver.findElement(errorReservedUsername).getText();
    }

    public String getErrorEmptyPasswordText() {
        return driver.findElement(errorEmptyPassword).getText();
    }

    public String getErrorTooShortPasswordText() {
        return driver.findElement(errorTooShortPassword).getText();
    }

    public String getErrorInvalidPasswordText() {
        return driver.findElement(errorInvalidPassword).getText();
    }

    public String getErrorLikeUsernamePasswordText() {
        return driver.findElement(errorLikeUsernamePassword).getText();
    }

    public String getErrorEasyPasswordText() {
        return driver.findElement(errorEasyPassword).getText();
    }

    public SignUpPage typeUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
        return this;
    }

    public SignUpPage typePassword(String username) {
        driver.findElement(passwordField).sendKeys(username);
        return this;
    }

    public SignUpPage registeredWithInvalidCreds(String username, String password) {
        this.typeUsername(username);
        this.typePassword(password);
        driver.findElement(submitButton).click();
        return new SignUpPage(driver);
    }
}
