package com.restapibasis.framework.api.TestUtils;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.restapibasis.framework.api.SetUp.APISetUp;

public class ExtentManager extends APISetUp {
    private static ExtentReports extent;
    private static ExtentHtmlReporter htmlReporter;

    //private static String filePath = "./extentreport.html";

    public static ExtentReports getExtent(String filePath){

        if(extent != null){
            return extent;
        }else{
            extent = new ExtentReports();
            extent.attachReporter(getHtmlReporter(filePath));
            extent.setAnalysisStrategy(AnalysisStrategy.CLASS);
            return extent;
        }
    }

   public static ExtentHtmlReporter getHtmlReporter(String filePath){
       htmlReporter =  new ExtentHtmlReporter(filePath);
       htmlReporter.setAppendExisting(false);
       htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/src/test/resources/extentConfigproperty/ReportsConfig.xml");
       return htmlReporter;
   }
}