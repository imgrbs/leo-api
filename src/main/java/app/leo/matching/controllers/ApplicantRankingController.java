package app.leo.matching.controllers;


import app.leo.matching.models.ApplicantRanking;
import app.leo.matching.services.ApplicantRankingService;
import app.leo.matching.validator.CreateApplicantRankingRequest;
import app.leo.matching.validator.PutApplicantRankingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin("*")
@RestController
public class ApplicantRankingController {

    @Autowired
    private ApplicantRankingService applicantRankingService;

    @PostMapping(path= "/matches/{matchId:[\\d+]}/ranking")
    public void createApplicantRanking(@Valid @PathVariable("matchId") Long matchId,@Valid @RequestBody List<CreateApplicantRankingRequest> applicantRankingRequest){
                long userId = 1;
                createApplicantRankingusingRequestList(matchId,userId,applicantRankingRequest);
    }

    private List<ApplicantRanking> createApplicantRankingusingRequestList(long matchId,long userId,List<CreateApplicantRankingRequest> applicantRankingRequestList){
        List<ApplicantRanking> applicantRankingList = new ArrayList<ApplicantRanking>();
        for(CreateApplicantRankingRequest applicantRankingRequest:applicantRankingRequestList){
            try {
                ApplicantRanking applicantRanking = applicantRankingService.createApplicantRanking(matchId,userId,applicantRankingRequest.getPositionId(),applicantRankingRequest.getSequence());
                applicantRankingList.add(applicantRanking);
            }catch (Exception e){
                e.printStackTrace();
            }
            return applicantRankingList;
        }
        return applicantRankingList;
    }
    @PutMapping(path  = "/matches/{matchId:[\\d]}/applicants/ranking")
    public List<ApplicantRanking> updateApplicantRanking(@PathVariable long matchId, @Valid @RequestBody List<PutApplicantRankingRequest> putApplicantRankingRequestList) {
        long applicantId = 1;
        List<ApplicantRanking> putApplicantRanking = new ArrayList<ApplicantRanking>();
        for(PutApplicantRankingRequest putApplicantRankingRequest:putApplicantRankingRequestList) {
            ApplicantRanking applicantRanking = applicantRankingService.getApplicantRankingByApplicantIdandPositionId(applicantId, putApplicantRankingRequest.getPositionId());
            if (applicantRanking != null) {
                applicantRankingService.deleteApplicantRanking(applicantRanking);
            } else {
                continue;
            }
            try {
                applicantRanking = applicantRankingService.createApplicantRanking(matchId, applicantId, putApplicantRankingRequest.getPositionId(), putApplicantRankingRequest.getSequece());
                putApplicantRanking.add(applicantRanking);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return putApplicantRanking;
    }

    @DeleteMapping(path = "/matches/{matchId:[\\d]}/ranking")
    public void deleteApplicantRanking(@PathVariable long matchId,@Valid @RequestBody ApplicantRanking applicantRanking){
        applicantRankingService.deleteApplicantRankingById(applicantRanking.getId());
    }


}
