import config.TestConfig;

import org.testng.annotations.Test;

import static constants.Constants.Actions.*;
import static constants.Constants.Servers.petstoreBaseUrl;
import static io.restassured.RestAssured.given;


public class PETSTORE_TEST extends TestConfig {


    //GET inventory
    @Test
    public void getInventory() {
        given().log().uri().
                when().get(petstoreBaseUrl + PETSTORE_INVENTORY).
                then().spec(responseSpecForGET).log().all();

    }


    //Place an order for a pet
    @Test
    public void placeAnOrder() {

        String jsonBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"petId\": 0,\n" +
                "  \"quantity\": 0,\n" +
                "  \"shipDate\": \"2021-04-10T20:03:53.951Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true\n" +
                "}";

        given().header("Content-Type", "application/json").body(jsonBody).log().uri().
                when().post(petstoreBaseUrl + PETSTORE_ORDERS).
                then().log().all().statusCode(200);
    }


    //Find purchase order by ID
    @Test
    public void getOrderByID() {
        given().log().uri().
                when().get(petstoreBaseUrl + PETSTORE_ORDER1).
                then().spec(responseSpecForGET).log().body();
    }

    //Delete purchase order by ID
    @Test
    public void deleteOrderByID() {
        given().log().uri().
                when().get(petstoreBaseUrl + PETSTORE_ORDER1).
                then().log().all().statusCode(200);
    }


}
