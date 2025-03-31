package com.juneit;

import com.juneit.Pages.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActionClassTests {
    private final PropertiesLoader properties = new PropertiesLoader();
    private static final WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    MainPage mainPage = new MainPage(driver);
    LoginWindow loginWindow = new LoginWindow(driver);
    Actions actions = new Actions(driver);
    CommonHandlers commonHandlers = new CommonHandlers(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @BeforeEach
    public void setup() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        ((HasAuthentication) driver).register(UsernameAndPassword.of(properties.username,
                properties.password));
        driver.get(properties.baseUrl);
    }

    @AfterAll
    public static void close() {
        driver.close();
    }

    @Test
    public void assertLoginPositiveTest() throws InterruptedException {
        actions.click(mainPage.getSignInButton()).perform();
        actions.click(loginWindow.getInputEmailLogin()).sendKeys(properties.userEmail)
                .click(loginWindow.getSignInWindowButton()).sendKeys(properties.userPassword)
                .click(loginWindow.getSignInWindowButton())
                .perform();
        wait.until(ExpectedConditions.visibilityOf(mainPage.getUserNameHeader()));
        assertTrue(mainPage.getUserNameHeader().isDisplayed());
        assertEquals("Anna", mainPage.getUserNameHeader().getText());
        commonHandlers.logout();
        assertTrue(mainPage.getSignInButton().isDisplayed());
    }

    @Test
    public void assertFAQTooltip() throws InterruptedException {
        actions.moveToElement(mainPage.getFAQButton()).perform();
        String tooltipText = (String) js.executeScript(
                "return document.querySelector('.FAQButton_hint__1BI_4').textContent;");
        assertEquals(tooltipText, mainPage.getTextFAQButton().getText());
        System.out.println("Tooltip Text: " + tooltipText);
    }
}
