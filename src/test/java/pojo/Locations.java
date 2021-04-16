package pojo;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Locations {

    private int location_id;
    private String street_address;
    private String postal_code;
    private String city;
    private String state_province;
    private String country_id;


}
