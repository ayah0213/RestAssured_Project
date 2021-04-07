package day3;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import test_util.SpartanNoAuth_BaseTest;

import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpartanJSONPath_Test extends SpartanNoAuth_BaseTest {

    // http://3.92.215.98:8000/api/spartans/16

    @Test
    public void testOne() {

        Response response = given()
                .pathParam("id", "18").
                        when()
                .get("/spartans/{id}").prettyPeek();

        // using Path method to extract data
        int myId = response.path("id");
        System.out.println("myId = " + myId);

        // JsonPAth :
        // -------few meanings:
        //1. just like xPath --> its like used to provide location of certain daa
        //2. JsonPath  as a class coming from Restassured to provide reusable methods toe extract data
        //3. JsonPath() method of Response object to get JsonPAth object
        JsonPath jp = response.jsonPath(); // 2-> for var type, 3-> for method

        myId = jp.getInt("id");
        System.out.println("myId = " + myId);

        long phone = jp.getLong("phone");
        System.out.println("phone = " + phone);

        // getting whole data as MAP data-collection, for just Printing
        // -->  jp.getMap("").soutv
        System.out.println("jp.getMap() = " + jp.getMap("")); // ask for nothing but get whole DATA

        // getting whole data as MAP data-collection, for just Storing
        // -->   Map<String, Object> mapOfWholeData = jp.getMap("") -> then .soutv evverything
        Map<String, Object> mapOfWholeData = jp.getMap("");
        System.out.println("mapOfWholeData = " + mapOfWholeData);


    }

    @DisplayName("Extract data from GET/ spartans")
    @Test
    public void testGetAllSpartans() {

//    Response response = get("/spartans");
//    JsonPath jp = response.jsonPath();
//    get in 1 line ABOVE 2 responses
        JsonPath jp = get("/spartans").jsonPath(); // return TYPE JsonPath obj
        // print 1st object id in Jso array response
        System.out.println("Getting 1st guy's ID = " + jp.getInt("id[0]"));
        // print 2nd Objects name
        System.out.println("2nd object name = " + jp.getString("name[1]"));
        // print 16th Object phoneNum
        System.out.println("Phone number of Object-16 = " + jp.getLong("phone[15]"));

        // save all data of 1 object as a list of MAP
        Map<String, Object> listofData = jp.getMap("[0]");
        System.out.println("listofData = " + listofData);
        // extract data from that list any object
        System.out.println("Gender of my received Map value = " + listofData.get("gender"));
        System.out.println("Phone of my received Map value  = " + listofData.get("phone"));
        System.out.println("Name of my received Map value  = " + listofData.get("name"));


    }

    @DisplayName("Extract data from GET/spartans/search")
    @Test
    public void testGetSearchSpartans() {

        // http://3.92.215.98:8000/api/spartans/search?nameContains=Blythe&gender=Female

        JsonPath jp = given()
                .queryParam("nameContains", "Blythe")
                .queryParam("gender", "Female")
                .log().all().
                        when()
                .get("/spartans/search")
                .prettyPeek()
                .jsonPath();

        // find out 1st guys with ID , second guy's name
        // content [0] .id --> key = content; value = arrays objects inside of that content
        System.out.println("Id of the 1st guys = " + jp.get("content[0].id"));
    }

    @DisplayName("Extract multiple data from GET/spartans/search")
    @Test
    public void testGetSearchSpartanAny() {
        // http://3.92.215.98:8000/api/spartans/search?nameContains=t&gender=Female
        JsonPath jp = given()
                .queryParam("nameContains", "t")
                .queryParam("gender", "Female")
                .log().all().
                        when()
                .get("/spartans/search")
                .prettyPeek().jsonPath();
        // storing some Data into Map
        Map<String, Object> listOfData = jp.getMap("content[3]");
        System.out.println("listOfData = " + listOfData);
        System.out.println("Name of object number 3 = " + listOfData.get("name"));
        System.out.println("Phone number of object number 3 = " + listOfData.get("phone"));

        // now im getting specific Name from  whole data providint index number
        System.out.println("Name of 3rd Object = " + jp.get("content[3].name"));
        System.out.println("phone number of object number 8 = " + jp.get("content[9].phone"));
        System.out.println("Name of object number 8 = " + jp.get("content[9].name"));
    }

    @DisplayName("Saving json array fields into List")
    @Test
    public void testSavingJsonArrayIntoList() {

        JsonPath jp = given()
                .queryParam("nameContains", "t")
                .queryParam("gender", "Female")
                .log().all().
                        when()
                .get("/spartans/search")
                .prettyPeek().jsonPath();

        // save all the id into a LIST
        System.out.println("List of all ID  = " + jp.getList("content.id"));
        System.out.println("List of all names = " + jp.getList("content.name"));
        System.out.println("List of all ph numbers  = " + jp.getList("content.phone"));
        System.out.println("List of all genders = " + jp.getList("content.gender"));


        List<String> listOfNames = jp.getList("content.name");
        List<Integer> listOfAlId = jp.getList("content.id", Integer.class);
        List<Long> listOfPhone = jp.getList("content.phone", Long.class);


    }


    @DisplayName("Get List of spartans practiceGET/ spartans")
    @Test
    public void testGetListOfSpartans() {

        JsonPath jp = get("/spartans").jsonPath(); // return TYPE JsonPath obj
        // save the List into List obj and assert the size

        List<Integer> allId = jp.getList("id", Integer.class);
        assertThat(allId, hasSize(127));
        List<String> allNames = jp.getList("names",String.class);
        assertThat(allNames, hasSize(127));
        List<Long> allPhone = jp.getList("phone",Long.class);
        assertThat(allPhone, hasSize(127));

    }




}