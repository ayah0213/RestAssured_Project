package day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import pojo.Spartan;
import spartan_util.SpartanUtil;
import spartan_util.SpartanNoAuth_BaseTest;

import java.util.Map;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpartanRandomPOST_Test extends SpartanNoAuth_BaseTest{

    @DisplayName("POST / spartans with random DATA")
    @Test
    public void addOneRandomTEst(){
                                        // call the method which we created in SpartanUtil class to reuse it here
        Map<String, Object> randomRequestBodyMap  = SpartanUtil.getRandomSpartanMap();
        //this is the MAp obj we sent
        //to body, its expected result

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(randomRequestBodyMap)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .body("data.name", is(randomRequestBodyMap.get("name"))) //-> assertion
                .body("data.gender", is(randomRequestBodyMap.get("gender"))) //-> assertion
                .body("data.phone", is(randomRequestBodyMap.get("phone")));//-> assertion


    }

    @DisplayName("POST / spartans with random Spartan POJO DATA")
    @Test
    public void addOneRandomSpartanPOJOTEst(){

        Spartan randomPOJO = SpartanUtil.getRandomSpartanPOJO();

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(randomPOJO)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .body("data.name", is (randomPOJO.getName()))
                .body("data.gender", is (randomPOJO.getGender()))
                .body("data.phone", is (randomPOJO.getPhone()))

                     ;

    }


    @DisplayName("POST / add new DATA and send GET/ spartans/{id} to verify the data")
    @Test
    public void testPostAndAdd1DataToVerify() {


   /// DO 2 THING IN 1 SHOT
   // CREATE A VARIABLE AND THEN REUSE IT IN SAME CHAIN

   // task:
        // 1 step send POST request, save the id that generated
        // check status code 201
        // send GET request using you id you saved from above
        // check status code is 200 and body match for exactly what u send
        Spartan randpmPOJO = SpartanUtil.getRandomSpartanPOJO();

        Response response =
                given()
                .log().body()
                .contentType(ContentType.JSON)
                .body(randpmPOJO)
                .when()
                .post("/spartans")
                .prettyPeek();

        // 2 way of extracting data from BODY
        int newId = response.path("data.id");
       // int newid = response.jsonPath().getInt("data.id");
        System.out.println("newid = " + newId );
        assertThat(response.statusCode(), is(201));

        // sending GET request
        given()
                .log().uri()
                .pathParam("id",newId)
                .when()
                .get("/spartans/{id}")
                .then()
                .log().body()
                .statusCode(200)
                .body("id", is(newId))
        .body("name", is(randpmPOJO.getName()))
        .body("phone", is(randpmPOJO.getPhone() ))
        ;



    }

    @DisplayName("POST / add new DATA and send GET/ spartans/{id} to verify 2")
    @Test
    public void testPostAndAdd1DataToVerifyinTheChain() {
        // send POST request veridy 201 and then extract the id in the same method chain

        Spartan spRandom = SpartanUtil.getRandomSpartanPOJO();
        int newId =
                given()
                .log().body()
                .contentType(ContentType.JSON)
                .body(spRandom)
                .when()
                .post("/spartans")
                .then()
                .log().body()
                .statusCode(201)
                .extract()  // returns Extractable data-inform, used to keep going after the validation
        .path("data.id")// now we're showing as path what= we want to extracrt?
                ;
        System.out.println("newId = " + newId);
        
    }

    @DisplayName("POST / add new DATA and send GET/ spartans/{id} to verify 3")
    @Test
    public void testOneDataThenExtractHeader() {

        Spartan randomPojo = SpartanUtil.getRandomSpartanPOJO();

      // extract LOCATION Header and assert status code 201

        String locationHeader =
                given()
                .log().body()
                .contentType(ContentType.JSON)
                        .body(randomPojo)
                .when()
                .post("/spartans")
                .then()
                .statusCode(201)
                .extract()
                .header("Location")
                ;
        System.out.println("locationHeader = " + locationHeader);


        // using LOCATION Header which we above extracted sending GET request
       //  get(locationHeader).prettyPeek();




    }
}
