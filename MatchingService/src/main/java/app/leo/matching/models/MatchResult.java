package app.leo.matching.models;

import app.leo.matching.demo.Applicant;
import app.leo.matching.demo.Recruiter;

import java.util.List;
import java.util.Map;

public class MatchResult {

    private Map<Position, List<ApplicantMatch>> result;

    public MatchResult(Map<Position, List<ApplicantMatch>> result) {
        this.result = result;
    }

    public Map<Position, List<ApplicantMatch>> getResult() {
        return result;
    }

    public void setResult(Map<Position, List<ApplicantMatch>> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "result=" + result +
                '}';
    }
}
