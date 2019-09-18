package app.leo.matching.DTO;

import java.util.List;

public class RecruiterJoinMatchRequest {

    private List<PositionDTO>  positions;
    private User user;
    private String token;

    public RecruiterJoinMatchRequest() {
    }

    public List<PositionDTO> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionDTO> positions) {
        this.positions = positions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
