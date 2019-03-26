package app.leo.matching.models;

import javax.persistence.*;


@Entity
@Table(name = "applicant_rankings")
public class ApplicantRanking extends Ranking {

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

}
