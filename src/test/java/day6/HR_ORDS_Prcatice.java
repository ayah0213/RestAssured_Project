package day6;

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
import org.junit.jupiter.api.BeforeAll;

public class HR_ORDS_Prcatice {



    // url:  GET http://3.92.215.98:1000/ords/hr/api/regions
    // base uri = http://3.92.215.98:1000
    // base path = /ords/hr/api
    // resources = /regions

    @BeforeAll
    public static void init(){
    baseURI = "http://3.92.215.98:1000";
    basePath = "/ords/hr/api";


    }



    @DisplayName("GET /regions_tets")
    @Test
    public void testAllRegions(){

    //    get("/regions").prettyPeek();


        // log the request uri
        // send get GET / region request
        // log the response
        // test status code is 200
        // test count is 4
        // test the size of items json array is 4
        given()
                .log().uri()
                .when()
                .get("/regions")
                .then()
                .log().all()
                .statusCode(200)
                .body("count", is(4))
                .body("items", hasSize(4))
                ;




    }


    // another way style of getting informt
    @DisplayName("GET /regions_tets2")
    @Test
    public void testAllRegions2(){
        Response response =
                given()
                        .log().uri()
                        .when()
                        .get("/regions").prettyPeek();

        assertThat(response.statusCode(), is(200));
        // verufy the count field from Json is 3
        // 2-ways of assertions:
        assertThat(response.path("count"), equalTo(4));
        assertThat(response.path("items"), hasSize(4));




    }

        @AfterAll
    public static void cleanup() {
            reset();


        }


}

