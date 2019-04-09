package app.leo.matching.controllers;

import app.leo.matching.models.Position;
import app.leo.matching.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
public class PositionController {

    @Autowired
    private PositionService positionService;

    @PutMapping(path = "/matches/{matchId:[\\d]}/recruiters")
    public void putRecruiterToUnclarify(@PathVariable long matchId,@Valid @RequestBody Long positionId) {
        try {
            positionService.putRecruiterToUnclarify(positionId);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping(path = "/healthcheck")
    public Date getDate(){
        return new Date();
    }

    @GetMapping(path= "matches/{matchId:[\\d]}/positions")
    public List<Position> getPositionsByMatchId(@PathVariable long matchId){
        return positionService.getPositionByMatchId(matchId);
    }
}
