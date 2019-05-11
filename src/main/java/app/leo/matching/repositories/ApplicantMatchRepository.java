package app.leo.matching.repositories;

import app.leo.matching.models.ApplicantMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantMatchRepository extends JpaRepository<ApplicantMatch, Long> {

    List<ApplicantMatch> getApplicantMatchByMatchId(long matchId);

    ApplicantMatch getApplicantMatchByParticipantId(long participantId);

    ApplicantMatch getApplicantMatchByApplicantIdAndMatchId(long applicantId, long matchId);

    @Query(value = "select DISTINCT(a.participant_id) , a.applicant_id ,a.match_id from leo.applicant_matches a inner join leo.applicant_rankings ar on a.participant_id= ar.applicant_match_id where a.match_id = ?1 and ar.position_id = ?2" , nativeQuery = true)
    List<ApplicantMatch> getApplicantMatchByMatchIdAndPositionId(Long matchId,Long positionId);

    List<ApplicantMatch> getApplicantMatchByApplicantId(long applicantId);
}
