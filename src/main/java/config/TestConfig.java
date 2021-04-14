package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;




public class TestConfig {

//Req specification forJSON
    protected RequestSpecification requestSpecificationForJson = new RequestSpecBuilder()
            .addHeader("Content-Type","application/json")
            .addCookie("TestCookieJSON")
            .build();

    //Req specification forXML
    protected RequestSpecification requestSpecificationForXml = new RequestSpecBuilder()
            .addHeader("Content-Type","application/xml; charset=ISO-8859-1")
            .addCookie("TestCookieXML")
            .build();


    protected ResponseSpecification responseSpecForGET = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    protected ResponseSpecification responseSpecForPOST = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();



    }
