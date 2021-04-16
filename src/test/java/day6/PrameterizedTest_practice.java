package day6;

import HR_APi.HR_TestBase;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

public class PrameterizedTest_practice extends HR_TestBase {


    //  // GET /countries/{country_id}


    @Test
    public void testCountry() {

        //   RestAssured.get("/countries").prettyPeek();
        get("/countries/AR").prettyPeek();
    }


    @ParameterizedTest
    @ValueSource(strings = {"AR", "AU", "AE", "US1"})
    public void testingSingleWithValue(String countryIdArg) {
        System.out.println("countryIdArg = " + countryIdArg);
        given()
                .pathParam("country_id", countryIdArg)
                .log().uri()
                .when()
                .get("/countries/{country_id}")
                .then()
                .log().body()
                .statusCode(200)
                // check the count is exacctly 1
                .body("count", is(1));
    }




    @ParameterizedTest
    @CsvSource({
            "AR, Argentina",
            "US, United States of America",
            "UK, United Kingdom"
    })
    public void testSingleCountryWithCSVSource(String countryIDArg, String countryNameArg){

        // send REquest  to GET / countries{country_id}
        // expected country name match the same corresponding country_id
        given()
                .log().uri()
                .pathParam("country_id",countryIDArg)
                .when()
                .get("/countries/{country_id}")
                .then()
                .body("items[0].country_name", is (countryNameArg))
                ;
    }


    // write static method that returns List of Country_id

    @ParameterizedTest
    @MethodSource("getManyCountryIds")
    public void testCountryWithMethodSource(String countryIdArg){
        System.out.println("countryIdArg = " + countryIdArg);
        // GET /countries/{country_id}
        given()
                .log().uri()
                 .pathParam("country_id", countryIdArg).
        when()
                .get("/countries/{country_id}").
        then()
                .log().body()
                .statusCode(200)
                .body("count", is(1) )
        ;
    }
    //// write static method that return list of country ids
    public static List<String> getManyCountryIds(){
       //  List<String> countryNAmeList = Arrays.asList("AR","BE","US"); // this is one specific ones

        // Send Request to GET / countries and save the country_id as List<String>
        List<String> countryNAmeList =
                get("countries")
                .jsonPath().getList("items.country_id", String.class);
      return countryNAmeList;
    }

}
