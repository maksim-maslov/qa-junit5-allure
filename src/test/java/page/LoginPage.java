package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By submitButton = By.xpath("//form[@class='body svelte-no02r']/button");
    private By usernameField = By.xpath("//input[@name='login']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By domainList = By.xpath("//select[@class='domain-select svelte-no02r']");
    private By domainField = By.xpath("//select[@class='domain-select svelte-no02r']/option[@value='@bk.ru']");
    private By error = By.xpath("//*[@class='error svelte-no02r']");
    private By createAccLink = By.xpath("//*[@id='PH_regLink']");


    public LoginPage typeUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
        return this;
    }

    public LoginPage typeDomain() {
        driver.findElement(domainList).click();
        driver.findElement(domainField).click();
        return this;
    }

    public LoginPage submitUsername(String username) {
        this.typeUsername(username);
        this.typeDomain();
        driver.findElement(submitButton).click();
        return new LoginPage(driver);
    }

    public void submitPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(submitButton).click();
    }

    public LoginPage submitInvalidPassword(String username, String password) {
        submitUsername(username);
        submitPassword(password);
        return new LoginPage(driver);
    }

    public String getErrorText() {
        return driver.findElement(error).getText();
    }

    public SignUpPage createAccount() {
        driver.findElement(createAccLink).click();
        return new SignUpPage(driver);
    }
}
