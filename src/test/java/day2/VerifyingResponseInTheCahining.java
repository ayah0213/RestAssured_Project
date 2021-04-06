package day2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test_util.SpartanNoAuth_BaseTest;


import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;

public class VerifyingResponseInTheCahining extends SpartanNoAuth_BaseTest {

    @DisplayName("Verifying the GET/spartans/{id} response directly in the chain")
    @Test
    public void testOneSpartaninShot(){

         given()
                       .log().all() // this will log the REQUEST
                       .pathParam("id",16).

         when()
                         .get("/spartans/{id}").
        then()
                         .log().all() // this will log the RESPONSE
                         .statusCode(200)
                         .header("Content-Type", is("application/json") )
                         .contentType("application/json")
                         .body("id", is(16))
                         .body("gender",is("Male"))
                         .body("name",is("Sinclair"));

    }


}
