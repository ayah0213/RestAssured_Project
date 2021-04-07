package day3;



import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import test_util.SpartanNoAuth_BaseTest;

import org.junit.jupiter.api.*;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;
@DisplayName("Testing adding data to PSartan app multiple way")
public class POSTData extends SpartanNoAuth_BaseTest{

  @DisplayName("POST/ spartans with String Body")
  @Test
  public void testPostsDataWithStringBody(){
      // we gonna  send POST, send-create new Spartan
      /*
           {
    "name" : "Mikail",
    "gender": "Male",
    "phone" : 1238569016
 }  */
      // storing into String our Post Body
String postStringBody = " {\n" +
        "    \"name\" : \"Mikail\",\n" +
        "    \"gender\": \"Male\",\n" +
        "    \"phone\" : 1238569016\n" +
        "    }";

      given()
          .log().all()
              //.header("Content-Type", "application/json")
          // we can use .contentType(ContentType.JSON) as Enum instead of Accept and Headers -->Types
          .contentType(ContentType.JSON) // this is providing HEADER for the request
          .body(postStringBody).
          when()
          .post("/spartans")
          .then()
           .log().all()
          .statusCode(is(201))
            .contentType(ContentType.JSON) // this is an Asserting response Header in json
          .body("success", is("A Spartan is Born!")) // asserting  success msg
          .body("data.name", is("Mikail")); // asserting name


  }

    @DisplayName("POST/ spartans with externalFile FILE ")
    @Test
    public void testPostsDataWithJsonFileAsBody(){

      // singleSpartan.json with below content
/*              {
    "name" : "Marala",
    "gender": "Female",
    "phone" : 1238569896
    }
 */
        // create FILE obj under Project level as same name as new File
        File jsonFile = new File("singleSpartan.json");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(jsonFile)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201);

    }


    @DisplayName("POST /spartans with MAP object")
    @Test
    public void testPostWithMapObjectAsBody(){
/*              {
    "name" : "Marala",
    "gender": "Female",
    "phone" : 1238569896
    }
 */

Map <String, Object> bodyMap = new LinkedHashMap<>();
bodyMap.put("name","Mikail");
bodyMap.put("gender","Male");
bodyMap.put("phone",1238569896L);
        System.out.println(bodyMap);
        // we re expecting this Java Map object to be converted into Json String
        // and send as body initially it failed, RestAssured can not fnd any SERIALIZATION
        // to be able to convert JAva object to Json. That's why we added Jackson-bind-dependency
        // in pom.xml so RestAssured used it to make conversion Happen.
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201);

    }


    @DisplayName("POST /spartans with POJO")
    @Test
    public void testPostWithPOJOAsBody() {

   Spartan sp = new Spartan("Mikail","Female",1238569896L);
        /*
        { "name": "Mikail",
        "gender": "Male",
        "phone": 1238569896
    }
         */
        System.out.println("sp= "+sp);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(sp)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201);

    }


  }



