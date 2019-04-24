package app.leo.matching.repositories;

import app.leo.matching.models.RecruiterMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruiterMatchRepository extends JpaRepository<RecruiterMatch,Long> {

    List<RecruiterMatch> getRecruiterMatchesByRecruiterId(long recruierId);
}
