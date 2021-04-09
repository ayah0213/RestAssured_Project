package MovieAPI;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import librray1.Lib1TestBase;
import librray1.Lib1Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

import static io.restassured.RestAssured.*;

public class MovieTestBase {

    @BeforeAll
    public static void init(){
        baseURI = "http://www.omdbapi.com";

    }


    @AfterAll
    public static void cleanup(){
        reset();
    }

}
