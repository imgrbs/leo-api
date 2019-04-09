package app.leo.matching.validator;

public class PutApplicantRankingRequest {
    private long positionId;
    private int sequece;

    public PutApplicantRankingRequest() {
    }

    public PutApplicantRankingRequest(long positionId, int sequece) {
        this.positionId = positionId;
        this.sequece = sequece;
    }

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public int getSequece() {
        return sequece;
    }

    public void setSequece(int sequece) {
        this.sequece = sequece;
    }
}
