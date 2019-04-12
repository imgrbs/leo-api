package app.leo.matching;

import app.leo.matching.generators.ApplicantMatchGenerator;
import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.Match;
import app.leo.matching.models.Position;
import app.leo.matching.repositories.ApplicantMatchRepository;
import app.leo.matching.repositories.ApplicantRankingRepository;
import app.leo.matching.repositories.MatchRepository;
import app.leo.matching.repositories.PositionRepository;
import app.leo.matching.services.ApplicantMatchService;
import app.leo.matching.services.ApplicantRankingService;
import app.leo.matching.services.MatchService;
import app.leo.matching.services.PositionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ApplicantMatchTest {

    @Mock
    private ApplicantMatchRepository applicantMatchRepository;


    private ApplicantMatchService applicantMatchService;

    private ApplicantMatch applicantMatch;

    private Match match;
    private List<ApplicantMatch> applicantMatchListCase;
    private Position position;


    @Before
    public void setUp() throws Exception{

        this.match = new Match(7L,"Find my Applicant");
        this.applicantMatch = ApplicantMatchGenerator.generateApplicantMatch(match, "Programmer", 1);
        this.applicantMatchListCase = new ArrayList<ApplicantMatch>();
        this.applicantMatchListCase.add(applicantMatch);
        this.position = this.applicantMatchListCase.get(0).getApplicantRanking().get(0).getPosition();
        match.setApplicantMatches(applicantMatchListCase);

        this.applicantMatchRepository = mock(ApplicantMatchRepository.class);
        this.applicantMatchService = mock(ApplicantMatchService.class);
    }

    @Test
    public void getApplicantsByPositionIdShouldReturnApplicantList(){
        Mockito.when(applicantMatchRepository.getApplicantMatchByMatchIdAndPositionId(this.match.getId(),1L)).thenReturn(this.applicantMatchListCase);

        List<ApplicantMatch> applicantMatchList =applicantMatchService.getApplicantMatchesByMatchIdandPositionId(this.match.getId(),1L);

        Assert.assertNotNull(applicantMatchList);
    }

}
