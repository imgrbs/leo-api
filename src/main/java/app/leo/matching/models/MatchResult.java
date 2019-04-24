package app.leo.matching.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "match_results")
public class MatchResult implements Serializable {

    @Id
    @GeneratedValue
    private long matchResultId;

    private long matchId;

    @ManyToOne
    @JoinColumn(name="applicant_match_id")
    private ApplicantMatch applicantMatch;

    @ManyToOne
    @JoinColumn(name = "position_match_id")
    private Position position;

    public MatchResult() {
    }

    public MatchResult(long matchId, ApplicantMatch applicantMatch, Position position) {
        this.matchId = matchId;
        this.applicantMatch = applicantMatch;
        this.position = position;
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

    public long getMatchResultId() {
        return matchResultId;
    }

    public void setMatchResultId(long matchResultId) {
        this.matchResultId = matchResultId;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
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
