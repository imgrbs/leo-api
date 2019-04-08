package app.leo.matching.services;

import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.Match;
import app.leo.matching.models.Position;
import app.leo.matching.models.RecruiterRanking;
import app.leo.matching.repositories.PositionRepository;
import app.leo.matching.repositories.RecruiterRankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruiterRankingService {

    @Autowired
    private RecruiterRankingRepository recruiterRankingRepository;
    @Autowired
    private MatchService matchService;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private ApplicantMatchService applicantMatchService;


    public RecruiterRankingService() {
    }

    public RecruiterRankingService(RecruiterRankingRepository recruiterRankingRepository, MatchService matchService, PositionRepository positionRepository, ApplicantMatchService applicantMatchService) {
        this.recruiterRankingRepository = recruiterRankingRepository;
        this.matchService = matchService;
        this.positionRepository =positionRepository;
        this.applicantMatchService = applicantMatchService;
    }


    public RecruiterRanking createRecruiterRanking(long matchId, long applicantMatchId, long positionId, int sequence) throws Exception {
        Match match =matchService.getMatchByMatchId(matchId);
        ApplicantMatch applicantMatch =applicantMatchService.getApplicantMatchByApplicantId(applicantMatchId);
        Position position = positionRepository.findPositionById(positionId);
        RecruiterRanking saveRecruiterRanking = new RecruiterRanking(sequence,match,position,applicantMatch);
        return recruiterRankingRepository.save(saveRecruiterRanking);
    }

}
