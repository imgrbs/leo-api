package app.leo.matching.models;

import app.leo.matching.demo.Applicant;
import app.leo.matching.demo.Recruiter;

import java.util.List;
import java.util.Map;

public class MatchResult {

    private Map<Recruiter, List<Applicant>> result;

    public MatchResult(Map<Recruiter, List<Applicant>> result) {
        this.result = result;
    }

    public Map<Recruiter, List<Applicant>> getResult() {
        return result;
    }

    public void setResult(Map<Recruiter, List<Applicant>> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "result=" + result +
                '}';
    }
}
