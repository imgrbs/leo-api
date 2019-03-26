package app.leo.matching.demo;

import app.leo.matching.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MatchingService {

    public static List<Recruiter> matching(List<Applicant> applicantsToMatch, List<Recruiter> recruitersToMatch) {

        while (!applicantsToMatch.isEmpty()) {
            Applicant applicant = applicantsToMatch.remove(0);
            List<Recruiter> preferredRecruiters = new ArrayList<>(applicant.getPreferredRecruiters());
            while (!preferredRecruiters .isEmpty()) {
                Recruiter candidateRecruiter = preferredRecruiters .remove(0);
                if (candidateRecruiter.willAcceptApplicant(applicant)) {
                    Applicant removedApplicant = candidateRecruiter.acceptApplicant(applicant);
                    if (removedApplicant != null) {
                        applicantsToMatch.add(0, removedApplicant);
                    }
                    applicant.setPreferredRecruiters(preferredRecruiters );
                    break;
                }
            }
        }
        return recruitersToMatch;
    }

    public static MatchResult createResultMatch(List<Position> recruiters){
        Map<Position,List<RecruiterRanking>> result = recruiters.stream().collect(Collectors.toMap(recruiter -> recruiter,Position::getRecruiterRankings));

        return null;
    }

    public static boolean isAllMatched(List<ApplicantMatch> applicants){
        for (ApplicantMatch applicantMatch: applicants) {
            boolean isAllMatched = applicantMatch.getApplicantRanking().isEmpty();
            if(!isAllMatched)
              continue;
            return isAllMatched;
        }
        return false;
    }

    public boolean isAcceptedByRecruiter(ApplicantMatch applicant, Position position){
         if(position.getCapacity()>0){
            return true;
         }
         return false;
    }

    public boolean isRecruiterHasFullCapacity(Position position){
        return position.getCapacity()>0;
    }

    public boolean isRankBetterThanAcceptedList(ApplicantMatch applicant,Position position,MatchResult matchResult){
        ArrayList<RecruiterRanking>   rankings = new ArrayList<RecruiterRanking>(position.getRecruiterRankings());
        Map<Position, List<ApplicantMatch>> matchResult1 =  matchResult.getResult();
        ArrayList<ApplicantMatch> applicantMatches = new ArrayList<ApplicantMatch>(matchResult1.get(position));
        return rankings.get(rankings.indexOf(applicant)).getSequence() < rankings.get(rankings.indexOf(applicantMatches.get(applicantMatches.size()-1))).getSequence();
    }

//    public MatchResult addAcceptedApplicantAndRecruiterToMatchResult  (ApplicantMatch applicant,RecruiterMatch recruiter){
//
//    }
}
