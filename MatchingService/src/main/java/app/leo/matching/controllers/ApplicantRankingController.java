package app.leo.matching.controllers;


import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.ApplicantRanking;
import app.leo.matching.models.Match;
import app.leo.matching.models.Position;
import app.leo.matching.repositories.ApplicantMatchRepository;
import app.leo.matching.repositories.PositionRepository;
import app.leo.matching.services.ApplicantRankingService;
import app.leo.matching.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@CrossOrigin("*")
@RestController
public class ApplicantRankingController {

    @Autowired
    private ApplicantRankingService applicantRankingService;

    @PostMapping(name= "matches/{matchId}/ranking")
    public ApplicantRanking createApplicantRanking(@PathVariable long matchId,@RequestBody Map<String,Object> jsonBody){
            try {
                long userId = 1;

                int sequence = Integer.parseInt(jsonBody.get("sequence").toString());

                if(sequence<=0){
                    throw new Exception();
                }

                long positionId = Integer.parseInt(jsonBody.get("positionId").toString());
                return applicantRankingService.createApplicantRanking(matchId,userId,positionId,sequence);

            }catch(Exception e){
                return null;
            }

    }
}
