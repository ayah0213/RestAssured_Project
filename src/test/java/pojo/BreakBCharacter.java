package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.util.List;
import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)



public class BreakBCharacter {
    // // get all characters from char_id, name, occupation, nickname, appearnce

    @JsonProperty("char_id")
    private int character_id;
    private String name;
    private List<String> occupation;
    private String nickname;
    private List<Integer> appearance;
}
