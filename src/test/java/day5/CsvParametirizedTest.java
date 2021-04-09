package day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.util.List;

public class CsvParametirizedTest {


// Test first number + second number + third number

    @ParameterizedTest
    @CsvSource({ // only cares about Collumn how many tables we have ,
                 // then we have to initialize it inside methodNAme bellow
            "1, 3 , 4",
            "2, 3 , 5",
            "8, 7 , 15",
            "10, 9 , 19"
    })

    public void testAddition( int num1, int num2, int expectedResult){
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("expectedResult = " + expectedResult);
        assertThat(num1+num2, equalTo(expectedResult));

    }

    // Write a parameterized test for this request
// GET https://api.zippopotam.us/us/{state}/{city}

    @ParameterizedTest
    @CsvSource({
            "NY, New York",
            "VA, Fairfax",
            "VA, Arlington",
            "MA, Boston",
            "NY, Brooklyn",
            "MD, Annapolis"
    })
    public void testStateCityEndPoint(String stateArg, String cityArg){
        System.out.println("stateArg = " + stateArg);
        System.out.println("cityArg = " + cityArg);

        int placeCount =
                             given()
                            .baseUri("https://api.zippopotam.us")
                            .log().uri()
                            .pathParam("state", stateArg)
                            .pathParam("city", cityArg)
                            .when()
                            .get("/us/{state}/{city}")
                            .then()
                            //.log().all()
                            .statusCode(200)
                            .extract().jsonPath()
                            .getList("places")
                            .size()
                ;
        System.out.println("placeCount = " + placeCount);



    }
}
