package day4;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import test_util.ConfigurationReader;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;


@DisplayName("Librray App Simple test")
public class LibraryAppTest {



    @BeforeAll
    public static void init(){
        baseURI  = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";
    }

    @AfterAll
    public static void cleanup(){
        reset();
    }




    @DisplayName("Test POST/login")
    @Test
    public  void testLogin (){
        given()
                .log().all()
                .contentType(ContentType.URLENC)  // this is "x-www-form-urlencoded" Content type inpostman
                .formParam("email", ConfigurationReader.getProperty("lib1user")) // key format param
                .formParam("password",ConfigurationReader.getProperty("lib1pasw")) // value format param
        .when()
                .post("/login")
                .then()
                .statusCode(200)
                .log().all()
        .body("token", is(notNullValue()))
                ;


    }


    @DisplayName("Test token")
    @Test
    public  void testGetTokenAndDecodeToken (){

     // first send res to POST / login extract TOKEN
        // then send request to POST / decode to verify emails and other info


        String username = ConfigurationReader.getProperty("lib1user");
        String password = ConfigurationReader.getProperty("lib1pasw");

        String myToken =
                given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email", username)
                .formParam("password", password)
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("token")
                ;

        // now we gonna send 2nd request to take extracted Token
        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("token", myToken)
                .when()
                .post("/decode")
                .then()
                .log().all()
                .statusCode(200)
                .body("email", is(username))
                ;

    }


    @DisplayName("GET / dashboard_stats endpoint")
    @Test
    public  void testGETDashboardNumbers () {

//        String token =
//                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiMjA5MCIsImZ1bGxfbmFtZSI6IlRlc3QgTGlicmFyaWFuIDY5IiwiZW1haWwiOiJsaWJyYXJpYW42OUBsaWJyYXJ5IiwidXNlcl9ncm91cF9pZCI6IjIifSwiaWF0IjoxNjE3ODEwODM0LCJleHAiOjE2MjA0MDI4MzR9.VBRfszcvXCHF--YcksdROZHOH_IHPCBSY8eIz6ubW5Q";

        // first send res to POST / login extract TOKEN
        // then send request to POST / decode tp get TOKEN
        String username = ConfigurationReader.getProperty("lib1user");
        String password = ConfigurationReader.getProperty("lib1pasw");

        String myToken =
                given()
                        .contentType(ContentType.URLENC)
                        .formParam("email", username)
                        .formParam("password", password)
                        .when()
                        .post("/login")
                        .path("token");

        given()
                .header("x-library-token", myToken)
                . when()
                .get("/dashboard_stats" )
                .then()
                .log().all()
                .statusCode(200)
        .body("book_count", is("2908"))
        .body("borrowed_books", is("794"))
        .body("users", is("8986"))
                ;


        // ANOTHER @ ND STYLE OF EXTRACTING DATA

        // alternatively extract JSONPATH object after
        // status code check, then assert numbers seperately
        JsonPath jp=
        given()
                .header("x-library-token", myToken)
                . when()
                .get("/dashboard_stats" )
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                ;

                assertThat(jp.getInt("book_count"), is(2908) );
                assertThat(jp.getInt("borrowed_books"), is(794));
                assertThat(jp.getInt("users"), is(8986));

    }

    }
