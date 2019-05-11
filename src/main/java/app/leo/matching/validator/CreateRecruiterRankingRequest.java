package app.leo.matching.validator;

public class CreateRecruiterRankingRequest {
    private long participantId;
    private int sequence;

    public CreateRecruiterRankingRequest() {
    }

    public CreateRecruiterRankingRequest(long participantId, int sequence) {
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
