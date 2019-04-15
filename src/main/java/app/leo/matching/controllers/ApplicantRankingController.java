package app.leo.matching.controllers;


import app.leo.matching.models.ApplicantRanking;
import app.leo.matching.services.ApplicantRankingService;
import app.leo.matching.validator.CreateApplicantRankingRequest;
import app.leo.matching.validator.PutApplicantRankingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin("*")
@RestController
public class ApplicantRankingController {

    @Autowired
    private ApplicantRankingService applicantRankingService;

    @PostMapping(path= "/matches/{matchId:[\\d+]}/applicants/ranking")
    public ResponseEntity<List<CreateApplicantRankingRequest>> createApplicantRanking(@Valid @PathVariable("matchId") Long matchId, @Valid @RequestBody List<CreateApplicantRankingRequest> applicantRankingRequest){
        long userId = 1;
        for(CreateApplicantRankingRequest applicantRanking : applicantRankingRequest){
            applicantRankingService.createApplicantRanking(matchId, userId, applicantRanking.getPositionId(), applicantRanking.getSequence());
        }
        return new ResponseEntity<List<CreateApplicantRankingRequest>>(applicantRankingRequest, HttpStatus.CREATED);
    }

    @GetMapping(path= "/matches/{matchId:[\\d+]}/applicants/ranking")
    public ResponseEntity<List<ApplicantRanking>> getApplicantRanking(@Valid @PathVariable("matchId") Long matchId){
        long userId = 1;
        return new ResponseEntity<List<ApplicantRanking>>(applicantRankingService.getApplicantRankingByMatchIdAndApplicantId(matchId, userId), HttpStatus.OK);
    }

    @PutMapping(path  = "/matches/{matchId:[\\d]}/applicants/ranking")
    public ResponseEntity<List<PutApplicantRankingRequest>> updateApplicantRanking(@PathVariable long matchId, @Valid @RequestBody List<PutApplicantRankingRequest> putApplicantRankingRequestList) {
        long applicantId = 1;
        applicantRankingService.updateApplicantRankingByMatchIdAndApplicantId(matchId, applicantId, putApplicantRankingRequestList);
        return new ResponseEntity<List<PutApplicantRankingRequest>>(putApplicantRankingRequestList, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/matches/{matchId:[\\d]}/ranking")
    public void deleteApplicantRanking(@PathVariable long matchId,@Valid @RequestBody ApplicantRanking applicantRanking){
        applicantRankingService.deleteApplicantRankingById(applicantRanking.getId());
    }
}
