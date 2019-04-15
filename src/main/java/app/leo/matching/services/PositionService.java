package app.leo.matching.services;

import app.leo.matching.models.Position;
import app.leo.matching.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;

    public PositionService() {
    }

    public PositionService(PositionRepository positionRepository){
        this.positionRepository = positionRepository;
    }

    public List<Position> getPositionByMatchId(long matchId){
       return positionRepository.getPositionByMatchId(matchId);
    }

    public List<Position> getPositionByMatchIdAndRecruiterId(long recruiterId,long matchId){
        return positionRepository.getPositionsByRecruiterMatchIdAndMatchId(recruiterId,matchId);
    }
}
