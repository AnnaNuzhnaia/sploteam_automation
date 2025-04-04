package com.juneit;

import com.juneit.Pages.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

public class TimeAndCalendarTests {
    private final PropertiesLoader properties = new PropertiesLoader();
    private static final WebDriver driver = new ChromeDriver();
    MainPage mainPage = new MainPage(driver);
    EventsPage eventsPage = new EventsPage(driver);

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
    public void assertLocationTimeZone() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); // only HH:mm
        String timeString = currentTime.format(formatter);
        assertEquals(timeString, mainPage.getLocationTimeZone().getText().substring(0, 5));
    }

    @Test
    public void assertYearInCopyright() {
        String currentYear = String.valueOf(Year.now().getValue());
        assertEquals(currentYear, mainPage.getCopyRight().getText().substring(10));
    }

    @Test
    public void assertCalendar() {
        mainPage.getButtonCreateGameMainPage().click();
        eventsPage.getCalendarDateList().get(0).click();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d");
        String tomorrow = LocalDate.now().plusDays(1).format(formatter);
        String lastCalendarDate = LocalDate.now().plusDays(13).format(formatter);
        assertEquals("Сегодня", eventsPage.getCalendarDateList().get(0).getText().substring(3));
        assertEquals(tomorrow, eventsPage.getCalendarDateList().get(1).getText().substring(3));
        assertEquals(lastCalendarDate, eventsPage.getCalendarDateList().get(13).getText().substring(3));
    }
}