package app.leo.matching;

import app.leo.matching.generators.ApplicantMatchGenerator;
import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.Match;
import app.leo.matching.models.Position;
import app.leo.matching.models.RecruiterRanking;
import app.leo.matching.repositories.ApplicantMatchRepository;
import app.leo.matching.repositories.MatchRepository;
import app.leo.matching.repositories.PositionRepository;
import app.leo.matching.repositories.RecruiterRankingRepository;
import app.leo.matching.services.ApplicantMatchService;
import app.leo.matching.services.MatchService;
import app.leo.matching.services.RecruiterRankingService;
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
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class RecruiterRankingTest {

    @Mock
    private RecruiterRankingRepository recruiterRankingRepository;
    @Mock
    private PositionRepository positionRepository;
    @Mock
    private MatchRepository matchRepository;
    @Mock
    private ApplicantMatchRepository applicantMatchRepository;

    private ApplicantMatchService applicantMatchService;
    private MatchService matchService;
    private RecruiterRankingService recruiterRankingService;

    private ApplicantMatch applicantMatch;

    private Match match;
    private List<ApplicantMatch> applicantMatchListCase;
    private Position position;

    @Before
    public void setup()throws Exception{
        this.match = new Match(6L,"Create Ranking");
        this.applicantMatch = ApplicantMatchGenerator.generateApplicantMatch(match, "Programmer", 1);
        this.applicantMatchListCase = new ArrayList<ApplicantMatch>();
        this.applicantMatchListCase.add(applicantMatch);
        this.position = this.applicantMatchListCase.get(0).getApplicantRanking().get(0).getPosition();
        match.setApplicantMatches(applicantMatchListCase);


        this.positionRepository = mock(PositionRepository.class);
        this.recruiterRankingRepository = mock(RecruiterRankingRepository.class);
        this.matchRepository = mock(MatchRepository.class);
        this.applicantMatchRepository = mock(ApplicantMatchRepository.class);
        this.applicantMatchService = new ApplicantMatchService(applicantMatchRepository);
        this.matchService = new MatchService(matchRepository,applicantMatchRepository);
        this.recruiterRankingService = new RecruiterRankingService(recruiterRankingRepository,matchService,positionRepository,applicantMatchService);
    }

    @Test
    public void createRecruiterRankingShouldReturnRecruiterRanking(){
        RecruiterRanking recruiterRanking = new RecruiterRanking(3,this.match,this.position,this.applicantMatch);
        Mockito.when(matchService.getMatchByMatchId(this.match.getId())).thenReturn(this.match);
        Mockito.when(applicantMatchService.getApplicantMatchByApplicantId(this.applicantMatch.getId())).thenReturn(this.applicantMatchListCase.get(0));
        Mockito.when(this.positionRepository.findPositionById(1)).thenReturn(this.position);
        Mockito.when(this.recruiterRankingRepository.save(any(RecruiterRanking.class))).thenReturn(recruiterRanking);
        try {
            RecruiterRanking savedRecruiterRanking = recruiterRankingService.createRecruiterRanking(this.match.getId(), this.applicantMatch.getId(), 1, 3);
            Assert.assertNotNull(savedRecruiterRanking);
            Assert.assertEquals(3, recruiterRanking.getSequence());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
