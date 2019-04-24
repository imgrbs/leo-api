package app.leo.matching.DTO;

public class User {
    private long userId;
    private String role;

    public User() {
    }

    public User(long userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
