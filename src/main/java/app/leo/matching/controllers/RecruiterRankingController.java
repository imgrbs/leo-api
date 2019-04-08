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
import java.util.ArrayList;
import java.util.List;

@RestController
public class RecruiterRankingController {

    @Autowired
    private RecruiterRankingService recruiterRankingService;

    @PostMapping("/matches/{matchId:[\\d]}/recruiters/ranking")
    public void createRecruiterRanking(@PathVariable long matchId, @Valid @RequestBody List<CreateRecruiterRankingRequest>createRecruiterRankingRequestList){
        long positionId = 1;
        createRecruiterRankingusingRequestList(matchId,positionId,createRecruiterRankingRequestList);
    }

    private List<RecruiterRanking> createRecruiterRankingusingRequestList(long matchId, long userId, List<CreateRecruiterRankingRequest> recruiterRankingRequests){
        List<RecruiterRanking> recruiterRankings = new ArrayList<RecruiterRanking>();
        for(CreateRecruiterRankingRequest recruiterRankingRequest:recruiterRankingRequests){
            try {
                RecruiterRanking recruiterRanking= recruiterRankingService.createRecruiterRanking(matchId,userId,recruiterRankingRequest.getApplicantId(),recruiterRankingRequest.getSequence());
                recruiterRankings.add(recruiterRanking);
            }catch (Exception e){
                e.printStackTrace();
            }
            return recruiterRankings;
        }
        return recruiterRankings;
    }
}
