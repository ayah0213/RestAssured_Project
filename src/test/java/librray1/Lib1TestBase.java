package librray1;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import test_util.ConfigurationReader;
import test_util.DB_Utility;

import static io.restassured.RestAssured.*;

public class Lib1TestBase {

public static  String token;

    public static String librarianToken;

    @BeforeAll
    public static void init(){
        baseURI  = "http://library1.cybertekschool.com" ;
        basePath = "/rest/v1" ;
        librarianToken = getToken("librarian69@library","KNPXrm3S");

        // CREATING CONNECTION WITH DB
        String url = ConfigurationReader.getProperty("library1.database.url");
        String username = ConfigurationReader.getProperty("library1.database.username");
        String password = ConfigurationReader.getProperty("library1.database.password");
        DB_Utility.createConnection(url,username,password);


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
        DB_Utility.destroy();
    }
}
