package app.leo.matching.models;

import javax.persistence.*;


@Entity
@Table(name = "applicant_rankings")
public class ApplicantRanking extends Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicantRankingId;
    @ManyToOne
    private ApplicantMatch applicantMatch;

    @ManyToOne
    private Position position;

    public ApplicantMatch getApplicantMatch() {
        return applicantMatch;
    }

    public void setApplicantMatch(ApplicantMatch applicantMatch) {
        this.applicantMatch = applicantMatch;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Long getApplicantRankingId() {
        return applicantRankingId;
    }

    public void setApplicantRankingId(Long applicantRankingId) {
        this.applicantRankingId = applicantRankingId;
    }
}
