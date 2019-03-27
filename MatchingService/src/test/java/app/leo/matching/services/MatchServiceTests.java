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


    private ApplicantMatch applicantMatch2;
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
        this.applicantMatch2 = ApplicantMatchGenerator.generateApplicantMatch(match2, "Frontend-Developer", 1);
        this.applicantMatchListCase2 = new ArrayList<ApplicantMatch>();
        this.applicantMatchListCase2.add(applicantMatch2);
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
    public void normalMatching(){
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchId(this.match1.getId())).thenReturn(this.applicantMatchListCase1);
        List<MatchResult> results =matchService.matching(this.match1.getId());

        Assert.assertEquals(1,results.size());
    }

    @Test
    public void equalsTest(){
        Assert.assertEquals(true, this.applicantMatch1.equals(this.applicantMatchListCase1.get(0)));
    }

}
