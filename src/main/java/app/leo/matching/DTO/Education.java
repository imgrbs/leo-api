package app.leo.matching.DTO;

public class Education {
    private int id;
    private String educationName;
    private String gpax;

    public Education() {
    }

    public Education(int id, String educationName, String gpax) {
        this.id = id;
        this.educationName = educationName;
        this.gpax = gpax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getGpax() {
        return gpax;
    }

    public void setGpax(String gpax) {
        this.gpax = gpax;
    }
}
