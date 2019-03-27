package app.leo.matching.generators;

import app.leo.matching.models.*;

import java.util.ArrayList;
import java.util.List;

public class ApplicantMatchGenerator {
    public static ApplicantMatch generateApplicantMatch(Match match, String positionName, int capacity) {
        RecruiterMatch recruiter = new RecruiterMatch(match.getId(), match,true);
        Position position = new Position(positionName, capacity, recruiter);

        ApplicantMatch applicant = new ApplicantMatch();
        ApplicantRanking applicantRank1 = new ApplicantRanking(1, match, applicant, position);

        List<ApplicantRanking> applicantRankingList = new ArrayList<ApplicantRanking>();
        applicantRankingList.add(applicantRank1);

        RecruiterRanking recruiterRank1 = new RecruiterRanking(1, match, position, applicant);
        List<RecruiterRanking> recruiterRankingList = new ArrayList<>();
        recruiterRankingList.add(recruiterRank1);
        position.setRecruiterRankings(recruiterRankingList);

        return applicant;
    }
}
