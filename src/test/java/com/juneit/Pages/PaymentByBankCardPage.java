package com.juneit.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PaymentByBankCardPage {

    public static final String PAGE_PAYMENT_WITH_BANKCARD_CLASS = "c-heading";
    public static final String PAYMENT_PLATFORM_BUTTON_PAY_CLASS = "r-submit";

    private WebDriver driver;
    public PaymentByBankCardPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPagePaymentByBankCard(){
        return driver.findElement(By.className(PAGE_PAYMENT_WITH_BANKCARD_CLASS));
    }

    public WebElement getButtonPayment(){
        return driver.findElement(By.className(PAYMENT_PLATFORM_BUTTON_PAY_CLASS));
    }
}
