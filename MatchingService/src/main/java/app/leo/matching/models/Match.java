package app.leo.matching.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<ApplicantMatch> applicantMatchList;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ApplicantMatch> getApplicantMatchList() {
        return applicantMatchList;
    }

    public void setApplicantMatchList(List<ApplicantMatch> applicantMatchList) {
        this.applicantMatchList = applicantMatchList;
    }
}
