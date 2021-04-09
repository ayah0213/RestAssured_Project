package day5;
import MovieAPI.MovieTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;

import io.restassured.path.json.JsonPath;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


public class StarWarApi_Test extends MovieTestBase {


    /*
    Interview Questions :
Send request to  GET https://swapi.dev/api/people/
Find out average height of all people showed up in the response
     */



    @DisplayName("GET average height from GET /api/people/")
    @Test
    public void testGetAverageHeight() {

        List<Integer> allHeight = get("/people")
                .jsonPath()
                .getList("results.height", Integer.class)
                ;
  // now we gonna LAMBDA expression to see all heights in vertical matter
   // allHeight.forEach(System.out::println); // --> LAMBDA expression

        System.out.println("allHeight = " + allHeight);
         int total = 0;
         for (Integer height : allHeight){
             total+=height;
         }
         int average = total/(allHeight.size());
        System.out.println("average = " + average);

    }

    // above code will retrieve only first page include 10 people
    // but we want to have more than 1- ppl in STAR WAR
    // we can get total count of people in first response COUNT field
    // then decide how many pages we have to go through by sending more request
    // then loop through the rest of pages to add all HEIGHT to the LIST
    // and calculate the average from final list
    // in order to go to next page we can use
    // page query parameter to decide which page want to see

    // HERE is step:
    // create empty Integer Lst
    // end get(/people) ->>
    // capture the total count using jsonPath
    // save first page height into List

    // Loop from page 2 till last page
    // get the List of heights Integer using jsonPath
    // add thi to the LiST


    @DisplayName("GET all  height from all pages then find average GET /api/people/")
    @Test
    public void testGetALLPagesAverageHeight() {

        List<Integer> allHeights = new ArrayList<>();

        //send initial request, find total count and decide how many pages
        JsonPath jp = get("/people").jsonPath();
        int peopleCount = jp.get("count");  // 82
        // if there is remainder we want to +1, if tehre is no keep it
        int pageCount = (peopleCount % 10 ==0) ? peopleCount / 10 : (peopleCount/10)+1;
        List<Integer> firstPageHeghts = jp.getList("results.height");
        allHeights.addAll(firstPageHeghts);

         // now its time loop and the rest of the pages
        for (int pageNum = 2; pageNum <= pageCount; pageNum++) {
            // GET /pepople?page=yourPageNumberGoesHEre
            List<Integer> heightsOnThisPage = get("/people?page="+pageNum)
                    .jsonPath().getList("results.height");
            allHeights.addAll(heightsOnThisPage);

        }
        System.out.println("allHeights = " + allHeights);

    }




}
