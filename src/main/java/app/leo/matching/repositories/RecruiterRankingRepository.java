package app.leo.matching.repositories;

import app.leo.matching.models.RecruiterRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRankingRepository extends JpaRepository<RecruiterRanking,Long> {

    @Query(name = "select rr from RecruiterRanking rr where rr.position_id = 1? and rr.applicant_match_id = 2?",nativeQuery = true)
    public RecruiterRanking findRankingbyPositionIdandApplicantId(long positionId,long applicantMatchId);
}
