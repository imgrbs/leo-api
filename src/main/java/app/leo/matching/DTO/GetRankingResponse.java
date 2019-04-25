package app.leo.matching.DTO;

public class GetRankingResponse {
    private long rankingId;
    private int sequence;
    private long matchId;
    private GetApplicantsByMatchIdResponse applicantMatch;
    private GetPositionsByMatchIdResponse position;

    public GetRankingResponse() {
    }

    public long getRankingId() {
        return rankingId;
    }

    public void setRankingId(long rankingId) {
        this.rankingId = rankingId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public GetApplicantsByMatchIdResponse getApplicantMatch() {
        return applicantMatch;
    }

    public void setApplicantMatch(GetApplicantsByMatchIdResponse applicantMatch) {
        this.applicantMatch = applicantMatch;
    }

    public GetPositionsByMatchIdResponse getPosition() {
        return position;
    }

    public void setPosition(GetPositionsByMatchIdResponse position) {
        this.position = position;
    }
}
