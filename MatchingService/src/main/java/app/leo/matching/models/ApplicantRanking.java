package app.leo.matching.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "applicant_rankings")
public class ApplicantRanking extends Ranking {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_match_id")
    @JsonBackReference
    private ApplicantMatch applicantMatch;


    @ManyToOne
    @JoinColumn(name = "position_id")
    @JsonManagedReference
    private Position position;

    public ApplicantRanking() {
    }

    public ApplicantRanking(@NotNull int sequence, Match match, ApplicantMatch applicantMatch, Position position) {
        super(sequence, match);
        this.applicantMatch = applicantMatch;
        this.position = position;
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
