package app.leo.matching.repositories;

import app.leo.matching.models.MatchResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchResultRepository extends JpaRepository<MatchResult,Long> {

    MatchResult getMatchResultByApplicantMatchIdAndMatchId(long applicantMatchId,long matchId);

    List<MatchResult> getMatchResultByPositionIdAndMatchId(long positionId , long matchId);
}
