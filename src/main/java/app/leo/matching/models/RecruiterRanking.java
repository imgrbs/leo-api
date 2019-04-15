package app.leo.matching.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "recruiter_rankings")
public class RecruiterRanking extends Ranking {

    @ManyToOne
    @JoinColumn(name = "position_id")
    @JsonBackReference
    private Position position;

    @ManyToOne
    @JoinColumn(name = "applicant_match_id")
    @JsonBackReference
    private ApplicantMatch applicantMatch;

    public RecruiterRanking() {
    }

    public RecruiterRanking(Position position, ApplicantMatch applicantMatch) {
        this.position = position;
        this.applicantMatch = applicantMatch;
    }

    public RecruiterRanking(@NotNull int sequence, Match match, Position position, ApplicantMatch applicantMatch) {
        super(sequence, match);
        this.position = position;
        this.applicantMatch = applicantMatch;
    }

    public RecruiterRanking(ApplicantMatch applicantMatch) {
        this.applicantMatch = applicantMatch;
    }

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

    @Override
    public boolean equals(Object o) {
        if(o instanceof RecruiterRanking) {
            RecruiterRanking recruiterRanking = (RecruiterRanking) o;
            ApplicantMatch applicantMatch = recruiterRanking.getApplicantMatch();
            return this.applicantMatch.equals(this.getApplicantMatch());
        }
        return false;
    }

    @Override
    public String toString() {
        return "RecruiterRanking{" +
                "position=" + position +
                ", applicantMatch=" + applicantMatch +
                '}';
    }
}
