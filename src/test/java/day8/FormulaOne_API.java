package day8;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Driver;

import java.util.List;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;

public class FormulaOne_API {


    @BeforeAll
    public static void init(){
       baseURI = "http://ergast.com";
       basePath = "/api/f1";
    }
//Send GET Request to http://ergast.com/api/f1/drivers.json
//Save first driver into Driver POJO
//Save all driver into List<POJO>

    @DisplayName("GET /drivers.json and save result to POJO")
    @Test
    public void testDriver() {


        // get 1st driver as POJO
        JsonPath jp = get("/drivers.json").jsonPath();

        Driver d1 =jp.getObject("MRData.DriverTable.Drivers[0]",Driver.class);
        System.out.println("d1 = " + d1);
        System.out.println("===========================================");



        // Get all drivers as List<Driver>
        List<Driver> allDriver = jp.getList("MRData.DriverTable.Drivers" , Driver.class) ;
        System.out.println("allDriver = " + allDriver);


        System.out.println("================================================");
        // print all American Drivers


        // shortcut of each loop is  "iter" == allDriver.itter
        for (Driver driver : allDriver) {
            if (driver.getNationality().equals("American")){
                System.out.println("driver = " + driver.getGivenName());
            }
        }
    }


@AfterAll
    public static void cleanup(){
        reset();
}
}
