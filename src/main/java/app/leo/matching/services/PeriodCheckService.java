package app.leo.matching.services;

import app.leo.matching.DTO.MatchDTO;
import app.leo.matching.adapters.MatchManagementAdapter;
import app.leo.matching.exceptions.WrongPeriodException;
import app.leo.matching.util.DateUtil;
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

    private LocalDate currentDate = new DateUtil().getCurrentDate();

    private Logger logger = LoggerFactory.getLogger(PeriodCheckService.class);

    public PeriodCheckService() {
    }

    public PeriodCheckService(MatchManagementAdapter matchManagementAdapter, Logger logger) {
        this.matchManagementAdapter = matchManagementAdapter;
        this.logger = logger;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public LocalDate convertDateToLocalDate(Date dateToConvert){
        return dateToConvert.toInstant().atZone(ZoneId.of("Asia/Bangkok")).toLocalDate();
    }

    public boolean todayIsNotInPeriod(LocalDate currentDate, LocalDate startPeriodDate, LocalDate endPeriodDate){
        return  currentDate.isBefore(startPeriodDate)|| currentDate.isAfter(endPeriodDate);
    }
    public void todayIsJoiningPeriod(String token, long matchId){
        MatchDTO match = matchManagementAdapter.getMatchByMatchId(token,matchId);
        if(todayIsNotInPeriod(currentDate,convertDateToLocalDate(match.getStartJoiningDate()), convertDateToLocalDate(match.getEndJoiningDate()))){
            logger.warn("Sorry, it's not period for joining match.");
            throw new WrongPeriodException("Sorry, it's not period for joining.");
        }
    }

    public void todayIsApplicantRankingPeriod(String token,long matchId){
        MatchDTO match = matchManagementAdapter.getMatchByMatchId(token,matchId);
        if(todayIsNotInPeriod(currentDate,convertDateToLocalDate(match.getEndJoiningDate()).plusDays(1),convertDateToLocalDate(match.getApplicantRankingEndDate()))){
            logger.warn("Sorry, it's not period for Applicant Ranking.");
            throw new WrongPeriodException("Sorry, it's not period for Applicant Ranking.");
        }
    }

    public void todayIsInRecruiterRankingPeriod(String token,long matchId){
        MatchDTO match = matchManagementAdapter.getMatchByMatchId(token,matchId);
        if(todayIsNotInPeriod(currentDate,convertDateToLocalDate(match.getApplicantRankingEndDate()).plusDays(1),convertDateToLocalDate(match.getRecruiterRankingEndDate()))){
            logger.warn("Sorry, it's not period for Recruiter Ranking.");
            throw new WrongPeriodException("Sorry, it's not period for Recruiter Ranking.");
        }
    }

    public void todayIsInAnnouncePeriod(String token,long matchId){
        MatchDTO match = matchManagementAdapter.getMatchByMatchId(token,matchId);
        if(currentDate.isBefore(convertDateToLocalDate(match.getAnnounceDate()))){
            logger.warn("Sorry, it's not announce yet");
            throw new WrongPeriodException("Sorry, it's not announce yet");
        }
    }
}
