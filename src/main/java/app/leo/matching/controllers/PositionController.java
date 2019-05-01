package app.leo.matching.controllers;

import app.leo.matching.DTO.GetPositionsByMatchIdResponse;
import app.leo.matching.DTO.Recruiter;
import app.leo.matching.models.Position;
import app.leo.matching.services.PositionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<List<GetPositionsByMatchIdResponse>> getPositionsByMatchId(@PathVariable long matchId){
        List<Position> positions = positionService.getPositionByMatchId(matchId);
        return new ResponseEntity<>(this.responseBuilder(positions), HttpStatus.OK);
    }

    @GetMapping(path="/matches/{matchId:[\\d]}/recruiters/positions")
    public ResponseEntity<List<GetPositionsByMatchIdResponse>> getPositionsByRecruiterIdAndMatchId(@PathVariable long matchId){
        long recruiterId = 1;
        List<Position> positions = positionService.getPositionByMatchIdAndRecruiterId(recruiterId, matchId);
        return new ResponseEntity<>(this.responseBuilder(positions), HttpStatus.OK);
    }

    private List<GetPositionsByMatchIdResponse> responseBuilder(List<Position> positions) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        List<GetPositionsByMatchIdResponse> responses = new ArrayList<>();
        for(Position position:positions){
            GetPositionsByMatchIdResponse response =modelMapper.map(position,GetPositionsByMatchIdResponse.class);
            Recruiter recruiter = new Recruiter(1L,"Microsoft word co., Ltd","Phayathai, BKK");
            response.setRecruiter(recruiter);
            responses.add(response);
        }
        return responses;
    }
}
