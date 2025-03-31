package com.juneit.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class MyGamesPage {

    public static final String CARD_GAME_BUTTON_ABOUT_GAME_CLASS = "EventCard_bottomRow__1CodI";
    public static final String CARD_GAME_INFO_SPORT_CLASS =  "EventCard_sport__2ZcAA";
    public static final String CARD_GAME_INFO_ARENA_CLASS = "EventCard_eventTypeRow__arena__3ljYS";
    public static final String CARD_GAME_INFO_PRICE_CLASS = "EventCard_price__6dgeG";
    public static final String CARD_GAME_INFO_DATE_CLASS = "EventCard_mainContent__firstRow__1WLC5";

    private WebDriver driver;
    public MyGamesPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getCardGameButtonAboutGame(){
        return driver.findElements(By.className(CARD_GAME_BUTTON_ABOUT_GAME_CLASS));
    }
    public List<WebElement> getCardGameInfoSport(){
        return driver.findElements(By.className(CARD_GAME_INFO_SPORT_CLASS));
    }
    public List<WebElement> getCardGameInfoArena(){
        return driver.findElements(By.className(CARD_GAME_INFO_ARENA_CLASS));
    }
    public List<WebElement> getCardGameInfoPrice(){
        return driver.findElements(By.className(CARD_GAME_INFO_PRICE_CLASS));
    }
    public List<WebElement> getCardGameInfoDate(){
        return driver.findElements(By.className(CARD_GAME_INFO_DATE_CLASS));
    }

}
