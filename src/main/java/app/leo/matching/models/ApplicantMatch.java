package app.leo.matching.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "applicant_matches")
public class ApplicantMatch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    @JsonBackReference
    private Match match;

    @OneToMany(mappedBy = "applicantMatch")
    @JsonManagedReference
    private List<ApplicantRanking> applicantRanking;

    private long applicantId;

    public ApplicantMatch() {

    }

    public ApplicantMatch(Match match) {
        this.match = match;
    }

    public ApplicantMatch(long applicantId,Match match) {
        this.match = match;
        this.applicantId = applicantId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
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
        return applicantMatch.getMatch().getId() == this.getMatch().getId();
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
