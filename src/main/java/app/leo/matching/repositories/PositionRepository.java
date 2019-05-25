package app.leo.matching.repositories;

import app.leo.matching.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {

    List<Position> getPositionByMatchId(long matchId);

    Position findPositionById(long id);

    List<Position> getPositionByMatchIdAndRecruiterMatchParticipantId(long matchId, long recruiterMatchId);
}
