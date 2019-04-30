package app.leo.matching.services;

import app.leo.matching.generators.ApplicantMatchGenerator;
import app.leo.matching.models.*;
import app.leo.matching.repositories.ApplicantMatchRepository;
import app.leo.matching.repositories.ApplicantRankingRepository;

import app.leo.matching.repositories.PositionRepository;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MatchingServiceTests {

    @Mock
    private ApplicantMatchRepository applicantMatchRepository;
    @Mock
    private ApplicantRankingRepository applicantRankingRepository;
    @Mock
    private PositionRepository positionRepository;

    private MatchingService matchingService;
    private ApplicantMatchService applicantMatchService;
    private PositionService positionService;

    private ApplicantMatch applicantMatch1;
    private List<ApplicantMatch> applicantMatchListCase1;
    private List<Position> positionsListCase1;


    private ApplicantMatch applicant1Match2;
    private ApplicantMatch applicant2Match2;
    private List<ApplicantMatch> applicantMatchListCase2;


    private List<ApplicantMatch> applicantMatchListCase3;

    private List<ApplicantMatch> applicantMatchListCase4;

    private List<ApplicantMatch> applicantMatchListCase5;

    @Before
    public void setUp() {
        this.applicantMatch1 = ApplicantMatchGenerator.generateApplicantMatch(1L, "Data Engineer", 1);
        this.applicantMatchListCase1 = new ArrayList<>();
        this.applicantMatchListCase1.add(applicantMatch1);
        this.positionsListCase1 = ApplicantMatchGenerator.getAllPositionsFromMatch(applicantMatchListCase1);

        this.applicant1Match2 = ApplicantMatchGenerator.generateApplicantMatch(2L, "Frontend-Developer", 1);
        this.applicant2Match2 = ApplicantMatchGenerator.generateApplicantMatch(2L, "Frontend-Developer", 1);
        this.applicantMatchListCase2 = new ArrayList<>();
        this.applicantMatchListCase2.add(applicant1Match2);
        this.applicantMatchListCase2.add(applicant2Match2);

        this.applicantMatchListCase3 = ApplicantMatchGenerator.generateApplicantMatchForAtCapacityNoSwap(3L,"Data Engineer","Back-End Developer",1,1);

        this.applicantMatchListCase4 = ApplicantMatchGenerator.generateApplicantMatchForRankisBetter(4L,"Data Engineer","Back-End Developer",1,1);

        this.applicantMatch1 = ApplicantMatchGenerator.generateApplicationMatchForNoMatchWereMade(5L,"Maréchal",1);
        this.applicantMatchListCase5=new ArrayList<>();
        this.applicantMatchListCase5.add(applicantMatch1);

        this.applicantRankingRepository = Mockito.mock(ApplicantRankingRepository.class);
        this.applicantMatchRepository = Mockito.mock(ApplicantMatchRepository.class);
        this.positionRepository = Mockito.mock(PositionRepository.class);
        this.matchingService = new MatchingService(this.applicantMatchRepository);
        this.positionService = new PositionService(this.positionRepository);
        this.applicantMatchService = new ApplicantMatchService(applicantMatchRepository);
    }

    @Test
    public void testNormalMatchingShouldReturnMatchResult(){
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchId(2L)).thenReturn(this.applicantMatchListCase2);
        // arrange Applicant 1
        ApplicantMatch applicant1 = this.applicantMatchListCase2.get(0);
        Position applicant1Rank1 = applicant1.getApplicantRanking().get(0).getPosition();
        MatchResult matchResult1 = new MatchResult(applicant1, applicant1Rank1);
        // arrange Applicant 2
        ApplicantMatch applicant2 = this.applicantMatchListCase2.get(1);
         Position applicant2Rank1 = applicant2.getApplicantRanking().get(0).getPosition();
        MatchResult matchResult2 = new MatchResult(applicant2, applicant2Rank1);
        // arrange Applicant 1
        List<MatchResult> matchResults = new ArrayList<>();
        matchResults.add(matchResult1);
        matchResults.add(matchResult2);

        List<ApplicantMatch> applicantMatches = applicantMatchService.getApplicantMatchByMatchId(2L);

        // act
        List<MatchResult> actMatchResults = matchingService.matching(2L, applicantMatches);

        // assert
        Assert.assertEquals(2, actMatchResults.size());
        Assert.assertEquals(matchResults,actMatchResults);
    }

    @Test
    public void testMatchWhenPositionAtCapacityShouldReturnMatchResult(){
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchId(3L)).thenReturn(this.applicantMatchListCase3);
        MatchResult matchResult1 = new MatchResult(this.applicantMatchListCase3.get(0),this.applicantMatchListCase3.get(0).getApplicantRanking().get(0).getPosition());
        MatchResult matchResult2 = new MatchResult(this.applicantMatchListCase3.get(1),this.applicantMatchListCase3.get(1).getApplicantRanking().get(1).getPosition());
        List<MatchResult> matchResults = new ArrayList<>();
        matchResults.add(matchResult1);
        matchResults.add(matchResult2);

        List<ApplicantMatch> applicantMatches = applicantMatchService.getApplicantMatchByMatchId(3L);

        List<MatchResult> actMatchResults = matchingService.matching(3L, applicantMatches);

        Assert.assertEquals(2,actMatchResults.size());
        Assert.assertEquals(matchResults,actMatchResults);

    }

    @Test
    public void testMatchWhenRankisBetterShouldReturnMatchResult(){
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchId(4L)).thenReturn(this.applicantMatchListCase4);
        MatchResult matchResult1 = new MatchResult(this.applicantMatchListCase4.get(0),this.applicantMatchListCase4.get(0).getApplicantRanking().get(0).getPosition());
        MatchResult matchResult2 = new MatchResult(this.applicantMatchListCase4.get(1),this.applicantMatchListCase4.get(1).getApplicantRanking().get(1).getPosition());
        List<MatchResult> matchResults = new ArrayList<>();
        matchResults.add(matchResult1);
        matchResults.add(matchResult2);


        List<ApplicantMatch> applicantMatches = applicantMatchService.getApplicantMatchByMatchId(4L);

        List<MatchResult> actMatchResults = matchingService.matching(4L, applicantMatches);

        Assert.assertEquals(2,actMatchResults.size());
        Assert.assertEquals(matchResults, actMatchResults);
    }

    @Test
    public void testMatchWhenNoMatchWereMadeShouldReturnNull(){
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchId(5L)).thenReturn(this.applicantMatchListCase5);
        MatchResult matchResult = new MatchResult(this.applicantMatchListCase5.get(0),null);
        List<MatchResult> matchResults = new ArrayList<>();
        matchResults.add(matchResult);

        List<ApplicantMatch> applicantMatches = applicantMatchService.getApplicantMatchByMatchId(5L);

        List<MatchResult> actMatchResults = matchingService.matching(5L, applicantMatches);

        Assert.assertEquals(1,actMatchResults.size());
    }


    @Test
    public void findPositionByMatchIdShouldReturnPosition(){
        Mockito.when(positionRepository.getPositionByMatchId(1L)).thenReturn(this.positionsListCase1);

        List<Position> positions = positionService.getPositionByMatchId(1L);

        Assert.assertEquals(1, positions.size());
        Assert.assertEquals(this.positionsListCase1.get(0), positions.get(0));
        Assert.assertEquals(this.positionsListCase1.get(0).getId(), positions.get(0).getId());

    }
}
