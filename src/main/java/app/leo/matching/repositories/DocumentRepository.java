package app.leo.matching.repositories;

import app.leo.matching.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {

    @Query(value = "SELECT d.* FROM documents d where d.applicant_match_Id =?1 " ,nativeQuery = true)
    List<Document> findDocumentByApplicantMatchId(long id);

}
