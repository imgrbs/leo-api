package app.leo.matching.controllers;


import app.leo.matching.models.RecruiterRanking;
import app.leo.matching.services.RecruiterRankingService;
import app.leo.matching.validator.CreateRecruiterRankingRequest;
import app.leo.matching.validator.PutRecruiterRankingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
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

    @PutMapping(path ="/matches/{matchId:[\\d]}/recruiters/ranking")
    public List<RecruiterRanking> updateRecruiterRanking(@PathVariable long matchId, @Valid @RequestBody List<PutRecruiterRankingRequest> recruiterRankingRequestList){
        long positionId = 1;
        List<RecruiterRanking> putRecruiterRanking = new ArrayList<RecruiterRanking>();
        for(PutRecruiterRankingRequest putRecruiterRankingRequest:recruiterRankingRequestList){
            RecruiterRanking recruiterRanking =recruiterRankingService.findRecruiterRankingByPositionIdandApplicantId(positionId,putRecruiterRankingRequest.getApplicantId());
            if(recruiterRanking != null){
                recruiterRankingService.delectRecruiterRanking(recruiterRanking);
            }else{
                continue;
            }
            try {
                recruiterRanking = recruiterRankingService.createRecruiterRanking(matchId, putRecruiterRankingRequest.getApplicantId(), positionId, putRecruiterRankingRequest.getSequence());
                putRecruiterRanking.add(recruiterRanking);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
      return putRecruiterRanking;
    }
}
