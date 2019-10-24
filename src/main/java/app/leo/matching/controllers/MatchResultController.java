package app.leo.matching.controllers;

import app.leo.matching.DTO.*;
import app.leo.matching.adapters.ProfileAdapter;
import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.MatchResult;
import app.leo.matching.models.Position;
import app.leo.matching.models.RecruiterMatch;
import app.leo.matching.services.ApplicantMatchService;
import app.leo.matching.services.MatchResultService;
import app.leo.matching.services.PositionService;
import app.leo.matching.services.RecruiterMatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MatchResultController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private MatchResultService matchResultService;

    @Autowired
    private RecruiterMatchService recruiterMatchService;

    @Autowired
    private ApplicantMatchService applicantMatchService;

    @Autowired
    private ProfileAdapter profileAdapter;

    @GetMapping(path = "user/matches/{matchId:[\\d]}/result")
    public ResponseEntity<List<GetMatchResultByUserIdAndMatchIdResponse>> getMatchResultByUserMatchIdAndMatchId(@PathVariable long matchId ,
                                                                                                                @RequestAttribute("user") User user,
                                                                                                                @RequestAttribute("token") String token){
        long userId = user.getUserId();
        String role = user.getRole();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        List<GetMatchResultByUserIdAndMatchIdResponse> matchResultResponse = new ArrayList<>();

        if(role.equals("applicant")) {
            ApplicantMatch applicantMatch = applicantMatchService.getApplicantMatchByApplicantIdAndMatchId(userId, matchId);
            MatchResult matchResult = matchResultService.getMatchResultByApplicantMatchIdAndMatchId(token,applicantMatch.getParticipantId(), matchId);

            GetMatchResultByUserIdAndMatchIdResponse response =modelMapper.map(matchResult,GetMatchResultByUserIdAndMatchIdResponse.class) ;
            responseEdit(token,applicantMatch,matchResult.getPosition(),response);
            matchResultResponse.add(response);
        }else if(role.equals("recruiter")){
            RecruiterMatch recruiterMatch = recruiterMatchService.getRecruiterMatchByRecruiterIdAndMatchId(userId, matchId);
            List<Position> positions = positionService.getPositionByMatchIdAndRecruiterMatchParticipantId(matchId, recruiterMatch.getParticipantId());
            for (Position position: positions) {
                List<MatchResult> positionMatchResults =  matchResultService.getMatchResultByPositionIdAndMatchId(position.getId(), matchId);
                for (MatchResult matchResult: positionMatchResults) {
                    GetMatchResultByUserIdAndMatchIdResponse response = modelMapper.map(
                            matchResult,
                            GetMatchResultByUserIdAndMatchIdResponse.class
                    );
                    responseEdit(token,matchResult.getApplicantMatch(),matchResult.getPosition(),response);
                    matchResultResponse.add(response);
                }
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(matchResultResponse, HttpStatus.OK);
    }

    private void responseEdit(String token,ApplicantMatch applicantMatch,Position position,GetMatchResultByUserIdAndMatchIdResponse response){
       Applicant applicant =getApplicantFromApplicantProfile(token,applicantMatch);
       GetApplicantsByMatchIdResponse applicantsByMatchIdResponse = new GetApplicantsByMatchIdResponse();
       applicantsByMatchIdResponse.setApplicant(applicant);
       applicantsByMatchIdResponse.setApplicantId(applicantMatch.getApplicantId());
       applicantsByMatchIdResponse.setMatchId(applicantMatch.getMatchId());
       applicantsByMatchIdResponse.setParticipantId(applicantMatch.getParticipantId());
       response.setApplicant(applicantsByMatchIdResponse);
       Recruiter recruiter =getRecruiterFromRecruiterProfile(token,position);
       GetPositionsByMatchIdResponse positionResponse = new GetPositionsByMatchIdResponse();
       positionResponse.setRecruiter(recruiter);
       positionResponse.setCapacity(position.getCapacity());
       positionResponse.setId(position.getId());
       positionResponse.setName(position.getName());
       positionResponse.setMoney(position.getMoney());
        response.setPosition(positionResponse);
    }

    private Recruiter getRecruiterFromRecruiterProfile(String token,Position position){
        RecruiterProfile recruiterProfile = profileAdapter.getRecruiterProfileByUserId(token,position.getRecruiterMatch().getRecruiterId());
        Recruiter recruiter = new Recruiter();
        recruiter.setName(recruiterProfile.getName());
        recruiter.setLocation(recruiterProfile.getLocation());
        return recruiter;
    }
    private Applicant getApplicantFromApplicantProfile(String token, ApplicantMatch applicantMatch){
        ApplicantProfile applicantProfile = profileAdapter.getApplicantProfileByUserId(token,applicantMatch.getApplicantId());
        Applicant applicant =new Applicant();
        applicant.setEducations(applicantProfile.getEducations());
        applicant.setName(applicantProfile.getFirstName() + " " + applicantProfile.getLastName());
        applicant.setWebsite(applicantProfile.getWebsite());
        applicant.setTelno(applicantProfile.getTelNo());
        applicant.setSkills(applicantProfile.getSkills());
        applicant.setEmail(applicantProfile.getEmail());
        return applicant;
    }
}
