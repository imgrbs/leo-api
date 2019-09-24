package app.leo.matching.controllers;

import app.leo.matching.DTO.PositionDTO;
import app.leo.matching.DTO.RecruiterJoinMatchRequest;
import app.leo.matching.DTO.User;
import app.leo.matching.exceptions.AlreadyJoinedException;
import app.leo.matching.exceptions.WrongRoleException;
import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.Position;
import app.leo.matching.models.RecruiterMatch;
import app.leo.matching.services.ApplicantMatchService;
import app.leo.matching.services.PeriodCheckService;
import app.leo.matching.services.PositionService;
import app.leo.matching.services.RecruiterMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Controller
public class JoinMatchController {
    @Autowired
    private ApplicantMatchService applicantMatchService;

    @Autowired
    private RecruiterMatchService recruiterMatchService;

    @Autowired
    private PeriodCheckService periodCheckService;

    @Autowired
    private PositionService positionService;
    @PostMapping("/matches/{matchId}/applicants/join")
    public ResponseEntity<ApplicantMatch> joinMatch(@PathVariable long matchId,
                                                    @RequestAttribute(name ="user") User user,
                                                    @RequestAttribute(name ="token") String token){
        periodCheckService.todayIsJoiningPeriod(token,matchId);
        if(user.getRole().equals("applicant")) {
            ApplicantMatch existedApplicantMatch = applicantMatchService.getApplicantMatchByApplicantIdAndMatchId(user.getUserId(), matchId);
            if (existedApplicantMatch != null) {
                throw new AlreadyJoinedException("You already joined this match");
            }
            ApplicantMatch applicantMatch = new ApplicantMatch(user.getUserId(), matchId);
            applicantMatch.setJoinDate(new Timestamp(System.currentTimeMillis()));
            return new ResponseEntity<>(applicantMatchService.saveApplicantMatch(applicantMatch), HttpStatus.ACCEPTED);
        }else{
            throw new WrongRoleException("You are not applicant");
        }
    }

    @PostMapping("/matches/{matchId}/recruiters/join")
    public ResponseEntity<RecruiterMatch> joinMatch(@PathVariable long matchId, @RequestBody @Valid RecruiterJoinMatchRequest recruiterJoinMatchRequest,
                                                    @RequestAttribute("user")User user,
                                                    @RequestAttribute("token") String token){
        periodCheckService.todayIsJoiningPeriod(token,matchId);
        if(user.getRole().equals("recruiter")) {
            RecruiterMatch recruiterMatch = new RecruiterMatch();
            recruiterMatch.setMatchId(matchId);
            recruiterMatch.setRecruiterId(user.getUserId());
            recruiterMatch.setJoinDate(new Timestamp(System.currentTimeMillis()));
            recruiterMatch = recruiterMatchService.saveRecruiterMatch(recruiterMatch);
            List<Position> positionList = convertToPosition(recruiterJoinMatchRequest.getPositions(), matchId,recruiterMatch);
            recruiterMatch.setPositions(positionList);
            recruiterMatch = recruiterMatchService.saveRecruiterMatch(recruiterMatch);
            return new ResponseEntity<>(recruiterMatch, HttpStatus.ACCEPTED);
        }else{
            throw new WrongRoleException("You are not recruiter");
        }
    }

    private List<Position> convertToPosition(List<PositionDTO> positionList,long matchId,RecruiterMatch recruiterMatch) {
        List<Position> result = new ArrayList<>();
        for(PositionDTO positionDTO:positionList){
            Position position = new Position(positionDTO.getName(),positionDTO.getCapacity(),matchId,positionDTO.getMoney(),positionDTO.getDescription());
            position.setRecruiterMatch(recruiterMatch);
            position = positionService.savePosition(position);
            result.add(position);
        }
        return result;
    }

    private static class PositionDTOList extends ArrayList<PositionDTO> {
    }
}
