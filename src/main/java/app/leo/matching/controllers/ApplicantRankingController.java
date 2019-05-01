package app.leo.matching.controllers;


import app.leo.matching.DTO.*;
import app.leo.matching.models.ApplicantRanking;
import app.leo.matching.services.ApplicantRankingService;
import app.leo.matching.validator.CreateApplicantRankingRequest;
import app.leo.matching.validator.PutApplicantRankingRequest;
import org.modelmapper.ModelMapper;
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
        return new ResponseEntity<>(applicantRankingRequest, HttpStatus.CREATED);
    }

    @GetMapping(path= "/matches/{matchId:[\\d+]}/applicants/ranking")
    public ResponseEntity<List<GetRankingResponse>> getApplicantRanking(@Valid @PathVariable("matchId") Long matchId){
        long userId = 1;
        return new ResponseEntity<>(mapApplicantRankingtoResponse(applicantRankingService.getApplicantRankingByMatchIdAndApplicantId(matchId, userId)), HttpStatus.OK);
    }

    private List<GetRankingResponse> mapApplicantRankingtoResponse(List<ApplicantRanking> applicantRankingList){
        ModelMapper modelMapper =new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        List<GetRankingResponse> responses = new ArrayList<>();
        for(ApplicantRanking applicantRanking:applicantRankingList){
            GetRankingResponse getRankingResponse = modelMapper.map(applicantRanking,GetRankingResponse.class);
            GetPositionsByMatchIdResponse position  = getRankingResponse.getPosition();
            mockRecruiterInstall(position);
            GetApplicantsByMatchIdResponse applicant = getRankingResponse.getApplicantMatch();
            mockApplicantInstall(applicant);
            getRankingResponse.setApplicantMatch(applicant);
            getRankingResponse.setPosition(position);
            responses.add(getRankingResponse);
        }
        return  responses;
    }

    private void mockRecruiterInstall(GetPositionsByMatchIdResponse position){
        Recruiter recruiter = new Recruiter(1L,"Microsoft word co., Ltd","Phayathai, BKK");
        position.setRecruiter(recruiter);
    }

    private void mockApplicantInstall(GetApplicantsByMatchIdResponse applicant){
        List<Education> educations = new ArrayList<>();
        educations.add(new Education(1, "School of Information Technology", "4.00"));
        Applicant mockApplicant = new Applicant(1, "Tae Keerati", educations);
        applicant.setApplicant(mockApplicant);
    }

    @PutMapping(path  = "/matches/{matchId:[\\d]}/applicants/ranking")
    public ResponseEntity<List<PutApplicantRankingRequest>> updateApplicantRanking(@PathVariable long matchId, @Valid @RequestBody List<PutApplicantRankingRequest> putApplicantRankingRequestList) {
        long applicantId = 1;
        applicantRankingService.updateApplicantRankingByMatchIdAndApplicantId(matchId, applicantId, putApplicantRankingRequestList);
        return new ResponseEntity<>(putApplicantRankingRequestList, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/matches/{matchId:[\\d]}/ranking")
    public void deleteApplicantRanking(@PathVariable long matchId,@Valid @RequestBody ApplicantRanking applicantRanking){
        applicantRankingService.deleteApplicantRankingById(applicantRanking.getId());
    }
}
