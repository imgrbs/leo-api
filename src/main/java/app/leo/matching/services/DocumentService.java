package app.leo.matching.services;

import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.Document;
import app.leo.matching.repositories.DocumentRepository;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentManagementService documentManagementService;

    public List<S3ObjectInputStream> getDocumentsByApplicantMatchId(long applicantMatchId){
        List<Document> documents =  documentRepository.findDocumentByApplicantMatchId(applicantMatchId);
        List<S3ObjectInputStream> inputStreams = new ArrayList<>();
        for(Document document:documents){
            S3ObjectInputStream s3ObjectInputStream = documentManagementService.getObjectInputStream(document.filename);
            inputStreams.add(s3ObjectInputStream);
        }

        return inputStreams;
    }

    public List<Document> uploadDocumentsToS3(List<MultipartFile> files, ApplicantMatch applicantMatch){
        List<String> documentList = documentManagementService.uploadMultipleFiles(files);
        List<Document> documents = new ArrayList<>();
        for(String documentName: documentList){
            Document document  = new Document();
            document.setFilename(documentName);
            document.setApplicantMatch(applicantMatch);
            document = documentRepository.save(document);
            documents.add(document);
        }
        return documents;
    }
}
