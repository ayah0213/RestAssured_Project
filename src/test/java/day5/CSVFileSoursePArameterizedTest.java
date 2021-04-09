package day5;

import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import test_util.ConfigurationReader;


public class CSVFileSoursePArameterizedTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/state_city_zipCount.csv", numLinesToSkip = 1)
    public void testStateCityToZipEndPointWithCSV( String statearg, String cityArg, int zipArg){

        System.out.println("statearg = " + statearg);
        System.out.println("cityArg = " + cityArg);
        System.out.println("zipArg = " + zipArg);

        given()
                .baseUri("https://api.zippopotam.us")
                .pathParam("state", statearg)
                .pathParam("city", cityArg)
                .log().uri()
                .when()
                .get("/us/{state}/{city}")
                .then()
                .statusCode(200)
                .body("places", hasSize(zipArg) )
                ;




    }
}
