package rest_assured_api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class RestTest {

    @Test(groups = "get_body")
    public void getResponseBody(){
        RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("5122657");
        ResponseBody body = response.getBody();

        System.out.println("Response body is " + body.asString());
    }
}