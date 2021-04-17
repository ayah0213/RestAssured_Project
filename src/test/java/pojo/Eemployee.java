package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
//ignore any Json field that doesnt match belongs Pojo fields


public class Eemployee {
    // we just want to represent the Employee data with these fields and ignore any other fields
    private int employee_id;
    private String first_name;
    private String last_name;
    private int salary;
    private int department_id;
}
