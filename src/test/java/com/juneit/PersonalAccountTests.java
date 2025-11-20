package com.juneit;

import com.juneit.Pages.CreateGameOptionsPage;
import com.juneit.Pages.EventsPage;
import com.juneit.Pages.MainPage;
import com.juneit.Pages.PersonalAccountPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class PersonalAccountTests {
    private final PropertiesLoader properties = new PropertiesLoader();
    private static final WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    MainPage mainPage = new MainPage(driver);
    PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
    EventsPage eventsPage = new EventsPage(driver);
    CreateGameOptionsPage createGameOptionsPage = new CreateGameOptionsPage(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;
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
    public void assertPositiveProfileCard () {
        commonHandlers.login(properties.userEmail, properties.userPassword);
        mainPage.getUserNameHeader().click();
        assertEquals("Anna",
                personalAccountPage.getNameProfileCardText());
        assertEquals("ann.latkina@gmail.com",
                personalAccountPage.getEmailProfileCardText());
        assertTrue(personalAccountPage.getEditButton().isDisplayed());
        assertEquals("Редактировать",
                personalAccountPage.getEditButton().getText());
        assertTrue(personalAccountPage.getDepositInfoText().contains("Личный счёт:"));
        assertTrue(personalAccountPage.getDepositPaymentButton().isDisplayed());
        assertEquals("Пополнить счет",
                personalAccountPage.getDepositPaymentButton().getText());
        commonHandlers.logout();
    }

//    @Test
//    public void assertPositiveEditUserName () {
//        login(userEmail, userPassword);
//        driver.findElement(By.className(USER_NAME_HEADER_SPLOTEAM_CLASS)).click();
//        assertEquals("Anna",driver.findElement(By.className(PROFILE_CARD_NAME_CLASS)).getText());
//        driver.findElement(By.className(PROFILE_CARD_EDIT_BUTTON_CLASS)).click ();
//        driver.findElement(By.className(PROFILE_CARD_EDIT_NAME_CLEAR_CLASS)).click ();
//        driver.findElement(By.className(PROFILE_CARD_EDIT_NAME_INPUT_CLASS)).sendKeys("Test");
//        driver.findElement(By.xpath(PROFILE_CARD_SAVE_EDIT_BUTTON_XPATH)).click ();
//        assertEquals("Ваш профиль успешно обновлён",
//                driver.findElement (By.className (PROFILE_CARD_CONFIRM_EDIT_CLASS)).getText());
//        driver.findElement(By.xpath(PROFILE_CARD_CONFIRM_EDIT_BUTTON_CLOSE_XPATH)).click();
//        driver.navigate().refresh(); //bug
//        assertEquals("Test",
//                driver.findElement (By.className (USER_NAME_HEADER_SPLOTEAM_CLASS)).getText());
//        driver.findElement(By.className(PROFILE_CARD_EDIT_NAME_CLEAR_CLASS)).click ();
//        driver.findElement(By.className(PROFILE_CARD_EDIT_NAME_INPUT_CLASS)).sendKeys("Anna");
//        driver.findElement(By.xpath(PROFILE_CARD_SAVE_EDIT_BUTTON_XPATH)).click ();
//        driver.findElement(By.xpath(PROFILE_CARD_CONFIRM_EDIT_BUTTON_CLOSE_XPATH)).click();
//        logout();
//    }
//
//    @Test
//    public void assertNegativeEditEmptyUserName () {
//        login(userEmail, userPassword);
//        driver.findElement(By.className(USER_NAME_HEADER_SPLOTEAM_CLASS)).click();
//        assertEquals("Anna",driver.findElement(By.className(PROFILE_CARD_NAME_CLASS)).getText());
//        driver.findElement(By.className(PROFILE_CARD_EDIT_BUTTON_CLASS)).click ();
//        driver.findElement(By.className(PROFILE_CARD_EDIT_NAME_CLEAR_CLASS)).click ();
//        driver.findElement(By.xpath(PROFILE_CARD_SAVE_EDIT_BUTTON_XPATH)).click ();
//        assertEquals("Выберите значение",
//                driver.findElement (By.className (WRONG_LOGIN_ERROR_CLASS)).getText());
//        assertTrue(driver.findElement(By.xpath(PROFILE_CARD_SAVE_EDIT_BUTTON_XPATH)).isDisplayed());
//        driver.findElement(By.className(PROFILE_CARD_BACK_CLASS)).click();
//        assertEquals("Anna",driver.findElement(By.className(PROFILE_CARD_NAME_CLASS)).getText());
//        logout();
//    }
}


