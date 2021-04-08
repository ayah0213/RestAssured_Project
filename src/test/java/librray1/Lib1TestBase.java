package librray1;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import test_util.ConfigurationReader;

import static io.restassured.RestAssured.*;

public class Lib1TestBase {

public static  String token;

//    @BeforeAll
//    public static void init(){
//        baseURI  = ConfigurationReader.getProperty("lib1URL");
//        basePath = ConfigurationReader.getProperty("lib1endPoint");
//    }
//
//
//    public static String getToken(String username, String password){
//
//
//
//        return given()
//                .contentType(ContentType.URLENC)
//                .formParam("email", username)
//                .formParam("password", password)
//                .when()
//                .post("/login")
//               .then()
//                .extract()
//                .path("token");
//
//    }


    public static String librarianToken;

    @BeforeAll
    public static void init(){
        baseURI  = "http://library1.cybertekschool.com" ;
        basePath = "/rest/v1" ;
        librarianToken = getToken(ConfigurationReader.getProperty("lib1user"), ConfigurationReader.getProperty("lib1pasw"));

    }
    public static String getToken(String username, String password){
        return given()
                .contentType(ContentType.URLENC)
                .formParam("email", username)
                .formParam("password" , password).
                        when()
                .post("/login")
                .path("token");
    }
    @AfterAll
    public static void cleanup(){
        reset();
    }
}
