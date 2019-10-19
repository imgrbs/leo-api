package app.leo.matching.repositories;

import app.leo.matching.models.DocumentPosition;
import app.leo.matching.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentPositionRepository extends JpaRepository<DocumentPosition,Long> {

    List<DocumentPosition> findByPosition(Position position);


    List<DocumentPosition> findByPositionMatchIdAndUserId(long positionMatchId,long userId);
}
