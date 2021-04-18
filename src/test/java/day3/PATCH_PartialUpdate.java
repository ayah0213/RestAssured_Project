package day3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import io.restassured.http.ContentType;
import spartan_util.SpartanNoAuth_BaseTest;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;

public class PATCH_PartialUpdate extends SpartanNoAuth_BaseTest {

    @DisplayName("PATCH/spartans/{id} body as a String")
    @Test
    public void testPatchPartialUpdateWithString() {

String patchBody = "{\"phone\" : 1234567891}";
        System.out.println(patchBody); // --> should be {"phone" : 1234567891}
        given()
                .log().all()
                .pathParam("id",18)
                .contentType(ContentType.JSON)
                .body(patchBody)
                .when()
                .patch("/spartans/{id}")
                .then()
                .statusCode(204);


    }
}