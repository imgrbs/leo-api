package app.leo.matching.models;


import javax.persistence.*;

@Entity
@Table(name = "recruiter_rankings")
public class RecruiterRanking extends Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recruiterRankingId;

    @ManyToOne
    private RecruiterMatch recruiterMatch;

    @ManyToOne
    private ApplicantMatch applicantMatch;

    public RecruiterMatch getRecruiterMatch() {
        return recruiterMatch;
    }

    public void setRecruiterMatch(RecruiterMatch recruiterMatch) {
        this.recruiterMatch = recruiterMatch;
    }

    public ApplicantMatch getApplicantMatch() {
        return applicantMatch;
    }

    public void setApplicantMatch(ApplicantMatch applicantMatch) {
        this.applicantMatch = applicantMatch;
    }

    public Long getRecruiterRankingId() {
        return recruiterRankingId;
    }

    public void setRecruiterRankingId(Long recruiterRankingId) {
        this.recruiterRankingId = recruiterRankingId;
    }
}
