package com.automationexcercise.testCases;

import com.automationexcercise.automationUtilities.BaseSteps;
import com.automationexcercise.automationUtilities.ExcelUtilities;
import com.automationexcercise.pageClasses.HomePage;
import com.automationexcercise.pageClasses.SignUpLoginPage;
import com.automationexcercise.pageClasses.UserHomePage;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TC02_UserLogin extends BaseSteps {
  private HomePage homePage;
   private SignUpLoginPage signupLoginPage;
   private  UserHomePage userHomePage;
    private Logger log;
    Properties prop;
    private String userName;
    private String password;
@BeforeTest
public void startReport(){
    initializeReport();
}
    @Test(dataProviderClass = ExcelUtilities.class,dataProvider="loginData")
    /**
     * @Author smrutisdet
     * userLogin method is used to log in to application with user credentials supplied via loginData provider
     * @Parameters
     * userName: it represents the username of user trying to log in
     * password: represents the password for the username provided
     * @Return null
     * @Updated By:newUser
     * this is updated with new implementation
     */
    public void userLogin(String userName,String password) {
        log = LogManager.getLogger(this.getClass().getName());
        homePage = new HomePage(getDriverInstance());
        signupLoginPage= new SignUpLoginPage(getDriverInstance());
        userHomePage = new UserHomePage(getDriverInstance());
        String methodName=new Exception().getStackTrace()[0].getMethodName();
        //String className= new Exception().getStackTrace()[0].getClassName();
        test = extent.createTest(methodName,"Sign in and verify page title");
        test.log(Status.INFO,"Starting user login page and verify title test run");
        test.assignCategory("SmokeTest");
        test.log(Status.INFO,"Click on signUp/Log-in Link");
        log.info("Click on signUp Or Log in Link");
        homePage.clickSignupOrLogin();
        test.log(Status.INFO,"verify signUp/Log-in page title");
        log.info("verify page title");
        Assert.assertTrue(signupLoginPage.verifySignUpLogInPageTitle(),"signupLoginPage Page title is mismatching");
        prop = new Properties();
        try {
            /*prop.load(TC02_UserLogin.class.getClassLoader().getResourceAsStream("configuration.properties"));
            userName = prop.getProperty("userName");
            password = prop.getProperty("password");*/
            log.info("Enter  user name");
            test.log(Status.INFO,"Enter  user name");
            signupLoginPage.enterUserName(userName);
            getDriverInstance().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            log.info("Enter  password");
            test.log(Status.INFO,"Enter  password");
            signupLoginPage.enterPassword(password);
            getDriverInstance().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            log.info("Click login Button");
            test.log(Status.INFO,"Click login Button");
            signupLoginPage.clickLoginButton();
            getDriverInstance().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            log.info("Verify User home page title");
            test.log(Status.INFO,"Verify User Home page title");
            Assert.assertTrue(userHomePage.verifyUserHomePageTitle(),"userHomePageTitleMismatching");
            getDriverInstance().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            log.info("Click logout link");
            test.log(Status.INFO,"Click logout link");
            userHomePage.clickLogoutLink();
            getDriverInstance().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /*@Test
    public void sampleFailedTest(){
        String methodName=new Exception().getStackTrace()[0].getMethodName();
        test = extent.createTest(methodName,"sample failed Test Case");
        test.log(Status.INFO,"Starting second fail test run");
        test.assignCategory("SanityTest");
        Assert.assertTrue(false);
    }*/
    @AfterTest
    public void endReport(){
    extent.flush();
    }
}
