package app.leo.matching.repositories;


import app.leo.matching.models.ApplicantRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRankingRepository extends JpaRepository<ApplicantRanking,Long> {

}
