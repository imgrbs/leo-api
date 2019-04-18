package app.leo.matching.DTO;

import app.leo.matching.models.ApplicantMatch;

import java.io.Serializable;
import java.util.List;

public class Match implements Serializable {

    private Long id;

    private List<ApplicantMatch> applicantMatches;

    private String name;

    public Match() {
    }

    public Match(Long id,String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ApplicantMatch> getApplicantMatches() {
        return applicantMatches;
    }

    public void setApplicantMatches(List<ApplicantMatch> applicantMatches) {
        this.applicantMatches = applicantMatches;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
