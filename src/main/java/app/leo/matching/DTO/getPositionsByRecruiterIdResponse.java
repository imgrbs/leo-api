package app.leo.matching.DTO;

import app.leo.matching.models.Position;

import java.util.List;

public class getPositionsByRecruiterIdResponse {
    private long matchId;
    private long recruiterId;
    private List<Position> positionList;

    public getPositionsByRecruiterIdResponse() {
    }

    public getPositionsByRecruiterIdResponse(long matchId, long recruiterId, List<Position> positionList) {
        this.matchId = matchId;
        this.recruiterId = recruiterId;
        this.positionList = positionList;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(long recruiterId) {
        this.recruiterId = recruiterId;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }
}
