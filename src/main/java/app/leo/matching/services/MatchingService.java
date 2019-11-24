package app.leo.matching.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.ApplicantRanking;
import app.leo.matching.models.MatchResult;
import app.leo.matching.models.Position;
import app.leo.matching.models.RecruiterRanking;
import app.leo.matching.repositories.ApplicantMatchRepository;
import app.leo.matching.repositories.MatchResultRepository;

@Service
public class MatchingService {

    @Autowired
    private ApplicantMatchRepository applicantMatchRepository;

    @Autowired
    private MatchResultRepository matchResultRepository;

    public MatchingService(ApplicantMatchRepository applicantMatchRepository) {
        this.applicantMatchRepository = applicantMatchRepository;
    }

    @Transactional
    public List<MatchResult> matchingByMatchId(long matchId) {
        matchResultRepository.deleteByMatchId(matchId);
        List<ApplicantMatch> applicantMatches = applicantMatchRepository.getApplicantMatchByMatchId(matchId);
        List<MatchResult> matchResults = this.matching(matchId, applicantMatches);
        return matchResultRepository.saveAll(matchResults);
    }

    public List<MatchResult> matching(long matchId, List<ApplicantMatch> applicantMatches) {
        List<MatchResult> matchResults = new ArrayList<>();
        Map<Position, List<ApplicantMatch>> positionAccepted = new HashMap<>();
        while (!this.allApplicantMatched(applicantMatches)) {
            ApplicantMatch applicant = applicantMatches.remove(0);
            List<ApplicantRanking> applicantRanking = applicant.getApplicantRanking();
            Collections.sort(applicantRanking);
            while (!applicantRanking.isEmpty()) {
                Position position = applicantRanking.remove(0).getPosition();
                List<ApplicantMatch> acceptedApplicant = positionAccepted.get(position);
                if (acceptedApplicant == null) {
                    acceptedApplicant = new ArrayList<>();
                }
                if (this.isRecruiterAccepted(applicant, position.getRecruiterRankings())) {
                    if ((this.isRecruiterHasFullCapacity(position, acceptedApplicant))) {
                        int removalApplicantIndex = this.findRemovalApplicantInPositionRanking(position, applicant, acceptedApplicant);
                        if (this.isRankBetterThanPositionAccepted(removalApplicantIndex)) {
                            ApplicantMatch removalApplicant = position.getRecruiterRankings().get(removalApplicantIndex).getApplicantMatch();
                            ApplicantMatch applicantMatch = acceptedApplicant.remove(acceptedApplicant.indexOf(removalApplicant));

                            matchResults = this.removeAcceptedApplicantInMatchResult(matchResults, removalApplicant);
                            positionAccepted.remove(position);
                            positionAccepted.put(position, acceptedApplicant);
                            matchResults.add(new MatchResult(matchId, applicant, position));
                            applicantMatches.add(removalApplicant);
                            break;
                        } else if(applicantRanking.isEmpty()) {
                            matchResults.add(new MatchResult(matchId, applicant, null));
                            break;
                        }
                    } else {
                        matchResults.add(new MatchResult(matchId, applicant, position));
                        acceptedApplicant.add(applicant);
                        positionAccepted.put(position, acceptedApplicant);
                        break;
                    }
                } else if(applicantRanking.isEmpty()) {
                    matchResults.add(new MatchResult(matchId, applicant, null));
                    break;
                }
            }
        }
        return matchResults;
    }

    private boolean allApplicantMatched(List<ApplicantMatch> applicants){
        return applicants.isEmpty();
    }


    private boolean isRecruiterAccepted(ApplicantMatch applicant, List<RecruiterRanking> recruiterRanking){
        RecruiterRanking ranking = new RecruiterRanking(applicant);
        return recruiterRanking.contains(ranking);
    }

    private boolean isRecruiterHasFullCapacity(Position position, List<ApplicantMatch> applicantAccepted){
        int capacity = position.getCapacity();
        return applicantAccepted.size() == capacity;
    }

    private boolean isRankBetterThanPositionAccepted (int indexOfApplicant) {
        return  indexOfApplicant > -1;
    }

    private boolean isIndexCorrected(int indexOfApplicant) {
        return  indexOfApplicant > -1;
    }

    private int findRemovalApplicantInPositionRanking(Position position, ApplicantMatch applicant, List<ApplicantMatch> applicantAccepted){
        int indexOfRemovalApplicant = -1;
        List<RecruiterRanking> recruiterRankings = new ArrayList<>(position.getRecruiterRankings());
        int indexOfNewApplicant = new RecruiterRankingList(recruiterRankings).indexOf(applicant);
        if (this.isIndexCorrected(indexOfNewApplicant)) {
            int newApplicantSequence = recruiterRankings.get(indexOfNewApplicant).getSequence();
            for (ApplicantMatch applicantMatch: applicantAccepted) {
                int indexOfOldApplicant = new RecruiterRankingList(recruiterRankings).indexOf(applicantMatch);
                int oldApplicantSequence = recruiterRankings.get(indexOfOldApplicant).getSequence();
                if (oldApplicantSequence > newApplicantSequence) {
                    indexOfRemovalApplicant = indexOfOldApplicant;
                    break;
                }
            }
        }
        return indexOfRemovalApplicant;
    }

    private List<MatchResult> removeAcceptedApplicantInMatchResult(List<MatchResult> matchResults, ApplicantMatch removalApplicant) {
        for (int index = 0; index < matchResults.size(); index ++) {
            if (removalApplicant.equals(matchResults.get(index).getApplicantMatch())) {
                matchResults.remove(index);
                break;
            }
        }
        return matchResults;
    }
    
    private class RecruiterRankingList {
        List<RecruiterRanking> recruiterRankings;

        public RecruiterRankingList(List<RecruiterRanking> recruiterRankings) {
            this.recruiterRankings = recruiterRankings;
        }
        
        public int indexOf(ApplicantMatch applicantMatch) {
            int index = -1;
              for (int i = 0; i < this.recruiterRankings.size(); i++) {
                if (applicantMatch.equals(this.recruiterRankings.get(i).getApplicantMatch())) {
                    index = i;
                    break;
                }
              }
            return index;
        }
    }

}
