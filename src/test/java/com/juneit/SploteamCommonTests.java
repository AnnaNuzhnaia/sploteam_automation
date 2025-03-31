package com.juneit;

import com.juneit.Pages.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class SploteamCommonTests {
    private final PropertiesLoader properties = new PropertiesLoader();
    private static final WebDriver driver = new ChromeDriver();
    MainPage mainPage = new MainPage(driver);
    EventsPage eventsPage = new EventsPage(driver);
    LoginWindow loginWindow = new LoginWindow(driver);
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
    public void assertMainPageIsLoaded() {
        assertTrue(mainPage.getSignInButton().isDisplayed());
        assertTrue(mainPage.getLogoHeader().isDisplayed());
    }

    @Test
    public void assertCreateGameTabOPen() {
        mainPage.getButtonCreateGameMainPage().click();
        for (int i = 0; i < eventsPage.getEventsAndCreateGamesTabs().size(); i++){
            if (eventsPage.getEventsAndCreateGamesTabs().get(i).getAttribute("class").contains("active")){
                assertEquals("Создать игру", eventsPage.getEventsAndCreateGamesTabs().get(i).getText());
            }
        }
    }

    @Test
    public void assertEventsTabOpen() {
        mainPage.getMoreGamesButton().click();
        for (int i = 0; i < eventsPage.getEventsAndCreateGamesTabs().size(); i++){
            if (eventsPage.getEventsAndCreateGamesTabs().get(i).getAttribute("class").contains("active")){
                assertEquals("События", eventsPage.getEventsAndCreateGamesTabs().get(i).getText());
            }
        }
    }

    // password masking

    @Test
    public void assertPasswordHiddenStyle() {
        mainPage.getSignInButton().click();
        loginWindow.getInputEmailLogin().sendKeys("email@email.com");
        loginWindow.getInputPasswordLogin().sendKeys("password");
        String defaultPasswordAttributeType = loginWindow.getInputPasswordLogin().getAttribute("type");
        assertEquals("password", defaultPasswordAttributeType);

        String textStyleHiddenPassword = (String) js.executeScript(
                "return window.getComputedStyle(arguments[0]).getPropertyValue('-webkit-text-security');",
                loginWindow.getInputPasswordLogin());
        assertEquals("disc", textStyleHiddenPassword);
        System.out.println("Before clicking show - Webkit Text Security: " + textStyleHiddenPassword);

        driver.findElement(By.className("FormInput_togglePassVisibleHide__3KKSI")).click();
        String shownPasswordAttributeType = loginWindow.getInputPasswordLogin().getAttribute("type");
        assertEquals("text", shownPasswordAttributeType);

        String textStyleShownPassword = (String) js.executeScript(
                "return window.getComputedStyle(arguments[0]).getPropertyValue('-webkit-text-security');",
                loginWindow.getInputPasswordLogin());
        assertEquals("none", textStyleShownPassword);
        System.out.println("After clicking show - Webkit Text Security: " + textStyleShownPassword);
    }

}


