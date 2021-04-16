package pojo;

public class REGIONS {

    private int region_id;
    private String region_name;

    public int getRegion_id() {
        return region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    @Override
    public String toString() {
        return "REGIONS{" +
                "region_id=" + region_id +
                ", region_name='" + region_name + '\'' +
                '}';
    }
}
