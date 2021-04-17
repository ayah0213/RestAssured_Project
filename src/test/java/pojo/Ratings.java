package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Ratings {



  @JsonProperty("Source")
    private String source;
  @JsonProperty("Value")
    private String value ;

}
