package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.*;

@Getter
@ToString


public class Departments {
    // instructing Jackson to bind Json filed called "department_id" to below java field
    @JsonProperty("department_id")
    private int departmentId;  // --"department_id"

    @JsonProperty("department_name")
    private String name;   // we do what we want by changing name but using JsonProperty

    private int manager_id;
    private int location_id;

}
