package app.leo.matching.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;


@Entity
@Table(name = "applicant_rankings")
public class ApplicantRanking extends Ranking {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_match_id")
    @JsonBackReference
    private ApplicantMatch applicantMatch;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    @JsonBackReference
    private Position position;

    public ApplicantRanking() {
    }

    public ApplicantRanking(ApplicantMatch applicantMatch, Position position) {
        this.applicantMatch = applicantMatch;
        this.position = position;
    }

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
