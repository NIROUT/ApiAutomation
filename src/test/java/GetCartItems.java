import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class GetCartItems extends BaseClass{
    @BeforeClass
    public void getsingleProduct() throws InterruptedException {
        RestAssured.baseURI = "http://simple-grocery-store-api.online/";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/carts/"+cartid+"/items");
        Thread.sleep(2000);

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
        Assert.assertEquals(status, 200);
        System.out.println("Valid Status Code:" + status);

    }

    @Test
    public void CheckResponseTime() {
        long responseTime = response.getTime();
        System.out.println("Response Time:" + responseTime);
        if (responseTime > 2000)
            System.out.println("Response Time is greater then 2000:" + responseTime);

        Assert.assertTrue(responseTime < 2000);

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
