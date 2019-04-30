package app.leo.matching.repositories;

import app.leo.matching.models.MatchResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchResultRepository extends JpaRepository<MatchResult,Long> {

    MatchResult getMatchResultByApplicantMatchIdAndMatchId(long applicantMatchId,long matchId);

    MatchResult getMatchResultByPositionIdAndMatchId(long positionId , long matchId);
}
