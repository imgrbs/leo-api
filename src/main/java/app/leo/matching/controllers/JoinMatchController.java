package app.leo.matching.controllers;

import app.leo.matching.DTO.User;
import app.leo.matching.exceptions.AlreadyJoinedException;
import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.services.ApplicantMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import java.sql.Timestamp;


@Controller
public class JoinMatchController {
    @Autowired
    private ApplicantMatchService applicantMatchService;

    @PostMapping("/joinMatch/{matchId}")
    public ResponseEntity<ApplicantMatch> joinMatch(@PathVariable long matchId,
    @RequestAttribute("user") User user){
        ApplicantMatch existedApplicantMatch = applicantMatchService.getApplicantMatchByApplicantIdAndMatchId(user.getUserId(),matchId);
        if(existedApplicantMatch != null){
            throw new AlreadyJoinedException("You already joined this match");
        }
        ApplicantMatch applicantMatch = new ApplicantMatch(user.getUserId(),matchId);
        applicantMatch.setJoinDate(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>( applicantMatchService.saveApplicantMatch(applicantMatch), HttpStatus.ACCEPTED);
    }
}
