package app.leo.matching.services;

import app.leo.matching.models.MatchResult;
import app.leo.matching.repositories.MatchResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchResultService {

    @Autowired
    private MatchResultRepository matchResultRepository;
    @Autowired
    private PeriodCheckService periodCheckService;

    public MatchResult getMatchResultByApplicantMatchIdAndMatchId(String token,long applicantMatchId,long matchId){
        //periodCheckService.todayIsInAnnouncePeriod(token,matchId);
        return matchResultRepository.getMatchResultByApplicantMatchParticipantIdAndMatchId(applicantMatchId,matchId);
    }

    public List<MatchResult> getMatchResultByPositionIdAndMatchId(long positionId, long matchId){
        return matchResultRepository.getMatchResultByPositionIdAndMatchId(positionId,matchId);
    }
}
