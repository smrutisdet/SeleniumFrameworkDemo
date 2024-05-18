package com.automationexcercise.automationUtilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;

public class BaseSteps {
    private Properties prop;
    private String browserName;
    private String appurl;
    protected static final ThreadLocal<WebDriver> driver=new ThreadLocal<>();
    private Logger log;
    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
public static WebDriver getDriverInstance(){
    return driver.get();// returns web driver object when called
}
    @BeforeMethod
    public void  setup() {
        try {
            log = LogManager.getLogger("com.automationexcercise.automationUtilities.BaseSteps");
            prop = new Properties();
            prop.load(BaseSteps.class.getClassLoader().getResourceAsStream("configuration.properties"));
            browserName= System.getProperty("browser");
            log.info("Browser info passed from command line is :" + browserName);
            if (browserName.equalsIgnoreCase("chrome")) {
                //WebDriverManager.chromedriver().setup();
                driver.set(ThreadGuard.protect(new ChromeDriver()));
            } else if (browserName.equalsIgnoreCase("firefox")) {
                //WebDriverManager.firefoxdriver().setup();
                driver.set(ThreadGuard.protect(new FirefoxDriver()));
            } else if (browserName.equalsIgnoreCase("edge")) {
                //WebDriverManager.edgedriver().setup();
                driver.set(ThreadGuard.protect(new EdgeDriver()));
            }
            if (driver != null) {
                appurl = prop.getProperty("url");
                log.info("url  from .properties file is :" + appurl);
                log.info("Navigating to the URL");
                getDriverInstance().get(appurl);
                getDriverInstance().manage().window().maximize();
                getDriverInstance().manage().deleteAllCookies();
                getDriverInstance().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
                getDriverInstance().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public void initializeReport(){
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/extentSparkReport.html");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Automation Test Execution Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        extent=new ExtentReports();
        extent.attachReporter(sparkReporter);
}
    public static String captureScreenshot(WebDriver driver) throws IOException {
        String FileSeparator = System.getProperty("file.separator");
        String Extent_report_path = "."+FileSeparator+"Reports";
        String ScreenshotPath = Extent_report_path+FileSeparator+"screenshots";
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String screenshotName = "screenshot"+Math.random()+".png";
        String screenshotpath = ScreenshotPath+FileSeparator+screenshotName;
        FileUtils.copyFile(src,new File(screenshotpath));
        return "."+FileSeparator+"screenshots"+FileSeparator+screenshotName;
    }
    @AfterMethod
    public void tearDown() {
        getDriverInstance().quit();
        log.info("Closed  browser driver ");
        driver.remove();// Removes current thread's value for thread local variable
    }
}
