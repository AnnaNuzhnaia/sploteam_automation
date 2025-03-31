package com.juneit;

import com.juneit.Pages.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

public class CreateEventTests {
    private final PropertiesLoader properties = new PropertiesLoader();
    private static final WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    MainPage mainPage = new MainPage(driver);
    EventsPage eventsPage = new EventsPage(driver);
    CreateGameOptionsPage createGameOptionsPage = new CreateGameOptionsPage(driver);
    PersonalAccountPage personalAccountPage  = new PersonalAccountPage(driver);
    LoginWindow loginWindow = new LoginWindow(driver);
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
    public void assertCreateGamePageIsOpenForEachArena() throws InterruptedException {
        commonHandlers.login(properties.userEmail, properties.userPassword);
        mainPage.getButtonCreateGameMainPage().click();
        wait.until(ExpectedConditions.invisibilityOf(eventsPage.getLoaderWrapper()));
        wait.until(ExpectedConditions.textToBePresentInElement(eventsPage.getArenaFilter(),
                "Все арены"));
        eventsPage.getChosenDate().click();
        for (int i = 0; i < eventsPage.getArenaSection().size(); i++){
            String arenaName = eventsPage.getArenaName(i).getText();
            eventsPage.getArenaTimeTable(i).get(0).click();
            eventsPage.getGameDurationOptions(i).click();
            eventsPage.getDurationList().get(0).click();
            eventsPage.getGameStartOptions(i).click();
            eventsPage.getStartTimeList().get(5).click();
            eventsPage.getCreateGameForArenaButton(i).click();
            wait.until(ExpectedConditions.textToBePresentInElement(createGameOptionsPage.getTitlePage().get(0), "Создание игры"));
            assertTrue(createGameOptionsPage.getInfoAboutCreateGameText().contains(arenaName));
            driver.navigate().back();
        }
        commonHandlers.logout();
    }

    @Test
    public void assertDuration30MinNegative() throws InterruptedException {
        commonHandlers.login(properties.userEmail, properties.userPassword);
        mainPage.getButtonCreateGameMainPage().click();
        eventsPage.getTimeList().get(0).click();
        eventsPage.getDuration().click();
        for (int i = 0; i < eventsPage.getDurationList().size(); i++){
            assertFalse(eventsPage.getDurationList().get(i).getText().contains("0.5")); //check that all duration options aren't equal to 0.5 hour
        }
        commonHandlers.logout();
    }

    @Test
    public void assert1HourDurationGameForLastTime() throws InterruptedException {
        commonHandlers.login(properties.userEmail, properties.userPassword);
        mainPage.getButtonCreateGameMainPage().click();
        eventsPage.getCalendarDateList().get(13).click(); //choose the last date
        eventsPage.getTimeList().get(0).click();
        eventsPage.getStartTime().click();
        eventsPage.getStartTimeList().get(eventsPage.getStartTimeList().size() - 1).click(); //choose the last time of start game
        eventsPage.getDuration().click();
        assertEquals(1, eventsPage.getDurationList().size()); // check that there is only 1 duration option
        assertEquals("1 час", eventsPage.getDurationList().get(0).getText()); // check that this duration is equal to 1 hour
        commonHandlers.logout();
    }

    @Test
    public void assertAvailableOpenedAndClosedGamesInMoreThan24Hours() throws InterruptedException {
        commonHandlers.login(properties.userEmail, properties.userPassword);
        mainPage.getButtonCreateGameMainPage().click();
        wait.until(ExpectedConditions.textToBePresentInElement(eventsPage.getArenaFilter(),
                "Все арены"));
        eventsPage.getCalendarDateList().get(5).click(); //choose the date in more than 24 hours
        eventsPage.getTimeList().get(1).click();
        eventsPage.getDuration().click();
        eventsPage.getDurationList().get(0).click();
        eventsPage.getStartTime().click();
        eventsPage.getStartTimeList().get(5).click();
        eventsPage.getButtonCreateGame().click();
        createGameOptionsPage.getOpenListOptionTeam().click();
        assertEquals("Нет, у меня есть свои игроки, поиграем между собой",
                createGameOptionsPage.getListOptions().get(1).getText()); // check that there is closed game
        assertEquals("Хочу найти игроков для совместной игры",
                createGameOptionsPage.getListOptions().get(2).getText()); // check that there is opened game
        commonHandlers.logout();
    }

