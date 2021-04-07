package spartan_util;

import com.github.javafaker.Faker;
import pojo.Spartan;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUtil {

/*
used to return valid Map object to represent post body for POST / spartans request
@return Map object with Random name, gender, phone range -> (5000000000-1000000000)

 */


   private static Faker faker = new Faker();

    public static Map<String, Object> getRandomSpartanMap(){


        // method which getting RANDOM SPARTAN with MAP key+value format with returning random data objects
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name",faker.name().firstName());
        bodyMap.put("gender",faker.demographic().sex());
        bodyMap.put("phone",faker.number().numberBetween(5000000000L, 1000000000L));
        return bodyMap;

    }


    // method which getting RANDOM SPARTAN with POJO with returning random data objects
    public static Spartan getRandomSpartanPOJO(){

        Spartan sp = new Spartan();
        sp.setName(faker.name().firstName());
        sp.setGender(faker.demographic().sex());
        sp.setPhone(faker.number().numberBetween(5000000000L, 1000000000L));
        return sp;
    }


    // to test it we can try to print each of them
//    public static void main(String[] args) {
//
//        System.out.println(getRandomSpartanMap());
//
//        System.out.println(getRandomSpartanPOJO());
//    }

}
