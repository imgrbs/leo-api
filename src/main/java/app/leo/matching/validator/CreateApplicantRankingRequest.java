package app.leo.matching.validator;

public class CreateApplicantRankingRequest {
    private int sequence;
    private long positionId;

    public CreateApplicantRankingRequest() {
    }

    public CreateApplicantRankingRequest( int sequence, long positionId) {

        this.sequence = sequence;
        this.positionId = positionId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }
}
