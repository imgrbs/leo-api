package app.leo.matching.controllers;

import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.Match;
import app.leo.matching.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping(path = "matches/{matchId:[\\d]}")
    public ResponseEntity<Match> getProductsByShopId(@PathVariable Long matchId) {
        return new ResponseEntity<Match>(this.matchService.getMatchByMatchId(matchId), HttpStatus.OK);
    }

    @GetMapping(path = "matches/{matchId:[\\d]}/applicants")
    public ResponseEntity<List<ApplicantMatch>> getApplicantMatchByMatchId(@PathVariable Long matchId) {
        return new ResponseEntity<List<ApplicantMatch>>(this.matchService.getApplicantMatchByMatchId(matchId), HttpStatus.OK);
    }
}
