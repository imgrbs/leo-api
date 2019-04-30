package app.leo.matching.repositories;

import app.leo.matching.models.ApplicantMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantMatchRepository extends JpaRepository<ApplicantMatch, Long> {

    List<ApplicantMatch> getApplicantMatchByMatchId(long matchId);

    ApplicantMatch getApplicantMatchById(long id);

    ApplicantMatch getApplicantMatchByApplicantIdAndMatchId(long applicantId, long matchId);

    @Query(value = "select a.id , a.applicant_id ,a.match_id from applicant_matches a inner join applicant_rankings ar on a.id = ar.applicant_match_id where a.match_id = ?1 and ar.position_id = ?2" , nativeQuery = true)
    List<ApplicantMatch> getApplicantMatchByMatchIdAndPositionId(Long matchId,Long positionId);

    List<ApplicantMatch> getApplicantMatchByApplicantId(long applicantId);
}
