package app.leo.matching.repositories;

import app.leo.matching.models.MatchResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchResultRepository extends JpaRepository<MatchResult,Long> {

    MatchResult getMatchResultByApplicantMatch_IdAndMatchId(long applicantMatchId,long matchId);

    MatchResult getMatchResultByPosition_IdAndMatchId(long positionId , long matchId);
}
