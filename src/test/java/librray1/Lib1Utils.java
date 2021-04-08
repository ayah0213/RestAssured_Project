package librray1;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Lib1Utils {

public static String getToken(String username, String password){



    return given()
                    .contentType(ContentType.URLENC)
                    .formParam("email", username)
                    .formParam("password", password)
                    .when()
                    .post("/login")
                    .path("token");

}

public static Map<String, Object> getCreateRandomBook(){
/*  book information that i want to add by using LibUtils class method:
            name Awesome book
            isbn IMDBS132
            year 2019
            author Ike
            book_category_id 2
            description good book
         */
    Faker faker = new Faker();

    Map<String, Object> myBookMap = new HashMap<>() ;
    myBookMap.put("name",faker.book().title());
    myBookMap.put("isbn",faker.number().digits(8));
    myBookMap.put("year",faker.number().numberBetween(1600,2021));
    myBookMap.put("author",faker.book().author());
    myBookMap.put("book_category_id",faker.number().numberBetween(1,20));
    myBookMap.put("description",faker.book().genre());

    return myBookMap;






}

}
