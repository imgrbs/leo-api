package app.leo.matching.controllers;

import app.leo.matching.models.RecruiterRanking;
import app.leo.matching.services.RecruiterRankingService;
import app.leo.matching.validator.CreateRecruiterRankingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RecruiterRankingController {

    @Autowired
    private RecruiterRankingService recruiterRankingService;

    @PostMapping("/matches/{matchId:[\\d]}/recruiters/ranking")
    public RecruiterRanking createRecruiterRanking(@PathVariable long matchId, @Valid @RequestBody CreateRecruiterRankingRequest createRecruiterRankingRequest){
        long positionId = 1;
        return recruiterRankingService.createRecruiterRanking(matchId,createRecruiterRankingRequest.getApplicantId(),positionId,createRecruiterRankingRequest.getSequence());
    }
}
