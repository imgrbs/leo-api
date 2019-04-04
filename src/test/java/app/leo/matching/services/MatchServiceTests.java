package app.leo.matching.services;

import app.leo.matching.generators.ApplicantMatchGenerator;
import app.leo.matching.models.*;
import app.leo.matching.repositories.ApplicantMatchRepository;
import app.leo.matching.repositories.ApplicantRankingRepository;
import app.leo.matching.repositories.MatchRepository;

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

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class MatchServiceTests {

    @Mock
    private MatchRepository matchRepository;
    @Mock
    private ApplicantMatchRepository applicantMatchRepository;
    @Mock
    private ApplicantRankingRepository applicantRankingRepository;
    @Mock
    private PositionRepository positionRepository;


    private ApplicantRankingService applicantRankingService;
    private MatchService matchService;

    private PositionService positionService;

    private ApplicantMatch applicantMatch1;
    private Match match1;
    private List<ApplicantMatch> applicantMatchListCase1;
    private List<Position> positionsListCase1;


    private ApplicantMatch applicant1Match2;
    private ApplicantMatch applicant2Match2;
    private Match match2;
    private List<ApplicantMatch> applicantMatchListCase2;


    private Match match3;
    private List<ApplicantMatch> applicantMatchListCase3;

    private Match match4;
    private List<ApplicantMatch> applicantMatchListCase4;

    private Match match5;
    private List<ApplicantMatch> applicantMatchListCase5;

    private Match match6;
    private List<ApplicantMatch> applicantMatchListCase6;
    private Position position6;


    @Before
    public void setUp() throws Exception {
        this.match1 = new Match(1L, "SIT Career Day 2019");
        this.applicantMatch1 = ApplicantMatchGenerator.generateApplicantMatch(match1, "Data Engineer", 1);
        this.applicantMatchListCase1 = new ArrayList<ApplicantMatch>();
        this.applicantMatchListCase1.add(applicantMatch1);
        this.match1.setApplicantMatches(this.applicantMatchListCase1);
        this.positionsListCase1 = ApplicantMatchGenerator.getAllPositionsFromMatch(this.match1);

        this.match2 = new Match(2L, "KMUTT Job Fair");
        this.applicant1Match2 = ApplicantMatchGenerator.generateApplicantMatch(match2, "Frontend-Developer", 1);
        this.applicant2Match2 = ApplicantMatchGenerator.generateApplicantMatch(match2, "Frontend-Developer", 1);
        this.applicantMatchListCase2 = new ArrayList<ApplicantMatch>();
        this.applicantMatchListCase2.add(applicant1Match2);
        this.applicantMatchListCase2.add(applicant2Match2);
        this.match2.setApplicantMatches(applicantMatchListCase2);

        this.match3 = new Match(3L,"Gacha Fest.");
        this.applicantMatchListCase3 = ApplicantMatchGenerator.generateApplicantMatchForAtCapacityNoSwap(match3,"Data Engineer","Back-End Developer",1,1);
        match3.setApplicantMatches(applicantMatchListCase3);

        this.match4 = new Match(4L,"Food Lift fest.");
        this.applicantMatchListCase4 = ApplicantMatchGenerator.generateApplicantMatchForRankisBetter(match4,"Data Engineer","Back-End Developer",1,1);
        match4.setApplicantMatches(applicantMatchListCase4);

        this.match5 = new Match(5L,"Génial! Leo");
        this.applicantMatch1 = ApplicantMatchGenerator.generateApplicationMatchForNoMatchWereMade(match5,"Maréchal",1);
        this.applicantMatchListCase5=new ArrayList<ApplicantMatch>();
        this.applicantMatchListCase5.add(applicantMatch1);
        match5.setApplicantMatches(applicantMatchListCase5);


        this.applicantRankingRepository = Mockito.mock(ApplicantRankingRepository.class);
        this.matchRepository = Mockito.mock(MatchRepository.class);
        this.applicantMatchRepository = Mockito.mock(ApplicantMatchRepository.class);
        this.positionRepository = Mockito.mock(PositionRepository.class);
        this.matchService = new MatchService(this.matchRepository, this.applicantMatchRepository);
        this.positionService = new PositionService(this.positionRepository);
        this.applicantRankingService = new ApplicantRankingService(this.matchService,this.applicantMatchRepository,this.positionRepository,this.applicantRankingRepository);

    }

    @Test
    public void findByIdShouldReturnValidMatchWhenMatchIdIsCorrect() {
        Mockito.when(matchRepository.getMatchById(this.match1.getId())).thenReturn(this.match1);

        Match matchMock = matchService.getMatchByMatchId(this.match1.getId());

        Assert.assertEquals(this.match1.getName(), matchMock.getName());
    }


    @Test
    public void testEqualsApplicantMatchShouldReturnTrue(){
        Assert.assertEquals(true, this.applicantMatchListCase1.get(0).equals(this.applicantMatchListCase1.get(0)));
    }

    @Test
    public void getApplicantMatchByMatchIdShouldReturnDataCorrectly() {
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchId(this.match1.getId())).thenReturn(this.applicantMatchListCase1);

        List<ApplicantMatch> applicantMatches = matchService.getApplicantMatchByMatchId(this.match1.getId());

        Assert.assertEquals(1, applicantMatches.size());
        Assert.assertEquals(this.applicantMatchListCase1.get(0), applicantMatches.get(0));
        Assert.assertEquals(this.applicantMatchListCase1.get(0).getId(), applicantMatches.get(0).getId());
    }

    @Test
    public void getApplicantMatchByInvalidMatchIdShouldReturnNull() {
        long matchId = 2;
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchId(matchId)).thenReturn(new ArrayList<>());
        List<ApplicantMatch> applicantMatches = matchService.getApplicantMatchByMatchId(matchId);

        Assert.assertEquals(0, applicantMatches.size());
    }

    @Test
    public void testNormalMatchingShouldReturnMatchResult(){
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchId(this.match2.getId())).thenReturn(this.applicantMatchListCase2);
        // arrange Applicant 1
        ApplicantMatch applicant1 = this.applicantMatchListCase2.get(0);
        Position applicant1Rank1 = applicant1.getApplicantRanking().get(0).getPosition();
        MatchResult matchResult1 = new MatchResult(applicant1, applicant1Rank1);
        // arrange Applicant 2
        ApplicantMatch applicant2 = this.applicantMatchListCase2.get(1);
         Position applicant2Rank1 = applicant2.getApplicantRanking().get(0).getPosition();
        MatchResult matchResult2 = new MatchResult(applicant2, applicant2Rank1);
        // arrange Applicant 1
        List<MatchResult> matchResults = new ArrayList<MatchResult>();
        matchResults.add(matchResult1);
        matchResults.add(matchResult2);

        List<ApplicantMatch> applicantMatches = matchService.getApplicantMatchByMatchId(this.match2.getId());

        // act
        List<MatchResult> actMatchResults = matchService.matching(applicantMatches);

        // assert
        Assert.assertEquals(2, actMatchResults.size());
        Assert.assertEquals(true, matchResults.equals(actMatchResults));
    }

    @Test
    public void testMatchWhenPositionAtCapacityShouldReturnMatchResult(){
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchId(this.match3.getId())).thenReturn(this.applicantMatchListCase3);
        MatchResult matchResult1 = new MatchResult(this.applicantMatchListCase3.get(0),this.applicantMatchListCase3.get(0).getApplicantRanking().get(0).getPosition());
        MatchResult matchResult2 = new MatchResult(this.applicantMatchListCase3.get(1),this.applicantMatchListCase3.get(1).getApplicantRanking().get(1).getPosition());
        List<MatchResult> matchResults = new ArrayList<MatchResult>();
        matchResults.add(matchResult1);
        matchResults.add(matchResult2);

        List<ApplicantMatch> applicantMatches = matchService.getApplicantMatchByMatchId(this.match3.getId());

        List<MatchResult> actMatchResults = matchService.matching(applicantMatches);

        Assert.assertEquals(2,actMatchResults.size());
        Assert.assertEquals(true,matchResults.equals(actMatchResults));

    }

    @Test
    public void testMatchWhenRankisBetterShouldReturnMatchResult(){
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchId(this.match4.getId())).thenReturn(this.applicantMatchListCase4);
        MatchResult matchResult1 = new MatchResult(this.applicantMatchListCase4.get(0),this.applicantMatchListCase4.get(0).getApplicantRanking().get(0).getPosition());
        MatchResult matchResult2 = new MatchResult(this.applicantMatchListCase4.get(1),this.applicantMatchListCase4.get(1).getApplicantRanking().get(1).getPosition());
        List<MatchResult> matchResults = new ArrayList<MatchResult>();
        matchResults.add(matchResult1);
        matchResults.add(matchResult2);


        List<ApplicantMatch> applicantMatches = matchService.getApplicantMatchByMatchId(this.match4.getId());

        List<MatchResult> actMatchResults = matchService.matching(applicantMatches);

        Assert.assertEquals(2,actMatchResults.size());
        Assert.assertEquals(matchResults, actMatchResults);
    }

    @Test
    public void testMatchWhenNoMatchWereMadeShouldReturnNull(){
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchId(this.match5.getId())).thenReturn(this.applicantMatchListCase5);
        MatchResult matchResult = new MatchResult(this.applicantMatchListCase5.get(0),null);
        List<MatchResult> matchResults = new ArrayList<MatchResult>();
        matchResults.add(matchResult);

        List<ApplicantMatch> applicantMatches = matchService.getApplicantMatchByMatchId(this.match5.getId());

        List<MatchResult> actMatchResults = matchService.matching(applicantMatches);

        Assert.assertEquals(1,actMatchResults.size());
    }


    @Test
    public void findPositionByMatchIdShouldReturnPosition(){
        Mockito.when(positionRepository.getPositionByMatchId(this.match1.getId())).thenReturn(this.positionsListCase1);

        List<Position> positions = positionService.getPositionByMatchId(this.match1.getId());

        Assert.assertEquals(1, positions.size());
        Assert.assertEquals(this.positionsListCase1.get(0), positions.get(0));
        Assert.assertEquals(this.positionsListCase1.get(0).getId(), positions.get(0).getId());

    }



}
