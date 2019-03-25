package app.leo.matching.services;

import app.leo.matching.models.Match;
import app.leo.matching.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match getMatchByMatchId(long id) {
        return this.matchRepository.getMatchByMatchId(id);
    }
}
