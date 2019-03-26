package app.leo.matching.models;

import app.leo.matching.demo.Applicant;
import app.leo.matching.demo.Recruiter;

import java.util.List;
import java.util.Map;

public class MatchResult {

    private Map<RecruiterMatch, List<ApplicantMatch>> result;

    public MatchResult(Map<RecruiterMatch, List<ApplicantMatch>> result) {
        this.result = result;
    }

    public Map<RecruiterMatch, List<ApplicantMatch>> getResult() {
        return result;
    }

    public void setResult(Map<RecruiterMatch, List<ApplicantMatch>> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "result=" + result +
                '}';
    }
}
