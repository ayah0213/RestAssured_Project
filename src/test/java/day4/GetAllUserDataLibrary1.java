package day4;

import librray1.Lib1TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import test_util.ConfigurationReader;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;
public class GetAllUserDataLibrary1 extends Lib1TestBase {

@DisplayName("GET /get_all_users")
    @Test
    public void getAllUsersasList(){
    // we can comment out cause we moved it into TEST BASE class and extend it
//        String myToken = getToken(ConfigurationReader.getProperty("lib1user"),ConfigurationReader.getProperty("lib1pasw"));
//        System.out.println("myToken = " + myToken);
    System.out.println("token = " + librarianToken);

    List<String> allNAmes =
            given()
            .log().all()
            .header("x-library-token", librarianToken)
            .when()
            .get("/get_all_users")
            .then()
            //.log().all()
            .statusCode(200)
            .extract().jsonPath()
            .getList("name", String.class)
            ;
    // find out unique name count and size
    assertThat(allNAmes,hasSize(9053) );
    Set<String> uniqueNames= new HashSet<>(allNAmes);
    System.out.println("uniqueNames size = " + uniqueNames.size());


}
}
