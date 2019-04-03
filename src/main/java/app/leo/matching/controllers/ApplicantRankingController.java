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

    @PostMapping(name= "matches/{matchId}/ranking")
    public ApplicantRanking createApplicantRanking(@PathVariable long matchId,@Valid @RequestBody CreateApplicantRankingRequest applicantRankingRequest){
                long userId = 1;
                return applicantRankingService.createApplicantRanking(matchId,userId,applicantRankingRequest.getPositionId(),applicantRankingRequest.getSequence());
    }

    @PutMapping(path  = "/matches/{matchId:[\\d]}/ranking")
    public ApplicantRanking  updateApplicantRanking(@PathVariable long matchId,@Valid @RequestBody ApplicantRanking applicantRanking){
        return applicantRankingService.updateApplicantRanking(applicantRanking);
    }
}
