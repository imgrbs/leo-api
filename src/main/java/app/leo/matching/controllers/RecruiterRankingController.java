package app.leo.matching.controllers;


import app.leo.matching.DTO.*;
import app.leo.matching.adapters.ProfileAdapter;
import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.RecruiterMatch;
import app.leo.matching.models.RecruiterRanking;
import app.leo.matching.services.RecruiterRankingService;
import app.leo.matching.validator.CreateRecruiterRankingRequest;
import app.leo.matching.validator.PutRecruiterRankingRequest;
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
public class RecruiterRankingController {

    @Autowired
    private RecruiterRankingService recruiterRankingService;

    @Autowired
    private ProfileAdapter profileAdapter;

    @PostMapping("/matches/{matchId:[\\d]}/positions/{positionId:[\\d]}/ranking")
    public ResponseEntity<List<CreateRecruiterRankingRequest>>createRecruiterRanking(@PathVariable long matchId,
                                                                                     @PathVariable long positionId,
                                                                                     @Valid @RequestBody List<CreateRecruiterRankingRequest>createRecruiterRankingRequestList,
                                                                                     @RequestAttribute("token") String token){
        for(CreateRecruiterRankingRequest recruiterRankingRequest:createRecruiterRankingRequestList) {
            recruiterRankingService.createRecruiterRanking(token,matchId,recruiterRankingRequest.getParticipantId(),positionId,recruiterRankingRequest.getSequence());
        }
        return  new ResponseEntity<>(createRecruiterRankingRequestList,HttpStatus.CREATED);
    }

    @PutMapping(path ="/matches/{matchId:[\\d]}/positions/{positionId:[\\d]}/ranking")
    public ResponseEntity<List<PutRecruiterRankingRequest>> updateRecruiterRanking(@PathVariable long matchId,
                                                                                   @PathVariable long positionId,
                                                                                   @Valid @RequestBody List<PutRecruiterRankingRequest> recruiterRankingRequestList,
                                                                                   @RequestAttribute("token") String token){
        recruiterRankingService.updateRecuiterRankingByMatchIdAndPositionId(token,matchId,positionId,recruiterRankingRequestList);
      return new ResponseEntity<>(recruiterRankingRequestList,HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/matches/{matchId:[\\d]}/recruiters/positions/{positionId:[\\d]}/ranking")
    public ResponseEntity<List<GetRankingResponse>> getPositionRankingByPositionId (@PathVariable long matchId,
                                                                                    @PathVariable long positionId,
                                                                                    @RequestAttribute("token") String token){
        List<RecruiterRanking> recruiterRankingList = recruiterRankingService.getRecruiterRankingByMatchIdAndPositionId(matchId,positionId);
       return new ResponseEntity<>(mapApplicantRankingtoResponse(recruiterRankingList,token),HttpStatus.OK);
    }


    private List<GetRankingResponse> mapApplicantRankingtoResponse(List<RecruiterRanking> recruiterRankingList,String token){
        ModelMapper modelMapper =new ModelMapper();
        List<GetRankingResponse> responses = new ArrayList<>();

        for(RecruiterRanking recruiterRanking :recruiterRankingList){
            GetRankingResponse getRankingResponse = modelMapper.map(recruiterRanking,GetRankingResponse.class);
            GetPositionsByMatchIdResponse position  = getRankingResponse.getPosition();
            recruiterProfileInstall(position,token, recruiterRanking.getPosition().getRecruiterMatch());
            GetApplicantsByMatchIdResponse applicant = getRankingResponse.getApplicantMatch();
            applicantProfileInstall(applicant, token ,recruiterRanking.getApplicantMatch());
            getRankingResponse.setApplicantMatch(applicant);
            getRankingResponse.setPosition(position);
            responses.add(getRankingResponse);
        }
        return  responses;
    }

    private void recruiterProfileInstall(GetPositionsByMatchIdResponse position, String token, RecruiterMatch recruiterMatch){
        RecruiterProfile recruiterPro = (RecruiterProfile) profileAdapter.getRecruiterProfileByUserId(token,recruiterMatch.getRecruiterId());
        Recruiter recruiter = new Recruiter();
        recruiter.setName(recruiterPro.getName());
        recruiter.setLocation(recruiterPro.getLocation());
        position.setRecruiter(recruiter);
    }

    private void applicantProfileInstall(GetApplicantsByMatchIdResponse applicant, String token, ApplicantMatch applicantMatch){
        ApplicantProfile applicantProfile = (ApplicantProfile) profileAdapter.getApplicantProfileByUserId(token,applicantMatch.getApplicantId());
        Applicant applicant1 = new Applicant();
        applicant1.setEducations(applicantProfile.getEducations());
        applicant1.setName(applicantProfile.getFirstName() + " " + applicantProfile.getLastName());
        applicant.setApplicant(applicant1);
    }

}
