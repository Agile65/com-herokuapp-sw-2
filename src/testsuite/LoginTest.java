package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        //  1. Enter “tomsmith” username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");
        //  Enter “SuperSecretPassword!” password
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("SuperSecretPassword!");
        //  Click on ‘LOGIN’ button
        WebElement loginButton = driver.findElement(By.className("radius"));
        loginButton.click();
        //  Verify the text “Secure Area”
        String expectedMessage = "Secure Area";
        WebElement actualTextElement = driver.findElement(By.xpath("//h2"));
        String actualMessage = actualTextElement.getText();
        Assert.assertEquals(" Secure Area not displayed", expectedMessage, actualMessage);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        // 2. Enter “tomsmith1” username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith1");
        // Enter “SuperSecretPassword!” password
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("SuperSecretPassword!");
        // Click on ‘LOGIN’ button
        WebElement loginButton = driver.findElement(By.className("radius"));
        loginButton.click();
        // Verify the error message “Your username is invalid!”
        String expectedMessage = "Your username is invalid!\n" +
                "×";
        WebElement actualDisplayMessage = driver.findElement(By.id("flash"));
        String actualMessage = actualDisplayMessage.getText();
        Assert.assertEquals(" Your username is invalid! not displayed", expectedMessage, actualMessage);
    }

    @Test
    public void verifyThePasswordErrorMessage() {
        // 3. Enter “tomsmith” username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");
        // Enter “SuperSecretPassword” password
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("SuperSecretPassword");
        // Click on ‘LOGIN’ button
        WebElement loginButton = driver.findElement(By.className("radius"));
        loginButton.click();
        // Verify the error message “Your password is invalid!”
        String expectedMessage = "Your password is invalid!\n" +
                "×";
        WebElement actualDisplayMessage = driver.findElement(By.id("flash"));
        String actualMessage = actualDisplayMessage.getText();
        Assert.assertEquals(" Your password is invalid! not displayed", expectedMessage, actualMessage);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
