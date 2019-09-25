package app.leo.matching;

import app.leo.matching.DTO.MatchDTO;
import app.leo.matching.adapters.MatchManagementAdapter;
import app.leo.matching.exceptions.WrongPeriodException;
import app.leo.matching.services.PeriodCheckService;
import app.leo.matching.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class PeriodCheckServiceTest {

    @Mock
    private MatchManagementAdapter matchManagementAdapter;

    @InjectMocks
    private PeriodCheckService periodCheckService;

    private DateUtil dateUtil = new DateUtil();

    @Mock
    private Logger logger;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.matchManagementAdapter = mock(MatchManagementAdapter.class);
        this.logger = mock(Logger.class);
        this.periodCheckService = new PeriodCheckService(matchManagementAdapter,logger);
    }


    @Test(expected = WrongPeriodException.class)
    public void checkItIsNotJoiningPeriodShouldReturnException() {
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setStartJoiningDate(new Date(2019, Calendar.SEPTEMBER,1));
        matchDTO.setEndJoiningDate(new Date(2019,Calendar.SEPTEMBER ,1));
        Date today = new Date(2019,Calendar.SEPTEMBER,11);
        this.periodCheckService.setCurrentDate(today);
        Mockito.when(this.matchManagementAdapter.getMatchByMatchId("ABCDEFG!@#$",1)).thenReturn(matchDTO);
        this.periodCheckService.todayIsJoiningPeriod("ABCDEFG!@#$",1 );
    }

    @Test
    public void TodayIsJoiningPeriodShouldReturnNoException(){
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setStartJoiningDate(new Date(2019,Calendar.SEPTEMBER,1));
        matchDTO.setEndJoiningDate(new Date(2019,Calendar.SEPTEMBER,12));
        Date today = new Date(2019,Calendar.SEPTEMBER,11);
        this.periodCheckService.setCurrentDate(today);
        Mockito.when(this.matchManagementAdapter.getMatchByMatchId("ABCDEFG!@#$",1)).thenReturn(matchDTO);
        this.periodCheckService.todayIsJoiningPeriod("ABCDEFG!@#$",1 );
    }

    @Test(expected = WrongPeriodException.class)
    public void checkItIsNotApplicantRankingPeriodShouldThrowException(){
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setEndJoiningDate(new Date(2019,Calendar.SEPTEMBER,2));
        matchDTO.setApplicantRankingEndDate(new Date(2019,Calendar.SEPTEMBER,2));
        Date today =new Date(2019,Calendar.SEPTEMBER,26);
        this.periodCheckService.setCurrentDate(today);
        Mockito.when(this.matchManagementAdapter.getMatchByMatchId("ABCDEFG!@#$",1)).thenReturn(matchDTO);
        this.periodCheckService.todayIsApplicantRankingPeriod("ABCDEFG!@#$",1);
    }

    @Test
    public void TodayIsApplicantRankingPeriodShouldThrowNoException(){
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setEndJoiningDate(new Date(2019,Calendar.SEPTEMBER,2));
        matchDTO.setApplicantRankingEndDate(new Date(2019,Calendar.SEPTEMBER,27));
        Date today =new Date(2019,Calendar.SEPTEMBER,26);
        this.periodCheckService.setCurrentDate(today);
        Mockito.when(this.matchManagementAdapter.getMatchByMatchId("ABCDEFG!@#$",1)).thenReturn(matchDTO);
        this.periodCheckService.todayIsApplicantRankingPeriod("ABCDEFG!@#$",1);
    }


    @Test(expected = WrongPeriodException.class)
    public void TodayIsNotRecruiterRankingPeriodShouldThrowException() {
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setApplicantRankingEndDate(new Date(2019,Calendar.SEPTEMBER,2));
        matchDTO.setRecruiterRankingEndDate(new Date(2019,Calendar.SEPTEMBER,27));
        Date today =new Date(2019,Calendar.SEPTEMBER,28);
        this.periodCheckService.setCurrentDate(today);
        Mockito.when(this.matchManagementAdapter.getMatchByMatchId("ABCDEFG!@#$",1)).thenReturn(matchDTO);
        this.periodCheckService.todayIsInRecruiterRankingPeriod("ABCDEFG!@#$",1);
    }

    @Test
    public void TodayIsRecruiterRankingPeriodShouldThrowException() {
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setApplicantRankingEndDate(new Date(2019,Calendar.SEPTEMBER,2));
        matchDTO.setRecruiterRankingEndDate(new Date(2019,Calendar.SEPTEMBER,27));
        Date today =new Date(2019,Calendar.SEPTEMBER,26);
        this.periodCheckService.setCurrentDate(today);
        Mockito.when(this.matchManagementAdapter.getMatchByMatchId("ABCDEFG!@#$",1)).thenReturn(matchDTO);
        this.periodCheckService.todayIsInRecruiterRankingPeriod("ABCDEFG!@#$",1);
    }

    @Test(expected = WrongPeriodException.class)
    public void TodayIsNotAnnouceDateShouldThrowException(){
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setAnnounceDate(new Date(2019,Calendar.SEPTEMBER,27));
        Date today =new Date(2019,Calendar.SEPTEMBER,26);
        this.periodCheckService.setCurrentDate(today);
        Mockito.when(this.matchManagementAdapter.getMatchByMatchId("ABCDEFG!@#$",1)).thenReturn(matchDTO);
        this.periodCheckService.todayIsInAnnouncePeriod("ABCDEFG!@#$",1);
    }

    @Test
    public void TodayIsAnnonceDateShouldReturnNoException(){
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setAnnounceDate(new Date(2019,Calendar.SEPTEMBER,20));
        Date today =new Date(2019,Calendar.SEPTEMBER,20);
        this.periodCheckService.setCurrentDate(today);
        Mockito.when(this.matchManagementAdapter.getMatchByMatchId("ABCDEFG!@#$",1)).thenReturn(matchDTO);
        this.periodCheckService.todayIsInAnnouncePeriod("ABCDEFG!@#$",1);
    }
}