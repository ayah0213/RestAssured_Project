package day7;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;


import org.junit.jupiter.api.DisplayName;
import pojo.SpartanPOJO;
import test_util.SpartanNoAuth_BaseTest;

import java.util.List;

public class SpartanDeserialization_Test extends SpartanNoAuth_BaseTest {

    //Serialization : Java object to Json (or any other type of txt)

    // Deserialization : Json (txt) to Java


    @DisplayName("GET /spartan/{id}")
    @Test
    public void testGetOneData() {

        given()
                .pathParam("id", 2)
                .when()
                .get("/spartans/{id}")
                .then()
                .statusCode(200)
                .log().body()
        ;


        // send same request store the result into SPartanPOJO object


       // Diserilization without providing PATH  using REPONSE
       Response response =  given()
                .pathParam("id", 2)
                .when()
                .get("/spartans/{id}");


        SpartanPOJO sp = response.as(SpartanPOJO.class) ;
        System.out.println("sp = " + sp);

        // Diserilization with providing PATH and class name using JSONPath
        JsonPath jp = response.jsonPath();
        SpartanPOJO sp1 = jp.getObject("", SpartanPOJO.class) ;
        System.out.println("sp1 = " + sp1);


    }


    @DisplayName("GET /spartans/search")
    @Test
    public void testSearch() {

        // {{base_url}}/spartans/search?nameContains=t&gender=Female
        // send get request about end point and save first object is type as SPARTANPojo

       Response response =  given()
                .queryParam("nameContain","a")
                .queryParam("gender","Male")
                .when()
                .get("/spartans/search")

                ;

        // RESPONSE as will not work here
        // cause we nee to provide path to get json obj we want to content[0]

        JsonPath jp = response.jsonPath() ;
        SpartanPOJO sp = jp.getObject("content[0]", SpartanPOJO.class);
        System.out.println("sp = " + sp);



        // this is how we can do whole thing in one chain

        SpartanPOJO sp1 =
                given()
                        .queryParam("nameContain","a")
                        .queryParam("gender","Male")
                        .when()
                        .get("/spartans/search")
                        .jsonPath()
                .getObject("content[0]", SpartanPOJO.class);
        System.out.println("sp1 = " + sp1);
    }



    @DisplayName("GET /spartans/search and save them as LIST SPartan POJO ")
    @Test
    public void testSearchSaveASList() {

        // {{base_url}}/spartans/search?nameContains=t&gender=Female
        // send get request about end point and save json array into List<SpartanPOJO>

        List<SpartanPOJO> list = given()
                .queryParam("nameContain","a")
                .queryParam("gender","Male")
                .when()
                .get("/spartans/search")
                .jsonPath().getList("content", SpartanPOJO.class);
                //somth .class return type is class to specify what kind of item u want ti have anywehere
        System.out.println("list = " + list);


    }




    }
