package rest_assured_api;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class GetApiExamples {

    static final String url = "https://gorest.co.in/public/v2/posts";

    @Test(groups = "get_body")
    public static void getResponseBody() {
        given().when().get(url).then().log()
                .all();
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

    @Test(groups = "get_status")
    public static void getResponseStatus() {
        int statusCode = given().when().get(url).getStatusCode();
        System.out.println("The response status is " + statusCode);

        given().when().get(url).then().assertThat().statusCode(200);
        System.out.println();
    }

    @Test(groups = "get_all")
    public static void getResponseAll() {
        System.out.println("list/am");
        given().when().get("https://www.list.am").then().log().body();
        System.out.println("list/am");
    }
}
