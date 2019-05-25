package app.leo.matching.controllers;

import app.leo.matching.DTO.GetPositionsByMatchIdResponse;
import app.leo.matching.DTO.Recruiter;
import app.leo.matching.DTO.User;
import app.leo.matching.models.Position;
import app.leo.matching.models.RecruiterMatch;
import app.leo.matching.services.PositionService;
import app.leo.matching.services.RecruiterMatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin("*")
@RestController
public class PositionController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private RecruiterMatchService recruiterMatchService;

    @GetMapping(path = "/healthcheck")
    public Date getDate(){
        return new Date();
    }

    private Map<Long, Long> recruiterMatchIdMap = new HashMap<Long, Long>();

    private void installMap(){
        recruiterMatchIdMap.put(2L,1L);
        recruiterMatchIdMap.put(6L,2L);
    }

    @GetMapping(path= "matches/{matchId:[\\d]}/positions")
    public ResponseEntity<List<GetPositionsByMatchIdResponse>> getPositionsByMatchId(@PathVariable long matchId){
        List<Position> positions = positionService.getPositionByMatchId(matchId);
        return new ResponseEntity<>(this.responseBuilder(positions), HttpStatus.OK);
    }

    @GetMapping(path="/matches/{matchId:[\\d]}/recruiters/positions")

    public ResponseEntity<List<GetPositionsByMatchIdResponse>> getPositionsByRecruiterIdAndMatchId(@PathVariable long matchId,@RequestAttribute("user") User user){
        long recruiterId = user.getUserId();
        System.out.println(recruiterMatchService.getRecruiterMatchByRecruiterIdAndMatchId(recruiterId, matchId));
        RecruiterMatch recruiterMatch = recruiterMatchService.getRecruiterMatchByRecruiterIdAndMatchId(recruiterId, matchId);
        List<Position> positions = positionService.getPositionByMatchIdAndRecruiterMatchParticipantId(matchId, recruiterMatch.getParticipantId());
        return new ResponseEntity<>(this.responseBuilder(positions), HttpStatus.OK);
    }

    private List<GetPositionsByMatchIdResponse> responseBuilder(List<Position> positions) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        List<GetPositionsByMatchIdResponse> responses = new ArrayList<>();
        for(Position position:positions){
            GetPositionsByMatchIdResponse response =modelMapper.map(position,GetPositionsByMatchIdResponse.class);
            Recruiter recruiter= null;
            if(position.getId() < 3)
                recruiter = new Recruiter(1L,"Microsoft word co., Ltd","Phayathai, BKK");
            else
                recruiter = new Recruiter(2L,"Facebook co., Ltd","San francisco, USA");
            response.setRecruiter(recruiter);
            responses.add(response);
        }
        return responses;
    }
}
