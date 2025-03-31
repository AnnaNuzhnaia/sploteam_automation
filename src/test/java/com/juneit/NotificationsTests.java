package com.juneit;

import com.juneit.Pages.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NotificationsTests {
    private final PropertiesLoader properties = new PropertiesLoader();
    private static final WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    MainPage mainPage = new MainPage(driver);
    PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
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
    @Order(1)
    public void assertNumberNotificationsAfterReading() throws InterruptedException {
        commonHandlers.login(properties.userEmail, properties.userPassword);
        mainPage.getNotifications().click();
        int numberUnreadNotification = Integer.parseInt(personalAccountPage.getNumberUnreadNotification().getText());
        System.out.println(numberUnreadNotification);
        personalAccountPage.getListUnreadNotification().get(0).click();
        //until the unread message icon (dot on the left) is no longer displayed
        wait.until(ExpectedConditions.invisibilityOf(personalAccountPage.getIconReadNotification(0)));
        int newNumberUnreadNotification = Integer.parseInt(personalAccountPage.getNumberUnreadNotification().getText());
        System.out.println(newNumberUnreadNotification);
        assertEquals(newNumberUnreadNotification, numberUnreadNotification - 1);
        commonHandlers.logout();
    }

    @Test
    @Order(2)
    public void assertAllNotificationsRead() throws InterruptedException {
        commonHandlers.login(properties.userEmail, properties.userPassword);
        mainPage.getNotifications().click();
        personalAccountPage.getCheckboxHighlightAllNotifications().click();
        personalAccountPage.getReadNotificationButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(personalAccountPage.getSectionNotification(),
                "Не найдено уведомлений по выбранной категории."));
//        check that the message counter in the bell and notification menu disappears
        assertTrue(mainPage.getCountNotificationInHeader().isEmpty());
        assertTrue(personalAccountPage.getNumberUnreadNotificationList().isEmpty());
        commonHandlers.logout();
    }

    @Test
    @Order(3)
    public void assertDelete2Notifications() throws InterruptedException {
        commonHandlers.login(properties.userEmail, properties.userPassword);
        mainPage.getUserNameHeader().click();
        wait.until(ExpectedConditions.invisibilityOf(personalAccountPage.getLoaderWrapper()));
        wait.until(ExpectedConditions.textToBePresentInElement(personalAccountPage.getMenuPersonalAccount().get(6),
                "Уведомления"));
        personalAccountPage.getMenuPersonalAccount().get(6).click();
        personalAccountPage.getTypeNotification().get(0).click();
        personalAccountPage.getCheckboxHighlightAllNotifications().click();
        int numberAllNotification = Integer.parseInt(personalAccountPage.getNumberSelectedNotifications().getText());
        System.out.println(numberAllNotification);
        personalAccountPage.getCheckboxHighlightAllNotifications().click();
        personalAccountPage.getCheckboxHighlightNotification().get(1).click();
        personalAccountPage.getCheckboxHighlightNotification().get(2).click();
        personalAccountPage.getDeleteNotificationButton().click();
        wait.until(ExpectedConditions.invisibilityOf(personalAccountPage.getDeleteNotificationButton()));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className(personalAccountPage.SECTION_NOTIFICATION_BUTTON_CLASS),0));
        personalAccountPage.getCheckboxHighlightAllNotifications().click();
        int newNumberAllNotification = Integer.parseInt(personalAccountPage.getNumberSelectedNotifications().getText());
        System.out.println(newNumberAllNotification);
        assertEquals(newNumberAllNotification, numberAllNotification - 2);
        commonHandlers.logout();
    }

    @Test
    @Order(4)
    public void assertDeleteAllNotifications() throws InterruptedException {
        commonHandlers.login(properties.userEmail, properties.userPassword);
        mainPage.getUserNameHeader().click();
        wait.until(ExpectedConditions.invisibilityOf(personalAccountPage.getLoaderWrapper()));
        wait.until(ExpectedConditions.textToBePresentInElement(personalAccountPage.getMenuPersonalAccount().get(6),
                "Уведомления"));        personalAccountPage.getMenuPersonalAccount().get(6).click();
        personalAccountPage.getTypeNotification().get(0).click();
        personalAccountPage.getCheckboxHighlightAllNotifications().click();
        personalAccountPage.getDeleteNotificationButton().click();
        wait.until(ExpectedConditions.invisibilityOf(personalAccountPage.getDeleteNotificationButton()));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className(personalAccountPage.SECTION_NOTIFICATION_BUTTON_CLASS),0));        assertEquals("Не найдено уведомлений по выбранной категории.",
                personalAccountPage.getMessageNotFoundfNotifications().getText());
        commonHandlers.logout();
    }

    @Test
    public void assertDelete1Notifications() {
        commonHandlers.login(properties.userEmail, properties.userPassword);
        mainPage.getUserNameHeader().click();
        wait.until(ExpectedConditions.invisibilityOf(personalAccountPage.getLoaderWrapper()));
        wait.until(ExpectedConditions.textToBePresentInElement(personalAccountPage.getMenuPersonalAccount().get(6),
                "Уведомления"));
        personalAccountPage.getMenuPersonalAccount().get(6).click();
        personalAccountPage.getTypeNotification().get(0).click();
        int numberAllNotification = 0;
        int notificationPagesSize = personalAccountPage.getNotificationPages().size();
        if (notificationPagesSize == 0) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(personalAccountPage.NOTIFICATION_SECTION_CLASS)));
            numberAllNotification = personalAccountPage.getNotificationSections().size();
        } else {
            for (int i = 0; i < notificationPagesSize; i++) {
                personalAccountPage.getNotificationPages().get(i).click();
                wait.until(ExpectedConditions.attributeContains(personalAccountPage.getNotificationPages().get(i), "class", "PageButton_selected__3547Y"));
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(personalAccountPage.NOTIFICATION_SECTION_CLASS)));
                numberAllNotification += personalAccountPage.getNotificationSections().size();
            }
            personalAccountPage.getNotificationPages().get(0).click();
        }
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(personalAccountPage.getCheckboxHighlightNotification().get(1))
        ));
        personalAccountPage.getCheckboxHighlightNotification().get(1).click();
        personalAccountPage.getDeleteNotificationButton().click();
        wait.until(ExpectedConditions.invisibilityOf(personalAccountPage.getDeleteNotificationButton()));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className(personalAccountPage.SECTION_NOTIFICATION_BUTTON_CLASS),0));
        int numberAllNotificationAfter = 0;
        if (notificationPagesSize == 0) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(personalAccountPage.NOTIFICATION_SECTION_CLASS)));
            numberAllNotificationAfter = personalAccountPage.getNotificationSections().size();
        } else {
            for (int i = 0; i < notificationPagesSize; i++) {
                personalAccountPage.getNotificationPages().get(i).click();
                wait.until(ExpectedConditions.attributeContains(personalAccountPage.getNotificationPages().get(i), "class", "PageButton_selected__3547Y"));
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(personalAccountPage.NOTIFICATION_SECTION_CLASS)));
                numberAllNotificationAfter += personalAccountPage.getNotificationSections().size();
            }
            personalAccountPage.getNotificationPages().get(0).click();
        }
        assertEquals(numberAllNotificationAfter, numberAllNotification-1);
        commonHandlers.logout();
    }
}
