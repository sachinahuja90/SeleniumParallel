package com.nag.nagp.testListeners;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.nag.nagp.reporterLogger.ReportFactory;

public class TestListener implements ITestListener  {

	/* Function Decription - This function will Listen to Test Start call and will be executed before starting
	 * 						 the testcase.    
	 * Created by - Sachin Ahuja
	 * Created on - 21st April
	 * Modified by
	 * Modified on
	 * */

	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	/* Function Decription - This function will Listen to Test Skipping call and will be executed after
	 * 						 any testcase is skipped.    
	 * Created by - Sachin Ahuja
	 * Created on - 21st April
	 * Modified by
	 * Modified on
	 * */

	@Override
	public void onTestSkipped(ITestResult result) {
		ReportFactory.getInstance().newTest(result.getName(),result);
		ReportFactory.getInstance().info("Testcase get skipped");
		ReportFactory.getInstance().skipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		
	}

	


	

}
