package app.leo.matching.DTO;

public class Education {
    private int id;
    private String university;
    private String year;
    private String major;
    private String gpax;

    public Education() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGpax() {
        return gpax;
    }

    public void setGpax(String gpax) {
        this.gpax = gpax;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
