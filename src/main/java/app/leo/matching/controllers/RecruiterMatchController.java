package app.leo.matching.controllers;

import app.leo.matching.models.RecruiterMatch;
import app.leo.matching.services.RecruiterMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecruiterMatchController {

    @Autowired
    private RecruiterMatchService recruiterMatchService;

    @GetMapping(path = "recruiter/matches")
    public List<RecruiterMatch> getListOfUserByRecruiterId(){
        long recruiterId = 1;
        return recruiterMatchService.getRecruiterMatchByRecruiterId(recruiterId);
    }

}
