package com.juneit;

import com.juneit.Pages.*;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommonHandlers {
    private final WebDriver driver;
    WebDriverWait wait;
    // Page objects
    MainPage mainPage;
    EventsPage eventsPage;
    CreateGameOptionsPage createGameOptionsPage;
    LoginWindow loginWindow;
    PersonalAccountPage personalAccountPage;
    ClassLoader classLoader;
    public CommonHandlers(WebDriver driver) {
        this.driver = driver;  // Initialize driver
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // Initialize page objects
        this.mainPage = new MainPage(driver);
        this.eventsPage = new EventsPage(driver);
        this.createGameOptionsPage = new CreateGameOptionsPage(driver);
        this.loginWindow = new LoginWindow(driver);
        this.personalAccountPage = new PersonalAccountPage(driver);
        this.classLoader = AvatarAndLogoTests.class.getClassLoader();
    }

    public void login(String userEmail, String userPassword) {
        mainPage.getSignInButton().click();
        loginWindow.getInputEmailLogin().sendKeys(userEmail);
        loginWindow.getInputPasswordLogin().sendKeys(userPassword);
        loginWindow.getSignInWindowButton().click();
    }

    public void logout() {
        mainPage.getUserNameHeader().click();
        personalAccountPage.getLogOutButton().click();
    }

    // Compare images pixel by pixel
    public boolean compareImages(File screenshot, File referenceImage) throws IOException {
        BufferedImage img1 = ImageIO.read(screenshot);
        BufferedImage img2 = ImageIO.read(referenceImage);

        if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
            return false;
        }

        for (int x = 0; x < img1.getWidth(); x++) {
            for (int y = 0; y < img1.getHeight(); y++) {
                if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    //take a screenshot and compare it to the reference
    public void assertCompareScreenschotAndExpectedImage(WebElement element, String expectedImageFileName) throws URISyntaxException, IOException {
        //take a screenshot element
        File screenshot = element.getScreenshotAs(OutputType.FILE);
        //load the expected file from the resource
        URL resourceUrl = classLoader.getResource(expectedImageFileName);
        File expectedImageFile = Paths.get(resourceUrl.toURI()).toFile();
        //compare files
        assertTrue(compareImages(screenshot, expectedImageFile));
    }

    //load the file from the resource
    public File loadFileFromResource(String FileName) throws URISyntaxException, IOException {
        URL resource = classLoader.getResource(FileName);
        //return the file path
        return Paths.get(resource.toURI()).toFile();
    }

    //create reference data, then manually transfer to the resource folder
    public void takeAndSaveScreenshot(WebElement element, String fileName) throws IOException {
        //take a screenshot element
        File screenshot = element.getScreenshotAs(OutputType.FILE);
        //specify the path for saving
        File destinationFile = new File("target/" + fileName);
        //copy the screenshot to a file
        FileHandler.copy(screenshot, destinationFile);
    }
}
