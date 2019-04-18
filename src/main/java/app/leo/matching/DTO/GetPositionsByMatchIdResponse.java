package app.leo.matching.DTO;



public class GetPositionsByMatchIdResponse {
    private long id;
    private String name;
    private String money;
    private int capacity;
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
}
