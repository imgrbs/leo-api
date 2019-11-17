package app.leo.matching.controllers;

import app.leo.matching.DTO.Applicant;
import app.leo.matching.DTO.ApplicantProfile;
import app.leo.matching.DTO.DocumentDTO;
import app.leo.matching.DTO.GetApplicantsByMatchIdResponse;
import app.leo.matching.adapters.ProfileAdapter;
import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.services.ApplicantMatchService;
import app.leo.matching.services.DocumentPositionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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

    @Autowired
    private DocumentPositionService documentPositionService;

    @GetMapping(path = "/matches/{matchId}/positions/{positionId}/applicants")
    public ResponseEntity<List<GetApplicantsByMatchIdResponse>> getApplicantMatchesByPositionId(@PathVariable long matchId,
                                                                                                @PathVariable long positionId,
                                                                                                @RequestAttribute("token") String token){
        List<ApplicantMatch> applicantMatches = applicantMatchService.getApplicantMatchesByMatchIdandPositionId(matchId,positionId);
        return new ResponseEntity<>(this.responseBuilder(applicantMatches,token,true,positionId), HttpStatus.OK);
    }

    @GetMapping(path = "matches/{matchId}/applicants")
    public ResponseEntity<List<GetApplicantsByMatchIdResponse>> getApplicantMatchByMatchId(@PathVariable Long matchId,
                                                                                           @RequestAttribute("token") String token) {
        List<ApplicantMatch> applicantMatches = this.applicantMatchService.getApplicantMatchByMatchId(matchId);
        return new ResponseEntity<>(this.responseBuilder(applicantMatches,token,false,0), HttpStatus.OK);
    }

    private List<GetApplicantsByMatchIdResponse> responseBuilder(List<ApplicantMatch> applicantMatches, String token, Boolean documentWanted, long positionId ) {
        ModelMapper modelMapper = new ModelMapper();
        List<GetApplicantsByMatchIdResponse> responses = new ArrayList<>();
        for (ApplicantMatch applicantMatch : applicantMatches) {
            long applicantId = applicantMatch.getApplicantId();
            GetApplicantsByMatchIdResponse response = modelMapper.map(applicantMatch, GetApplicantsByMatchIdResponse.class);
            ApplicantProfile applicantProfile = profileAdapter.getApplicantProfileByUserId(token, applicantId);
            Applicant applicant = matchResponseWithApplicant(applicantProfile);
            response.setApplicant(applicant);
            if(documentWanted) {
                List<Long> documentIdList = documentPositionService.getDocumentByPositionIdAndApplicantId(positionId, applicantId).size() != 0 ?documentPositionService.getDocumentByPositionIdAndApplicantId(positionId, applicantId).get(0).getFilesId():new ArrayList<>();
                List<DocumentDTO> documentList = profileAdapter.getDocumentByDocumentIdList(token, documentIdList);
                response.setDocuments(documentList);
            }
            responses.add(response);
        }
        return responses;
    }

    private Applicant matchResponseWithApplicant(ApplicantProfile applicantProfile){
        Applicant applicant = new Applicant();
        applicant.setName(applicantProfile.getFirstName() + " " + applicantProfile.getLastName());
        applicant.setEducations(applicantProfile.getEducations());
        applicant.setEmail(applicantProfile.getEmail());
        applicant.setSkills(applicantProfile.getSkills());
        applicant.setTelno(applicantProfile.getTelNo());
        applicant.setWebsite(applicantProfile.getWebsite());
        applicant.setExperiences(applicantProfile.getExperiences());
        applicant.setPictureUrl(applicantProfile.getPictureUrl());
        applicant.setId(applicantProfile.getApplicantId());
        return applicant;
    }
    @GetMapping("/numberOfApplicant/{matchId}")
    public ResponseEntity<Integer> getNumberOfApplicant(@PathVariable long matchId){
        return new ResponseEntity<>(applicantMatchService.countApplicantMatchByMatchId(matchId),HttpStatus.OK);
    }
}
