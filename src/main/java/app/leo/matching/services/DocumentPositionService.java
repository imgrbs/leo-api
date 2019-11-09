package app.leo.matching.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.leo.matching.models.DocumentPosition;
import app.leo.matching.models.Position;
import app.leo.matching.repositories.DocumentPositionRepository;

@Service
public class DocumentPositionService {

    @Autowired
    private DocumentPositionRepository documentPositionRepository;

    public List<DocumentPosition> getDocumentByMatchIdandUserId(long positionMatchId, long userId ){
        return documentPositionRepository.findByPositionMatchIdAndUserId(positionMatchId,userId);
    }

    public List<DocumentPosition> getDocumentByPosition(Position position){
        return documentPositionRepository.findByPosition(position);
    }

    public DocumentPosition createDocumentPosition(DocumentPosition documentPosition){
        return documentPositionRepository.save(documentPosition);
    }

    @Transactional
    public List<DocumentPosition> createDocumentPositions(List<DocumentPosition> documentPositions){
        deleteDocumentPositions(documentPositions);
        return documentPositionRepository.saveAll(documentPositions);
    }

    private void deleteDocumentPositions(List<DocumentPosition> documentPositions) {
        documentPositions.forEach(documentPosition -> {
            documentPositionRepository.deleteByUserIdAndPositionId(
                documentPosition.getUserId(),
                documentPosition.getPosition().getId()
            );
        });
    }

    public List<DocumentPosition> getDocumentByPositionIdAndApplicantId(long positionId, long applicantId){
        return documentPositionRepository.findByPositionIdAndUserId(positionId,applicantId);
    }
}
