package app.leo.matching.repositories;

import app.leo.matching.models.ApplicantMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantMatchRepository extends JpaRepository<ApplicantMatch, Long> {
    public List<ApplicantMatch> getApplicantMatchByMatchId(long matchId);
}
