package app.leo.matching.DTO;

public class User {
    private long id;
    private long profileId;
    private String role;

    public User() {
    }

    public User(long userId, String role, long profileId) {
        this.id = userId;
        this.role = role;
        this.profileId = profileId;
    }

    public long getUserId() {
        return id;
    }

    public void setUserId(long userId) {
        this.id = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }
}
