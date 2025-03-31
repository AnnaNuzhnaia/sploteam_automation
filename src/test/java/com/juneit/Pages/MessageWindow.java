package com.juneit.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class MessageWindow {

    public static final String CLOSE_REACTION_MESSAGE_XPATH = "/html/body/div[3]/div/div/button";
    public static final String REACTION_MESSAGE_CLASS = "ReactModal__Content";
    public static final String MESSAGE_LEAVE_GAME_CLASS = "ViewEventPage_pageAlert__2Fb_Z";
    public static final String CONFIRM_LEAVE_GAME_XPATH = "/html/body/div[3]/div/div/div/div/button[1]";

    private WebDriver driver;
    public MessageWindow(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getCloseMessageWindow(){
        return driver.findElement(By.xpath(CLOSE_REACTION_MESSAGE_XPATH));
    }

    public List<WebElement> getOpenMessageWindow(){
        return driver.findElements(By.className(REACTION_MESSAGE_CLASS));
    }

    public String getMessageAboutLeaveGameText(){
        return driver.findElement(By.className(MESSAGE_LEAVE_GAME_CLASS)).getText();
    }

    public WebElement getButtonConfirmLeaveGame(){
        return driver.findElement(By.xpath(CONFIRM_LEAVE_GAME_XPATH));
    }
}
