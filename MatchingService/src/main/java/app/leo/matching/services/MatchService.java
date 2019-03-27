package app.leo.matching.services;

import app.leo.matching.models.*;
import app.leo.matching.repositories.ApplicantMatchRepository;
import app.leo.matching.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ApplicantMatchRepository applicantMatchRepository;

    public MatchService(MatchRepository matchRepository, ApplicantMatchRepository applicantMatchRepository) {
        this.matchRepository = matchRepository;
        this.applicantMatchRepository = applicantMatchRepository;
    }

    public Match getMatchByMatchId(long id) {
        return this.matchRepository.getMatchById(id);
    }

    public List<ApplicantMatch> getApplicantMatchByMatchId(long matchId) {
        return this.applicantMatchRepository.getApplicantMatchByMatchId(matchId);
    }

    public List<MatchResult> matching(long matchId) {
        List<ApplicantMatch> applicantMatches = this.getApplicantMatchByMatchId(matchId);
        List<MatchResult> matchResults = new ArrayList<MatchResult>();
        Map<Position, List<ApplicantMatch>> positionAccepted = new HashMap<Position, List<ApplicantMatch>>();
        while (!this.allApplicantMatched(applicantMatches)) {
            ApplicantMatch applicant = applicantMatches.remove(0);
            List<ApplicantRanking> applicantRanking = applicant.getApplicantRanking();
            while (!applicantRanking.isEmpty()) {
                Position position = applicantRanking.remove(0).getPosition();
                List<ApplicantMatch> acceptedApplicant = positionAccepted.get(position);
                if ((!this.isRecruiterHasFullCapacity(position, acceptedApplicant)) && this.isRecruiterAccepted(applicant, position.getRecruiterRankings())) {
                    matchResults.add(new MatchResult(applicant, position));
                    if (acceptedApplicant.isEmpty()) {
                        acceptedApplicant = new ArrayList<>();
                        acceptedApplicant.add(applicant);
                    }
                    positionAccepted.put(position, acceptedApplicant);
                } else {
                    int removalApplicantIndex = this.findRemovalApplicantInPositionRanking(position, applicant, positionAccepted);
                    if (this.isRankBetterThanPositionAccepted(removalApplicantIndex)) {
                        ApplicantMatch applicantMatch = acceptedApplicant.remove(removalApplicantIndex);
                        matchResults = this.removeAcceptedApplicantInMatchResult(matchResults, removalApplicantIndex);
                        positionAccepted.clear();
                        positionAccepted.put(position, acceptedApplicant);
                        matchResults.add(new MatchResult(applicant, position));
                        applicantMatches.add(applicantMatch);
                    } else {
                        matchResults.add(new MatchResult(applicant, null));
                    }
                }
            }
        }
        return matchResults;
    }

    public boolean allApplicantMatched(List<ApplicantMatch> applicants){
        return applicants.isEmpty();
    }


    public boolean isRecruiterAccepted(ApplicantMatch applicant, List<RecruiterRanking> recruiterRanking){
        if (recruiterRanking.contains(applicant)) {
            return true;
        }
        return false;
    }

    public boolean isRecruiterHasFullCapacity(Position position, List<ApplicantMatch> applicantAccepted){
        int capacity = position.getCapacity();
        if(applicantAccepted != null)
            return applicantAccepted.size() == capacity || capacity <= 0;
        return capacity<=0;
    }

    public boolean isRankBetterThanPositionAccepted (int indexOfApplicant) {
        return  indexOfApplicant > -1;
    }

    public int findRemovalApplicantInPositionRanking(Position position, ApplicantMatch applicant, Map<Position, List<ApplicantMatch>> positionAccepted){
        int indexOfRemovalApplicant = -1;
        List<RecruiterRanking> recruiterRankings = new ArrayList<RecruiterRanking>(position.getRecruiterRankings());
        List<ApplicantMatch> applicantAccepted = new ArrayList<ApplicantMatch>(positionAccepted.get(position));
        int indexOfNewApplicant = recruiterRankings.indexOf(applicant);
        int newApplicantSequence = recruiterRankings.get(indexOfNewApplicant).getSequence();
        for (ApplicantMatch applicantMatch: applicantAccepted) {
            int indexOfOldApplicant = recruiterRankings.indexOf(applicantMatch);
            int oldApplicantSequence = recruiterRankings.get(indexOfOldApplicant).getSequence();
            if (oldApplicantSequence < newApplicantSequence) {
                indexOfRemovalApplicant = indexOfOldApplicant;
                break;
            }
        }
        return indexOfRemovalApplicant ;
    }

    public List<MatchResult> removeAcceptedApplicantInMatchResult(List<MatchResult> matchResults, int removalApplicantIndex) {
        matchResults.remove(removalApplicantIndex);
        return matchResults;
    }

}
