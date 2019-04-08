package app.leo.matching.controllers;


import app.leo.matching.models.ApplicantRanking;
import app.leo.matching.services.ApplicantRankingService;
import app.leo.matching.validator.CreateApplicantRankingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin("*")
@RestController
public class ApplicantRankingController {

    @Autowired
    private ApplicantRankingService applicantRankingService;

    @PostMapping(path= "/matches/{matchId:[\\d+]}/ranking")
    public ApplicantRanking createApplicantRanking(@Valid @PathVariable("matchId") Long matchId,@Valid @RequestBody CreateApplicantRankingRequest applicantRankingRequest){
                long userId = 1;
                return applicantRankingService.createApplicantRanking(matchId,userId,applicantRankingRequest.getPositionId(),applicantRankingRequest.getSequence());
    }

    @PutMapping(path  = "/matches/{matchId:[\\d]}/ranking")
    public ApplicantRanking  updateApplicantRanking(@PathVariable long matchId,@Valid @RequestBody ApplicantRanking applicantRanking){
        return applicantRankingService.updateApplicantRanking(applicantRanking);
    }

    @DeleteMapping(path = "/matches/{matchId:[\\d]}/ranking")
    public void deleteApplicantRanking(@PathVariable long matchId,@Valid @RequestBody ApplicantRanking applicantRanking){
        applicantRankingService.deleteApplicantRanking(applicantRanking.getId());
    }
}
