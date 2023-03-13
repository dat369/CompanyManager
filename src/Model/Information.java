package Model;


/**
 *
 * @author Phan Trinh Tien Dat
 */
public abstract class Information {
    private int role;
    private String id;
    private String accountEmployee;
    private String workStartingDate;
    private float productivityScore;
    private double monthlyInCome;
    private double rewardSalary;
    private double allowance;

    public double getAllowance() {
        return allowance;
    }
    public void setAllowance(double allowance) {
        this.allowance = allowance;
    } 
    public Information() {
    }
    public Information(int role, String id, String accountEmployee, String workStartingDate, float productivityScore, double monthlyInCome, double rewardSalary, double allowance) {
        this.role = role;
        this.id = id;
        this.accountEmployee = accountEmployee;
        this.workStartingDate = workStartingDate;
        this.productivityScore = productivityScore;
        this.monthlyInCome = monthlyInCome;
        this.rewardSalary = rewardSalary;
        this.allowance = allowance;
    }
    public int getRole() {
        return role;
    }
    public void setRole(int role) {
        this.role = role;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAccountEmployee() {
        return accountEmployee;
    }
    public void setAccountEmployee(String accountEmployee) {
        this.accountEmployee = accountEmployee;
    }
    public String getWorkStartingDate() {
        return workStartingDate;
    }
    public void setWorkStartingDate(String workStartingDate) {
        this.workStartingDate = workStartingDate;
    }
    public float getProductivityScore() {
        return productivityScore;
    }
    public void setProductivityScore(float productivityScore) {
        this.productivityScore = productivityScore;
    }
    public double getMonthlyInCome() {
        return monthlyInCome;
    }
    public void setMonthlyInCome(double monthlyInCome) {
        this.monthlyInCome = monthlyInCome;
    }
    public double getRewardSalary() {
        return rewardSalary;
    }
    public void setRewardSalary(double rewardSalary) {
        this.rewardSalary = rewardSalary;
    }  
    @Override
    public String toString() {
        return "Id: " + id + ", Role: " + role + 
                ", Account employee: " + accountEmployee + 
                ", Work starting date: " + workStartingDate + 
                ", Productivity score: " + productivityScore;
    }
}