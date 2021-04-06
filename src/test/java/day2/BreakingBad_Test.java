package day2;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test_util.BreakingBad_testBase;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;
public class BreakingBad_Test extends BreakingBad_testBase {

    // https://www.breakingbadapi.com/api/characters?name=Walter

    @DisplayName("GET/characters with name query param")
   @Test
    public void testFilterCharacterNAme(){
    given()
                       .log().uri()
                      .queryParam("name","Walter").
            when()
                       .get("/characters").
            then()
                      .log().all()
                       .statusCode(200)
            .header("Content-Type","application/json; charset=utf-8")
                       .contentType("application/json; charset=utf-8")
            ;

}

@DisplayName("TEST GET/ characters/{char_id}")
    @Test
    public void test1Characters(){
               given()
                       .pathParam("char_id",1)
                            .log().uri().

                when()
                          .get("/characters/{char_id}").
                then()
                          .log().all()
                .statusCode(200)
                .header("Content-Type","application/json; charset=utf-8")
                .contentType("application/json; charset=utf-8");


}

// /episode/60

    @DisplayName("TEST GET/ episode/{episode_id}")
    @Test
    public void test1Episode() {

                 given()
                                .pathParam("episodes_id",60)
                                 .log().all().
                when()
                           .get("episodes/{episodes_id}").
                 then()
                            .log().all()
                            .statusCode(200)
                            .contentType(ContentType.JSON)
                ;



    }
}
