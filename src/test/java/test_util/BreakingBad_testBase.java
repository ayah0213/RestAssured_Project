package test_util;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BreakingBad_testBase {

    // https://www.breakingbadapi.com/api/characters?name=Walter
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = ConfigurationReader.getProperty("breakingBad");
        RestAssured.basePath = "/api";
    }

    @AfterAll
    public static void cleanUp(){
        // willdestroy evevrything after all tests run
        RestAssured.reset();
    }



}
