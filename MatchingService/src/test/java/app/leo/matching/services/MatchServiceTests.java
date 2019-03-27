package app.leo.matching.services;

import app.leo.matching.generators.ApplicantMatchGenerator;
import app.leo.matching.models.*;
import app.leo.matching.repositories.ApplicantMatchRepository;
import app.leo.matching.repositories.MatchRepository;

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
public class MatchServiceTests {

    @Mock
    private MatchRepository matchRepository;
    @Mock
    private ApplicantMatchRepository applicantMatchRepository;

    private MatchService matchService;

    private ApplicantMatch applicantMatch1;
    private Match match1;
    private List<ApplicantMatch> applicantMatchListCase1;


    private ApplicantMatch applicant1Match2;
    private ApplicantMatch applicant2Match2;
    private Match match2;
    private List<ApplicantMatch> applicantMatchListCase2;


    @Before
    public void setUp() throws Exception {
        this.match1 = new Match(1L, "SIT Career Day 2019");
        this.applicantMatch1 = ApplicantMatchGenerator.generateApplicantMatch(match1, "Data Engineer", 1);
        this.applicantMatchListCase1 = new ArrayList<ApplicantMatch>();
        this.applicantMatchListCase1.add(applicantMatch1);
        this.match1.setApplicantMatches(this.applicantMatchListCase1);

        this.match2 = new Match(2L, "KMUTT Job Fair");
        this.applicant1Match2 = ApplicantMatchGenerator.generateApplicantMatch(match2, "Frontend-Developer", 1);
        this.applicant2Match2 = ApplicantMatchGenerator.generateApplicantMatch(match2, "Frontend-Developer", 1);
        this.applicantMatchListCase2 = new ArrayList<ApplicantMatch>();
        this.applicantMatchListCase2.add(applicant1Match2);
        this.applicantMatchListCase2.add(applicant2Match2);
        this.match2.setApplicantMatches(applicantMatchListCase2);

        this.matchRepository = Mockito.mock(MatchRepository.class);
        this.applicantMatchRepository = Mockito.mock(ApplicantMatchRepository.class);
        this.matchService = new MatchService(this.matchRepository, this.applicantMatchRepository);
    }

    @Test
    public void findByIdShouldReturnValidMatchWhenMatchIdIsCorrect() {
        Mockito.when(matchRepository.getMatchById(this.match1.getId())).thenReturn(this.match1);

        Match matchMock = matchService.getMatchByMatchId(this.match1.getId());

        Assert.assertEquals(this.match1.getName(), matchMock.getName());
    }


    @Test
    public void testEqualsApplicantMatchShouldReturnTrue(){
        Assert.assertEquals(true, this.applicantMatch1.equals(this.applicantMatchListCase1.get(0)));
    }

    @Test
    public void getApplicantMatchByMatchIdShouldReturnDataCorrectly() {
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchId(this.match1.getId())).thenReturn(this.applicantMatchListCase1);

        List<ApplicantMatch> applicantMatches = matchService.getApplicantMatchByMatchId(this.match1.getId());

        Assert.assertEquals(1, applicantMatches.size());
        Assert.assertEquals(this.applicantMatch1, applicantMatches.get(0));
        Assert.assertEquals(this.applicantMatch1.getId(), applicantMatches.get(0).getId());
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

        // act
        List<MatchResult> actMatchResults = matchService.matching(this.match2.getId());

        // assert
        Assert.assertEquals(2, actMatchResults.size());
        Assert.assertEquals(true, matchResults.equals(actMatchResults));
    }

}
