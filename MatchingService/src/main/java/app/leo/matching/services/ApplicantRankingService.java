package app.leo.matching.services;


import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.ApplicantRanking;
import app.leo.matching.models.Match;
import app.leo.matching.models.Position;
import app.leo.matching.repositories.ApplicantMatchRepository;
import app.leo.matching.repositories.ApplicantRankingRepository;
import app.leo.matching.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantRankingService {

    @Autowired
    private MatchService matchService;

    @Autowired
    private ApplicantMatchRepository applicantMatchRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ApplicantRankingRepository applicantRankingRepository;

    public ApplicantRankingService() {
    }

    public ApplicantRankingService(MatchService matchService,ApplicantMatchRepository applicantMatchRepository,PositionRepository positionRepository,ApplicantRankingRepository applicantRankingRepository){
        this.matchService = matchService;
        this.applicantMatchRepository = applicantMatchRepository;
        this.positionRepository = positionRepository;
        this.applicantRankingRepository = applicantRankingRepository;
    }
    public ApplicantRanking createApplicantRanking(long matchId, long userId, long positionId, int sequence){

        Match match = matchService.getMatchByMatchId(matchId);
        ApplicantMatch applicantMatch = applicantMatchRepository.getApplicantMatchById(userId);
        Position position = positionRepository.findPositionById(positionId);
        ApplicantRanking applicantRanking = new ApplicantRanking(sequence,match,applicantMatch,position);
        return applicantRankingRepository.save(applicantRanking);
    }
}
