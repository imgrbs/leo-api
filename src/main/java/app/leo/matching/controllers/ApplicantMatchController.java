package app.leo.matching.controllers;

import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.RecruiterMatch;
import app.leo.matching.repositories.ApplicantMatchRepository;
import app.leo.matching.services.ApplicantMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin("*")
@RestController
public class ApplicantMatchController {

    @Autowired
    private ApplicantMatchService applicantMatchService;

    @GetMapping(path = "/matches/{matchId:[\\d]}/positions/{positionId:[\\d]}/applicants")
    public ResponseEntity<List<ApplicantMatch>> getApplicantMatchesByPositionId(@PathVariable long matchId, @PathVariable long positionId){
        return new ResponseEntity<>(applicantMatchService.getApplicantMatchesByMatchIdandPositionId(matchId,positionId), HttpStatus.OK);
    }

    @GetMapping(path = "matches/{matchId:[\\d]}/applicants")
    public ResponseEntity<List<ApplicantMatch>> getApplicantMatchByMatchId(@PathVariable Long matchId) {
        return new ResponseEntity<>(this.applicantMatchService.getApplicantMatchByMatchId(matchId), HttpStatus.OK);
    }
}
