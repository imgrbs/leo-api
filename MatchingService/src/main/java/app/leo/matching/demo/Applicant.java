package app.leo.matching.demo;

import java.util.ArrayList;
import java.util.List;

public class Applicant {

    private String name;
    private List<Recruiter> preferredRecruiters = new ArrayList<>();

    public Applicant(String name) {
        this.name = name;
    }

    public Applicant(String name, List<Recruiter> preferredRecruiters) {
        this.name = name;
        this.preferredRecruiters = preferredRecruiters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Recruiter> getPreferredRecruiters() {
        return preferredRecruiters;
    }

    public void setPreferredRecruiters(List<Recruiter> preferredRecruiters) {
        this.preferredRecruiters = preferredRecruiters;
    }
}
