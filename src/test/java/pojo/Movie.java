package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import groovy.transform.ToString;
import lombok.Getter;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)

public class Movie {

    // title Str, year int , Released str ,Languag
@JsonProperty("Title")
    private String title;

@JsonProperty("Year")
    private int year;

@JsonProperty("Released")
    private String released;

@JsonProperty("Language")
    private String language;
}