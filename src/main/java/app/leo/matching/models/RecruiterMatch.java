package app.leo.matching.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recruiter_matches")
public class RecruiterMatch  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    @JsonBackReference
    private Match match;

    private long recruiterId;


    @OneToMany(mappedBy = "recruiterMatch")
    @JsonManagedReference
    private List<Position> Position;

    public RecruiterMatch() {
    }

    public RecruiterMatch(long recruiterId, Match match) {

        this.match = match;
        this.recruiterId = recruiterId;
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

    public long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(long recruiterId) {
        this.recruiterId = recruiterId;
    }

    public List<app.leo.matching.models.Position> getPosition() {
        return Position;
    }

    public void setPosition(List<app.leo.matching.models.Position> position) {
        Position = position;
    }
}
