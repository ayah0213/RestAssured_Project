package day1;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// setting dipslay name of the test class test result by using @DisplayANme
@DisplayName("Day 1 Junit 5")
public class Junit_intro {

@DisplayName("Testing numbers")
    @Test
    public void test(){
        System.out.println("LEarning junit 5");

        assertEquals(1,1);
        assertEquals(1,2,"somth is wrong");
    }



    @DisplayName("TEtsing tsrats with A")
    // add 1 test to assert your nme 1st character start with letter A
    @Test
    public void nameAssertion(){
        String name = "Ayjeren";

        assertEquals('A',name.charAt(0));

       // TEST Lifecycle Anotations from JUnit5 :
        // @BeforeAll @AfterAll @BeforeEach @AfterEach





    }



}
