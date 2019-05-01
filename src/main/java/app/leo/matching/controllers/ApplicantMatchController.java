package app.leo.matching.controllers;

import app.leo.matching.DTO.Applicant;
import app.leo.matching.DTO.Education;
import app.leo.matching.DTO.GetApplicantsByMatchIdResponse;
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

    @GetMapping(path = "/matches/{matchId:[\\d]}/positions/{positionId:[\\d]}/applicants")
    public ResponseEntity<List<GetApplicantsByMatchIdResponse>> getApplicantMatchesByPositionId(@PathVariable long matchId, @PathVariable long positionId){
        List<ApplicantMatch> applicantMatches = applicantMatchService.getApplicantMatchesByMatchIdandPositionId(matchId,positionId);
        return new ResponseEntity<>(this.responseBuilder(applicantMatches), HttpStatus.OK);
    }

    @GetMapping(path = "matches/{matchId:[\\d]}/applicants")
    public ResponseEntity<List<GetApplicantsByMatchIdResponse>> getApplicantMatchByMatchId(@PathVariable Long matchId) {
        List<ApplicantMatch> applicantMatches = this.applicantMatchService.getApplicantMatchByMatchId(matchId);
        return new ResponseEntity<>(this.responseBuilder(applicantMatches), HttpStatus.OK);
    }

    private List<GetApplicantsByMatchIdResponse> responseBuilder(List<ApplicantMatch> applicantMatches) {
        ModelMapper modelMapper = new ModelMapper();
        List<GetApplicantsByMatchIdResponse> responses = new ArrayList<>();
        List<Education> educations = new ArrayList<>();
        educations.add(new Education(1, "School of Information Technology", "4.00"));
        Applicant[] applicants = {
                new Applicant(1, "Tae Keerati", educations),
                new Applicant(2, "Volk Natchanon", educations),
        };
        for (int i = 0; i < applicantMatches.size(); i++) {
            GetApplicantsByMatchIdResponse response = modelMapper.map(applicantMatches.get(i), GetApplicantsByMatchIdResponse.class);
            response.setApplicant(applicants[i]);
            responses.add(response);
        }
        return responses;
    }
}
