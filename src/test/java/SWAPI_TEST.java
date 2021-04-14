import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;


public class SWAPI_TEST {

    @Test(description = "regular GET")
    public void getAllEndpoints() {
        given().log().uri()
                .when().get("https://swapi.dev/api")
                .then().body("films", equalTo("http://swapi.dev/api/films/")).log().all();
    }

    //parametrized get
    @Test(description = "parametrized GET")
    public void queryParamGET() {

        String parameterValue = "test";
        String responseValue = "098f6bcd4621d373cade4e832627b4f6";
//adding parameters
        given().param("text", parameterValue).log().uri()
                .when().get("http://md5.jsontest.com")
                .then().assertThat()
                .body("md5", equalTo(responseValue)).log().all();
    }


    //parametrized get with queryParam
    @Test(description = "QueryParametrized GET")
    public void queryParamGET1() {

        String parameterValue = "test";
        String responseValue = "098f6bcd4621d373cade4e832627b4f6";
//adding parameters
        given().queryParam("text", parameterValue).log().uri()
                .when().get("http://md5.jsontest.com")
                .then().assertThat()
                .body("md5", equalTo(responseValue)).log().all();
    }

    //parametrized get
    @Test(description = "PathParametrized GET")
    public void pathParamGET() {

        String season = "2017";
        int numberOfRaces = 20;
        given().pathParam("raceSeason", season)
                .when().get("http://ergast.com/api/f1/{raceSeason}/circuits.json")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("MRData.CircuitTable.Circuits.circuitId", hasSize(numberOfRaces))
                .and().contentType(equalTo("application/json; charset=utf-8"))
                .and().header("Content-Length", equalTo("4551"));

    }


    //Passing parameters between tests by extract
    @Test
    public void retrieveCountryFromLocation0() {
//retrieving locations name
        String location0 = given()
                .when().get("http://ergast.com/api/f1/2017/circuits.json")
                .then().extract().path("MRData.CircuitTable.Circuits.circuitId[0]");

        //retrieving countries
        given().pathParam("location", location0)
                .when().get("http://ergast.com/api/f1/circuits/{location}.json")
                .then().assertThat().body("MRData.CircuitTable.Circuits.Location[0].country", equalTo("Australia"));

    }

}


