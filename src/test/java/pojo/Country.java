package pojo;


import lombok.*;


@Getter  //this is from Lombok
@Setter   //this is from Lombok
@ToString  //this is from Lombok
@NoArgsConstructor
@AllArgsConstructor

public class Country {


    private String country_id;
    private String country_name;
    private int region_id;


}
