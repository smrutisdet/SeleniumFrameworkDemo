package com.automationexcercise.pageClasses;

import org.apache.logging.log4j.LogManager;
import org.apache.poi.hpsf.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class HomePage {
    WebDriver driver;
    Properties prop;
    private Logger log;
    private Boolean status;
    private String expectedTitle;
    @FindBy(xpath = "//a[normalize-space()='Signup / Login']")
    @CacheLookup
    private WebElement signUpOrLoginLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log = LogManager.getLogger(this.getClass().getName());
    }

    public void clickSignupOrLogin() {
        signUpOrLoginLink.click();
        log.info("Clicked on Sign up or Log in Link");
    }
}
