
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ReplaceAnIteminCart extends BaseClass {

    @BeforeClass
    public void Replaceitemincart() {
        RestAssured.baseURI = "http://simple-grocery-store-api.online/";
        httpRequest = RestAssured.given();
        JSONObject requestparams = new JSONObject();
        requestparams.put("productId", "4646");
        httpRequest.header("content-type", "application/json; charset=utf-8");
        httpRequest.body(requestparams.toString());
        response = httpRequest.request(Method.PUT, "/carts/" + cartid + "/items/"+itemId);

    }


    @Test
    public void StatusCode() {
        int status = response.getStatusCode();
        Assert.assertEquals(status, 204);
        System.out.println("Valid Status Code:" + status);

    }

    @Test
    public void CheckResponseTime() {
        long responseTime = response.getTime();
        System.out.println("Response Time:" + responseTime);
        if (responseTime > 2500)
            System.out.println("Response Time is greater then 2500:" + responseTime);

        Assert.assertTrue(responseTime < 2500);

    }


    @AfterClass
    public void tearDown() {
        System.out.println("***********Test Case Finished*************");

    }
}
