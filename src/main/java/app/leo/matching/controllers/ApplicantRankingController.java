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
import java.util.*;


@CrossOrigin("*")
@RestController
public class ApplicantRankingController {

    @Autowired
    private ApplicantRankingService applicantRankingService;


    @PostMapping(path= "/matches/{matchId:[\\d+]}/applicants/ranking")
    public ResponseEntity<List<CreateApplicantRankingRequest>> createApplicantRanking(@Valid @PathVariable("matchId") Long matchId,
                                                                                      @Valid @RequestBody List<CreateApplicantRankingRequest> applicantRankingRequest,
                                                                                      @RequestAttribute("user") User user,
                                                                                      @RequestAttribute("token") String token){
        long userId = user.getUserId();
        for(CreateApplicantRankingRequest applicantRanking : applicantRankingRequest){
            applicantRankingService.createApplicantRanking(token,matchId, userId, applicantRanking.getPositionId(), applicantRanking.getSequence());
        }
        return new ResponseEntity<>(applicantRankingRequest, HttpStatus.CREATED);
    }

    @GetMapping(path= "/matches/{matchId:[\\d+]}/applicants/ranking")
    public ResponseEntity<List<GetRankingResponse>> getApplicantRanking(@Valid @PathVariable("matchId") Long matchId,@RequestAttribute("user") User user){
        long userId = user.getUserId();
        return new ResponseEntity<>(mapApplicantRankingtoResponse(applicantRankingService.getApplicantRankingByMatchIdAndApplicantId(matchId, userId),user), HttpStatus.OK);
    }

    private List<GetRankingResponse> mapApplicantRankingtoResponse(List<ApplicantRanking> applicantRankingList, User user){
        ModelMapper modelMapper =new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        List<GetRankingResponse> responses = new ArrayList<>();
        for(ApplicantRanking applicantRanking:applicantRankingList){
            GetRankingResponse getRankingResponse = modelMapper.map(applicantRanking,GetRankingResponse.class);
            GetPositionsByMatchIdResponse position  = getRankingResponse.getPosition();
            mockRecruiterInstall(position);
            GetApplicantsByMatchIdResponse applicant = getRankingResponse.getApplicantMatch();
            mockApplicantInstall(applicant,user);
            getRankingResponse.setApplicantMatch(applicant);
            getRankingResponse.setPosition(position);
            responses.add(getRankingResponse);
        }
        return  responses;
    }

    private void mockRecruiterInstall(GetPositionsByMatchIdResponse position){
        Recruiter recruiter = null;
        if(position.getId() < 3) {
            recruiter = new Recruiter(1L, "Microsoft word co., Ltd", "Phayathai, BKK");
        }else
            recruiter = new Recruiter(2L,"Facebook co., Ltd","San francisco,USA");
        position.setRecruiter(recruiter);
    }

    private void mockApplicantInstall(GetApplicantsByMatchIdResponse applicant,User user){
        List<Education> educations = new ArrayList<>();
        educations.add(new Education(1, "School of Information Technology", "4.00"));
        Applicant mockApplicant = null;
            if(user.getUserId() == 3 ){
                mockApplicant = new Applicant(3, "Jill Jirapa", educations);
            }else if(user.getUserId() == 2 ){
                mockApplicant = new Applicant(2,"Volk Natchanon",educations);
            }else{
                mockApplicant = new Applicant(1,"Tae Keerati",educations);
            }
        applicant.setApplicant(mockApplicant);
    }

    @PutMapping(path  = "/matches/{matchId:[\\d]}/applicants/ranking")
    public ResponseEntity<List<PutApplicantRankingRequest>> updateApplicantRanking(
            @PathVariable long matchId,
            @Valid @RequestBody List<PutApplicantRankingRequest> putApplicantRankingRequestList,
            @RequestAttribute("user") User user,
            @RequestAttribute("token") String token) {
        long applicantId = user.getUserId();
        applicantRankingService.updateApplicantRankingByMatchIdAndApplicantId(token,matchId, applicantId, putApplicantRankingRequestList);
        return new ResponseEntity<>(putApplicantRankingRequestList, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/matches/{matchId:[\\d]}/ranking")
    public void deleteApplicantRanking(@PathVariable long matchId,@Valid @RequestBody ApplicantRanking applicantRanking){
        applicantRankingService.deleteApplicantRankingById(applicantRanking.getId());
    }
}
