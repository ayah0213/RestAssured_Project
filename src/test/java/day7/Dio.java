package day7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.DioDog;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
public class Dio {

    //SEND GET https://e94d0713-391b-4218-906d-66e9614ea580.mock.pstmn.io/dio
    /*
    {
        "breed": "Yorkie",
        "color": "Gold",
        "age": 3,
        "owner": {
            "ownerName": "Inci",
            "address": "123 main street"
        }
    }
     */
    // represent the result json in pojo , and print them out
    @DisplayName("Practice nested json into POJO")
    @Test
    public void testDio() {

String reqURL ="https://e94d0713-391b-4218-906d-66e9614ea580.mock.pstmn.io/dio";
       DioDog dio = get(reqURL).as(DioDog.class);
        System.out.println("dio = " + dio);
    }
}
