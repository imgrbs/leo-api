package app.leo.matching.DTO;

import java.util.Date;

public class MatchDTO {

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

    public Date getStartJoiningDate() {
        return startJoiningDate;
    }

    public void setStartJoiningDate(Date startJoiningDate) {
        this.startJoiningDate = startJoiningDate;
    }

    public Date getApplicantRankingEndDate() {
        return applicantRankingEndDate;
    }

    public void setApplicantRankingEndDate(Date applicantRankingEndDate) {
        this.applicantRankingEndDate = applicantRankingEndDate;
    }

    public Date getRecruiterRankingEndDate() {
        return recruiterRankingEndDate;
    }

    public void setRecruiterRankingEndDate(Date recruiterRankingEndDate) {
        this.recruiterRankingEndDate = recruiterRankingEndDate;
    }

    public Date getAnnounceDate() {
        return announceDate;
    }

    public void setAnnounceDate(Date announceDate) {
        this.announceDate = announceDate;
    }

    public Date getEndJoiningDate() {
        return endJoiningDate;
    }

    public void setEndJoiningDate(Date endJoiningDate) {
        this.endJoiningDate = endJoiningDate;
    }
}
