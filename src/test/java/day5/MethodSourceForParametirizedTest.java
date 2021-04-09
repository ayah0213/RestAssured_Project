package day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MethodSourceForParametirizedTest {

    @ParameterizedTest
    @MethodSource( "getManyNames")

    public void testPrintName(String name) {
        System.out.println("name = " + name);
        assertTrue(name.length()>4);
    }
    // create a static method to return many names
    public static Stream<String> getManyNames() {

        List<String> nameList = Arrays.asList("Emre","Dian","Toki","Mayah","Zoyah","Sonyah","Liyah","Erjon","Afrooz","Lilah");
        return nameList.stream();



    ///2nd option
//    // create a static method to return many names
//    public static List<String> getManyNames() {
//
//        List<String> nameList = Arrays.asList("Emre","Dian","Toki","Mayah","Zoyah","Sonyah","Liyah","Erjon","Afrooz","Lilah");
//        return nameList;




            }

}

