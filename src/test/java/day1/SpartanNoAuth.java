package day1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.*;
import spartan_util.SpartanNoAuth_BaseTest;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Sartan App GET request")

public class SpartanNoAuth extends SpartanNoAuth_BaseTest {


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
