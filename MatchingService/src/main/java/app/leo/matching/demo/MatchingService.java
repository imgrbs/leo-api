package app.leo.matching.services;

import app.leo.matching.models.Applicant;
import app.leo.matching.models.MatchResult;
import app.leo.matching.models.Recruiter;

import java.util.ArrayList;
import java.util.List;

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
}
