package librray1;

import io.restassured.http.ContentType;

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

}
