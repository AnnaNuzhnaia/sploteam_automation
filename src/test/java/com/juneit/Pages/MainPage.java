package com.juneit.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class MainPage {

    public static final String SIGNIN_BUTTON_CLASS = "header__signIn";
    public static final String USER_NAME_HEADER_SPLOTEAM_CLASS = "profileText__name";
    public static final String BUTTON_CREATE_GAME_MAIN_PAGE_XPATH = "//*[@id=\"root\"]/div[2]/section[3]/div/div[2]/a";
    public static final String DEPOSIT_HEADER_SPLOTEAM_CLASS = "profileText__balance";
    public static final String LOGO_HEADER_SPLOTEAM_CLASS = "header__logo";
    public static final String SHOW_MORE_GAMES_BUTTON_XPATH = "//*[@id=\"root\"]/div[2]/section[2]/div/div[3]/a";
    public static final String LOCATION_TIME_ZONE_CLASS = "locationTimezone";
    public static final String COPY_RIGHT_CLASS = "footer__copyright";
    public static final String NOTIFICATION_BUTTON_XPATH = "//*[@id=\"root\"]/header/div/div/div[2]/div[1]";
    public static final String NUMBER_UNREAD_NOTIFICATION_XPATH = "//*[@id=\"root\"]/header/div/div/div[2]/div[1]/div";
    public static final String FAQ_BUTTON_CLASS = "FAQButton_button__v0fPl";
    public static final String FAQ_BUTTON_TEXT_CLASS = "FAQButton_hint__1BI_4";
    public static final String AVATAR_AREA_HEADER_CLASS = "UserBlock_avatar__3L1nR";
    public static final String IMAGE_FOR_CAMPS_BUTTON_CLASS = "CampsButton_img__3CoXw";
    public static final String COUNT_NOTIFICATION_CLASS = "Widget_label__3nbvW";


    private WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getCountNotificationInHeader(){
        List<WebElement> countNotificationInHeader = driver.findElements(By.className(COUNT_NOTIFICATION_CLASS));
        return countNotificationInHeader;
    }
    public WebElement getImageForCampsButton(){
        WebElement imageForCampsButton = driver.findElement(By.className(IMAGE_FOR_CAMPS_BUTTON_CLASS));
        return imageForCampsButton;
    }

    public WebElement getAvatarAreaUserInHeaderProfile(){
        WebElement avatarAreaUserInHeaderProfile = driver.findElement(By.className(AVATAR_AREA_HEADER_CLASS));
        return avatarAreaUserInHeaderProfile;
    }

    public WebElement getTextFAQButton(){
        WebElement textFAQButton = driver.findElement(By.className(FAQ_BUTTON_TEXT_CLASS));
        return textFAQButton;
    }

    public WebElement getFAQButton(){
        WebElement buttonFAQ = driver.findElement(By.className(FAQ_BUTTON_CLASS));
        return buttonFAQ;
    }

    public WebElement getNumberUnreadNotifications(){
        WebElement numberUnreadNotifications = driver.findElement(By.xpath(NUMBER_UNREAD_NOTIFICATION_XPATH));
        return numberUnreadNotifications;
    }

    public WebElement getNotifications(){
        WebElement notifications = driver.findElement(By.xpath(NOTIFICATION_BUTTON_XPATH));
        return notifications;
    }

    public WebElement getCopyRight(){
        WebElement copyRight = driver.findElement(By.className(COPY_RIGHT_CLASS));
        return copyRight;
    }

    public WebElement getLocationTimeZone(){
        WebElement locationTimeZone = driver.findElement(By.className(LOCATION_TIME_ZONE_CLASS));
        return locationTimeZone;
    }

    public WebElement getMoreGamesButton(){
        WebElement moreGamesButton = driver.findElement(By.xpath(SHOW_MORE_GAMES_BUTTON_XPATH));
        return moreGamesButton;
    }

    public WebElement getSignInButton(){
        return driver.findElement(By.className(SIGNIN_BUTTON_CLASS));
    }

    public WebElement getUserNameHeader(){
        return driver.findElement(By.className(USER_NAME_HEADER_SPLOTEAM_CLASS));
    }

    public WebElement getButtonCreateGameMainPage(){
        return driver.findElement(By.xpath(BUTTON_CREATE_GAME_MAIN_PAGE_XPATH));
    }

    public String getDepositHeaderText(){
        return driver.findElement(By.className(DEPOSIT_HEADER_SPLOTEAM_CLASS)).getText();
    }

    public WebElement getLogoHeader(){
        WebElement logoHeader = driver.findElement(By.className(LOGO_HEADER_SPLOTEAM_CLASS));
        return logoHeader;
    }



}
