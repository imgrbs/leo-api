package app.leo.matching.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
        if(o instanceof ApplicantMatch) {
            ApplicantMatch applicantMatch = (ApplicantMatch)o;
            System.out.println("Shappy");
            return this.applicantMatch.equals(this.getApplicantMatch());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "RecruiterRanking{" +
                "position=" + position +
                ", applicantMatch=" + applicantMatch +
                '}';
    }
}
