package app.leo.matching.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "matches")
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "match")
    @JsonManagedReference
    private List<ApplicantMatch> applicantMatches;

    public Match() {
    }

    public Match(Long id, @NotBlank String name) {
        this.id = id;
        this.name = name;
    }

    @NotBlank
    private String name;

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
