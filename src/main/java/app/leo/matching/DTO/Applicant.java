package app.leo.matching.DTO;

import java.util.List;

public class Applicant {
    private long id;
    private String name;
    private List<Education> educations;

    public Applicant() {
    }

    public Applicant(long id, String name, List<Education> educations) {
        this.id = id;
        this.name = name;
        this.educations = educations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }
}
