import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class CreatenewOrder extends BaseClass {
    @BeforeClass
    public void Creatneworder() {
        RestAssured.baseURI = "http://simple-grocery-store-api.online/";
        httpRequest = RestAssured.given();
        JSONObject requestparams = new JSONObject();
        requestparams.put("cartId", cartid);
        requestparams.put("customerName", "rahul");
        httpRequest.header("content-type", "application/json; charset=utf-8");
        httpRequest.header("Authorization", accessToken);
        httpRequest.body(requestparams.toString());
        response = httpRequest.request(Method.POST, "/orders");

    }
    @Test
    public void respose() {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody != null);
        System.out.println(responseBody);

    }

    @Test
    public void StatusCode() {
        int status = response.getStatusCode();
        Assert.assertEquals(status, 201);
        System.out.println("Valid Status Code:" + status);

    }

    @Test
    public void CheckResponseTime() {
        long responseTime = response.getTime();
        System.out.println("Response Time:" + responseTime);
        if (responseTime > 3000)
            System.out.println("Response Time is greater then 2500:" + responseTime);

        Assert.assertTrue(responseTime < 3000);

    }


    @AfterClass
    public void tearDown() {
        System.out.println("***********Test Case Finished*************");

    }
}
