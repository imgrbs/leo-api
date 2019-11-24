package app.leo.matching.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.leo.matching.models.MatchResult;

@Repository
public interface MatchResultRepository extends JpaRepository<MatchResult,Long> {

    MatchResult getMatchResultByApplicantMatchParticipantIdAndMatchId(long participantId,long matchId);

    List<MatchResult> getMatchResultByPositionIdAndMatchId(long positionId , long matchId);

		void deleteByMatchId(long matchId);
}
