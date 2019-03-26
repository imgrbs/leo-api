package app.leo.matching.services;

import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.Match;
import app.leo.matching.models.MatchResult;
import app.leo.matching.repositories.ApplicantMatchRepository;
import app.leo.matching.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return this.matchRepository.getMatchByMatchId(id);
    }

    public List<ApplicantMatch> getApplicantMatchByMatchId(long matchId) {
        return this.applicantMatchRepository.getApplicantMatchByMatchId(matchId);
    }

}
