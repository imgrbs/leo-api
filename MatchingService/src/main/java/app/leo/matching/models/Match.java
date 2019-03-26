package app.leo.matching.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "matches")
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "match")
    private List<ApplicantMatch> applicants;

    public Match() {
    }

    public Match(Long id, @NotBlank String name) {
        this.id = id;
        this.name = name;
    }

    @NotBlank
    private String name;

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

    public List<ApplicantMatch> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<ApplicantMatch> applicants) {
        this.applicants = applicants;
    }
}
