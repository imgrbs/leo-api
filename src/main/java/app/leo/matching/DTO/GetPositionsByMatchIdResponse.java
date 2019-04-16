package app.leo.matching.DTO;

import app.leo.matching.models.Position;

import java.util.List;

public class GetPositionsByMatchIdResponse {
    private List<Position> positionList;

    public GetPositionsByMatchIdResponse() {
    }

    public GetPositionsByMatchIdResponse(List<Position> positionList) {
        this.positionList = positionList;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }
}
