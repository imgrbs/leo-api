package app.leo.matching.repositories;

import app.leo.matching.models.RecruiterRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RecruiterRankingRepository extends JpaRepository<RecruiterRanking,Long> {

    @Query(value = "select rr.* from recruiter_rankings rr where rr.position_id = ?1 and rr.applicant_match_id = ?2",nativeQuery = true)
    RecruiterRanking getRecruiterRankingbyPositionIdandApplicantMatchId(long positionId,long applicantMatchId);

    @Transactional
    void deleteRecruiterRankingByMatchIdAndPositionId(long matchId, long positionId);

    List<RecruiterRanking> getRecruiterRankingByMatchIdAndPositionId(long matchId,long positionId);

}
