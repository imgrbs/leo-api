package app.leo.matching.validator;

public class PutRecruiterRankingRequest {
    private long applicantId;
    private int sequence;

    public PutRecruiterRankingRequest() {
    }

    public PutRecruiterRankingRequest(long applicantId, int sequence) {
        this.applicantId = applicantId;
        this.sequence = sequence;
    }

    public long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
