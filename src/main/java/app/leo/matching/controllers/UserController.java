package app.leo.matching.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import app.leo.matching.DTO.User;
import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.RecruiterMatch;
import app.leo.matching.models.UserMatch;
import app.leo.matching.services.ApplicantMatchService;
import app.leo.matching.services.RecruiterMatchService;

@RestController
public class UserController {

    @Autowired
    private ApplicantMatchService applicantMatchService;

    @Autowired
    private RecruiterMatchService recruiterMatchService;

    @GetMapping("/user/matches")
    public ResponseEntity<List<UserMatch>> getUserMatchByUserId(@RequestAttribute("user") User user ){
        long profileId = user.getProfileId();
        String role = user.getRole();
        if(role.equals("applicant")){
            List<ApplicantMatch> applicantMatchList = applicantMatchService.getApplicantMatchByApplicantId(profileId);
            return new ResponseEntity<List<UserMatch>>(new ArrayList<>(applicantMatchList), HttpStatus.OK);
        }else if(role.equals("recruiter")){
            List<RecruiterMatch> recruiterMatchList =recruiterMatchService.getRecruiterMatchByRecruiterId(profileId);
            return new ResponseEntity<>(new ArrayList<>(recruiterMatchList), HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
}
