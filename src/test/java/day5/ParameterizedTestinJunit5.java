package day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import MovieAPI.MovieTestBase;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

public class ParameterizedTestinJunit5 {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10})
    public void testPrintMultipleIterations( int num){

       // int num = 10;
        System.out.println("num = " + num);
        assertTrue(num>1);

    }

    @ParameterizedTest
    @ValueSource(strings = {"Mustafa", "Ayjeren", "Erjon" ,"Diana","Dildarymmmm"})
    public void testNames(String names){
        System.out.println("names = " + names);
        // asssert that each name has more than 5 characters
        assertTrue(names.length()<=5);
    }



        // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
        // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code is 200

    @ParameterizedTest
    @ValueSource(ints = {22030,22031, 22032, 22033 , 22034, 22035, 22036})
    public void testZipcodes(int zipcodes){
        System.out.println("zipcodes = " + zipcodes);
       given()
               .baseUri("https://api.zippopotam.us")
               .log().all()
               .pathParam("zipcode",zipcodes) //--> we have to put our ValueSource in here
               .when()
               .get("/us/{zipcode}")
               .then()
               .log().all()
               .statusCode(200);
    }







}
