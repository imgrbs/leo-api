package app.leo.matching.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "recruiter_rankings")
public class RecruiterRanking extends Ranking {

    @ManyToOne
    @JsonBackReference
    private Position position;

    @ManyToOne
    @JoinColumn(name = "applicant_match_id")
    @JsonBackReference
    private ApplicantMatch applicantMatch;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public ApplicantMatch getApplicantMatch() {
        return applicantMatch;
    }

    public void setApplicantMatch(ApplicantMatch applicantMatch) {
        this.applicantMatch = applicantMatch;
    }

    public long getApplicantMatchId() {
        return this.applicantMatch.getId();
    }

}
