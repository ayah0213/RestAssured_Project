package day7;

import HR_APi.HR_TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Locations;
import java.util.List;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.Map;

public class LocationsUsingLambokPOJO extends HR_TestBase {

    // send the request to /locations and save the result into LIST of Locations as POJO


    @DisplayName("GET /regions")
    @Test
    public void testGetALlLocationsAsList() {

        List<Locations> allLocations = get("locations")
                .jsonPath().getList("items",  Locations.class);
        System.out.println("allLocations = " + allLocations);

//        Map<String, String> mapList =get("locations")
//                .jsonPath().getMap("items.street_address",)
//        System.out.println("allLocations = " + allLocations);

    }

    }
