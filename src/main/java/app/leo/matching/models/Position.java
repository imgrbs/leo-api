package app.leo.matching.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
 @Entity
@Table(name = "positions")
 @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
 public class Position implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private int capacity;

    private long matchId;

    private String description;

    private String money;

    @ElementCollection
    private List<String> documents;

    @OneToMany(mappedBy = "position")
    @JsonProperty("collection")
    @JsonManagedReference
    private List<RecruiterRanking> recruiterRankings;

    @ManyToOne
    @JoinColumn(name = "recruiter_match_id")
    @JsonIgnore
    private RecruiterMatch recruiterMatch;

    @OneToMany(mappedBy = "position")
    @JsonIgnore
    private List<ApplicantRanking> applicantRankings;

    public Position() {
    }

    public Position(@NotBlank String name, @NotNull int capacity, RecruiterMatch recruiterMatch) {

        this.name = name;
        this.capacity = capacity;
        this.recruiterMatch = recruiterMatch;
    }

     public Position(@NotBlank String name, @NotNull int capacity, long matchId, String money) {
         this.name = name;
         this.capacity = capacity;
         this.matchId = matchId;
         this.money = money;
     }

     public Position(@NotBlank String name, @NotNull int capacity, long matchId, String description, String money) {
         this.name = name;
         this.capacity = capacity;
         this.matchId = matchId;
         this.description = description;
         this.money = money;
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

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public RecruiterMatch getRecruiterMatch() {
        return recruiterMatch;
    }

    public void setRecruiterMatch(RecruiterMatch recruiterMatch) {
        this.recruiterMatch = recruiterMatch;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

     public String getDescription() {
         return description;
     }

     public void setDescription(String description) {
         this.description = description;
     }

     public List<String> getDocuments() {
         return documents;
     }

     public void setDocuments(List<String> documents) {
         this.documents = documents;
     }
 }
