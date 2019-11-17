package app.leo.matching.controllers;

import app.leo.matching.DTO.User;
import app.leo.matching.exceptions.WrongRoleException;
import app.leo.matching.models.MatchResult;
import app.leo.matching.services.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchingController {

    @Autowired
    MatchingService matchingService;

    private final String ORGANIZER_ROLE = "organizer";
    @PostMapping("/matches/{matchId}/matching")
    public ResponseEntity<List<MatchResult>>createRecruiterRanking(@PathVariable long matchId, @RequestAttribute("user") User user){
        if(user.getRole().equals(ORGANIZER_ROLE)) {
            return new ResponseEntity<>(matchingService.matchingByMatchId(matchId), HttpStatus.CREATED);
        }
        throw new WrongRoleException("You are not organizer, You can not call matching");
    }
}
