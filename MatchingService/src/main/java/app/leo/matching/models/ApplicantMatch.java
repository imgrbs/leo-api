package app.leo.matching.models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "applicant_matches")
public class ApplicantMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isComfirmation;

    @ManyToOne
    private Match match;

    @OneToMany
    private List<ApplicantRanking> applicantRanking;

    public ApplicantMatch() {
    }

    public ApplicantMatch(Long id, boolean isComfirmation, Match match) {
        this.id = id;
        this.isComfirmation = isComfirmation;
        this.match = match;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isComfirmation() {
        return isComfirmation;
    }

    public void setComfirmation(boolean comfirmation) {
        isComfirmation = comfirmation;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public List<ApplicantRanking> getApplicantRanking() {
        return applicantRanking;
    }

    public void setApplicantRanking(List<ApplicantRanking> applicantRanking) {
        this.applicantRanking = applicantRanking;
    }
}
