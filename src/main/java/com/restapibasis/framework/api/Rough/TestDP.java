package com.restapibasis.framework.api.Rough;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDP {

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod(){
        return new Object[][] {{2, 3 , 5,"Abc"}, {5, 7, 9,"cde"},{1, 2, 3,"lll"}};
    }

    @Test(dataProviderClass = TestDP.class,dataProvider = "data-provider")
    public void myTest (int a, int b, int result,String x) {
        int sum = a + b;
        Assert.assertEquals(result, sum,x);
    }
}
