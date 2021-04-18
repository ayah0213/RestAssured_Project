package day2;

import io.restassured.response.Response;
import spartan_util.SpartanNoAuth_BaseTest;

import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Spartan Test with path param")
public class SpartanTest_PathParam extends SpartanNoAuth_BaseTest {


@Test
    public void getOneSpartan(){

    // get("/spartans/16").prettyPeek();


    // 2nd way to GET RESPOSNSE
    // i want to provide 16 as path variable
    // and want to provide accept header
   Response r1 =
           given()
            .header("Accept","application/json")
            .pathParam("spartan_id",16).
            when()
            .get("/spartans/{spartan_id}")
            .prettyPeek();


    // 3rd way  GET RESPONSE
    // to get path variable and value directly in GET METHOD
    // this is same as .header("Accept", "application/jon")
   Response r2 =
           given()
            .accept("application/json").
            when()
            .get("/spartans/{spartan_id}",16)
            .prettyPeek();


}

@DisplayName("logging the request")
@Test
    public void getOnePSartanWithLog(){


    Response response =
            given()
                    .log().all() // will get evevything about ur request
                    .accept("application/json")
                    .pathParam("id",16)
            .when()
                  .get("/spartans/{id}")
                  .prettyPeek();
    assertThat(response.statusCode(), equalTo(200));
    assertThat(response.contentType(),is("application/json") );




}





}
