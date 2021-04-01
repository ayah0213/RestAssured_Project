package day1;


import org.junit.jupiter.api.*;

@DisplayName("LEarning Test Lifecycle Annotations")
public class TestLifeCycleAnnotations {

    @BeforeAll
    public static void init(){
        System.out.println("Before all this running");
    }


    @AfterAll
    public static void destroy(){
        System.out.println("Close after all running ");
    }

    @BeforeEach
    public void initEach(){
        System.out.println("Before each is running");
    }

    @AfterEach
    public void tearDownEach(){
        System.out.println("Aftre each  is running");
    }

    @Test
    public void test1(){
        System.out.println("Test 1 is running");
    }


    // ignoring certain Test @Disabled annotation
    @Disabled
    @Test
    public void test2(){
        System.out.println("Test 2 is running");
    }





}
