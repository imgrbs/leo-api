package app.leo.matching.services;

import app.leo.matching.models.RecruiterMatch;
import app.leo.matching.repositories.RecruiterMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruiterMatchService {

    @Autowired
    private RecruiterMatchRepository recruiterMatchRepository;

    public List<RecruiterMatch> getRecruiterMatchByRecruiterId(long recruiterId){
        return recruiterMatchRepository.getRecruiterMatchesByRecruiterId(recruiterId);
    }

    public RecruiterMatch getRecruiterMatchByRecruiterIdAndMatchId(long recruiterId, long matchId){
        return recruiterMatchRepository.getRecruiterMatchByRecruiterIdAndMatchId(recruiterId, matchId);
    }
}
