package app.leo.matching.services;


import app.leo.matching.DTO.MatchDTO;
import app.leo.matching.adapters.MatchManagementAdapter;
import app.leo.matching.exceptions.WrongPeriodException;
import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.ApplicantRanking;
import app.leo.matching.models.Position;
import app.leo.matching.repositories.ApplicantMatchRepository;
import app.leo.matching.repositories.ApplicantRankingRepository;
import app.leo.matching.repositories.PositionRepository;
import app.leo.matching.validator.PutApplicantRankingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantRankingService {

    @Autowired
    private ApplicantMatchRepository applicantMatchRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ApplicantRankingRepository applicantRankingRepository;

    @Autowired
    private PeriodCheckService periodCheckService;

    public ApplicantRankingService() {
    }

    public ApplicantRankingService(
            ApplicantMatchRepository applicantMatchRepository,
            PositionRepository positionRepository,
            ApplicantRankingRepository applicantRankingRepository,
            PeriodCheckService periodCheckService
    ){
        this.applicantMatchRepository = applicantMatchRepository;
        this.positionRepository = positionRepository;
        this.applicantRankingRepository = applicantRankingRepository;
        this.periodCheckService = periodCheckService;
    }
    public ApplicantRanking createApplicantRanking(String token,long matchId, long userId, long positionId, int sequence) {
        periodCheckService.todayIsApplicantRankingPeriod(token,matchId);
        ApplicantMatch applicantMatch = applicantMatchRepository.getApplicantMatchByApplicantIdAndMatchId(userId, matchId);
        Position position = positionRepository.findPositionById(positionId);
        ApplicantRanking applicantRanking = new ApplicantRanking(sequence,matchId,applicantMatch,position);
        return applicantRankingRepository.save(applicantRanking);
    }

    public void updateApplicantRankingByMatchIdAndApplicantId(String token,long matchId, long userId, List<PutApplicantRankingRequest> applicantRankings) {
        periodCheckService.todayIsApplicantRankingPeriod(token, matchId);
        ApplicantMatch applicantMatch = applicantMatchRepository.getApplicantMatchByApplicantIdAndMatchId(userId, matchId);
        applicantRankingRepository.deleteApplicantRankingByMatchIdAndApplicantMatchParticipantId(matchId, applicantMatch.getParticipantId());
        for(PutApplicantRankingRequest putApplicantRankingRequest : applicantRankings) {
            this.createApplicantRanking(token,matchId, applicantMatch.getParticipantId(), putApplicantRankingRequest.getPositionId(), putApplicantRankingRequest.getSequence());
        }
    }

    public ApplicantRanking updateApplicantRanking(ApplicantRanking applicantRanking){
        return  applicantRankingRepository.save(applicantRanking);
    }

    public void deleteApplicantRanking(ApplicantRanking applicantRanking){ applicantRankingRepository.delete(applicantRanking);}
    public void deleteApplicantRankingById(Long id) {
        applicantRankingRepository.deleteById(id);
    }

    public List<ApplicantRanking> getApplicantRankingByMatchIdAndApplicantId(long matchId, long applicantId){
        ApplicantMatch applicantMatch = applicantMatchRepository.getApplicantMatchByApplicantIdAndMatchId(applicantId, matchId);
        return applicantRankingRepository.getApplicantRankingByMatchIdAndApplicantMatchParticipantIdOrderBySequenceAsc(matchId, applicantMatch.getParticipantId());
    }
}
