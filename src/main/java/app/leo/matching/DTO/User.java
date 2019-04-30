package app.leo.matching.DTO;

public class User {
    private long id;
    private String role;

    public User() {
    }

    public User(long userId, String role) {
        this.id = userId;
        this.role = role;
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
}
