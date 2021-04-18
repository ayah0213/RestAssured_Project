package day8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.SpartanPOJO;
import test_util.DB_Utility;
import spartan_util.SpartanNoAuth_BaseTest;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Map;

public class Spartan_DB_Test extends SpartanNoAuth_BaseTest {

    @Test
    public void testDB_Connection() {

        DB_Utility.runQuery("SELECT * FROM SPARTANS") ;
        DB_Utility.displayAllData();
    }

    @DisplayName("Test GET / spartans{id} match DB DATA")
    @Test
    public void testSingleSpartanRespondMAtchDBResult(){
        // expected data -- DB query res
        // actual data -- spi response json

        // we want to TEST Spartan with ID of 20, api response json match database data
        // Run Query SELECT * from spartans where spartans id = 20
        int spartanID = 1;
        DB_Utility.runQuery("SELECT * FROM SPARTANs WHERE SPARTAN_ID= "+spartanID);
        DB_Utility.displayAllData();

        System.out.println("=================================================");

        // getting very 1st row as MAP
        Map<String, String> firstRow =DB_Utility.getRowMap(1);
        System.out.println("expected API response data = " + firstRow);




//        // send API REQUEST
        given()
                .pathParam("id", spartanID)
                .log().uri()
                .when()
                .get("/spartans/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .body("id",is(spartanID))
                .body("name" ,  is( firstRow.get("NAME") )    )
                .body("gender" , is (firstRow.get("GENDER") ) )
                // phone jsonpath here is returning int if number fall into the range of int and long if the number fall into range of long
                // in order to make sure the type match , you need to make sure both side is long or int
                // easy way to make sure this phone jsonPath always return long is
                // using groovy method called toLong() to get long at all times
                .body("phone.toLong()" , is ( Long.parseLong(firstRow.get("PHONE") )   ) )
        ;


    }

    @DisplayName("Test GET / spartans{id} match DB DATA with POJO")
    @Test
    public void testSingleSpartanRespondMAtchDBResult_WithPOJO(){

        int spartanID = 20;
        DB_Utility.runQuery("SELECT * FROM SPARTANS WHERE SPARTAN_ID= "+spartanID);
       Map<String,String> expectREsulMap = DB_Utility.getRowMap(1);

        SpartanPOJO actaulResult = given()
                                       .pathParam("id",spartanID)
                                          .when()
                                           .get("/spartans/{id}")
                                           .as(SpartanPOJO.class);
        System.out.println(actaulResult);
        assertThat(actaulResult.getId(), is(spartanID));
        assertThat(actaulResult.getName(), is(expectREsulMap.get("NAME")));
        assertThat(actaulResult.getGender(), is(expectREsulMap.get("GENDER")));


    }


    // WHAT IF I WANT TI GET THE ID DYNAMICALLY INSTEAD OF HARDCODING
    @DisplayName("Test GET / spartans{id} match DB DATA with POJO")
    @Test
    public void testSingleSpartanRespondMAtchDBResult_WithPOJO_2(){


        DB_Utility.runQuery("SELECT * FROM SPARTANS");
        Map<String,String> expectREsulMap = DB_Utility.getRowMap(1);


       // get the ID from this map and save it into below variable
        int spartanID = Integer.parseInt(expectREsulMap.get("SPARTAN_ID"));


        SpartanPOJO actaulResult = given()
                .pathParam("id",spartanID)
                .when()
                .get("/spartans/{id}")
                .as(SpartanPOJO.class);
        System.out.println(actaulResult);
        assertThat(actaulResult.getId(), is(spartanID));
        assertThat(actaulResult.getName(), is(expectREsulMap.get("NAME")));
        assertThat(actaulResult.getGender(), is(expectREsulMap.get("GENDER")));


    }

}
