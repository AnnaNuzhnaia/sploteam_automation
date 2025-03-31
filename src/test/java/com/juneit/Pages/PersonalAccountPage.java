package com.juneit.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class PersonalAccountPage {

    public static final String LOGOUT_BUTTON_CLASS = "LKLayout_exit__1QjSv";
    public static final String MY_GAMES_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[1]/div/div[2]";
    public static final String PROFILE_CARD_PERSONAL_ACCOUNT_TEXT_XPATH =
            "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[3]/div[1]";
    public static final String NUMBER_UNREAD_NOTIFICATION_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[1]/div/div[2]/div[2]";
    public static final String LIST_UNREAD_NOTIFICATION_CLASS = "Notification_notification___WK9I";
    public static final String TYPE_NOTIFICATION_CLASS = "NotificationGroupsNav_categoryOption__2-oRm";
    public static final String ICON_UNREAD_NOTIFICATION_CLASS = "Notification_unreadIcon__cDpno";
    public static final String CHECKBOX_HIGHLIGHT_ALL_CLASS = "FlatOrangeCheckbox_checkbox__3PiR2";
    public static final String READ_NOTIFICATION_BUTTON_CLASS = "NotificationsHeader_readButton__3oVV_";
    public static final String NOT_FOUND_NOTIFICATION_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div";
    public static final String SECTION_NOTIFICATION_CLASS = "NotificationsSection_column2__q4wB4";
    public static final String MENU_PERSONAL_ACCOUNT_CLASS = "Tabs_tab__3e8GV";
    public static final String SECTION_MENU_PERSONAL_ACCOUNT_CLASS = "Tabs_tabList__1pP5W ";
    public static final String NUMBER_SELECTED_NOTIFICATION_CLASS = "NotificationsHeader_selected__value__2Aj6L";
    public static final String CHECKBOX_HIGHLIGHT_NOTIFICATION_CLASS = "FlatOrangeCheckbox_label__2d43u";
    public static final String DELETE_NOTIFICATION_BUTTON_CLASS = "NotificationsHeader_deleteButton__3bMJ4";
    public static final String AVATAR_AREA_USER_PROFILE_CLASS = "ProfileAvatar_avatar__tog10";
    public static final String EDIT_USER_PROFILE_BUTTON_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div[2]/div/div/div[2]/div[4]";
    public static final String CHANGE_AVATAR_BUTTON_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div/div[1]/div[2]";
    public static final String CHOSE_FILE_FOR_AVATAR_INPUT = "input[type='file']";
    public static final String CONFIRM_CHOOSE_FILE_FOR_AVATAR_BUTTON_XPATH = "/html/body/div[3]/div/div/div/div[3]/button[2]";
    public static final String CHANGE_AVATAR_MESSAGE_CLASS = "avatar_avatarCropHelpText__2HBWi";
    public static final String AVATAR_AREA_DIALOG_WINDOW_XPATH = "/html/body/div[3]/div/div/div/div[2]/div/div[1]/div";
    public static final String CLOSE_CHANGE_AVATAR_BUTTON_XPATH = "/html/body/div[3]/div/div/button";
    public static final String SAVE_EDIT_PROFILE_BUTTON_XPATH = "//*[@id=\"root\"]/div[2]/div/div[3]/div/div[2]/form/button";
    public static final String CLOSE_SAVE_EDIT_PROFILE_MESSAGE_BUTTON_XPATH = "/html/body/div[3]/div/div/button";
    public static final String NOTIFICATION_SECTION_CLASS = "Notification_notification___WK9I";
    public static final String ICON_READ_NOTIFICATION_CLASS = "Notification_slideOut__2_2xA";
    public static final String SECTION_NOTIFICATION_BUTTON_CLASS = "NotificationsHeader_buttons__36NOg";
    public static final String NOTIFICATION_PAGES_CLASS = "PageButton_pageButton__2_oJ7";
    public static final String LOADER_WRAPPER_CLASS = "Loader_wrapper__YL9BV";

    private WebDriver driver;

    public List<WebElement> getNotificationPages(){
        List<WebElement> notificationPages = driver.findElements(By.className(NOTIFICATION_PAGES_CLASS));
        return notificationPages;
    }

    public WebElement getLoaderWrapper(){
        WebElement loaderWrapper = driver.findElement(By.className(LOADER_WRAPPER_CLASS));
        return loaderWrapper;
    }

    public WebElement getSectionMenu(){
        WebElement sectionMenu = driver.findElement(By.className(SECTION_MENU_PERSONAL_ACCOUNT_CLASS));
        return sectionMenu;
    }

    public WebElement getSectionNotification(){
        WebElement sectionNotification = driver.findElement(By.className(SECTION_NOTIFICATION_CLASS));
        return sectionNotification;
    }

    public List<WebElement> getNotificationSections(){
        List<WebElement> notificationSections = driver.findElements(By.className(NOTIFICATION_SECTION_CLASS));
        return notificationSections;
    }

    public List<WebElement> getIconUnreadNotification(int index){
        List<WebElement> iconUnreadNotification = getNotificationSections().get(index).findElements(By.className(ICON_UNREAD_NOTIFICATION_CLASS));
        return iconUnreadNotification;
    }

    public WebElement getIconReadNotification(int index){
        WebElement iconReadNotification = getNotificationSections().get(index).findElement(By.className(ICON_READ_NOTIFICATION_CLASS));
        return iconReadNotification;
    }

    public WebElement getCloseSaveEditProfileMessageButton(){
        WebElement closeSaveEditProfileMessageButton = driver.findElement(By.xpath(CLOSE_SAVE_EDIT_PROFILE_MESSAGE_BUTTON_XPATH));
        return closeSaveEditProfileMessageButton;
    }

    public WebElement getSaveEditProfileButton(){
        WebElement saveEditProfileButton = driver.findElement(By.xpath(SAVE_EDIT_PROFILE_BUTTON_XPATH));
        return saveEditProfileButton;
    }

    public WebElement getCloseChangeAvatarButton(){
        WebElement closeChangeAvatarButton = driver.findElement(By.xpath(CLOSE_CHANGE_AVATAR_BUTTON_XPATH));
        return closeChangeAvatarButton;
    }

    public WebElement getAvatarAreaDialogWindow(){
        WebElement avatarAreaDialogWindow = driver.findElement(By.xpath(AVATAR_AREA_DIALOG_WINDOW_XPATH));
        return avatarAreaDialogWindow;
    }

    public WebElement getChangeAvatarCropMessage(){
        WebElement changeAvatarCropMessage = driver.findElement(By.className(CHANGE_AVATAR_MESSAGE_CLASS));
        return changeAvatarCropMessage;
    }

    public WebElement getConfirmChooseFileForAvatarButton(){
        WebElement confirmChooseFileForAvatarButton = driver.findElement(By.xpath(CONFIRM_CHOOSE_FILE_FOR_AVATAR_BUTTON_XPATH));
        return confirmChooseFileForAvatarButton;
    }

    public WebElement getChooseFileForAvatarInput(){
        WebElement chooseFileForAvatarInput = driver.findElement(By.cssSelector(CHOSE_FILE_FOR_AVATAR_INPUT));
        return chooseFileForAvatarInput;
    }

    public WebElement getChangeAvatarButton(){
        WebElement changeAvatarButton = driver.findElement(By.xpath(CHANGE_AVATAR_BUTTON_XPATH));
        return changeAvatarButton;
    }

    public WebElement getEditUserProfileButton(){
        WebElement editUserProfileButton = driver.findElement(By.xpath(EDIT_USER_PROFILE_BUTTON_XPATH));
        return editUserProfileButton;
    }

    public WebElement getAvatarAreaUserProfile(){
        WebElement avatarAreaUserProfile = driver.findElement(By.className(AVATAR_AREA_USER_PROFILE_CLASS));
        return avatarAreaUserProfile;
    }

    public WebElement getDeleteNotificationButton(){
        WebElement deleteNotificationButton = driver.findElement(By.className(DELETE_NOTIFICATION_BUTTON_CLASS));
        return deleteNotificationButton;
    }

    public List<WebElement> getCheckboxHighlightNotification(){
        List<WebElement> checkboxHighlightNotification = driver.findElements(By.className(CHECKBOX_HIGHLIGHT_NOTIFICATION_CLASS));
        return checkboxHighlightNotification;
    }

    public WebElement getNumberSelectedNotifications(){
        WebElement numberSelectedNotifications = driver.findElement(By.className(NUMBER_SELECTED_NOTIFICATION_CLASS));
        return numberSelectedNotifications;
    }

    public List<WebElement> getMenuPersonalAccount(){
        List<WebElement> menuPersonalAccount = driver.findElements(By.className(MENU_PERSONAL_ACCOUNT_CLASS));
        return menuPersonalAccount;
    }

    public WebElement getMessageNotFoundfNotifications(){
        WebElement messageNotFoundfNotifications = driver.findElement(By.xpath(NOT_FOUND_NOTIFICATION_XPATH));
        return messageNotFoundfNotifications;
    }

    public WebElement getCheckboxHighlightAllNotifications(){
        WebElement checkboxHighlightAllNotifications = driver.findElement(By.className(CHECKBOX_HIGHLIGHT_ALL_CLASS));
        return checkboxHighlightAllNotifications;
    }

    public WebElement getReadNotificationButton(){
        WebElement readNotificationButton = driver.findElement(By.className(READ_NOTIFICATION_BUTTON_CLASS));
        return readNotificationButton;
    }

    public List<WebElement> getListUnreadNotification(){
        List<WebElement> listUnreadNotification = driver.findElements(By.className(LIST_UNREAD_NOTIFICATION_CLASS));
        return listUnreadNotification;
    }

    public List<WebElement> getTypeNotification(){
        List<WebElement> typeNotification = driver.findElements(By.className(TYPE_NOTIFICATION_CLASS));
        return typeNotification;
    }

    public WebElement getNumberUnreadNotification(){
        WebElement numberUnreadNotification = driver.findElement(By.xpath(NUMBER_UNREAD_NOTIFICATION_XPATH));
        return numberUnreadNotification;
    }

    public List<WebElement> getNumberUnreadNotificationList(){
        List<WebElement> numberUnreadNotificationList = driver.findElements(By.xpath(NUMBER_UNREAD_NOTIFICATION_XPATH));
        return numberUnreadNotificationList;
    }


    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getLogOutButton(){
        return driver.findElement(By.className(LOGOUT_BUTTON_CLASS));
    }

    public WebElement getMyGamesList(){
        return driver.findElement(By.xpath(MY_GAMES_XPATH));
    }

    public String getDepositInfoText(){
        return driver.findElement(By.xpath(PROFILE_CARD_PERSONAL_ACCOUNT_TEXT_XPATH)).getText();
    }

    public List<WebElement> getElementConfirmButton(){
        List<WebElement> elementConfirmButton = driver.findElements(By.xpath(CONFIRM_CHOOSE_FILE_FOR_AVATAR_BUTTON_XPATH));
        return elementConfirmButton;
    }
}