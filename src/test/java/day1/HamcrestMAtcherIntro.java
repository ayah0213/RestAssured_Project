package day1;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMAtcherIntro {

    @Test
    public void sinpleTest1(){
        //SYNTAX of Hamcrest Assertiom
        // assert 10 is equal 5+5
        assertThat(5+5, is(10));
        assertThat(5+5, equalTo(10));

        // matchers has overloaded version
        // 1st that accept actual value
        //2nd that accept another matcher
        // bllow exampl. is method is accepting another matcher aqualTo to make is readable
        assertThat(5+5, is(equalTo(10)));

        // negative Assertions
     assertThat(5+5, not(11)  );
     assertThat(5+5, is(not(11)));
     assertThat(5+5, is(not(equalTo(11))));

     //comparision numbers
        // greaterThen
        assertThat(5+5, is(greaterThan(9)));
        // lessThan
        assertThat(5+5, is(lessThan(70)));
       // greaterThanEqualTo()
        assertThat(5+5, is(greaterThanOrEqualTo(5)));
        // lessThanOrEqualTo()
        assertThat(5+5, is(lessThanOrEqualTo(11)));



    }


    @DisplayName("Matchers related tp Sring")
    @Test
    public void stringMatchers(){


        String msg = "B21 is learning Hamcrest";

        // checking for equality as same number above
        assertThat(msg, is( "B21 is learning Hamcrest"));
        assertThat(msg, is(equalTo( "B21 is learning Hamcrest")));
        assertThat(msg, equalTo( "B21 is learning Hamcrest"));

        // hceck is the msg starts with B21
        assertThat(msg, startsWith("B21"));

        // now do it in case insesitive matter
        assertThat(msg, startsWithIgnoringCase("b21"));


        // check what it ends with?
        assertThat(msg, endsWith("rest"));

        // contains Hamcrest method
        assertThat(msg, containsString("learning"));

        // ingonre case Contains method
        assertThat(msg, containsStringIgnoringCase("Learning"));


        // blank String method Hancrest
        String str = " ";
        assertThat(str, blankString());



    }

    @DisplayName("Hamcrest Support for Collections")
    @Test
    public void testCollections(){
        List<Integer> list = Arrays.asList(1,2,3,4,766,45,32,12); // size of list =8
        assertThat(list,hasSize(8));
        // check if this list has items 7
        assertThat(list, hasItem(4));
        // check if this list has items 7, 9,8 inside of my List
        assertThat(list, hasItems(1,766,4));

        // check if evevry item in this Lits in greater than 0
        assertThat(list, everyItem(greaterThan(0)));



    }

}
