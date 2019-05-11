package app.leo.matching.DTO;

public class GetApplicantsByMatchIdResponse {
    private long participantId;
    private long matchId;
    private long applicantId;
    private Applicant applicant;

    public GetApplicantsByMatchIdResponse() {
    }

    public GetApplicantsByMatchIdResponse(long participantId, long matchId, long applicantId, Applicant applicant) {
        this.participantId = participantId;
        this.matchId = matchId;
        this.applicantId = applicantId;
        this.applicant = applicant;
    }

    public long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(long participantId) {
        this.participantId = participantId;
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
