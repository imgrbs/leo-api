package app.leo.matching.DTO;

public class GetMatchResultByUserIdAndMatchIdResponse {

    private long matchResultId;
    private GetPositionsByMatchIdResponse position;
    private GetApplicantsByMatchIdResponse applicant;

    public GetMatchResultByUserIdAndMatchIdResponse() {
    }

    public long getMatchResultId() {
        return matchResultId;
    }

    public void setMatchResultId(long matchResultId) {
        this.matchResultId = matchResultId;
    }

    public GetPositionsByMatchIdResponse getPosition() {
        return position;
    }

    public void setPosition(GetPositionsByMatchIdResponse position) {
        this.position = position;
    }

    public GetApplicantsByMatchIdResponse getApplicant() {
        return applicant;
    }

    public void setApplicant(GetApplicantsByMatchIdResponse applicant) {
        this.applicant = applicant;
    }
}
