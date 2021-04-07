package day3;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import pojo.Spartan;
import test_util.SpartanNoAuth_BaseTest;
import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;

public class NEGATIVETestSpartanPOST extends SpartanNoAuth_BaseTest {

    @DisplayName("POST Without content type expected 415} ")
    @Test
    public void test1() {

        Spartan sp = new Spartan("B21", "Male",1234567892L);
        given()
                .log().body()
                .body(sp)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(415);


    }


    @DisplayName("POST Without valid Json expected 400  ")
    @Test
    public void test2() {

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("BAD JSON STRUCTURE HERE")
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(400);



    }

    @DisplayName("POST with valid Json, bad name - expect 400 with detailed name error msg")
    @Test
    public void test3() {

        Spartan sp = new Spartan("1","Male",1234567887L);
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(sp)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(400);

    }

    @DisplayName("POST req with bad name, phone - expect 400 with detailed name, phone error msg")
    @Test
    public void test4() {

        Spartan sps = new Spartan("1","Male",234867L);
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(sps)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(400);
    }

    @DisplayName("POST req with bad name, phone , gender - expect 400 with detailed name,3error msg")
    @Test
    public void test5() {

        Spartan spe = new Spartan("1","female",273827L);
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spe)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(400);
    }


}