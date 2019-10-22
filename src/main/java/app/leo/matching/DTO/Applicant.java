package app.leo.matching.DTO;

import java.util.List;

public class Applicant {
    private long id;
    private String name;
    private List<Education> educations;
    private String email;
    private String telno;
    private List<String> skills;
    private String website;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
