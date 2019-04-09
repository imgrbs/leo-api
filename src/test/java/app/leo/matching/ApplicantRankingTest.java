package app.leo.matching;

import app.leo.matching.generators.ApplicantMatchGenerator;
import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.ApplicantRanking;
import app.leo.matching.models.Match;
import app.leo.matching.models.Position;
import app.leo.matching.repositories.ApplicantMatchRepository;
import app.leo.matching.repositories.ApplicantRankingRepository;
import app.leo.matching.repositories.MatchRepository;
import app.leo.matching.repositories.PositionRepository;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ApplicantRankingTest {
    @Mock
    private ApplicantRankingRepository applicantRankingRepository;
    @Mock
    private ApplicantMatchRepository applicantMatchRepository;
    @Mock
    private PositionRepository positionRepository;
    @Mock
    private MatchRepository matchRepository;


    private MatchService matchService;
    private PositionService positionService;
    private ApplicantRankingService applicantRankingService;

    private ApplicantMatch applicantMatch;

    private Match match;
    private List<ApplicantMatch> applicantMatchListCase;
    private Position position;


    @Before
    public void setUp() throws Exception{

        this.match = new Match(6L,"Create Ranking");
        this.applicantMatch = ApplicantMatchGenerator.generateApplicantMatch(match, "Programmer", 1);
        this.applicantMatchListCase = new ArrayList<ApplicantMatch>();
        this.applicantMatchListCase.add(applicantMatch);
        this.position = this.applicantMatchListCase.get(0).getApplicantRanking().get(0).getPosition();
        match.setApplicantMatches(applicantMatchListCase);

        this.applicantRankingRepository = mock(ApplicantRankingRepository.class);
        this.matchRepository = mock(MatchRepository.class);
        this.applicantMatchRepository = mock(ApplicantMatchRepository.class);
        this.positionRepository = mock(PositionRepository.class);
        this.matchService = new MatchService(this.matchRepository, this.applicantMatchRepository);
        this.positionService = new PositionService(this.positionRepository);
        this.applicantRankingService = new ApplicantRankingService(this.matchService,this.applicantMatchRepository,this.positionRepository,this.applicantRankingRepository);

    }

    @Test
    public void createApplicantRankingShouldReturnApplicantRanking() {
        ApplicantRanking applicantRanking = new ApplicantRanking(3, this.match, this.applicantMatchListCase.get(0), this.position);
        Mockito.when(this.applicantMatchRepository.getApplicantMatchById(this.applicantMatchListCase.get(0).getId())).thenReturn(this.applicantMatchListCase.get(0));
        Mockito.when(this.positionRepository.findPositionById(6L)).thenReturn(this.position);
        Mockito.when(this.matchService.getMatchByMatchId(6L)).thenReturn(this.match);
        Mockito.when(this.applicantRankingRepository.save(any(ApplicantRanking.class))).thenReturn(applicantRanking);

        try {
            ApplicantRanking applicantRanking1 = this.applicantRankingService.createApplicantRanking(this.match.getId(), this.applicantMatchListCase.get(0).getId(), 6L, 3);
            Assert.assertEquals(applicantRanking, applicantRanking1);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Test
    public void updateApplicantRankingShouldReturnApplicantRanking(){
        ApplicantRanking beforeApplicantRanking = new ApplicantRanking(3,this.match,this.applicantMatchListCase.get(0),this.position);
        ApplicantRanking afterApplicantRanking =  new ApplicantRanking(2,this.match,this.applicantMatchListCase.get(0),this.position);
        afterApplicantRanking.setId(beforeApplicantRanking.getId());
        Mockito.when(this.applicantRankingRepository.save(afterApplicantRanking)).thenReturn(afterApplicantRanking);

        ApplicantRanking applicantRanking  = this.applicantRankingService.updateApplicantRanking(afterApplicantRanking);

        Assert.assertNotEquals(beforeApplicantRanking.getSequence(),applicantRanking.getSequence());
    }

    @Test
    public void deleteApplicantRanking(){
        applicantRankingService.deleteApplicantRanking(this.applicantMatchListCase.get(0).getApplicantRanking().get(0));

        verify(applicantRankingRepository,times(1)).delete(any(ApplicantRanking.class));
    }

    @Test
    public void deleteApplicantRankingById(){

        this.applicantRankingService.deleteApplicantRankingById(1L);
        verify(applicantRankingRepository,times(1)).deleteById(anyLong());

    }
}
