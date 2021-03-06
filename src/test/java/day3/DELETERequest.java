package day3;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import spartan_util.SpartanNoAuth_BaseTest;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;

public class DELETERequest extends SpartanNoAuth_BaseTest{

    @DisplayName("DELETE/spartans/{id} ")
    @Test
    public void testDELETEOneSpartan() {

        given()
                .log().all()
                .pathParam("id","18")
                .when()
                .delete("/spartans/{id}")
                .then()
                .statusCode(201); //once u deletd current ID 1 time u will get 404

    }

}
