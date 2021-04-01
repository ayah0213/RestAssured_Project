package day1;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;


@DisplayName("Intro to RestAssured")
public class RestAssured_Intro {


   @DisplayName("Testing Heloo")
    @Test
    public void testHello(){

     // GET  http://3.92.215.98:8000/api/hello
       // Save the response into object with type Response
       Response response = get("http://3.92.215.98:8000/api/hello");

       // extracting infromation from REsponse obj
       System.out.println("response.statusCode = " + response.statusCode()); // shortcut .soutv

       // getting Header specific one
       System.out.println("response.getHeader(Content-Type) = " + response.getHeader("Content-Type"));
       System.out.println("response.header(\"Date\") = " + response.header("Date"));

       // getting time which spent during executions
       System.out.println("response.getTime() = " + response.getTime());
       System.out.println("response.time() = " + response.time());

       // print the Body of Response
       System.out.println("response.asString() = " + response.asString());


       // get Content-Type header with ready method
       System.out.println("response.getContentType() = " + response.getContentType());

       // HAmcrest Matcher using Assertion
       assertThat(response.statusCode(), is(200) );
       assertThat(response.getContentType(), is("text/plain;charset=UTF-8"));
       assertThat(response.contentType(), startsWith("text/plain"));
       assertThat(response.asString(), is(equalTo("Hello from Sparta")));

     // printing the result
       // preetyPrint - returns String
       // preetyPeek - return same response Object
       response.prettyPeek();
       response.prettyPrint();


   }

   @DisplayName(("Testing GET /api/spartans/{id} Endpoint "))
    @Test
    public void testSingleSpartan(){

       Response response =
               get("http://3.92.215.98:8000/api/spartans/16")
                       .prettyPeek();

       System.out.println("response.getStatusCode() = " + response.getStatusCode());
       System.out.println("response.contentType() = " + response.contentType());
       System.out.println("response.header(\"Connection\") = " + response.header("Connection"));

       assertThat(response.statusCode(), is(200));
      assertThat(response.contentType(), is("application/json"));
      assertThat(response.header("Connection"), is(equalTo("keep-alive")));

      // u can use this to compare reh text with preety Peek format
       System.out.println(response.asString());

       System.out.println("---------------------------");
       System.out.println("Now we gonna learn about PATH");
       System.out.println("----------------------------");


       // getting the field  value of Json - Body
       // path method
       System.out.println("response.path(\"id\") = " + response.path("id"));
       System.out.println("response.path(\"name\") = " + response.path("name"));
       System.out.println("response.path(\"phone\") = " + response.path("phone"));
       System.out.println("response.path(\"gender\") = " + response.path("gender"));

       // save id and name into data-type
       int myId = response.path("id");
       String name = response.path("name");
       Long number = response.path("phone");
       String gender = response.path("gender");
       System.out.println("-----------Printing them---------");
       System.out.println("gender = " + gender);
       System.out.println("number = " + number);
       System.out.println("name = " + name);
       System.out.println("myId = " + myId);


   }



}
