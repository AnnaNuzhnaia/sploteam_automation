package com.juneit;

import com.juneit.Pages.LoginWindow;
import com.juneit.Pages.MainPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginTests {
    private final PropertiesLoader properties = new PropertiesLoader();
    private static final WebDriver driver = new ChromeDriver();
    MainPage mainPage = new MainPage(driver);
    LoginWindow loginWindow = new LoginWindow(driver);
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

    @Test
    public void assertPositiveLogin (){
        assertTrue(mainPage.getSignInButton().isDisplayed());
        commonHandlers.login(properties.userEmail, properties.userPassword);
        assertTrue(mainPage.getUserNameHeader().isDisplayed());
        assertEquals("Anna", mainPage.getUserNameHeader().getText());
        commonHandlers.logout();
        assertTrue(mainPage.getSignInButton().isDisplayed());
    }

    @Test
    public void assertWrongPasswordLogin () {
        assertTrue(mainPage.getSignInButton().isDisplayed());
        commonHandlers.login(properties.userEmail, "newPass");
        assertEquals("Неверный пароль",
                loginWindow.getTextMessageWrongLogin());
        assertTrue(mainPage.getSignInButton().isDisplayed());
    }

    @Test
    public void assertWrongEmailLogin () {
        assertTrue(mainPage.getSignInButton().isDisplayed());
        commonHandlers.login("wrongemail@gmail.com", properties.userPassword);
        assertEquals("Такой логин или пароль не найдены",
                loginWindow.getTextMessageWrongLogin());
        assertTrue(mainPage.getSignInButton().isDisplayed());
    }

    @Test
    public void assertEmptyEmailLogin () {
        commonHandlers.login("", properties.userPassword);
        assertTrue(mainPage.getSignInButton().isDisplayed());
        assertEquals(loginWindow.getInputEmailLogin(),
                driver.switchTo().activeElement());
    }

    @Test
    public void assertEmptyPasswordLogin () throws InterruptedException {
        commonHandlers.login(properties.userEmail, "");
        assertTrue(mainPage.getSignInButton().isDisplayed());
        assertEquals(loginWindow.getInputPasswordLogin(),
                driver.switchTo().activeElement());
    }

    @Test
    public void assertWrongLengthPasswordLogin () {
        assertTrue(mainPage.getSignInButton().isDisplayed());
        commonHandlers.login(properties.userEmail, "short");
        assertEquals("Неверное значение",
                loginWindow.getTextMessageWrongLogin());
        assertTrue(mainPage.getSignInButton().isDisplayed());
    }

    @Test
    public void assertWrongEmailWithoutAtLogin () {
        assertTrue(mainPage.getSignInButton().isDisplayed());
        commonHandlers.login("withoutatgmail.com", properties.userPassword);
        assertEquals("Адрес электронной почты должен содержать символ \"@\". В адресе \"withoutatgmail.com\" отсутствует символ \"@\".",
                loginWindow.validationMessage());
        assertTrue(mainPage.getSignInButton().isDisplayed());
    }
}
