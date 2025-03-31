package com.juneit;

import com.juneit.Pages.MainPage;
import com.juneit.Pages.PersonalAccountPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoadPersonalAccountPageTest {
    private final PropertiesLoader properties = new PropertiesLoader();
    private static final WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    MainPage mainPage = new MainPage(driver);
    PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
    CommonHandlers commonHandlers = new CommonHandlers(driver);
    private static List<Long> loadTimes = new ArrayList<>();

    @BeforeEach
    public void setup() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        ((HasAuthentication) driver).register(UsernameAndPassword.of(properties.username,
                properties.password));
        driver.get(properties.baseUrl);
    }

    @RepeatedTest(5)
    public void assertPersonalAccountPageLoadTime() throws IOException, URISyntaxException {
        commonHandlers.login(properties.userEmail, properties.userPassword);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.getUserNameHeader()));
        long beforeTime = System.currentTimeMillis();
        mainPage.getUserNameHeader().click();
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountPage.getEditUserProfileButton()));
        long afterTime = System.currentTimeMillis();
        long loadTime = afterTime - beforeTime;
        System.out.println("Time " + (1 + loadTimes.size()) + " Load " + " :" + loadTime + " ms");
// add to list
        loadTimes.add(loadTime);
        commonHandlers.logout();
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.getSignInButton()));
    }

    @AfterAll
    public static void close() {
        driver.close();
        long averageLoadTime = loadTimes.stream().mapToLong(Long::longValue).sum() / loadTimes.size();
        System.out.println("Average Time Load Page \"Личный кабинет\": " + averageLoadTime + " ms");
        assertTrue(averageLoadTime < 2000);
    }
}
