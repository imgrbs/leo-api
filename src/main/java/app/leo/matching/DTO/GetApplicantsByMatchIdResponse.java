package app.leo.matching.DTO;

public class GetApplicantsByMatchIdResponse {
    private long userId;
    private long matchId;
    private long applicantId;
    private Applicant applicant;

    public GetApplicantsByMatchIdResponse() {
    }

    public GetApplicantsByMatchIdResponse(long userId, long matchId, long applicantId, Applicant applicant) {
        this.userId = userId;
        this.matchId = matchId;
        this.applicantId = applicantId;
        this.applicant = applicant;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
