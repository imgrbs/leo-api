package app.leo.matching.services;

import app.leo.matching.DTO.Applicant;
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
    public void testMatchingWhenSomeoneHaveSameRankingShouldReturnMatchResult(){
        List<ApplicantMatch> applicantMatches = new ArrayList<>();

        Position position1 = new Position("Software Engineer", 1, null);
        position1.setId(1L);
        Position position2 = new Position("Developer", 1, null);
        position2.setId(3L);

        ApplicantMatch applicantMatch1 = new ApplicantMatch();
        applicantMatch1.setMatchId(1);
        applicantMatch1.setApplicantId(1L);
        applicantMatch1.setParticipantId(1L);
        List<ApplicantRanking> applicantRankings1 = new ArrayList<>();
        applicantRankings1.add(new ApplicantRanking(1, 1, applicantMatch1, position1));
        applicantRankings1.add(new ApplicantRanking(2, 1, applicantMatch1, position2));
        applicantMatch1.setApplicantRanking(applicantRankings1);

        ApplicantMatch applicantMatch2 = new ApplicantMatch();
        applicantMatch2.setMatchId(1);
        applicantMatch2.setApplicantId(2L);
        applicantMatch2.setParticipantId(2L);
        List<ApplicantRanking> applicantRankings2 = new ArrayList<>();
        applicantRankings2.add(new ApplicantRanking(1, 1, applicantMatch2, position2));
        applicantRankings2.add(new ApplicantRanking(2, 1, applicantMatch2, position1));
        applicantMatch2.setApplicantRanking(applicantRankings2);

        ApplicantMatch applicantMatch3 = new ApplicantMatch();
        applicantMatch3.setMatchId(1);
        applicantMatch3.setApplicantId(3L);
        applicantMatch3.setParticipantId(3L);
        List<ApplicantRanking> applicantRankings3 = new ArrayList<>();
        applicantRankings3.add(new ApplicantRanking(1, 1, applicantMatch3, position2));
        applicantRankings3.add(new ApplicantRanking(2, 1, applicantMatch3, position1));
        applicantMatch3.setApplicantRanking(applicantRankings3);

        List<RecruiterRanking> recruiterRankings1 = new ArrayList<>();
        recruiterRankings1.add(new RecruiterRanking(1,1, position1, applicantMatch3));
        recruiterRankings1.add(new RecruiterRanking(2,1, position1, applicantMatch1));
        recruiterRankings1.add(new RecruiterRanking(3,1, position1, applicantMatch2));
        position1.setRecruiterRankings(recruiterRankings1);

        List<RecruiterRanking> recruiterRankings2 = new ArrayList<>();
        recruiterRankings2.add(new RecruiterRanking(1,1, position2, applicantMatch3));
        recruiterRankings2.add(new RecruiterRanking(2,1, position2, applicantMatch1));
        recruiterRankings2.add(new RecruiterRanking(3,1, position2, applicantMatch2));
        position2.setRecruiterRankings(recruiterRankings2);

        applicantMatches.add(applicantMatch1);
        applicantMatches.add(applicantMatch2);
        applicantMatches.add(applicantMatch3);

        List<MatchResult> actMatchResults = matchingService.matching(1L, applicantMatches);

        Assert.assertEquals(3, actMatchResults.size());
        Assert.assertEquals(1L, actMatchResults.get(0).getApplicantMatch().getParticipantId().longValue());
        Assert.assertEquals(1L, actMatchResults.get(0).getPosition().getId().longValue());

        Assert.assertEquals(3L, actMatchResults.get(1).getApplicantMatch().getParticipantId().longValue());
        Assert.assertEquals(3L, actMatchResults.get(1).getPosition().getId().longValue());

        Assert.assertEquals(2L, actMatchResults.get(2).getApplicantMatch().getParticipantId().longValue());
        Assert.assertNull(actMatchResults.get(2).getPosition());

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
