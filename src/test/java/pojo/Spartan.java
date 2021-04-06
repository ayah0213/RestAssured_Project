package pojo;


// this is class is meant to to be BLUEPRINT for Creating SPartan POJO
// to represent json data with 3 fileds : name, gender, phone
//POJO : PLain old java object, used to represent Data
// Required Part of POJO :
//Encapsulated fields(private fileds public getters & setters)
// OPTIONALLY:
// No Arg Constructor
// we will add all our Constructor for easily creating object in one shot
// toString methods to view printed results...

public class Spartan {

private String name;
private String gender;
private Long phone;


public Spartan(){

}

    public Spartan(String name, String gender, Long phone) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Spartan{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }
}
