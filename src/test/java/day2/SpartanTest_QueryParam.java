package day2;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test_util.SpartanNoAuth_BaseTest;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpartanTest_QueryParam extends SpartanNoAuth_BaseTest {

    @DisplayName("GET /spartan/search Endpoint")
    @Test
    public void testSearch(){

        Response response =
                given()
                        .log().all() // this will log everything about the request
                         .queryParam("nameContains","Sinclair")
                          .queryParam("gender","Male").
              when()
                .get("/spartans/search")
                .prettyPeek();

        // print the TOTAL ELEMENT FIELD value from the response
        response.path(("totalElement"));

    }




}
