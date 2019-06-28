package app.leo.matching.services;

import app.leo.matching.DTO.MatchDTO;
import app.leo.matching.adapters.MatchManagementAdapter;
import app.leo.matching.exceptions.WrongPeriodException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class PeriodCheckService {
    @Autowired
    private MatchManagementAdapter matchManagementAdapter;

    private final Date currentDate = java.sql.Date.valueOf(LocalDate.now(ZoneId.of("Asia/Bangkok")));
    private Logger logger = LoggerFactory.getLogger(PeriodCheckService.class);

    public void todayIsApplicantRankingPeriod(String token,long matchId){
        MatchDTO match = matchManagementAdapter.getMatchByMatchId(token,matchId);
        if(currentDate.before(match.getStartJoiningDate())||currentDate.after(match.getApplicantRankingEndDate())){
            logger.warn("Sorry, it's not period for Applicant Ranking.");
            throw new WrongPeriodException("Sorry, it's not period for Applicant Ranking.");
        }
    }

    public void todayIsInRecruiterRankingPeriod(String token,long matchId){
        MatchDTO match = matchManagementAdapter.getMatchByMatchId(token,matchId);
        if(currentDate.before(match.getApplicantRankingEndDate())||currentDate.after(match.getRecruiterRankingEndDate())){
            logger.warn("Sorry, it's not period for Recruiter Ranking.");
            throw new WrongPeriodException("Sorry, it's not period for Recruiter Ranking.");
        }
    }

    public void todayIsInAnnouncePeriod(String token,long matchId){
        MatchDTO match = matchManagementAdapter.getMatchByMatchId(token,matchId);
        if(currentDate.before(match.getAnnounceDate())){
            logger.warn("Sorry, it's not announce yet");
            throw new WrongPeriodException("Sorry, it's not announce yet");
        }
    }
}
