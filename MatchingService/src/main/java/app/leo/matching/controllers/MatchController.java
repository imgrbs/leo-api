package app.leo.matching.controllers;

import app.leo.matching.models.Match;
import app.leo.matching.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MatchController {
    @Autowired
    private MatchService matchService;


    @GetMapping(path = "matches/{matchId:[\\d]}")
    public ResponseEntity<Match> getProductsByShopId(@PathVariable Long matchId) {
        return new ResponseEntity<Match>(this.matchService.getMatchByMatchId(matchId), HttpStatus.OK);
    }
}
