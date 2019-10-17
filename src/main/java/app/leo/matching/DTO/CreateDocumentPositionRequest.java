package app.leo.matching.DTO;

import java.util.List;

public class CreateDocumentPositionRequest {

    private long rankingId;
    private long matchId;
    private List<FilesDTO> files;
    private PositionDTO position;

    public long getRankingId() {
        return rankingId;
    }

    public void setRankingId(long rankingId) {
        this.rankingId = rankingId;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public List<FilesDTO> getFiles() {
        return files;
    }

    public void setFiles(List<FilesDTO> files) {
        this.files = files;
    }

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
        this.position = position;
    }
}
