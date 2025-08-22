package Listener;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputFilter.Status;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testNG.BaseClass;

public class ListenerImplimentaion implements ITestListener,ISuiteListener{
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Report configuration", true);
		Date d = new Date();
		String newdate = d.toString().replace(" ", " ").replace(":", "_");
		spark = new ExtentSparkReporter("./AdvanceReport/report_"+newdate+".html");
		spark.config().setDocumentTitle("Ninza CRM Test Report");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("os", "window11");
		report.setSystemInfo("browser", "edge");
		
		
	}

	@Override
	public void onFinish(ISuite suite) {
		report.flush();
		Reporter.log("Report backup", true);
		
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getMethod().getMethodName());
		test.log(com.aventstack.extentreports.Status.INFO,"========="+result.getMethod().getMethodName()+"EXECUTION STARTED======");
		
	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(com.aventstack.extentreports.Status.PASS,"========"+result.getMethod().getMethodName()+"SUCCESS=====");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		Date d = new Date();
		String newdate = d.toString().replace(" ", " ").replace(":", "_");
		
		
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src,testName+newdate);
		test.log(com.aventstack.extentreports.Status.FAIL,"======"+testName+"FAILURE======");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
	
		test.log(com.aventstack.extentreports.Status.SKIP,"========"+result.getMethod().getMethodName()+"SKIPPED==========");
	}

}
