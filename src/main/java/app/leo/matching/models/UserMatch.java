package app.leo.matching.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@MappedSuperclass
public class UserMatch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long participantId;

    @NotNull
    private long matchId;

    public UserMatch() {
    }

    public UserMatch(@NotNull long matchId) {
        this.matchId = matchId;
    }

    public UserMatch(long participantId,@NotNull long matchId) {
        this.participantId = participantId;
        this.matchId = matchId;
    }


    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }
}
