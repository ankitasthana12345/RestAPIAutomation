package com.restapibasis.framework.api.testcase;

import com.restapibasis.framework.api.SetUp.APISetUp;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;


public class TestCreateCustomerApi extends APISetUp {
	@Test
	public void validateCreateCustomerAPIWithValidData() {
		
		/*
		  URI :-  https://api.stripe.com/v1/customers
		  methodType :- Post
		  argument -email, description,name,account_balance
		  Authentication basic auth :-  
		  sk_test_51MfWEASJFkutFWRQdySVlBv2GeSOJ7KBdODRi9bZS0913996Bivmvrs2z2CHndnL76dsnGmmRSu47ScdGz4T94th00wZFCnoVK
		*/ 
		testLevellog.get().assignAuthor("Ankit");
		testLevellog.get().assignCategory("Regression");
		RequestSpecification spec=setRequestSpecification()
		.formParam("email", "ankit.asthana974@gmail.com").formParam("description", "Testing Stripe using rest assured")
		.formParam("balance", 10000).log().all();
		
		System.out.println("==============================================");
		
		Response response = spec.post("customers");
		testLevellog.get().info(response.asString());
		response.prettyPrint();
		Assert.assertEquals( response.getStatusCode(),200);
		
	}

}
