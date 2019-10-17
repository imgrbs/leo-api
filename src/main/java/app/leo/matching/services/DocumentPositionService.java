package app.leo.matching.services;

import app.leo.matching.models.DocumentPosition;
import app.leo.matching.models.Position;
import app.leo.matching.repositories.DocumentPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<DocumentPosition> createDocumentPositions(List<DocumentPosition> documentPositions){
        return documentPositionRepository.saveAll(documentPositions);
    }
}
