package de.hbrs.se2.womm.selenium;

import de.hbrs.se2.womm.selenium.using.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

class LoginPage extends AbstractPage {
    private WebDriver driver;
    private WebDriverWait wait;
    WebElement usernameField;
    WebElement passwordField;
    WebElement loginButton;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super("LoginView", "http://localhost:8080/vaadin/login");
        this.driver = driver;
        this.wait = wait;
    }

    public void goToWebsiteAndWaitUntilTitlePresent() {
        driver.get(getWebsiteUrl());
        wait.until(webDriver -> webDriver.getTitle().equals(getWebsiteTitle()));
    }
    private void setUp() {
        usernameField = driver.findElement(By.id("input-vaadin-text-field-6"));
        passwordField = driver.findElement(By.id("input-vaadin-password-field-7"));
        loginButton = driver.findElement(By.id("login-button")); //ToDo: ID ändern
    }


    public void login(String username, String password) {
        setUp();
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        wait.until(webDriver -> !(webDriver.getTitle().equals(getWebsiteTitle())));
    }
}