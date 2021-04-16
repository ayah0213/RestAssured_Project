package HR_APi;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class HR_TestBase {


    // url:  GET http://3.92.215.98:1000/ords/hr/api/regions
    // base uri = http://3.92.215.98:1000
    // base path = /ords/hr/api
    // resources = /regions etc..

    @BeforeAll
    public static void init(){
        baseURI = "http://3.92.215.98:1000";
        basePath = "/ords/hr/api";


    }
    @AfterAll
    public static void cleanup() {
        reset();


    }




}
