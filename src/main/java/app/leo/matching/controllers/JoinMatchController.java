package app.leo.matching.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.leo.matching.DTO.CheckJoinedResponse;
import app.leo.matching.DTO.PositionDTO;
import app.leo.matching.DTO.RecruiterJoinMatchRequest;
import app.leo.matching.DTO.User;
import app.leo.matching.adapters.MatchManagementAdapter;
import app.leo.matching.exceptions.AlreadyJoinedException;
import app.leo.matching.exceptions.WrongRoleException;
import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.Position;
import app.leo.matching.models.RecruiterMatch;
import app.leo.matching.models.UserMatch;
import app.leo.matching.services.ApplicantMatchService;
import app.leo.matching.services.PeriodCheckService;
import app.leo.matching.services.PositionService;
import app.leo.matching.services.RecruiterMatchService;


@RestController
public class JoinMatchController {
    @Autowired
    private ApplicantMatchService applicantMatchService;

    @Autowired
    private RecruiterMatchService recruiterMatchService;

    @Autowired
    private PeriodCheckService periodCheckService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private MatchManagementAdapter matchManagementAdapter;

    @PostMapping("/matches/{matchId}/applicants/join")
    public ResponseEntity<ApplicantMatch> joinMatch(@PathVariable long matchId,
                                                    @RequestAttribute(name ="user") User user,
                                                    @RequestAttribute(name ="token") String token){
        //periodCheckService.todayIsJoiningPeriod(token,matchId);
        if(user.getRole().equals("applicant")) {
            ApplicantMatch existedApplicantMatch = applicantMatchService.getApplicantMatchByApplicantIdAndMatchId(user.getProfileId(), matchId);
            if (existedApplicantMatch != null) {
                throw new AlreadyJoinedException("You already joined this match");
            }
            ApplicantMatch applicantMatch = new ApplicantMatch(user.getProfileId(), matchId);
            applicantMatch.setJoinDate(new Timestamp(getTimeNowInMilliSecond()));
            applicantMatch = applicantMatchService.saveApplicantMatch(applicantMatch);
            matchManagementAdapter.updateNumberOfUserInMatch(token,matchId);
            return new ResponseEntity<>(applicantMatch, HttpStatus.ACCEPTED);
        }else{
            throw new WrongRoleException("You are not applicant");
        }
    }

    @PostMapping("/matches/{matchId}/recruiters/join")
    public ResponseEntity<RecruiterMatch> joinMatch(@PathVariable long matchId, @RequestBody @Valid RecruiterJoinMatchRequest recruiterJoinMatchRequest,
                                                    @RequestAttribute("user")User user,
                                                    @RequestAttribute("token") String token){
        //periodCheckService.todayIsJoiningPeriod(token,matchId);
        if(user.getRole().equals("recruiter")) {
            RecruiterMatch recruiterMatch = new RecruiterMatch();
            recruiterMatch.setMatchId(matchId);
            recruiterMatch.setRecruiterId(user.getProfileId());
            recruiterMatch.setJoinDate(new Timestamp(getTimeNowInMilliSecond()));
            recruiterMatch = recruiterMatchService.saveRecruiterMatch(recruiterMatch);
            List<Position> positionList = convertToPosition(recruiterJoinMatchRequest.getPositions(), matchId,recruiterMatch);
            recruiterMatch.setPositions(positionList);
            recruiterMatch = recruiterMatchService.saveRecruiterMatch(recruiterMatch);
            matchManagementAdapter.updateNumberOfUserInMatch(token,matchId);
            return new ResponseEntity<>(recruiterMatch, HttpStatus.ACCEPTED);
        }else{
            throw new WrongRoleException("You are not recruiter");
        }
    }

    private long getTimeNowInMilliSecond(){
        return Calendar.getInstance(TimeZone.getTimeZone("GMT+0")).getTimeInMillis();
    }
    private List<Position> convertToPosition(List<PositionDTO> positionList,long matchId,RecruiterMatch recruiterMatch) {
        List<Position> result = new ArrayList<>();
        for(PositionDTO positionDTO:positionList){
            Position position = new Position(positionDTO.getName(),positionDTO.getCapacity(),matchId,positionDTO.getDescription(),positionDTO.getMoney(),positionDTO.getDocuments());
            position.setRecruiterMatch(recruiterMatch);
            position = positionService.savePosition(position);
            result.add(position);
        }
        return result;
    }

    @GetMapping("/matches/{matchId}/isJoin")
    public ResponseEntity<CheckJoinedResponse> userIsJoinedTheMatch(@RequestAttribute("user") User user,
                                                                    @PathVariable long matchId){
        UserMatch userMatch = null;
        if(user.getRole().equals("applicant")){
            userMatch= applicantMatchService.getApplicantMatchByApplicantIdAndMatchId(user.getProfileId(),matchId);
        }if(user.getRole().equals("recruiter")){
            userMatch = recruiterMatchService.getRecruiterMatchByRecruiterIdAndMatchId(user.getProfileId(),matchId);
        }
        if(userMatch != null){
            return new ResponseEntity<>(new CheckJoinedResponse(true),HttpStatus.OK);
        }

        return new ResponseEntity<>(new CheckJoinedResponse(false),HttpStatus.OK);
    }
}
