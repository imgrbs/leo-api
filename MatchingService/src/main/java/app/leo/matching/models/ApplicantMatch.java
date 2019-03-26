package app.leo.matching.models;

import javax.persistence.*;


@Entity
@Table(name = "applicant_matches")
public class ApplicantMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicantMatchId;

    private boolean isComfirmation;

    @OneToOne
    private Match match;

    @OneToMany
    private ApplicantRanking applicantRanking;

    public ApplicantMatch(Long applicantMatchId, boolean isComfirmation, Match match) {
        this.applicantMatchId = applicantMatchId;
        this.isComfirmation = isComfirmation;
        this.match = match;
    }

    public ApplicantMatch(Long applicantMatchId, boolean isComfirmation, Match match, ApplicantRanking applicantRanking) {
        this.applicantMatchId = applicantMatchId;
        this.isComfirmation = isComfirmation;
        this.match = match;
        this.applicantRanking = applicantRanking;
    }

    public Long getApplicantMatchId() {
        return applicantMatchId;
    }

    public void setApplicantMatchId(Long applicantMatchId) {
        this.applicantMatchId = applicantMatchId;
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

    public ApplicantRanking getApplicantRanking() {
        return applicantRanking;
    }

    public void setApplicantRanking(ApplicantRanking applicantRanking) {
        this.applicantRanking = applicantRanking;
    }
}
