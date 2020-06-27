package ua.rozetka;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class RealTimeReport   implements ITestListener{



	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test Started->" +result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test Successed->"+result.getName());
	}


	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test Failured->"+result.getName());
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test Skipped->"+result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test FailedButWithinSuccessPercentag->"+result.getName());
	}


	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Start Of Execution(TEST)->"+context.getName());
	}


	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Finish Of Execution(TEST)->"+context.getName());
	}

}
