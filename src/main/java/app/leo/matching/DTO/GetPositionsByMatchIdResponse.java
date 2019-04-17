package app.leo.matching.DTO;

import app.leo.matching.models.Position;

import java.util.List;

public class GetPositionsByMatchIdResponse {
    private long id;
    private String name;
    private String money;
    private int capacity;
    private String recruiterName;
    private String recruiterLocation;

    public GetPositionsByMatchIdResponse() {
    }

    public GetPositionsByMatchIdResponse(long id, String name, String money, int capacity) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.capacity = capacity;
    }

    public GetPositionsByMatchIdResponse(long id, String name, String money, int capacity, String recruiterName, String recruiterLocation) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.capacity = capacity;
        this.recruiterName = recruiterName;
        this.recruiterLocation = recruiterLocation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getRecruiterName() {
        return recruiterName;
    }

    public void setRecruiterName(String recruiterName) {
        this.recruiterName = recruiterName;
    }

    public String getRecruiterLocation() {
        return recruiterLocation;
    }

    public void setRecruiterLocation(String recruiterLocation) {
        this.recruiterLocation = recruiterLocation;
    }
}
