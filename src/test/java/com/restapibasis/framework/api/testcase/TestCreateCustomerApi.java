package com.restapibasis.framework.api.testcase;

import com.restapibasis.framework.api.SetUp.APISetUp;
import com.restapibasis.framework.api.TestUtils.DataProviderClass;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.Hashtable;

import static io.restassured.RestAssured.*;


public class TestCreateCustomerApi extends APISetUp {
	@Test(dataProviderClass = DataProviderClass.class,dataProvider = "dp")
	public void validateCreateCustomerAPI(Hashtable<String,String> data) {
		
		/*  URI :-  https://api.stripe.com/v1/customers
		  methodType :- Post
		  argument -email, description,name,account_balance
		  Authentication basic auth :-  
		  sk_test_51MfWEASJFkutFWRQdySVlBv2GeSOJ7KBdODRi9bZS0913996Bivmvrs2z2CHndnL76dsnGmmRSu47ScdGz4T94th00wZFCnoVK*/

		testLevellog.get().assignAuthor("Ankit");
		testLevellog.get().assignCategory("Regression");
		RequestSpecification spec=setRequestSpecification()
		.formParam("email", data.get("email")).formParam("description", data.get("description"))
		.formParam("balance", 10000).log().all();
		
		System.out.println("==============================================");
		
		Response response = spec.post("customers");
		testLevellog.get().info(response.asString());
		response.prettyPrint();
		Assert.assertEquals( response.getStatusCode(),200);

		//fetch the email from the response
		String emailInResponse = response.path("email");
		String descInResponse = response.path("description");

		Assert.assertEquals(emailInResponse,data.get("email"));
		Assert.assertEquals(descInResponse,data.get("description"));
		System.out.println("Footer ---->"+response.path("invoice_settings.footer"));

		JsonPath responseJson = new JsonPath(response.asString());
		System.out.println("Email using JsonPath-->"+responseJson.get("email"));
		
	}

	@Test(dataProviderClass = DataProviderClass.class,dataProvider = "dp")
	public void fetchCustomer(Hashtable<String,String> data){
		testLevellog.get().assignAuthor("Ankit");
		testLevellog.get().assignCategory("Regression");
		RequestSpecification spec=setRequestSpecification()
				.log().all();
		Response response = spec.request(data.get("methodType"),data.get("endPoint"));
		//response.prettyPrint();
		System.out.println("Size of data ->> "+response.path("data.size()"));
		ArrayList<String> liftoffIDDs =response.path("data.id");
		System.out.println(liftoffIDDs);


	}

}
