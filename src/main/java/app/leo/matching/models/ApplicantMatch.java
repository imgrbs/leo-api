package app.leo.matching.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "applicant_matches")
public class ApplicantMatch extends UserMatch {

    @OneToMany(mappedBy = "applicantMatch")
    @JsonManagedReference
    private List<ApplicantRanking> applicantRanking;

    private long applicantId;

    public ApplicantMatch() {

    }

    public ApplicantMatch( long applicantId,long matchId) {
        super(applicantId,matchId);
        this.applicantId = applicantId;
    }

    public List<ApplicantRanking> getApplicantRanking() {
        return applicantRanking;
    }

    public void setApplicantRanking(List<ApplicantRanking> applicantRanking) {
        this.applicantRanking = applicantRanking;
    }

    public long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }

    public boolean isIdEqual(ApplicantMatch applicantMatch){
        return applicantMatch.getParticipantId().equals(this.getParticipantId());
    }

    public boolean isSameMatchId(ApplicantMatch applicantMatch){
        return applicantMatch.getMatchId() == this.getMatchId();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ApplicantMatch){
            ApplicantMatch applicantMatch = (ApplicantMatch) obj;
            return this.isIdEqual(applicantMatch) && this.isSameMatchId(applicantMatch);
        }
        return false;
    }

}
