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

    private boolean isComfirmation;

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

    public ApplicantMatch(Long id, boolean isComfirmation, Match match) {
        this.id = id;
        this.isComfirmation = isComfirmation;
        this.match = match;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isComfirmation() {
        return isComfirmation;
    }

    public void setComfirmation(boolean comfirmation) {
        isComfirmation = comfirmation;
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ApplicantMatch){
            ApplicantMatch applicantMatch = (ApplicantMatch) obj;
            return applicantMatch.getId()==this.getId();
        }
        return false;
    }

    @Override
    public String toString() {
        return "ApplicantMatch{" +
                "id=" + id +
                ", isComfirmation=" + isComfirmation +
                ", match=" + match +
                ", applicantRanking=" + applicantRanking +
                ", applicantId=" + applicantId +
                '}';
    }
}
