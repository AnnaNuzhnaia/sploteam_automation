package com.juneit.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class GameInfoPage {

    public static final String INFO_PAGE_NEW_GAME_TITLE_CLASS = "ViewEventPage_pageTitle__3T3zC";
    public static final String PAGE_VIEW_EVENT_CLASS = "ViewEventPage_container__kXrMv";
    public static final String LEAVE_GAME_XPATH =
            "//*[@id=\"root\"]/div[2]/div/div[2]/div[2]/div[2]/div[2]/div/div/div[2]/span";
    public static final String PRICE_INFO_XPATH = "//*[@id=\"root\"]/div[2]/div/div[2]/div[1]/div[2]/ul/li[4]";

    private WebDriver driver;

    public GameInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getInfoPageNewGameTitle(){
        return driver.findElement(By.className(INFO_PAGE_NEW_GAME_TITLE_CLASS));
    }

    public WebElement getPageViewEvent(){
        return driver.findElement(By.className(PAGE_VIEW_EVENT_CLASS));
    }

    public WebElement getLeaveGame(){
        return driver.findElement(By.xpath(LEAVE_GAME_XPATH));
    }

    public String getPriceInfoText(){
        return driver.findElement(By.xpath(PRICE_INFO_XPATH)).getText();
    }
}
