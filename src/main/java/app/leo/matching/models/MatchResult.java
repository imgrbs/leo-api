package app.leo.matching.models;

public class MatchResult {

    private ApplicantMatch applicantMatch;
    private Position position;

    public MatchResult() {
    }

    public MatchResult(ApplicantMatch applicantMatch, Position position) {
        this.applicantMatch = applicantMatch;
        this.position = position;
    }

    public ApplicantMatch getApplicantMatch() {
        return applicantMatch;
    }

    public void setApplicantMatch(ApplicantMatch applicantMatch) {
        this.applicantMatch = applicantMatch;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object obj){
        MatchResult matchResult = (MatchResult) obj;

        boolean isSameApplicant = matchResult.getApplicantMatch().equals(this.applicantMatch);

        boolean isSamePosition = true;

        if(this.getPosition() != null) {
            isSamePosition = matchResult.getPosition().equals(this.getPosition());
        }

        return isSameApplicant && isSamePosition;
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "applicantMatch=" + applicantMatch +
                ", position=" + position +
                '}';
    }
}
