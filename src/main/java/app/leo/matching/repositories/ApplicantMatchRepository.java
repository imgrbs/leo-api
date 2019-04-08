package app.leo.matching.repositories;

import app.leo.matching.models.ApplicantMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantMatchRepository extends JpaRepository<ApplicantMatch, Long> {
    public List<ApplicantMatch> getApplicantMatchByMatchId(long matchId);

    public ApplicantMatch getApplicantMatchById(long id);

    @Query(value = "select a.* from applicant_matches a join applicant_rankings ar on a.id = ar.applicant_match_id" +
            "join positions p on p.id = ar.position_id where a.match_id = ?1 and p.recruiter_match_id = ?2", nativeQuery = true)
    public List<ApplicantMatch> getApplicantMatchByMatchIdandRecruiterId(Long matchId,Long recruiterMatchId);
}
