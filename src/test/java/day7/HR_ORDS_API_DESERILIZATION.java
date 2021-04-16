package day7;

import HR_APi.HR_TestBase;
import org.junit.jupiter.api.*;
import pojo.Country;
import pojo.REGIONS;

import java.util.List;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;

public class HR_ORDS_API_DESERILIZATION extends HR_TestBase {

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

}
