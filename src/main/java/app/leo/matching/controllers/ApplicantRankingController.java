package app.leo.matching.controllers;


import app.leo.matching.DTO.*;
import app.leo.matching.adapters.ProfileAdapter;
import app.leo.matching.models.ApplicantRanking;
import app.leo.matching.models.RecruiterMatch;
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

    @Autowired
    private ProfileAdapter profileAdapter;

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

    @GetMapping(path= "/matches/{matchId:[\\d]}/applicants/ranking")
    public ResponseEntity<List<GetRankingResponse>> getApplicantRanking(@Valid @PathVariable("matchId") Long matchId,
                                                                        @RequestAttribute("user") User user,
                                                                        @RequestAttribute("token") String token){
        long userId = user.getUserId();
        return new ResponseEntity<>(mapApplicantRankingtoResponse(applicantRankingService.getApplicantRankingByMatchIdAndApplicantId(matchId, userId),user,token), HttpStatus.OK);
    }

    private List<GetRankingResponse> mapApplicantRankingtoResponse(List<ApplicantRanking> applicantRankingList, User user,String token){
        ModelMapper modelMapper =new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        List<GetRankingResponse> responses = new ArrayList<>();
        for(ApplicantRanking applicantRanking:applicantRankingList){
            GetRankingResponse getRankingResponse = modelMapper.map(applicantRanking,GetRankingResponse.class);
            GetPositionsByMatchIdResponse position  = getRankingResponse.getPosition();
            recruiterProfileInstall(position,token,applicantRanking.getPosition().getRecruiterMatch());
            GetApplicantsByMatchIdResponse applicant = getRankingResponse.getApplicantMatch();
            applicantProfileInstall(applicant,token,user);
            getRankingResponse.setApplicantMatch(applicant);
            getRankingResponse.setPosition(position);
            responses.add(getRankingResponse);
        }
        return  responses;
    }

    private void recruiterProfileInstall(GetPositionsByMatchIdResponse position, String token, RecruiterMatch recruiterMatch){
        RecruiterProfile recruiterPro = profileAdapter.getRecruiterProfileByUserId(token,recruiterMatch.getRecruiterId());
        Recruiter recruiter = new Recruiter();
        recruiter.setName(recruiterPro.getName());
        recruiter.setLocation(recruiterPro.getLocation());
        recruiter.setEmail(recruiterPro.getEmail());
        recruiter.setTelno(recruiterPro.getTelNo());
        recruiter.setWebsite(recruiterPro.getWebsite());
        position.setRecruiter(recruiter);
    }

    private void applicantProfileInstall(GetApplicantsByMatchIdResponse applicant,String token,User user){
        ApplicantProfile applicantProfile = profileAdapter.getApplicantProfileByUserId(token,user.getUserId());
        Applicant applicant1 = new Applicant();
        applicant1.setEducations(applicantProfile.getEducations());
        applicant1.setName(applicantProfile.getFirstName() + " " + applicantProfile.getLastName());
        applicant1.setEmail(applicantProfile.getEmail());
        applicant1.setSkills(applicantProfile.getSkills());
        applicant1.setTelNo(applicantProfile.getTelNo());
        applicant1.setWebsite(applicantProfile.getWebsite());
        applicant.setApplicant(applicant1);
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
