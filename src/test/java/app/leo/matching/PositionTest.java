package app.leo.matching;

import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.Position;
import app.leo.matching.models.RecruiterMatch;
import app.leo.matching.models.RecruiterRanking;
import app.leo.matching.repositories.PositionRepository;
import app.leo.matching.services.PositionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class PositionTest {
    @Mock
    private PositionRepository positionRepository;

    private RecruiterMatch recruiter;
    private Position position;

    @Before
    public void setUp() throws Exception {
        recruiter = new RecruiterMatch();
        position = new Position("Programmer", 1, recruiter);
        position.setId(1l);

        positionRepository = mock(PositionRepository.class);
    }

    @Test
    public void testExample(){
        Assert.assertTrue(true);
    }

}
