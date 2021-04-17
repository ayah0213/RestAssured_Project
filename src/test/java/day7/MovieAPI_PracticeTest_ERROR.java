package day7;
import MovieAPI.MovieTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Movie;
import pojo.Ratings;

import java.util.List;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;

public class MovieAPI_PracticeTest_ERROR {
    // save the result of your request
    // SEND GET http://www.omdbapi.com/?t=Avenger&apikey=YOUR OWN API KEY goes here
    // save the response into Movie POJO , title Str, year int , Released str ,Language
    // ignore any unknown properties
    // match the json fields to pojo fields
    @DisplayName("GET GET http://www.omdbapi.com/?t=Avenger&apikey=YOUR OWN API KEY")
    @Test
    public void testMovieToPOJO(){

        Movie m1 =  given()
                .baseUri("http://www.omdbapi.com")
                .queryParam("apikey","b6d37d71")
                .queryParam("t","Avenger").
                        when()
                .get()
                        .jsonPath()
                        .getObject("",Movie.class)
              //  .as(Movie.class)
                ;
        System.out.println("m1 = " + m1);

    }



    @DisplayName("GET search for avengers and save Ratings field into List<Rating>")
    @Test

    public void testMovieRatingToPojo(){

        List<Ratings> allRatings = given()
                .queryParam("apikey","b6d37d71")
                .queryParam("t","Avengers")
                .when()
                .get()
                .jsonPath().getList("Ratings", Ratings.class);
        System.out.println("allRatings = " + allRatings);


    }


}
