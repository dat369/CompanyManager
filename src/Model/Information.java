package Model;

import java.time.LocalDate;

/**
 *
 * @author Phan Trinh Tien Dat
 */
public abstract class Information {

    private String id;
    private int role;
    private String accountEmployee;
    private String workStartingDate;
    private float productivityScore;
    private double monthlyInCome;
    private double rewardSalary;

    public Information() {
    }

    public Information(int role, String id, String accountEmployee, String workStartingDate, float productivityScore, double monthlyInCome, double rewardSalary) {
        this.role = role;
        this.id = id;
        this.accountEmployee = accountEmployee;
        this.workStartingDate = workStartingDate;
        this.productivityScore = productivityScore;
        this.monthlyInCome = monthlyInCome;
        this.rewardSalary = rewardSalary;
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
        return calReward();
    }

    public void setRewardSalary(double rewardSalary) {
        this.rewardSalary = rewardSalary;
    }

    public double calReward() {
        return this.productivityScore * 3000000;
    }

    public abstract double calMonthlyInCome();

    public int getTotalMonthWorked() {
        String[] dateSplits = workStartingDate.split("/");
        int monthStartWorking = Integer.parseInt(dateSplits[1]);
        int yearStarWorking = Integer.parseInt(dateSplits[2]);

        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        int monthDiff = currentMonth - monthStartWorking;
        int yearDiff = currentYear - yearStarWorking;
        int totalMonthWorked = yearDiff * 12 + monthDiff;
        return totalMonthWorked;
    }
}
