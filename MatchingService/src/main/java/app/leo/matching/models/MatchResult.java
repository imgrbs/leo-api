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
    public boolean equals(Object obj) {
        MatchResult matchResult = (MatchResult) obj;
        if(matchResult.applicantMatch.equals(this.applicantMatch)&&matchResult.getPosition().equals(matchResult.getPosition())){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "applicantMatch=" + applicantMatch +
                ", position=" + position +
                '}';
    }
}
