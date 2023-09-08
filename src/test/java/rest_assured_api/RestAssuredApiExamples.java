package rest_assured_api;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;

public class RestAssuredApiExamples {

    static final String url = "http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1";

    @Test(groups = "get_body")
    public static void getResponseBody() {
        given().when().get(url).then().log().all();
        given().queryParam("CUSTOMER_ID", "68195")
                .queryParam("PASSWORD", "1234!")
                .queryParam("Account_No", "1")
                .when().get("http://demo.guru99.com/V4/sinkministatement.php").then().log().body();
        System.out.println();
    }

    @Test(groups = "get_status")
    public static void getResponseStatus() {
        int statusCode = given().queryParam("CUSTOMER_ID", "68195")
                .queryParam("PASSWORD", "1234!")
                .queryParam("Account_No", "1")
                .when().get("http://demo.guru99.com/V4/sinkministatement.php").getStatusCode();
        System.out.println("The response status is " + statusCode);

        given().when().get(url).then().assertThat().statusCode(200);
        System.out.println();
    }

    @Test(groups = "get_headers")
    public static void getResponseHeaders() {
        System.out.println("The headers in the response " + get(url).then().extract().headers());
        System.out.println();
    }

    @Test(groups = "get_time")
    public static void getResponseTime() {
        System.out.println("The time taken to fetch the response " + get(url)
                .timeIn(TimeUnit.MILLISECONDS) + " milliseconds");
        System.out.println();
    }

    @Test(groups = "get_content_type")
    public static void getResponseContentType() {
        System.out.println("The content type of response " +
                get(url).then().extract()
                        .contentType());
        System.out.println();
    }
}
