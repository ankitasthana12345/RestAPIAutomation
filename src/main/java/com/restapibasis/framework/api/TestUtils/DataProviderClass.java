package com.restapibasis.framework.api.TestUtils;

import com.restapibasis.framework.api.SetUp.APISetUp;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.Hashtable;

public class DataProviderClass extends APISetUp {
    @DataProvider(name="dp")
    public Object[][] getData(Method m){
        String sheetName = m.getName();
        int rows = excel.getRowCount(sheetName);  //3
        int cols = excel.getColumnCount(sheetName); //2
        Object[][] data = new Object[rows-1][1]; // [2][1]Taken column as 1 because it will take define all column in Test cases
        Hashtable<String,String> table = null;
         for(int rowNum =2;rowNum<=rows;rowNum++){
             table = new Hashtable<String, String>();

          for(int colNum=0; colNum<cols;colNum++){

              table.put(excel.getCellData(sheetName,colNum,1) //Row is 1 because it  will Key(header) only
                      ,excel.getCellData(sheetName,colNum,rowNum));

              data[rowNum -2][0] = table;
          }
         }

        
        return data;
    }
}
