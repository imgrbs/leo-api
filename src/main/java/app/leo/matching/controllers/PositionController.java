package app.leo.matching.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import app.leo.matching.DTO.GetPositionsByMatchIdResponse;
import app.leo.matching.DTO.Recruiter;
import app.leo.matching.DTO.RecruiterProfile;
import app.leo.matching.DTO.User;
import app.leo.matching.adapters.ProfileAdapter;
import app.leo.matching.models.Position;
import app.leo.matching.models.RecruiterMatch;
import app.leo.matching.services.PositionService;
import app.leo.matching.services.RecruiterMatchService;

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

    @GetMapping(path= "matches/{matchId}/positions")
    public ResponseEntity<List<GetPositionsByMatchIdResponse>> getPositionsByMatchId(@PathVariable long matchId,
                                                                                     @RequestAttribute("token" ) String token){
        List<Position> positions = positionService.getPositionByMatchId(matchId);
        return new ResponseEntity<>(this.responseBuilder(positions,token), HttpStatus.OK);
    }

    @GetMapping(path="/matches/{matchId}/recruiters/positions")

    public ResponseEntity<List<GetPositionsByMatchIdResponse>> getPositionsByRecruiterIdAndMatchId(@PathVariable long matchId,
                                                                                                   @RequestAttribute("user") User user,
                                                                                                   @RequestAttribute("token") String token){
        long recruiterId = user.getProfileId();
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
