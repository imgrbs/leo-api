package app.leo.matching.DTO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class MatchDTO {

    private final ZoneId UTC = ZoneId.of("UTC");

    private Long id;

    private String name;

    private String description;

    private Date startJoiningDate;

    private Date endJoiningDate;

    private Date applicantRankingEndDate;

    private Date recruiterRankingEndDate;

    private Date announceDate;


    public MatchDTO() {
    }

    public MatchDTO(Long id, String name, String description, Date startJoiningDate, Date applicantRankingEndDate, Date recruiterRankingEndDate, Date announceDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startJoiningDate = startJoiningDate;
        this.applicantRankingEndDate = applicantRankingEndDate;
        this.recruiterRankingEndDate = recruiterRankingEndDate;
        this.announceDate = announceDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartJoiningDate() {
        return startJoiningDate.toInstant().atZone(UTC).toLocalDate();
    }

    public void setStartJoiningDate(Date startJoiningDate) {
        this.startJoiningDate = startJoiningDate;
    }

    public LocalDate getApplicantRankingEndDate() {
        return applicantRankingEndDate.toInstant().atZone(UTC).toLocalDate();
    }

    public void setApplicantRankingEndDate(Date applicantRankingEndDate) {
        this.applicantRankingEndDate = applicantRankingEndDate;
    }

    public LocalDate getRecruiterRankingEndDate() {
        return recruiterRankingEndDate.toInstant().atZone(UTC).toLocalDate();
    }

    public void setRecruiterRankingEndDate(Date recruiterRankingEndDate) {
        this.recruiterRankingEndDate = recruiterRankingEndDate;
    }

    public LocalDate getAnnounceDate() {
        return announceDate.toInstant().atZone(UTC).toLocalDate();
    }

    public void setAnnounceDate(Date announceDate) {
        this.announceDate = announceDate;
    }

    public LocalDate getEndJoiningDate() {
        return endJoiningDate.toInstant().atZone(UTC).toLocalDate();
    }

    public void setEndJoiningDate(Date endJoiningDate) {
        this.endJoiningDate = endJoiningDate;
    }
}
