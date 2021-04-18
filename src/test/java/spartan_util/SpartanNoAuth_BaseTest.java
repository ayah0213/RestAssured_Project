package spartan_util;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import test_util.ConfigurationReader;
import test_util.DB_Utility;

import javax.security.auth.login.Configuration;

public abstract class SpartanNoAuth_BaseTest {

    // http://3.92.215.98:8000/api/spartans

    @BeforeAll
    public static void init() {
        // iwll run  at very Beginning in all TESTS
        // this will set part of URL as RestAssured
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan");
        RestAssured.basePath = "/api";


        // CREATING CONNECTION WITH DB
        String url = ConfigurationReader.getProperty("spartan.database.url");
        String username = ConfigurationReader.getProperty("spartan.database.username");
        String password = ConfigurationReader.getProperty("spartan.database.password");
        DB_Utility.createConnection(url,username,password);


    }
    @AfterAll
    public static void cleanUAp(){
        // will destroy=clean up all test after each run
        RestAssured.reset();
        DB_Utility.destroy();
    }
}
