package com.automationexcercise.pageClasses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Properties;

public class UserHomePage {
    WebDriver driver;
    Properties prop;
    private Logger log;
    private Boolean status;
    private String expectedTitle;
    @FindBy(xpath = "//a[@href='/logout']")
    @CacheLookup
    private WebElement logoutLink;

    public UserHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log = LogManager.getLogger(this.getClass().getName());
    }
    public Boolean verifyUserHomePageTitle() {
        try {
            prop = new Properties();
            prop.load(SignUpLoginPage.class.getClassLoader().getResourceAsStream("configuration.properties"));
            expectedTitle = prop.getProperty("expectedUserHomePageTitle");
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
public void clickLogoutLink(){
        logoutLink.click();
        log.info("Clicked on logout link");
}
}
