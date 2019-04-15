package app.leo.matching.validator;

public class CreateRecruiterRankingRequest {
    private long applicantMatchId;
    private int sequence;

    public CreateRecruiterRankingRequest() {
    }

    public CreateRecruiterRankingRequest(long applicantMatchId, int sequence) {
        this.applicantMatchId = applicantMatchId;
        this.sequence = sequence;
    }

    public long getApplicantMatchId() {
        return applicantMatchId;
    }

    public void setApplicantMatchId(long applicantMatchId) {
        this.applicantMatchId = applicantMatchId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
