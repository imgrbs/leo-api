package app.leo.matching.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;

    @OneToMany
    private List<ApplicantMatch> applicantMatchList;

    public Match() {
    }

    public Match(Long matchId, @NotBlank String name) {
        this.matchId = matchId;
        this.name = name;
    }

    @NotBlank
    private String name;

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
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
