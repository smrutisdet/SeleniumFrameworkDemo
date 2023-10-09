package com.automationexcercise.testCases;

import com.automationexcercise.automationUtilities.BaseSteps;
import com.automationexcercise.pageClasses.HomePage;
import com.automationexcercise.pageClasses.SignUpLoginPage;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;

public class TC01_OpenLoginPage extends BaseSteps {
    private HomePage homePage;
   private SignUpLoginPage signupLoginPage;
    private Logger log;
@BeforeTest
public void startReport(){
    initializeReport();
}
    @Test
    public void openLoginPage() {
        log = LogManager.getLogger(this.getClass().getName());
        homePage = new HomePage(getDriverInstance());
        signupLoginPage= new SignUpLoginPage(getDriverInstance());
        String methodName=new Exception().getStackTrace()[0].getMethodName();
        //String className= new Exception().getStackTrace()[0].getClassName();
        test = extent.createTest(methodName,"Sign in and verify page title");
        test.log(Status.INFO,"Starting Open login page and verify title test run");
        test.assignCategory("SmokeTest");
        test.log(Status.INFO,"Click on signUp/Log-in Link");
        log.info("Click on signUp Or Log in Link");
        homePage.clickSignupOrLogin();
        test.log(Status.INFO,"verify signUp/Log-in page title");
        log.info("verify page title");
        Assert.assertTrue(signupLoginPage.verifySignUpLogInPageTitle(),"Page title is mismatching");

    }
    @AfterTest
    public void endReport(){
    extent.flush();
    }
}
