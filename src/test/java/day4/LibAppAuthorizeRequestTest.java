package day4;

import librray1.Lib1TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
public class LibAppAuthorizeRequestTest extends Lib1TestBase {

    @DisplayName("GET / get_user_by_id{user_d}")
    @Test
    public void testGETUserById() {



        // we can comment out cause we moved it into TEST BASE class and extend it
//        String myToken = getToken(ConfigurationReader.getProperty("lib1user"),ConfigurationReader.getProperty("lib1pasw"));
//        System.out.println("myToken = " + myToken);

        given()
                .log().all()
                .header("x-library-token", librarianToken)
                .pathParam("user_id",1)
                .when()
                .get("/get_user_by_id/{user_id}")
                .then()
                .log().all()
                .statusCode(200);


    }
}
