import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreatnewCart extends BaseClass {
    Response response;
    @BeforeClass
    public void Creatcart() {
        response = given().baseUri("http://simple-grocery-store-api.online/")
                .when()
                .post("/carts")
                .then()
                .extract().response();
        System.out.println(response.getBody().asString());
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
        if (responseTime > 2500)
            System.out.println("Response Time is greater then 2500:" + responseTime);

        Assert.assertTrue(responseTime < 2500);

    }

    @Test
    public void CheckContent() {
        String contenttype = response.header("content-type");
        System.out.println("Content Type:" + contenttype);
        Assert.assertEquals(contenttype, "application/json; charset=utf-8");

    }

    @AfterClass
    public void tearDown() {
        System.out.println("***********Test Case Finished*************");

    }
}
