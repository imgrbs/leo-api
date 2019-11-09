package app.leo.matching.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.leo.matching.models.DocumentPosition;
import app.leo.matching.models.Position;

@Repository
public interface DocumentPositionRepository extends JpaRepository<DocumentPosition,Long> {

    List<DocumentPosition> findByPosition(Position position);

    List<DocumentPosition> findByPositionIdAndUserId(long positionId,long userId);

    List<DocumentPosition> findByPositionMatchIdAndUserId(long positionMatchId,long userId);

    void deleteByUserIdAndPositionId(long userId, long positionId);
}
