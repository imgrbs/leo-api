package app.leo.matching.controllers;

import app.leo.matching.DTO.*;
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

    Recruiter recruiter = new Recruiter(1L,"Microsoft word co., Ltd","Phayathai, BKK");
    List<Education> educations = new ArrayList<>();
    Education education = new Education(1, "School of Information Technology", "4.00");
    Applicant applicant = new Applicant(1, "Tae Keerati", educations);

    @GetMapping(path = "user/matches/{matchId:[\\d]}/result")
    public ResponseEntity<List<GetMatchResultByUserIdAndMatchIdResponse>> getMatchResultByUserMatchIdAndMatchId(@PathVariable long matchId , @RequestAttribute("user") User user){
        long userId = user.getUserId();
        String role = user.getRole();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        List<GetMatchResultByUserIdAndMatchIdResponse> matchResultResponse = new ArrayList<>();

        if(role.equals("applicant")) {
            ApplicantMatch applicantMatch = applicantMatchService.getApplicantMatchByApplicantIdAndMatchId(userId, matchId);
             matchResultResponse.add(
                 modelMapper.map(
                     matchResultService.getMatchResultByApplicantMatchIdAndMatchId(applicantMatch.getUserId(), matchId),
                     GetMatchResultByUserIdAndMatchIdResponse.class
                 )
             );
        }else if(role.equals("recruiter")){
            RecruiterMatch recruiterMatch = recruiterMatchService.getRecruiterMatchByRecruiterIdAndMatchId(userId, matchId);
            List<Position> positions = positionService.getPositionByMatchIdAndRecruiterId(matchId, recruiterMatch.getUserId());
            for (Position position: positions) {
                List<MatchResult> positionMatchResults =  matchResultService.getMatchResultByPositionIdAndMatchId(position.getId(), matchId);
                for (MatchResult matchResult: positionMatchResults) {
                    matchResultResponse.add(
                        modelMapper.map(
                            matchResult,
                            GetMatchResultByUserIdAndMatchIdResponse.class
                        )
                    );
                }
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        // set mock data
        for (GetMatchResultByUserIdAndMatchIdResponse response: matchResultResponse) {
            GetPositionsByMatchIdResponse position = response.getPosition();
            position.setRecruiter(recruiter);
            GetApplicantsByMatchIdResponse applicantFromModelMap = response.getApplicant();
            applicantFromModelMap.setApplicant(applicant);
            response.setApplicant(applicantFromModelMap);
            response.setPosition(position);
        }
        return new ResponseEntity<>(matchResultResponse, HttpStatus.OK);
    }

}
