package app.leo.matching.validator;

public class PutApplicantRankingRequest {
    private long positionId;
    private int sequence;

    public PutApplicantRankingRequest() {
    }

    public PutApplicantRankingRequest(long positionId, int sequece) {
        this.positionId = positionId;
        this.sequence = sequece;
    }

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
