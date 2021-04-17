package day7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.BreakBCharacter;
import test_util.BreakingBad_testBase;

import java.util.List;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;

public class BreakingBad_API extends BreakingBad_testBase {



    @DisplayName("GET /api/character")
    @Test
    public void testBreakingBAdPojoDeserelazation() {
        // send all characters    https://www.breakingbadapi.com/api/characters
        //  get all characters from char_id, name, occupation, nickname, appearance as POJO

      // save 1st item into Character Pojo
        BreakBCharacter c1 = get("/characters").jsonPath()
                .getObject("[0]", BreakBCharacter.class);

        System.out.println("c1 = " + c1);


        System.out.println("==========================");

        // save alll items into List of Characters
        List<BreakBCharacter> listOfAlChar  = get("characters")
                .jsonPath()
                .getList("", BreakBCharacter.class);
        System.out.println("listOfAlChar = " + listOfAlChar);


        System.out.println("=====================================");
        // additionally print all Character name who appeared exactly 5 times --> check appearance list size
         for (BreakBCharacter each : listOfAlChar){
             if (each.getAppearance().size()== 5){
                 System.out.println(each.getName());
             }
         }

         // find oit all characters which showed up in season 3
        for (BreakBCharacter each : listOfAlChar){
            if (each.getAppearance().size()==1 && each.getAppearance().get(0)==3){
                System.out.println("each= "+each);
            }
        }


    }



}
