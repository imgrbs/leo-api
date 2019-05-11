package app.leo.matching.validator;

public class PutRecruiterRankingRequest {
    private long participantId;
    private int sequence;

    public PutRecruiterRankingRequest() {
    }

    public PutRecruiterRankingRequest(long participantId, int sequence) {
        this.participantId = participantId;
        this.sequence = sequence;
    }

    public long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(long participantId) {
        this.participantId = participantId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
