package app.leo.matching.models;

import javax.persistence.*;

@Entity
@Table(name = "recruiter_matches")
public class RecruiterMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recruiterMatchId;

    private boolean isComfirmation;

    @OneToOne
    private Match match;

    public Long getRecruiterMatchId() {
        return recruiterMatchId;
    }

    public void setRecruiterMatchId(Long recruiterMatchId) {
        this.recruiterMatchId = recruiterMatchId;
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
}
