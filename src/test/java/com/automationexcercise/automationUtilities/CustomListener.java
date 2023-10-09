package com.automationexcercise.automationUtilities;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static com.automationexcercise.automationUtilities.BaseSteps.test;

public class CustomListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, MarkupHelper.createLabel(result.getName().toUpperCase()+":PASS",
                ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
     //BaseSteps.test.log(Status.FAIL,result.getThrowable().getMessage());
        test.log(Status.FAIL, MarkupHelper.createLabel(result.getName().toUpperCase()+":FAIL",
                ExtentColor.RED));
        try {
            test.addScreenCaptureFromPath(BaseSteps.captureScreenshot(BaseSteps.getDriverInstance()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
