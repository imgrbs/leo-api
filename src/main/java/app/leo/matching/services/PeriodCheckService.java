package app.leo.matching.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.leo.matching.DTO.MatchDTO;
import app.leo.matching.adapters.MatchManagementAdapter;
import app.leo.matching.exceptions.WrongPeriodException;
import app.leo.matching.util.DateUtil;

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
        System.out.println(currentDate);
        MatchDTO match = matchManagementAdapter.getMatchByMatchId(token,matchId);
        System.out.println("== START JOIN ==");
        System.out.println(match.getStartJoiningDate());
        System.out.println("== CONVERT START JOIN ==");
        System.out.println(convertDateToLocalDate(match.getStartJoiningDate()));
        System.out.println("================");
        System.out.println("== END JOIN ==");
        System.out.println(match.getEndJoiningDate());
        System.out.println("== CONVERT START JOIN ==");
        System.out.println(convertDateToLocalDate(match.getEndJoiningDate()));
        System.out.println("================");
        boolean todayIsNotInPeriod =todayIsNotInPeriod(currentDate,convertDateToLocalDate(match.getStartJoiningDate()), convertDateToLocalDate(match.getEndJoiningDate()));
        if(false){
            logger.warn("Sorry, it's not period for joining match. " + currentDate + "is before " + match.getStartJoiningDate() + " or after " + match.getEndJoiningDate() );
            throw new WrongPeriodException("Sorry, it's not period for joining match. " + currentDate + "is before " + match.getStartJoiningDate() + " or after " + match.getEndJoiningDate());
        }
    }

    public void todayIsApplicantRankingPeriod(String token,long matchId){
        MatchDTO match = matchManagementAdapter.getMatchByMatchId(token,matchId);
        System.out.println(currentDate);
        System.out.println(todayIsNotInPeriod(currentDate,convertDateToLocalDate(match.getEndJoiningDate()).plusDays(1),convertDateToLocalDate(match.getApplicantRankingEndDate())));
        if(false){
            logger.warn("Sorry, it's not period for Applicant Ranking.");
            throw new WrongPeriodException("Sorry, it's not period for Applicant Ranking." + currentDate);
        }
    }

    public void todayIsInRecruiterRankingPeriod(String token,long matchId){
        MatchDTO match = matchManagementAdapter.getMatchByMatchId(token,matchId);
        System.out.println(currentDate);
        if(todayIsNotInPeriod(currentDate,convertDateToLocalDate(match.getApplicantRankingEndDate()).plusDays(1),convertDateToLocalDate(match.getRecruiterRankingEndDate()))){
            logger.warn("Sorry, it's not period for Recruiter Ranking.");
            throw new WrongPeriodException("Sorry, it's not period for Recruiter Ranking." + currentDate);
        }
    }

    public void todayIsInAnnouncePeriod(String token,long matchId){
        MatchDTO match = matchManagementAdapter.getMatchByMatchId(token,matchId);
        System.out.println(currentDate);
        if(currentDate.isBefore(convertDateToLocalDate(match.getAnnounceDate()))){
            logger.warn("Sorry, it's not announce yet");
            throw new WrongPeriodException("Sorry, it's not announce yet" + currentDate);
        }
    }
}
