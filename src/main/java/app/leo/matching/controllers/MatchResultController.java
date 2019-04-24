package app.leo.matching.controllers;

import app.leo.matching.DTO.*;
import app.leo.matching.models.MatchResult;
import app.leo.matching.services.MatchResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MatchResultController {

    @Autowired
    private MatchResultService matchResultService;

    @GetMapping(path = "user/matches/{matchId:[\\d]}/result")
    public ResponseEntity<GetMatchResultByUserIdAndMatchIdResponse> getMatchResultByUserMatchIdAndMatchId(@PathVariable long matchId , @RequestAttribute("user") User user){
        long applicantId = 1;
        String role = user.getRole();
        if(role.equals("applicant")) {
            ModelMapper modelMapper = new ModelMapper();
            GetMatchResultByUserIdAndMatchIdResponse matchResultResponse = modelMapper.map(matchResultService.getMatchResultByApplicantMatchIdAndMatchId(applicantId,matchId),GetMatchResultByUserIdAndMatchIdResponse.class);
            Recruiter recruiter = new Recruiter(1L,"Microsoft word co., Ltd","Phayathai, BKK");
            GetPositionsByMatchIdResponse position = matchResultResponse.getPosition();
            position.setRecruiter(recruiter);
            List<Education> educations = new ArrayList<>();
            educations.add(new Education(1, "School of Information Technology", "4.00"));
            Applicant applicant = new Applicant(1, "Tae Keerati", educations);
            GetApplicantsByMatchIdResponse applicantFromModelMap = matchResultResponse.getApplicant();
            applicantFromModelMap.setApplicant(applicant);
            matchResultResponse.setApplicant(applicantFromModelMap);
            matchResultResponse.setPosition(position);
            return new ResponseEntity<>( matchResultResponse, HttpStatus.OK);
        }else
            return  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
}
