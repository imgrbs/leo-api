package app.leo.matching.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "positions")
public class Position implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private int capacity;

    @OneToMany(mappedBy = "position")
    @JsonManagedReference
    private List<RecruiterRanking> recruiterRankings;

    @ManyToOne
    @JoinColumn(name = "recruiter_match_id")
    @JsonBackReference
    private RecruiterMatch recruiterMatch;

    public Position() {
    }

    public Position(@NotBlank String name, @NotNull int capacity, RecruiterMatch recruiterMatch) {

        this.name = name;
        this.capacity = capacity;
        this.recruiterMatch = recruiterMatch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<RecruiterRanking> getRecruiterRankings() {
        return recruiterRankings;
    }

    public void setRecruiterRankings(List<RecruiterRanking> recruiterRankings) {
        this.recruiterRankings = recruiterRankings;
    }

    public RecruiterMatch getRecruiterMatch() {
        return recruiterMatch;
    }

    public void setRecruiterMatch(RecruiterMatch recruiterMatch) {
        this.recruiterMatch = recruiterMatch;
    }

}
