package app.leo.matching.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class DocumentPosition {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.EAGER , optional = false)
    @JoinColumn(name = "position_id")
    private Position position;

    private long userId;

    @ElementCollection
    private List<Long> filesId;

    public DocumentPosition() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<Long> getFilesId() {
        return filesId;
    }

    public void setFilesId(List<Long> filesId) {
        this.filesId = filesId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
