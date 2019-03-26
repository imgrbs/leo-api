package app.leo.matching.models;

import javax.persistence.*;

@Entity
@Table(name = "recruiter_matches")
public class RecruiterMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isComfirmation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    private long recruiterId;

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

    public long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(long recruiterId) {
        this.recruiterId = recruiterId;
    }
}
