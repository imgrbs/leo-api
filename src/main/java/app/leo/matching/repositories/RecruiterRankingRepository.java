package app.leo.matching.repositories;

import app.leo.matching.models.RecruiterRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RecruiterRankingRepository extends JpaRepository<RecruiterRanking,Long> {

    @Transactional
    void deleteRecruiterRankingByMatchIdAndPositionId(long matchId, long positionId);

    List<RecruiterRanking> getRecruiterRankingByMatchIdAndPositionIdOrderBySequenceAsc(long matchId,long positionId);
}
