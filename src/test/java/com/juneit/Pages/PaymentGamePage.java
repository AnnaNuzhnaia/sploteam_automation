package com.juneit.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PaymentGamePage {

    public static final String EMAIL_XPATH = "//*[@id=\"root\"]/div[2]/div/div[2]/div/div[4]/span[2]/div[1]";
    public static final String PRICE_GAME_XPATH = "//*[@id=\"root\"]/div[2]/div/div[2]/div/div[2]/span[2]";
    public static final String NAME_GAMER_XPATH = "//*[@id=\"root\"]/div[2]/div/div[2]/div/div[1]/span[2]";
    public static final String BUTTON_CONFIRM_PAY_GAME_XPATH =
            "//*[@id=\"root\"]/div[2]/div/div[2]/div/div[4]/span[3]/form[1]/button[1]";
    public static final String BUTTON_CONFIRM_PAY_GAME_FROM_DEPOSIT_XPATH =
            "//*[@id=\"root\"]/div[2]/div/div[2]/div/div[4]/span[3]/form[1]/button[2]";

    private WebDriver driver;
    public PaymentGamePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getEmailInfoText(){
        return driver.findElement(By.xpath(EMAIL_XPATH)).getText();
    }

    public String getPriceInfoText(){
        return driver.findElement(By.xpath(PRICE_GAME_XPATH)).getText();
    }

    public String getNameGamer(){
        return driver.findElement(By.xpath(NAME_GAMER_XPATH)).getText();
    }

    public WebElement getButtonPayByCard(){
        return driver.findElement(By.xpath(BUTTON_CONFIRM_PAY_GAME_XPATH));
    }

    public WebElement getButtonPayFromDeposit(){
        return driver.findElement(By.xpath(BUTTON_CONFIRM_PAY_GAME_FROM_DEPOSIT_XPATH));
    }

}
