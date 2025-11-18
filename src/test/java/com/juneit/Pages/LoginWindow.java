package com.juneit.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginWindow {

    public static final String INPUT_EMAIL_LOGIN_CSS_LOCATOR = "input[type='email']";
    public static final String INPUT_PASSWORD_LOGIN_NAME = "password";
    public static final String SIGNIN_DIOLOGUE_WINDOW_BUTTON_XPATH = "/html/body/div[3]/div/div/div/div[2]/form/button";
    public static final String WRONG_LOGIN_ERROR_CLASS = "form_error__2xL0z";


    private WebDriver driver;
    public LoginWindow(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getInputEmailLogin(){
        return driver.findElement(By.cssSelector(INPUT_EMAIL_LOGIN_CSS_LOCATOR));
    }

    public WebElement getInputPasswordLogin(){
        return driver.findElement(By.name(INPUT_PASSWORD_LOGIN_NAME));
    }

    public WebElement getSignInWindowButton(){
        return driver.findElement(By.xpath(SIGNIN_DIOLOGUE_WINDOW_BUTTON_XPATH));
    }

    public String getTextMessageWrongLogin(){
        return driver.findElement(By.className(WRONG_LOGIN_ERROR_CLASS)).getText();
    }



}
