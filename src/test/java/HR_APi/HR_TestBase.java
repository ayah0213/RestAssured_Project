package HR_APi;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import test_util.ConfigurationReader;
import test_util.DB_Utility;

import static io.restassured.RestAssured.*;

public class HR_TestBase {


    // url:  GET http://3.92.215.98:1000/ords/hr/api/regions
    // base uri = http://3.92.215.98:1000
    // base path = /ords/hr/api
    // resources = /regions etc..

    @BeforeAll
    public static void init(){
        baseURI = ConfigurationReader.getProperty("hr.baseUri");
        basePath = "/ords/hr/api";



        // CREATING CONNECTION WITH DB
        String url = ConfigurationReader.getProperty("hr.database.url");
        String username = ConfigurationReader.getProperty("hr.database.username");
        String password = ConfigurationReader.getProperty("hr.database.password");
        DB_Utility.createConnection(url,username,password);


    }
    @AfterAll
    public static void cleanup() {
        reset();
        DB_Utility.destroy();


    }




}
