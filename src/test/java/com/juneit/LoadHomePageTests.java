package com.juneit;

import com.juneit.Pages.MainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoadHomePageTests {
    private final PropertiesLoader properties = new PropertiesLoader();
    private static final WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    MainPage mainPage = new MainPage(driver);

    @BeforeEach
    public void setup() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        ((HasAuthentication) driver).register(UsernameAndPassword.of(properties.username,
                properties.password));
    }

    @AfterAll
    public static void close() {
        driver.close();
    }

    @Test()
    @Disabled("use only for getting reference value")
    public void assertReferenceLoadHomePage() throws IOException, URISyntaxException, InterruptedException {
        long beforeTime = System.currentTimeMillis();
        driver.get(properties.baseUrl);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.getSignInButton()));
        long afterTime = System.currentTimeMillis();
        long loadTime = afterTime - beforeTime;
        System.out.println("Time Load :" + loadTime + " ms");
        // reference value = 3000 ms
    }

    @Test()
    public void assertLoadHomePage() throws IOException, URISyntaxException, InterruptedException {
        long referenceLoadHomePage = 3000;
        long beforeTime = System.currentTimeMillis();
        driver.get(properties.baseUrl);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.getSignInButton()));
        long afterTime = System.currentTimeMillis();
        long loadTime = afterTime - beforeTime;
        System.out.println("Time Load :" + loadTime + " ms");
        assertTrue(loadTime < referenceLoadHomePage);
    }
}
