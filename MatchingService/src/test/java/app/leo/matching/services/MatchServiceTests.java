package app.leo.matching.services;

import app.leo.matching.models.Match;
import app.leo.matching.repositories.MatchRepository;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MatchServiceTests {

    private MatchService matchService;

    @Mock
    private MatchRepository matchRepository;


    @Before
    public void setUp() throws Exception {
        this.matchRepository = Mockito.mock(MatchRepository.class);
        this.matchService = new MatchService(this.matchRepository);
    }

    @Test
    public void findByIdShouldReturnValidMatchWhenMatchIdIsCorrect() {
        Match match = new Match(1L, "SIT Career Day 2019");
        Mockito.when(matchRepository.getMatchByMatchId(match.getMatchId())).thenReturn(match);

        Match matchMock = matchService.getMatchByMatchId(match.getMatchId());

        Assert.assertEquals(matchMock.getName(), match.getName());
    }
}
