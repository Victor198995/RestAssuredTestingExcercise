import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class DATA_DRIVEN_TEST {

    //array with expecred results
    @DataProvider(name = "seasonsAndNumbersOfRaces")
    public Object[][] createTestDataRecords() {
        return new Object[][]{
                {"2017", 20},
                {"2016", 21},
                {"1966", 9}
        };
    }

    //Data-driven test with Data provider
    @Test(dataProvider = "seasonsAndNumbersOfRaces")
    public void data_Driven_Test(String season, int numbersOfRaces) {
        given().pathParam("raceSeason", season)
                .when().get("http://ergast.com/api/f1/{raceSeason}/circuits.json")
                .then().assertThat()
                .body("MRData.CircuitTable.Circuits.circuitId", hasSize(numbersOfRaces))
                .and()
                .statusCode(200);
    }

    //array with expecred results
    @DataProvider(name = "locationsCities")
    public Object[][] createTestDataRecords1() {
        return new Object[][]{
                {"albert_park", "Melbourne"},
                {"americas", "Austin"},
                {"bahrain", "Sakhir"}
        };
    }


    @Test(dataProvider = "locationsCities")
    public void dataDrivenRetrieveCountryFromLocation1(String location, String city) {
        //retrieving countries
        given().pathParam("loc", location)
                .when().get("http://ergast.com/api/f1/circuits/{loc}.json")
                .then().assertThat().body("MRData.CircuitTable.Circuits.Location[0].locality", equalTo(city));

    }
}
