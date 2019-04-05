package app.leo.matching.controllers;

import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.RecruiterMatch;
import app.leo.matching.repositories.ApplicantMatchRepository;
import app.leo.matching.services.ApplicantMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ApplicantMatchController {

    @Autowired
    private ApplicantMatchService applicantMatchService;


    @GetMapping("/matches/{matchId:[\\d]}/recruiters/applicants")
    public List<ApplicantMatch> getApplicantMatchesByPositionId(@PathVariable long matchId,@Valid @RequestBody RecruiterMatch recruiterMatch){
        return applicantMatchService.getApplicantMatchesByMatchIdandRecruiterMatchId(matchId,recruiterMatch);
    }
}
