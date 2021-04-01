package day1;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("OSartan App GET request")

public class SpartanNoAuth {

    // http://3.92.215.98:8000/api/spartans

    @BeforeAll
    public static void init(){
        // iwll run  at very Beginning in all TESTS
        // this will set part of URL as RestAssured
        RestAssured.baseURI = "http://3.92.215.98:8000";
        RestAssured.basePath = "/api";
    }


    @AfterAll
    public static void cleanUAp(){
        // will destroy=clean up all test after each run
        RestAssured.reset();
    }
    @Test
    public void sayHEllo(){
        // the actual request URL you have sent is this
        // baseURI + basePath+"/hello"
        get("/hello").prettyPeek();
    }

    @Test
    public void getAllSpartan(){
        // the actual request URL you have sent is this
        // baseURI + basePath+"/spartans/{id}"
       get("/spartans/12").prettyPeek();




    }



}
