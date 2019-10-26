package app.leo.matching.controllers;

import app.leo.matching.models.MatchResult;
import app.leo.matching.services.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchingController {

    @Autowired
    MatchingService matchingService;

    @PostMapping("/matches/{matchId}/matching")
    public ResponseEntity<List<MatchResult>>createRecruiterRanking(@PathVariable long matchId){
        return  new ResponseEntity<>(matchingService.matchingByMatchId(matchId), HttpStatus.CREATED);
    }
}
