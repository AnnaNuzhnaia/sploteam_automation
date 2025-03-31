package com.juneit.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class CreateGameOptionsPage {

    public static final String INFO_CREATE_GAME_TITLE_PAGE_CLASS = "Common_pageTitle__6GYuY";
    public static final String BUTTON_PAY_NEW_GAME_XPATH = "//*[@id=\"root\"]/div[2]/div/div/div[2]/form/div[6]/button";
    public static final String LIST_OPTIONS_CLASS = "react-select__option";
    public static final String OPTION_GAMER_FOR_GAME_XPATH = "//*[@id=\"root\"]/div[2]/div/div/div[2]/form/div[1]/div[1]/div/div[1]/div";
    public static final String OPEN_LIST_OPTION_TEAM_XPATH = "//*[@id=\"root\"]/div[2]/div/div/div[2]/form/div[1]/div/div/div[1]";
    public static final String INFO_ABOUT_CREATE_GAME_XPATH = "//*[@id=\"root\"]/div[2]/div/div/div[1]";
    public static final String OPEN_LIST_LEVEL_XPATH = "//*[@id=\"root\"]/div[2]/div/div/div[2]/form/div[4]/div/div/div[2]/div";
    public static final String OPEN_LIST_FORMAT_XPATH = "//*[@id=\"root\"]/div[2]/div/div/div[2]/form/div[3]/div/div/div[2]/div";
    public static final String OPEN_LIST_GAME_XPATH = "//*[@id=\"root\"]/div[2]/div/div/div[2]/form/div[2]/div/div[1]/div[2]";
    public static final String FIND_TEAM_TEXT_CLASS = "ControlLabel_controlLabel__1Kfgg";
    public static final String FINAL_PRICE_GAME_CLASS = "EventCreatePage_createGame__form__price__2dWmf";
    public static final String NAME_GAME_TEXT_XPATH = "//*[@id=\"root\"]/div[2]/div/div/div[2]/form/div[2]/div/div/div[1]/div";

    private WebDriver driver;
    public CreateGameOptionsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getTitlePage(){
        return driver.findElements(By.className(INFO_CREATE_GAME_TITLE_PAGE_CLASS));
    }

    public List<WebElement> getListOptions(){
        return driver.findElements(By.className(LIST_OPTIONS_CLASS));
    }

    public String getInfoAboutCreateGameText(){
        return driver.findElement(By.xpath(INFO_ABOUT_CREATE_GAME_XPATH)).getText();
    }

    public String getChosenTeamForGameText(){
        return driver.findElement(By.xpath(OPTION_GAMER_FOR_GAME_XPATH)).getText();
    }

    public WebElement getOpenListOptionTeam(){
        return driver.findElement(By.xpath(OPEN_LIST_OPTION_TEAM_XPATH));
    }

    public WebElement getListLevel(){
        return driver.findElement(By.xpath(OPEN_LIST_LEVEL_XPATH));
    }

    public WebElement getListFormat(){
        return driver.findElement(By.xpath(OPEN_LIST_FORMAT_XPATH));
    }

    public WebElement getListGame(){
        return driver.findElement(By.xpath(OPEN_LIST_GAME_XPATH));
    }

    public String getTextTitleFindTeam(){
        return driver.findElement(By.className(FIND_TEAM_TEXT_CLASS)).getText();
    }

    public String getTextFinalPriceGame (){
        return driver.findElement(By.className(FINAL_PRICE_GAME_CLASS)).getText();
    }

    public String getTextGame(){
        return driver.findElement(By.xpath(NAME_GAME_TEXT_XPATH)).getText();
    }

    public WebElement getButtonPayGame(){
        return driver.findElement(By.xpath(BUTTON_PAY_NEW_GAME_XPATH));
    }

    public void chooseRandomSport() {
        if (getListOptions().size() == 1) {
            getListOptions().get(0).click();
        } else {
            int randomSport = ThreadLocalRandom.current().nextInt(0,
                    getListOptions().size() - 1);
            getListOptions().get(randomSport).click();
        }
    }

    public void chooseRandomFormat() {
        if (getListOptions().size() == 1) {
            getListOptions().get(0).click();
        } else {
            int randomFormat = ThreadLocalRandom.current().nextInt(0,
                    getListOptions().size() - 1);
            getListOptions().get(randomFormat).click();
        }
    }

    public void chooseRandomLevel() {
        int randomLevel = ThreadLocalRandom.current().nextInt(0,
                getListOptions().size() - 1);
        getListOptions().get(randomLevel).click();
    }
}
