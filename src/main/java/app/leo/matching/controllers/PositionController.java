package app.leo.matching.controllers;

import app.leo.matching.DTO.GetPositionsByMatchIdResponse;
import app.leo.matching.DTO.Recruiter;
import app.leo.matching.DTO.RecruiterProfile;
import app.leo.matching.DTO.User;
import app.leo.matching.adapters.ProfileAdapter;
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

    @Autowired
    private ProfileAdapter profileAdapter;

    @GetMapping(path = "/healthcheck")
    public Date getDate(){
        return new Date();
    }

    private Map<Long, Long> recruiterMatchIdMap = new HashMap<>();

    private void installMap(){
        recruiterMatchIdMap.put(2L,1L);
        recruiterMatchIdMap.put(6L,2L);
    }

    @GetMapping(path= "matches/{matchId:[\\d]}/positions")
    public ResponseEntity<List<GetPositionsByMatchIdResponse>> getPositionsByMatchId(@PathVariable long matchId,
                                                                                     @RequestAttribute("token" ) String token){
        List<Position> positions = positionService.getPositionByMatchId(matchId);
        return new ResponseEntity<>(this.responseBuilder(positions,token), HttpStatus.OK);
    }

    @GetMapping(path="/matches/{matchId:[\\d]}/recruiters/positions")

    public ResponseEntity<List<GetPositionsByMatchIdResponse>> getPositionsByRecruiterIdAndMatchId(@PathVariable long matchId,
                                                                                                   @RequestAttribute("user") User user,
                                                                                                   @RequestAttribute("token") String token){
        long recruiterId = user.getUserId();
        RecruiterMatch recruiterMatch = recruiterMatchService.getRecruiterMatchByRecruiterIdAndMatchId(recruiterId, matchId);
        List<Position> positions = positionService.getPositionByMatchIdAndRecruiterMatchParticipantId(matchId, recruiterMatch.getParticipantId());
        return new ResponseEntity<>(this.responseBuilder(positions,token), HttpStatus.OK);
    }

    private List<GetPositionsByMatchIdResponse> responseBuilder(List<Position> positions,String token) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        List<GetPositionsByMatchIdResponse> responses = new ArrayList<>();
        for(Position position:positions){
            GetPositionsByMatchIdResponse response =modelMapper.map(position,GetPositionsByMatchIdResponse.class);
            Recruiter recruiter= new Recruiter();
            RecruiterProfile recruiterProfile = profileAdapter.getRecruiterProfileByUserId(token,position.getRecruiterMatch().getRecruiterId());
            setRecruiterAttributes(recruiter,recruiterProfile);
            response.setRecruiter(recruiter);
            responses.add(response);
        }
        return responses;
    }

    private void setRecruiterAttributes(Recruiter recruiter,RecruiterProfile recruiterProfile){
        recruiter.setName(recruiterProfile.getName());
        recruiter.setLocation(recruiterProfile.getLocation());
        recruiter.setWebsite(recruiterProfile.getWebsite());
        recruiter.setTelno(recruiterProfile.getTelNo());
        recruiter.setEmail(recruiterProfile.getEmail());
    }
    @GetMapping("/matches/{matchId}/position/count")
    public ResponseEntity<Integer> getPositionCount(@PathVariable long matchId){
        return new ResponseEntity<>( positionService.countPositionByMatchId(matchId),HttpStatus.OK);
    }
}
