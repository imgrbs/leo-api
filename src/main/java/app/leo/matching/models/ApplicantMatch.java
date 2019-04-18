package app.leo.matching.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "applicant_matches")
public class ApplicantMatch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long matchId;

    @OneToMany(mappedBy = "applicantMatch")
    @JsonManagedReference
    private List<ApplicantRanking> applicantRanking;

    private long applicantId;

    public ApplicantMatch() {

    }


    public ApplicantMatch(long applicantId,long matchId) {
        this.id = applicantId;
        this.applicantId = applicantId;
        this.matchId= matchId;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
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
        return applicantMatch.getId() == this.getId();
    }

    public boolean isSameMatchId(ApplicantMatch applicantMatch){
        return applicantMatch.matchId == this.matchId;
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
