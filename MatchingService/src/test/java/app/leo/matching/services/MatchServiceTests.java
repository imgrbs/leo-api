package app.leo.matching.services;

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

    private Match match;
    private ApplicantMatch applicantMatch;
    private List<ApplicantMatch> applicantMatchList;
    private List<ApplicantRanking> applicantRankingList;
    private List<RecruiterMatch> recruiterMatches;
    private List<Position> positions;
    private Position position;
    private MatchService matchService;
    private ApplicantRanking applicantRanking;
    private RecruiterRanking recruiterRanking;
    private List<RecruiterRanking> recruiterRankingList;

    @Mock
    private MatchRepository matchRepository;
    @Mock
    private ApplicantMatchRepository applicantMatchRepository;


    @Before
    public void setUp() throws Exception {
        this.match = new Match(1L, "SIT Career Day 2019");
        this.applicantMatch = new ApplicantMatch(1L, true, this.match);
        this.applicantMatchList = new ArrayList<ApplicantMatch>();
        this.recruiterMatches = new ArrayList<RecruiterMatch>();
        this.applicantRankingList = new ArrayList<ApplicantRanking>();
        this.positions = new ArrayList<Position>();
        this.recruiterRankingList = new ArrayList<RecruiterRanking>();
        //first data
        this.recruiterMatches.add(new RecruiterMatch(1L,this.match,true));
        this.position = new Position("Data Engineer", 1,this.recruiterMatches.get(0));
        this.applicantRanking = new ApplicantRanking(1,this.match,this.applicantMatch,position);
        this.applicantRankingList.add(this.applicantRanking);
        this.applicantMatch.setApplicantRanking(this.applicantRankingList);
        applicantMatchList.add(this.applicantMatch);
        positions.add(position);
        this.recruiterRanking = new RecruiterRanking(1,this.match,this.position,applicantMatch);
        this.recruiterRankingList.add(recruiterRanking);
        this.position.setRecruiterRankings(recruiterRankingList);

        //second data
        this.recruiterRankingList =new ArrayList<RecruiterRanking>();
        this.position = new Position("Front-End Developer", 1 , this.recruiterMatches.get(0));
        this.applicantMatch = new ApplicantMatch(2L, true, this.match);
        this.applicantRanking = new ApplicantRanking(1,this.match,this.applicantMatch,position);
        this.applicantRankingList.add(this.applicantRanking);
        this.applicantMatch.setApplicantRanking(this.applicantRankingList);
        applicantMatchList.add(this.applicantMatch);
        positions.add(position);
        this.recruiterRanking = new RecruiterRanking(2,this.match,this.position,applicantMatch);
        this.recruiterRankingList.add(recruiterRanking);
        this.position.setRecruiterRankings(recruiterRankingList);


        this.match.setApplicantMatches(this.applicantMatchList);
        this.matchRepository = Mockito.mock(MatchRepository.class);
        this.applicantMatchRepository = Mockito.mock(ApplicantMatchRepository.class);
        this.matchService = new MatchService(this.matchRepository, this.applicantMatchRepository);
    }

    @Test
    public void findByIdShouldReturnValidMatchWhenMatchIdIsCorrect() {
        Mockito.when(matchRepository.getMatchById(this.match.getId())).thenReturn(this.match);

        Match matchMock = matchService.getMatchByMatchId(this.match.getId());

        Assert.assertEquals(this.match.getName(), matchMock.getName());
    }

    @Test
    public void getApplicantMatchByMatchIdShouldReturnDataCorrectly() {
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchId(this.match.getId())).thenReturn(this.applicantMatchList);

        List<ApplicantMatch> applicantMatches = matchService.getApplicantMatchByMatchId(this.match.getId());

        Assert.assertEquals(2, applicantMatches.size());
        Assert.assertEquals(this.applicantMatch, applicantMatches.get(1));
        Assert.assertEquals(this.applicantMatch.getId(), applicantMatches.get(1).getId());
    }

    @Test
    public void getApplicantMatchByInvalidMatchIdShouldReturnNull() {
        long matchId = 2;
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchId(matchId)).thenReturn(new ArrayList<>());
        System.out.println(this.applicantMatch);
        List<ApplicantMatch> applicantMatches = matchService.getApplicantMatchByMatchId(matchId);

        Assert.assertEquals(0, applicantMatches.size());
    }

    @Test
    public void normalMatching(){
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchId(this.match.getId())).thenReturn(this.applicantMatchList);
        MatchResult matchResult1 = new MatchResult(this.applicantMatchList.get(0),positions.get(0));
        MatchResult matchResult2 = new MatchResult(this.applicantMatchList.get(1),positions.get(1));
        List<MatchResult> assertTestcase = new ArrayList<MatchResult>();
        assertTestcase.add(matchResult1);
        assertTestcase.add(matchResult2);
        List<MatchResult> results = matchService.matching(this.match.getId());
        System.out.println(results);
        System.out.println(assertTestcase);
        Assert.assertEquals(2,results.size());
        Assert.assertEquals(true,assertTestcase.equals(results));
    }

    @Test
    public void equalsTest(){
        ApplicantMatch am = new ApplicantMatch(1L, true, this.match);
        am.setApplicantRanking(this.applicantRankingList);
        Assert.assertEquals(true,am.equals(this.applicantMatchList.get(0)));
    }

}
