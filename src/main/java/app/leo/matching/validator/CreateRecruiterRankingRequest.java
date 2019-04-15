package app.leo.matching.validator;

public class CreateRecruiterRankingRequest {
    private long applicantId;
    private int sequence;

    public CreateRecruiterRankingRequest() {
    }

    public CreateRecruiterRankingRequest(long applicantId, int sequence) {
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
