package app.leo.matching.services;


import app.leo.matching.models.ApplicantMatch;
import app.leo.matching.models.RecruiterMatch;
import app.leo.matching.repositories.ApplicantMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantMatchService {

    @Autowired
    private ApplicantMatchRepository applicantMatchRepository;

    public ApplicantMatchService() {
    }

    public ApplicantMatchService(ApplicantMatchRepository applicantMatchRepository) {
        this.applicantMatchRepository =applicantMatchRepository;
    }

    public List<ApplicantMatch> getApplicantMatchesByMatchIdandRecruiterMatchId(long matchId, RecruiterMatch recruiterMatch){
        return applicantMatchRepository.getApplicantMatchByMatchIdandRecruiterId(matchId,recruiterMatch.getId());
    }

    public ApplicantMatch getApplicantMatchByApplicantId(long id){
        return applicantMatchRepository.getApplicantMatchById(id);
    }
}
