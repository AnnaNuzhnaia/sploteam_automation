package com.juneit;

import com.juneit.Pages.*;
import io.opentelemetry.context.Scope;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class AvatarAndLogoTests {
    private final PropertiesLoader properties = new PropertiesLoader();
    private static final WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
    public void assertDefaultAvatarInUserProfile() throws IOException, URISyntaxException {
        commonHandlers.login(properties.userEmail, properties.userPassword);
        mainPage.getUserNameHeader().click();
//        commonHandlers.takeAndSaveScreenshot(personalAccountPage.getAvatarAreaUserProfile(),
//                "ExpectedDefaultAvatarScreenshot.png");
        commonHandlers.assertCompareScreenschotAndExpectedImage(personalAccountPage.getAvatarAreaUserProfile(),
                "ExpectedDefaultAvatarScreenshot.png");
        commonHandlers.logout();
    }

    @Test
    public void assertDefaultAvatarInHeader() throws IOException, URISyntaxException {
        commonHandlers.login(properties.userEmail, properties.userPassword);
//        commonHandlers.takeAndSaveScreenshot(mainPage.getAvatarAreaUserInHeaderProfile(),
//                 "ExpectedDefaultAvatarInHeaderScreenshot.png");
        commonHandlers.assertCompareScreenschotAndExpectedImage(mainPage.getAvatarAreaUserInHeaderProfile(),
                "ExpectedDefaultAvatarInHeaderScreenshot.png");
        commonHandlers.logout();
    }

    @Test
    public void assertLoadingImageForAvatarInDialogWindow() throws URISyntaxException, InterruptedException, IOException {
        commonHandlers.login(properties.userEmail, properties.userPassword);
        mainPage.getUserNameHeader().click();
        personalAccountPage.getEditUserProfileButton().click();
        personalAccountPage.getChangeAvatarButton().click();
        //load file from resource
       File file = commonHandlers.loadFileFromResource("image.jpeg");
        //pass the full file path
        personalAccountPage.getChooseFileForAvatarInput().sendKeys(file.getAbsolutePath());
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountPage.getConfirmChooseFileForAvatarButton()));
        wait.until(ExpectedConditions.textToBePresentInElement(personalAccountPage.getChangeAvatarCropMessage(),
                "Пожалуйста, выберите часть изображения, которую Вы хотите использовать в качестве аватара."));
//      commonHandlers.takeAndSaveScreenshot(personalAccountPage.getAvatarAreaDialogWindow(),
//                "ExpectedAvatarscreenshot.png");
        commonHandlers.assertCompareScreenschotAndExpectedImage(personalAccountPage.getAvatarAreaDialogWindow(),
                "ExpectedAvatarscreenshot.png");
        personalAccountPage.getCloseChangeAvatarButton().click();
        personalAccountPage.getSaveEditProfileButton().click();
        personalAccountPage.getCloseSaveEditProfileMessageButton().click();
        commonHandlers.logout();
    }

    @Test
    public void assertLoadingImageForAvatarFromDesktop() throws URISyntaxException, InterruptedException, IOException {
        commonHandlers.login(properties.userEmail, properties.userPassword);
        mainPage.getUserNameHeader().click();
        personalAccountPage.getEditUserProfileButton().click();
        personalAccountPage.getChangeAvatarButton().click();
        // load file from desktop
        String desktopPath = System.getProperty("user.home") + "/Desktop/image.jpeg";
        personalAccountPage.getChooseFileForAvatarInput().sendKeys(desktopPath);
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountPage.getConfirmChooseFileForAvatarButton()));
        wait.until(ExpectedConditions.textToBePresentInElement(personalAccountPage.getChangeAvatarCropMessage(),
                "Пожалуйста, выберите часть изображения, которую Вы хотите использовать в качестве аватара."));
//        commonHandlers.takeAndSaveScreenshot(personalAccountPage.getAvatarAreaDialogWindow(),
//                "ExpectedAvatarScreenshotFromDesktop.png");
        commonHandlers.assertCompareScreenschotAndExpectedImage(personalAccountPage.getAvatarAreaDialogWindow(),
                "ExpectedAvatarScreenshotFromDesktop.png");
        personalAccountPage.getCloseChangeAvatarButton().click();
        personalAccountPage.getSaveEditProfileButton().click();
        personalAccountPage.getCloseSaveEditProfileMessageButton().click();
        commonHandlers.logout();
    }

    @Test
    public void assertLoadingIncorrectFileTypeImageForAvatar() throws URISyntaxException, InterruptedException, IOException {
       commonHandlers.login(properties.userEmail, properties.userPassword);
        mainPage.getUserNameHeader().click();
        personalAccountPage.getEditUserProfileButton().click();
        personalAccountPage.getChangeAvatarButton().click();
        File file = commonHandlers.loadFileFromResource("negative_test_image.gif");
        personalAccountPage.getChooseFileForAvatarInput().sendKeys(file.getAbsolutePath());
//switch to alert window
        Alert alert = driver.switchTo().alert();
        assertEquals("Тип файла должен быть jpeg или png.", alert.getText());
        alert.accept();
//check that there isn't button 'Подтвердить'
        assertEquals(0, personalAccountPage.getElementConfirmButton().size());
        personalAccountPage.getCloseChangeAvatarButton().click();
        personalAccountPage.getSaveEditProfileButton().click();
        personalAccountPage.getCloseSaveEditProfileMessageButton().click();
        commonHandlers.logout();
    }

    @Test
    public void assertLogo() throws IOException, URISyntaxException {
//        commonHandlers.takeAndSaveScreenshot(mainPage.getLogoHeader(),"ExpectedLogoHeader.png");
        commonHandlers.assertCompareScreenschotAndExpectedImage(mainPage.getLogoHeader(),
               "ExpectedLogoHeader.png");
    }

    @Test
    public void assertCampsImage() throws IOException, URISyntaxException {
//        commonHandlers.takeAndSaveScreenshot(mainPage.getImageForCampsButton(),"ExpectedImageCampsButton.png");
        commonHandlers.assertCompareScreenschotAndExpectedImage(mainPage.getImageForCampsButton(),
                "ExpectedImageCampsButton.png");
    }
}


