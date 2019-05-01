package app.leo.matching.validator;

public class PutRecruiterRankingRequest {
    private long userId;
    private int sequence;

    public PutRecruiterRankingRequest() {
    }

    public PutRecruiterRankingRequest(long userId, int sequence) {
        this.userId = userId;
        this.sequence = sequence;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
