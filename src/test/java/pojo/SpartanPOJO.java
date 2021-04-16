package pojo;

public class SpartanPOJO {

    private String name;
    private String gender;
    private int id;
    private Long phone;

//    public SpartanPOJO(String name, String gender, int id, Long phone) {
//        this.name = name;
//        this.gender = gender;
//        this.id = id;
//        this.phone = phone;
//    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public Long getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "SpartanPOJO{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", id=" + id +
                ", phone=" + phone +
                '}';
    }
}
