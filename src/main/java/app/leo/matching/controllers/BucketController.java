package app.leo.matching.controllers;

import app.leo.matching.DTO.User;
import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.Document;
import app.leo.matching.services.ApplicantMatchService;
import app.leo.matching.services.DocumentService;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class BucketController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ApplicantMatchService applicantMatchService;

    @PostMapping("/matches/{matchId}/upload-file")
    public ResponseEntity<List<Document>> uploadMultipleFiles(@ModelAttribute List<MultipartFile> files,
                                                        @RequestAttribute("user") User user,
                                                        @PathVariable long matchId){
        ApplicantMatch applicantMatch = applicantMatchService.getApplicantMatchByApplicantIdAndMatchId(user.getUserId(),matchId);
        return new ResponseEntity<>(documentService.uploadDocumentsToS3(files,applicantMatch), HttpStatus.OK);
    }

    @GetMapping("/matches/{matchId}/get-file")
    public ResponseEntity<List<S3ObjectInputStream>> getFromS3File(@RequestAttribute("user") User user,
                                                                   @PathVariable long matchId
                                                                   ){
        ApplicantMatch applicantMatch = applicantMatchService.getApplicantMatchByApplicantIdAndMatchId(user.getUserId(),matchId);
        return new ResponseEntity<>(documentService.getDocumentsByApplicantMatchId(applicantMatch.getParticipantId()),HttpStatus.OK);
    }
}
