package day3;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import pojo.Spartan;
import spartan_util.SpartanNoAuth_BaseTest;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;


public class SpartanPOSTUpdatingData extends SpartanNoAuth_BaseTest {

// you may repeated evevrything we did previously in post Test for providing Body
    // we will just look at Map and POJO

    /// PUT request with MAP & POJO

    @DisplayName("PUT/spartans/{id} body as MAP")
    @Test
    public void tesUpdatedWithMap(){
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name","Mikail");
        bodyMap.put("gender","Male");
        bodyMap.put("phone",1238569896L);

        given()
                .log().all()
                .pathParam("id",141)
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when()
                .put("/spartans/{id}")
                .then()
                .statusCode(204);

    }

    @DisplayName("PUT/spartans/{id} body as POJO")
    @Test
    public void tesUpdatedWithPOJO(){

        Spartan sps = new Spartan("Dean","Male",1238569896L);
        given()
                .log().all()
                .pathParam("id",141)
                .contentType(ContentType.JSON)
                .body(sps)
                .when()
                .put("/spartans/{id}")
                .then()
                .statusCode(204);
    }

}
