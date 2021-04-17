package day5;
import MovieAPI.MovieTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import test_util.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;


public class MovieAPI_Practice  {



    @DisplayName("Search movies JsonPath")
    @Test
    public void testSearch() {

        //GET http://www.omdbapi.com?s=Superman&type=series&Your_API_KEY_GOES_HERE
        // if you do not have other place you are using it ,
        // you directly provide your baseURI using baseURI method in given

     JsonPath jp =    given()
                .baseUri("http://www.omdbapi.com")
                .log().all()
                .queryParam("apiKey","b6d37d71")
                .queryParam("s","Superman")
                .queryParam("type","series")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .extract().
                jsonPath()
        ;

     // Json path to get all movie title is Search.title
     List<String> allTitles = jp.getList("Search.Title", String.class);
        System.out.println("allTitles = " + allTitles);
        assertThat(allTitles, hasSize(10)); //assert size is 10
        assertThat(allTitles.get(0), containsString("Superman"));// assert 1st ITEM contains Superman
        assertThat(allTitles, hasItem("Superman and Lois")); // assert it has Items Superm and Lois
        assertThat(allTitles, hasItems("Superman and Lois", "The New Adventures of Superman"));// assert it has Items Superm and Lois and the adventure of Superman
        assertThat(allTitles, everyItem(containsString("Superman"))); // assert taht evevry Items contains SUperman



    }

    @DisplayName("Search movies response body validation in teh chain")
    @Test
    public void testSearch2() {

        //GET http://www.omdbapi.com?s=Superman&type=series&Your_API_KEY_GOES_HERE
        // if you do not have other place you are using it ,
        // you directly provide your baseURI using baseURI method in given

       given()
                .baseUri("http://www.omdbapi.com")
                .log().all()
                .queryParam("apiKey", "b6d37d71")
                .queryParam("s", "Superman")
                .queryParam("type", "series")
                .when()
                .then()
                .log().all()
                .statusCode(200)
                .body("Search.Title", hasSize(10) )
       .body("Search[0].Title", containsString("Superman") )
       .body("Search.Title", hasItem("Superman and Lois"))
       .body("Search.Title", hasItems("Superman and Lois","The New Adventures of Superman"))
        .body("Search.Title",everyItem(containsString("Superman")) )

                ;




    }

    }
