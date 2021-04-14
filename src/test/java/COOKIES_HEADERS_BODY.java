import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static constants.Constants.Actions.JSON_PLACEHOLDER_POSTS1;
import static constants.Constants.Actions.SWAPI_PEOPLE;
import static constants.Constants.Servers.jsonPlaceholderBaseUrl;
import static constants.Constants.Servers.swapiBaseUrl;
import static io.restassured.RestAssured.given;

public class COOKIES_HEADERS_BODY {

    @Test(description = "getHeaders and Content-type from response")
    public void getHeadersFromResponse() {
        Response response =
                given().log().uri().
                        when().get(jsonPlaceholderBaseUrl+JSON_PLACEHOLDER_POSTS1).
                        then().extract().response();
        Headers headers = response.getHeaders();
        System.out.println("All Headers!!!: " + headers);

        String contentType = response.getContentType();
        System.out.println("Content-type is: " + contentType);
    }

    @Test
    public void AllDataResponseExtract() {
        Response response =
                given().log().uri().
                        when().get(swapiBaseUrl+SWAPI_PEOPLE).
                        then().extract().response();

        String jsonResponse = response.asString();
        System.out.println("All response JSON: " + jsonResponse);
    }

    @Test(description = "cookies extraction")
    public void getCookieFromResponse() {
        Response response =
                given().log().uri().
                        when().get(jsonPlaceholderBaseUrl+JSON_PLACEHOLDER_POSTS1).
                        then().extract().response();
        //All cookies
        Map<String, String> allCookie = response.getCookies();
        System.out.println("All cookieas: " + allCookie);
        //Particular cookie
        String someCookie = response.getCookie("__cfduid");
        System.out.println("Particular cookie: " + someCookie);
    }
}
