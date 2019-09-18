package app.leo.matching.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "applicant_rankings")
public class ApplicantRanking extends Ranking implements Comparable<ApplicantRanking>{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_match_id")
    @JsonBackReference(value = "applicantMatch-ranking")
    private ApplicantMatch applicantMatch;


    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    public ApplicantRanking() {
    }

    public ApplicantRanking(@NotNull int sequence, long matchId, ApplicantMatch applicantMatch, Position position) {
        super(sequence, matchId);
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

    public int compareTo(ApplicantRanking applicantRanking) {
        int Before = -1;
        int Equal = 0;
        int After = 1;
        int compareNumber = this.getSequence() - applicantRanking.getSequence();
        if( compareNumber <0){
            return Before;
        }else if(compareNumber >0){
            return After;
        }else{
            return Equal;
        }

    }

}
