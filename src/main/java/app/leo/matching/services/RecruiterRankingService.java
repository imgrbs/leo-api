package app.leo.matching.services;

import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.Match;
import app.leo.matching.models.Position;
import app.leo.matching.models.RecruiterRanking;
import app.leo.matching.repositories.PositionRepository;
import app.leo.matching.repositories.RecruiterRankingRepository;
import app.leo.matching.validator.PutRecruiterRankingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public RecruiterRanking createRecruiterRanking(long matchId, long applicantMatchId, long positionId, int sequence)  {
        Match match =matchService.getMatchByMatchId(matchId);
        ApplicantMatch applicantMatch =applicantMatchService.getApplicantMatchByApplicantId(applicantMatchId);
        Position position = positionRepository.findPositionById(positionId);
        RecruiterRanking saveRecruiterRanking = new RecruiterRanking(sequence,match,position,applicantMatch);
        return recruiterRankingRepository.save(saveRecruiterRanking);
    }

    public RecruiterRanking findRecruiterRankingByPositionIdandApplicantId(long positionId , long applicantId){
        return recruiterRankingRepository.getRecruiterRankingbyPositionIdandApplicantMatchId(positionId,applicantId);
    }

    public void delectRecruiterRanking(RecruiterRanking recruiterRanking){
        recruiterRankingRepository.delete(recruiterRanking);
    }

    public void updateRecuiterRankingByMatchIdAndPositionId(long matchId, long positionId, List<PutRecruiterRankingRequest> putRecruiterRankingRequestList){
        recruiterRankingRepository.deleteRecruiterRankingByMatchIdAndPositionId(matchId,positionId);
        for(PutRecruiterRankingRequest putRecruiterRankingRequest:putRecruiterRankingRequestList){
            this.createRecruiterRanking(matchId,putRecruiterRankingRequest.getApplicantId(),positionId,putRecruiterRankingRequest.getSequence());
        }
    }

    public List<RecruiterRanking> getRecruiterRankingByMatchIdAndPositionId(long matchId,long positionId){
        return recruiterRankingRepository.getRecruiterRankingByMatchIdAndPositionId(matchId,positionId);
    }
}
