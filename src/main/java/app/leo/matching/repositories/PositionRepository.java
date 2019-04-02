package app.leo.matching.repositories;

import app.leo.matching.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {

    @Query(name = "select * from positions p where p.match_id = ?1 ", nativeQuery = true)
    public List<Position> getPositionByMatchId(long matchId);

    public Position findPositionById(long Id);
}
