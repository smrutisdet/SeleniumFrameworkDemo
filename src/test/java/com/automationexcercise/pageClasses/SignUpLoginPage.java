package com.automationexcercise.pageClasses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Properties;

public class SignUpLoginPage {
    WebDriver driver;
    Properties prop;
    private Logger log;
    private Boolean status;
    private String expectedTitle;
    @FindBy(xpath = "//input[@data-qa='login-email']")
    @CacheLookup
    private WebElement emailIdTextBox;
    @FindBy(xpath = "//input[@data-qa='login-password']")
    @CacheLookup
    private WebElement passwordTextBox;
    @FindBy(xpath = "//button[@data-qa='login-button']")
    @CacheLookup
    private WebElement loginButton;

    public SignUpLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log = LogManager.getLogger(this.getClass().getName());
    }

    public void enterUserName(String userName) {
      emailIdTextBox.sendKeys(userName);
        log.info("Entered user name : "+userName);
    }
    public void enterPassword(String password) {
        passwordTextBox.sendKeys(password);
        log.info("Entered password : "+password);
    }
    public void clickLoginButton() {
       loginButton.click();
        log.info("Successfully clicked on login button");
    }
    public Boolean verifySignUpLogInPageTitle() {
        try {
            prop = new Properties();
            prop.load(SignUpLoginPage.class.getClassLoader().getResourceAsStream("configuration.properties"));
            expectedTitle = prop.getProperty("expectedSignupLoginPageTitle");
            log.info("Expected page title is: " + expectedTitle);
            if(expectedTitle.equalsIgnoreCase(driver.getTitle())){
                log.info("Actual Page tile is matching with expected page title value");
                status=true;
            }
            else{
                log.info("Actual Page tile is not matching with expected page title value");
                status=false;
            }
            //Assert.assertEquals(expectedTitle, driver.getTitle(), "page title is mismatching");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
