package app.leo.matching.DTO;

import app.leo.matching.models.Position;
import app.leo.matching.models.RecruiterMatch;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PositionDTO extends Position {

    public PositionDTO() {
    }

    public PositionDTO(@NotBlank String name, @NotNull int capacity, RecruiterMatch recruiterMatch) {
        super(name, capacity, recruiterMatch);
    }

    public PositionDTO(@NotBlank String name, @NotNull int capacity, long matchId, String money) {
        super(name, capacity, matchId, money);
    }

    public PositionDTO(@NotBlank String name, @NotNull int capacity, long matchId, String description, String money) {
        super(name, capacity, matchId, description, money);
    }
}
