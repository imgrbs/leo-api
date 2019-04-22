package app.leo.matching.DTO;

public class GetApplicantsByMatchIdResponse {
    private long id;
    private long matchId;
    private long applicantId;
    private Applicant applicant;

    public GetApplicantsByMatchIdResponse() {
    }

    public GetApplicantsByMatchIdResponse(long id, long matchId, long applicantId, Applicant applicant) {
        this.id = id;
        this.matchId = matchId;
        this.applicantId = applicantId;
        this.applicant = applicant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }
}
