package day4;

import librray1.Lib1TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test_util.ConfigurationReader;
import librray1.Lib1TestBase;
import librray1.Lib1Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test_util.ConfigurationReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import test_util.SpartanNoAuth_BaseTest;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;
public class GetAllUserDataLibrary1 extends Lib1TestBase {

@DisplayName("GET /get_all_users")
    @Test
    public void getAllUsers(){



    // we can comment out cause we moved it into TEST BASE class and extend it
//        String myToken = getToken(ConfigurationReader.getProperty("lib1user"),ConfigurationReader.getProperty("lib1pasw"));
//        System.out.println("myToken = " + myToken);
    System.out.println("token = " + myToken);
    given()
            .log().all()
            .header("x-library-token", myToken)
            .when()
            .get("/get_all_users")
            .then()
            .log().all()
            .statusCode(200);


}
}
