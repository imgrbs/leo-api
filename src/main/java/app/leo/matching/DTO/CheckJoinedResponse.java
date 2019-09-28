package app.leo.matching.DTO;

public class CheckJoinedResponse {

    private boolean isJoined;

    public CheckJoinedResponse() {
    }

    public CheckJoinedResponse(boolean isJoined) {
        this.isJoined = isJoined;
    }

    public boolean isJoined() {
        return isJoined;
    }

    public void setJoined(boolean joined) {
        isJoined = joined;
    }
}
