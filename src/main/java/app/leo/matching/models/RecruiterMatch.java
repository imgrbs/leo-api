package app.leo.matching.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "recruiter_matches")
public class RecruiterMatch extends UserMatch {

    private long recruiterId;


    @OneToMany(mappedBy = "recruiterMatch")
    @JsonManagedReference
    private List<Position> positions;

    public RecruiterMatch() {
    }

    public RecruiterMatch(long recruiterId, long matchId) {
        super(matchId);
        this.setUserId(recruiterId);
        this.recruiterId = recruiterId;
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
