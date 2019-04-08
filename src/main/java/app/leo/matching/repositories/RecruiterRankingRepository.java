package app.leo.matching.repositories;

import app.leo.matching.models.RecruiterRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRankingRepository extends JpaRepository<RecruiterRanking,Long> {

}
