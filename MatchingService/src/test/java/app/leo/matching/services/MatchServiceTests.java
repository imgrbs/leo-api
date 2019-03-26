package app.leo.matching.services;

import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.ApplicantRanking;
import app.leo.matching.models.Match;
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

    private MatchService matchService;

    @Mock
    private MatchRepository matchRepository;
    @Mock
    private ApplicantMatchRepository applicantMatchRepository;


    @Before
    public void setUp() throws Exception {
        this.match = new Match(1L, "SIT Career Day 2019");
        this.applicantMatch = new ApplicantMatch(1L, true, this.match);
        this.applicantMatchList = new ArrayList<ApplicantMatch>();
        applicantMatchList.add(this.applicantMatch);

        this.matchRepository = Mockito.mock(MatchRepository.class);
        this.applicantMatchRepository = Mockito.mock(ApplicantMatchRepository.class);
        this.matchService = new MatchService(this.matchRepository, this.applicantMatchRepository);
    }

    @Test
    public void findByIdShouldReturnValidMatchWhenMatchIdIsCorrect() {
        Mockito.when(matchRepository.getMatchByMatchId(this.match.getMatchId())).thenReturn(this.match);

        Match matchMock = matchService.getMatchByMatchId(this.match.getMatchId());

        Assert.assertEquals(this.match.getName(), matchMock.getName());
    }

    @Test
    public void getApplicantMatchByMatchIdShouldReturnDataCorrectly() {
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchId(this.match.getMatchId())).thenReturn(this.applicantMatchList);

        List<ApplicantMatch> applicantMatches = matchService.getApplicantMatchByMatchId(this.match.getMatchId());

        Assert.assertEquals(1, applicantMatches.size());
        Assert.assertEquals(this.applicantMatch, applicantMatches.get(0));
        Assert.assertEquals(this.applicantMatch.getApplicantMatchId(), applicantMatches.get(0).getApplicantMatchId());
    }

    @Test
    public void getApplicantMatchByInvalidMatchIdShouldReturnNull() {
        long matchId = 2;
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchId(matchId)).thenReturn(new ArrayList<>());

        List<ApplicantMatch> applicantMatches = matchService.getApplicantMatchByMatchId(matchId);

        Assert.assertEquals(0, applicantMatches.size());
    }
}
