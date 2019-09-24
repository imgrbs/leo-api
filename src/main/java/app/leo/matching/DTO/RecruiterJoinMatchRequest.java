package app.leo.matching.DTO;

import java.util.List;

public class RecruiterJoinMatchRequest {

    private List<PositionDTO>  positions;

    public RecruiterJoinMatchRequest() {
    }

    public List<PositionDTO> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionDTO> positions) {
        this.positions = positions;
    }

}
