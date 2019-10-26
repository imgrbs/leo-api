package app.leo.matching.controllers;

import app.leo.matching.DTO.Applicant;
import app.leo.matching.DTO.ApplicantProfile;
import app.leo.matching.DTO.GetApplicantsByMatchIdResponse;
import app.leo.matching.adapters.ProfileAdapter;
import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.services.ApplicantMatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
public class ApplicantMatchController {

    @Autowired
    private ApplicantMatchService applicantMatchService;

    @Autowired
    private ProfileAdapter profileAdapter;

    @GetMapping(path = "/matches/{matchId}/positions/{positionId}/applicants")
    public ResponseEntity<List<GetApplicantsByMatchIdResponse>> getApplicantMatchesByPositionId(@PathVariable long matchId,
                                                                                                @PathVariable long positionId,
                                                                                                @RequestAttribute("token") String token){
        List<ApplicantMatch> applicantMatches = applicantMatchService.getApplicantMatchesByMatchIdandPositionId(matchId,positionId);
        return new ResponseEntity<>(this.responseBuilder(applicantMatches,token), HttpStatus.OK);
    }

    @GetMapping(path = "matches/{matchId}/applicants")
    public ResponseEntity<List<GetApplicantsByMatchIdResponse>> getApplicantMatchByMatchId(@PathVariable Long matchId,
                                                                                           @RequestAttribute("token") String token) {
        List<ApplicantMatch> applicantMatches = this.applicantMatchService.getApplicantMatchByMatchId(matchId);
        return new ResponseEntity<>(this.responseBuilder(applicantMatches,token), HttpStatus.OK);
    }

    private List<GetApplicantsByMatchIdResponse> responseBuilder(List<ApplicantMatch> applicantMatches,String token) {
        ModelMapper modelMapper = new ModelMapper();
        List<GetApplicantsByMatchIdResponse> responses = new ArrayList<>();
        for (ApplicantMatch applicantMatch : applicantMatches) {
            long applicantId = applicantMatch.getApplicantId();
            GetApplicantsByMatchIdResponse response = modelMapper.map(applicantMatch, GetApplicantsByMatchIdResponse.class);
            ApplicantProfile applicantProfile = profileAdapter.getApplicantProfileByUserId(token, applicantId);
            Applicant applicant = new Applicant();
            applicant.setName(applicantProfile.getFirstName() + " " + applicantProfile.getLastName());
            applicant.setEducations(applicantProfile.getEducations());
            applicant.setEmail(applicantProfile.getEmail());
            applicant.setSkills(applicantProfile.getSkills());
            applicant.setTelno(applicantProfile.getTelNo());
            applicant.setWebsite(applicantProfile.getWebsite());
            applicant.setExperiences(applicantProfile.getExperiences());
            applicant.setId(applicantProfile.getApplicantId());
            response.setApplicant(applicant);
            responses.add(response);
        }
        return responses;
    }

    @GetMapping("/numberOfApplicant/{matchId}")
    public ResponseEntity<Integer> getNumberOfApplicant(@PathVariable long matchId){
        return new ResponseEntity<>(applicantMatchService.countApplicantMatchByMatchId(matchId),HttpStatus.OK);
    }
}
