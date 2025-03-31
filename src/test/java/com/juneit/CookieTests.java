package com.juneit;

import com.juneit.Pages.MainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CookieTests {
    private final PropertiesLoader properties = new PropertiesLoader();
    private static final WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    MainPage mainPage = new MainPage(driver);
    CommonHandlers commonHandlers = new CommonHandlers(driver);

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

    @Test()
    public void assertCookieForLogin() throws IOException, URISyntaxException, InterruptedException {
        Set<Cookie> beforeLoginCookies = driver.manage().getCookies();
        System.out.println("Cookie до аутентификации:");
        for (Cookie c : beforeLoginCookies) {
            System.out.println(c.getName() + " = " + c.getValue());
        }
        commonHandlers.login(properties.userEmail, properties.userPassword);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.getUserNameHeader()));
        Set<Cookie> afterLoginCookie = driver.manage().getCookies();
        System.out.println("Cookie после аутентификации:");
        for (Cookie c : afterLoginCookie) {
            System.out.println(c.getName() + " = " + c.getValue());
        }
//         cookie responsible for authentication - sploid
        driver.manage().deleteCookieNamed("sploid");
        driver.navigate().refresh();
        assertTrue(mainPage.getSignInButton().isDisplayed());
    }
}