    @Test
    public void assertAvailableOnlyClosedGamesInLessThan24Hours() throws InterruptedException {
        // Important! Run the test during arena business hours
        commonHandlers.login(properties.userEmail, properties.userPassword);
        mainPage.getButtonCreateGameMainPage().click();
        wait.until(ExpectedConditions.textToBePresentInElement(eventsPage.getArenaFilter(),
                "Все арены"));        eventsPage.getTimeList().get(0).click();
        eventsPage.getDuration().click();
        eventsPage.getDurationList().get(0).click();
        eventsPage.getStartTime().click();
        eventsPage.getStartTimeList().get(4).click();
        eventsPage.getButtonCreateGame().click();
        createGameOptionsPage.getOpenListOptionTeam().click();
        assertEquals("Нет, у меня есть свои игроки, поиграем между собой",
                createGameOptionsPage.getListOptions().get(1).getText()); // check that game is closed
        for (int i=0; i<createGameOptionsPage.getListOptions().size();i++){
            assertFalse(createGameOptionsPage.getListOptions().get(i).
                    getText().contains("Хочу найти игроков для совместной игры")); //check that there is not open game
        }
        commonHandlers.logout();
    }

    @Test
    @Disabled("specific start time")
    public void assertBorderLess24Hour() throws InterruptedException {
        // Important! Run the test at 09:40
        commonHandlers.login(properties.userEmail, properties.userPassword);
        mainPage.getButtonCreateGameMainPage().click();
        eventsPage.getCalendarDateList().get(1).click(); //choose tomorrow
        eventsPage.getFiltersList().get(0).click();
        for (int i = 0; i < eventsPage.getArenaList().size(); i++){
            if (eventsPage.getArenaList().get(i).getText().contains("Demo")){
                eventsPage.getArenaList().get(i).click(); // choose arena Demo
            }
        }
        eventsPage.getTimeList().get(0).click();
        eventsPage.getDuration().click();
        eventsPage.getDurationList().get(0).click();
        eventsPage.getStartTime().click();
        eventsPage.getStartTimeList().get(11).click(); // choose 9:30 hour
        eventsPage.getButtonCreateGame().click();
        createGameOptionsPage.getOpenListOptionTeam().click();
        assertEquals("Нет, у меня есть свои игроки, поиграем между собой",
                createGameOptionsPage.getListOptions().get(1).getText()); // check that game is closed
        for (int i=0; i<createGameOptionsPage.getListOptions().size();i++){
            assertFalse(createGameOptionsPage.getListOptions().get(i).
                    getText().contains("Хочу найти игроков для совместной игры")); //check that there is not open game
        }
        commonHandlers.logout();
    }

    @Test
    @Disabled("specific start time")
    public void assertBorderMore24Hour() throws InterruptedException {
        // Important! Run the test at 09:45
        commonHandlers.login(properties.userEmail, properties.userPassword);
        mainPage.getButtonCreateGameMainPage().click();
        wait.until(ExpectedConditions.textToBePresentInElement(eventsPage.getArenaFilter(),
                "Все арены"));
        eventsPage.getCalendarDateList().get(1).click(); //choose tomorrow
        eventsPage.getFiltersList().get(0).click();
        for (int i = 0; i < eventsPage.getArenaList().size(); i++){
            if (eventsPage.getArenaList().get(i).getText().contains("Demo")){
                eventsPage.getArenaList().get(i).click(); // choose arena Demo
            }
        }
        eventsPage.getTimeList().get(0).click();
        eventsPage.getDuration().click();
        eventsPage.getDurationList().get(0).click();
        eventsPage.getStartTime().click();
        eventsPage.getStartTimeList().get(12).click(); //choose 10:00
        eventsPage.getButtonCreateGame().click();
        createGameOptionsPage.getOpenListOptionTeam().click();
        assertEquals("Нет, у меня есть свои игроки, поиграем между собой",
                createGameOptionsPage.getListOptions().get(1).getText()); // check that there is closed game
        assertEquals("Хочу найти игроков для совместной игры",
                createGameOptionsPage.getListOptions().get(2).getText()); // check that there is opened game
        commonHandlers.logout();
    }
}


