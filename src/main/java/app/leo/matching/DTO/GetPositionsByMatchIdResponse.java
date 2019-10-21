package app.leo.matching.DTO;


import java.util.List;

public class GetPositionsByMatchIdResponse {
    private long id;
    private String name;
    private String money;
    private int capacity;
    private List<String> documents;
    private String description;
    private Recruiter recruiter;

    public GetPositionsByMatchIdResponse() {
    }

    public GetPositionsByMatchIdResponse(long id, String name, String money, int capacity) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.capacity = capacity;
    }

    public GetPositionsByMatchIdResponse(long id, String name, String money, int capacity,Recruiter recruiter) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.capacity = capacity;
        this.recruiter = recruiter;
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

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
