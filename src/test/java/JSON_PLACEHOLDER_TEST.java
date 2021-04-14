import config.TestConfig;

import org.testng.annotations.Test;

import static constants.Constants.Actions.JSON_PLACEHOLDER_POSTS;
import static constants.Constants.Actions.JSON_PLACEHOLDER_POSTS1;
import static constants.Constants.Servers.jsonPlaceholderBaseUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class JSON_PLACEHOLDER_TEST extends TestConfig {


    //get
    @Test
    public void PLACEHOLDER_GET() {
        given().log().all().
                when().get("https://jsonplaceholder.typicode.com/posts/1").
                then().spec(responseSpecForGET).log().body();
    }

    // post JSON
    @Test
    public void PLACEHOLDER_POST_JSON() {
//using specification
        String jsonBody = "{\n" +
                "    \"title\": \"foo1\",\n" +
                "    \"body\": \"bar1\",\n" +
                "    \"userId\": 1\n" +
                " }";
        given().spec(requestSpecificationForJson).body(jsonBody).log().all().
                when().post(jsonPlaceholderBaseUrl+JSON_PLACEHOLDER_POSTS).
                then().spec(responseSpecForPOST)
                .body("id", equalTo(101))
                .body("body", equalTo("bar1"))
                .log().body();
    }

    //post XML
    @Test
    public void PLACEHOLDER_POST_XML() {

        String xmlBody = "<title>foo1</title><body>bar1</body><userId>1</userId>";
        given().spec(requestSpecificationForXml).body(xmlBody).log().all().
                when().post(jsonPlaceholderBaseUrl+JSON_PLACEHOLDER_POSTS).
                then().spec(responseSpecForPOST)
                .log().body();
    }


    // put
    @Test
    public void PLACEHOLDER_PUT() {
        given().log().all().
                when().put(jsonPlaceholderBaseUrl+JSON_PLACEHOLDER_POSTS1).
                then()
                .body("id", equalTo(1))
                .log().body().statusCode(200);
    }

    //patch
    @Test
    public void PLACEHOLDER_PATCH() {

        String jsonbody = "{\n" +
                "    \"body\": \"barbarbar\"\n" +
                "}";
        given().spec(requestSpecificationForJson).body(jsonbody).log().all()
                .when().patch(jsonPlaceholderBaseUrl+JSON_PLACEHOLDER_POSTS1).
                then()
                .assertThat()
                .statusCode(200)
                .body("body", equalTo("barbarbar"));
    }

    //delete
    @Test
    public void PLACEHOLDER_DELETE() {
        given().log().all().
                when().delete(jsonPlaceholderBaseUrl+JSON_PLACEHOLDER_POSTS1).
                then().log().body().statusCode(200);
    }


}
