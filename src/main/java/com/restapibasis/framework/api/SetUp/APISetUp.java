package com.restapibasis.framework.api.SetUp;

import static io.restassured.RestAssured.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.restapibasis.framework.api.TestUtils.ExcelReader;
import com.restapibasis.framework.api.TestUtils.ExtentManager;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.restapibasis.framework.api.TestUtils.ConfigProperty;

import java.lang.reflect.Method;

public class APISetUp {
	public static ConfigProperty configProperty;
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"/src/test/resources/testData/TestData.xlsx");
    public static ExtentReports extentReports;
	public static ThreadLocal<ExtentTest> classLevellog = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testLevellog = new ThreadLocal<ExtentTest>();
	@BeforeSuite
	public void beforeSuite() {
		configProperty = ConfigFactory.create(ConfigProperty.class);
		RestAssured.baseURI = configProperty.getBaseURI();
		RestAssured.basePath = configProperty.getBasePath();
		extentReports= ExtentManager.getExtent(configProperty.getTestFilePath()+configProperty.getTestReportName());
	}
    @BeforeMethod
	public void beforeMethod(Method method){
		ExtentTest test = classLevellog.get().createNode(method.getName());
		testLevellog.set(test);
		testLevellog.get().info("Test case :- "+method.getName()+"execution started");
		//System.out.println("Test case :- "+method.getName()+"execution started");
	}
    @BeforeClass
	public void beforeClass(){
		ExtentTest classLevelTest = extentReports.createTest(getClass().getSimpleName());
		classLevellog.set(classLevelTest);
	}
	@AfterMethod
	public void afterMethod(ITestResult result){

		if(result.getStatus()==ITestResult.SUCCESS) {
			testLevellog.get().pass("Test case is passed");
		}else if(result.getStatus() == ITestResult.FAILURE){
			testLevellog.get().fail("Test case is failed");
		}else if(result.getStatus() == ITestResult.SKIP){
			testLevellog.get().skip("Test case is skipped");
		}
          extentReports.flush();
	}


	@AfterSuite
	public void afterSuite(){

	}

	public static RequestSpecification setRequestSpecification(){
		return given().auth().basic(configProperty.getSecretKey(),"");
	}

}
