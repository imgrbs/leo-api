package app.leo.matching.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "recruiter_matches")
public class RecruiterMatch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long matchId;

    private long recruiterId;


    @OneToMany(mappedBy = "recruiterMatch")
    @JsonManagedReference
    private List<Position> positions;

    public RecruiterMatch() {
    }

    public RecruiterMatch(long recruiterId, long matchId) {
        this.matchId = matchId;
        this.recruiterId = recruiterId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(long recruiterId) {
        this.recruiterId = recruiterId;
    }

    public List<app.leo.matching.models.Position> getPositions() {
        return positions;
    }

    public void setPositions(List<app.leo.matching.models.Position> position) {
        positions = position;
    }
}
