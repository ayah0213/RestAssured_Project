package day8;
import librray1.Lib1TestBase;
import org.junit.jupiter.api.Test;
import test_util.DB_Utility;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;

public class Library_API_DB_Test extends Lib1TestBase {



@Test
    public void testDashboardStatNumber(){

    DB_Utility.runQuery("SELECT count(*) FROM books");
    String bookCount = DB_Utility.getFirstRowFirstColumn();
    System.out.println("bookCount = " + bookCount);


    DB_Utility.runQuery("SELECT count(*) FROM users");
    String userCount = DB_Utility.getFirstRowFirstColumn();
    System.out.println("userCount = " + userCount);

    DB_Utility.runQuery("SELECT COUNT(*) FROM book_borrow WHERE returned_date IS NULL");
    String notReturnedBooks = DB_Utility.getFirstRowFirstColumn();
    System.out.println("notReturnedBooks = " + notReturnedBooks);


    // sending actual request
    given()
            .header("X-LIBRARY-TOKEN", librarianToken)
            .when()
                    .get("/dashboard_stats")
            .then()
            .log().all()
            .statusCode(200)
            .body("book_count", is(bookCount))
            .body("users",is(userCount))
            .body("borrowed_books", is(notReturnedBooks));


    System.out.println("=========================================================");

    // 2n way with  Soft Assertion OPTIONAL way

    given()
            .header("X-LIBRARY-TOKEN", librarianToken)
          .expect()
            .log().body()
            .statusCode(200)
            .body("book_count", is(bookCount))
            .body("users",is(userCount))
            .body("borrowed_books", is(notReturnedBooks))
            .when()
            .get("/dashboard_stats");

}


}
