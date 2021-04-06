package test_util;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import javax.security.auth.login.Configuration;

public abstract class SpartanNoAuth_BaseTest {

    // http://3.92.215.98:8000/api/spartans

    @BeforeAll
    public static void init(){
        // iwll run  at very Beginning in all TESTS
        // this will set part of URL as RestAssured
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan");
        RestAssured.basePath = "/api";
    }


    @AfterAll
    public static void cleanUAp(){
        // will destroy=clean up all test after each run
        RestAssured.reset();
    }
}
