package day2;

import librray1.Lib1TestBase;
import librray1.Lib1Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import test_util.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;
public class POST_AddBooksLIbrary1 extends Lib1TestBase {



    @DisplayName("POST /add_book")
    @Test
    public void postAddBooksTest(){

          //*  book information that i want to add by using LibUtils class method:
        given()
                .log().all()
                .header("x-library-token",librarianToken)
                .contentType(ContentType.URLENC)

                //using formParams we can pass multiple PARAMS in 1 shot
                .formParams(Lib1Utils.getCreateRandomBook())
                .when()
                .post("/add_book")
                .then()
                .log().all()
                .statusCode(200)
                ;
        // send additional request to GET . get_book_id/{book_id}
        // if its actually worked and added correctly



    }


    @DisplayName("POST /add_book and check if its created")
    @Test
    public void testAddedBookIfCreated() {

   Map<String, Object> newBook = Lib1Utils.getCreateRandomBook(); // used methods from libUtils

        int newBookId =
                given()
                        .log().all()
                        .header("x-library-token", librarianToken)
                        .contentType(ContentType.URLENC)
                        //using formParams we can pass multiple PARAMS in 1 shot
                        .formParams(newBook)
                        .when()
                        .post("/add_book")
                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract()
                        .jsonPath().getInt("book_id");
        // send additional request to GET . get_book_id/{book_id}
        // if its actually worked and added correctly
        given()
                .header("x-library-token", librarianToken)
                .log().uri()
                .pathParam("book_id", newBookId)
                .when()
                .get("get_book_by_id/{book_id}")
                .then()
                .log().body()
                .statusCode(200)
        .body("id",is(newBookId+"" )) // verify that id is String
        .body("name" ,is(newBook.get("name"))) // verify that name is String
                ;


    }
}
