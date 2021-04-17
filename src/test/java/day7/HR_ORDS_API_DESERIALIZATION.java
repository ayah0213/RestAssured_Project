package day7;

import HR_APi.HR_TestBase;
import org.junit.jupiter.api.*;
import pojo.Country;
import pojo.Departments;
import pojo.Eemployee;
import pojo.REGIONS;

import java.util.List;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;

public class HR_ORDS_API_DESERIALIZATION extends HR_TestBase {

    // send the request to /regions and save the result into LIST of Regions as POJO
    // assert the list size 4

    @DisplayName("GET /regions")
    @Test
    public void testGetREgionsAsListofPOJO() {

       List<REGIONS> regionsList = get("/regions")
               .jsonPath().getList("items", REGIONS.class);
        System.out.println("regionsList = " + regionsList);

    }



    // testing with LOMBOK
    @DisplayName("GET /countries with LOMBOK")
    @Test
    public void testGetCountriesWithLombok() {

        Country c1 =new Country("AR","Argentina",1);
        System.out.println("c1 = " + c1);



        // save 3rd country as COUNTRY POJO
        Country c3 =
                get("/countries")
                .jsonPath()
                .getObject("items[2]", Country.class);
        System.out.println("c3 = " + c3);


        // save all countries as List<POJO>
        List<Country> listCountries =  get("/countries")
                .jsonPath().getList("items", Country.class);
        System.out.println("listCountries = " + listCountries);
    }



       // We want to create pojo that represent Employee data
       // We only care about employee_id , first_name , last_name , salary, department_id
       // we want to save the json data as pojo and we want to ignore any other fields other than specified above
   @DisplayName("GET /employees")
    @Test
    public void testAllEmployees(){

        // get the 1st employee and save it into Employee pojo
       Eemployee e1 = get("/employees").jsonPath()
                      .getObject("items[0]", Eemployee.class);
       System.out.println("e1 = " + e1);


   }

    // Till this moment we have been naming our pojo class field names
//   to match exact name from json field
//  and yet there will be situations that the json field name
//    does not really work for java naming convention
// we want to be able to name the POJO field anything we want

    // Create a POJO class called Departments with below fields
    // departmentId , name , manager_id , location_id
    @DisplayName("GET /departments")
    @Test
    public void testAllDepartments() {

        Departments d1 = get("/departments")
                //.prettyPeek()
                .jsonPath()
                .getObject("items[0]", Departments.class);
        System.out.println("d1 = " + d1);

    }

}
