package app.leo.matching.controllers;

import app.leo.matching.DTO.GetPositionsByMatchIdResponse;
import app.leo.matching.models.Position;
import app.leo.matching.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping(path = "/healthcheck")
    public Date getDate(){
        return new Date();
    }

    @GetMapping(path= "matches/{matchId:[\\d]}/positions")
    public ResponseEntity<GetPositionsByMatchIdResponse> getPositionsByMatchId(@PathVariable long matchId){
        return new ResponseEntity<>(new GetPositionsByMatchIdResponse(positionService.getPositionByMatchId(matchId)), HttpStatus.OK);
    }

    @GetMapping(path="/matches/{matchId:[\\d]}/recruiters/positions")
    public ResponseEntity<List<Position>> getPositionsByRecruiterIdAndMatchId(@PathVariable long matchId){
        long recruiterId = 1;
        List<Position> positions = positionService.getPositionByMatchIdAndRecruiterId(recruiterId, matchId);
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }
}
