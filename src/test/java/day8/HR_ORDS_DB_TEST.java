package day8;

import HR_APi.HR_TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.REGIONS;
import test_util.DB_Utility;

import java.util.List;
import java.util.Map;

import static test_util.DB_Utility.*;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static test_util.DB_Utility.runQuery;


public class HR_ORDS_DB_TEST extends HR_TestBase {


    @Test
    public void testHR_ORDS_API(){
        runQuery("SELECT * FROM REGIONS");
        displayAllData();

        // send requets to GET /regions/{region_id} and compare with DB result
        DB_Utility.runQuery("SELECT * FROM REGIONS WHERE REGION_ID = 1");
        displayAllData();

        // save expected data for region_id 1 into List
        List<String> expRowAsList = DB_Utility.getRowDataAsList(1);


        // send API request to GET /regions/{region_id} with id of 1, save the result as POJO
        REGIONS reg = given()
                .log().all()
                .pathParam("region_id", 1)
                .when()
                .get("/regions/{region_id}")
                .jsonPath()
                .getObject("items[0]", REGIONS.class);
        System.out.println("reg = " + reg);

        // compare the result
        assertThat(reg.getRegion_id(), is(Integer.parseInt(expRowAsList.get(0) ) ) );
        assertThat(reg.getRegion_name(), is(expRowAsList.get(1)));


    }



    @DisplayName("tes GET /regions and compare with expected DB REsult")
    @Test
    public void testALLREGIONwithDB(){

        // get entire data and save into MAP
        DB_Utility.runQuery("SELECT * FROM REGIONS");

        // saving all Rows into List of Map
        List<Map<String, String>> expectedRowMap = DB_Utility.getRowMapAsListOfMap();
        System.out.println("expectedRowMap = " + expectedRowMap);

        List<REGIONS> allRegionPojoList = get("/regions")
                                            .jsonPath().getList("items", REGIONS.class);
        System.out.println("allRegionPojoList = " + allRegionPojoList);

        assertThat(expectedRowMap.size(), equalTo(allRegionPojoList.size()));

        for (int i = 0; i < expectedRowMap.size() ; i++) {
            // compare each region id and region name match teh expected region_id and name
            assertThat(allRegionPojoList.get(i).getRegion_id(), is(Integer.parseInt(expectedRowMap.get(i).get("REGION_ID") ) ) );
            assertThat(allRegionPojoList.get(i).getRegion_name(), is(expectedRowMap.get(i).get("REGION_NAME")));
        }


    }

}
