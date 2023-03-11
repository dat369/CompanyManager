package Model;

import controller.Allowance;

public class Dev extends Information implements Allowance {

    private int doneTaskNumber;
    private double allowance;

    public Dev() {
    }

    public Dev( int role, String id, String accountEmployee, String workStartingDate, float productivityScore, double monthlyInCome, double rewardSalary,int doneTaskNumber) {
        super(role, id, accountEmployee, workStartingDate, productivityScore, monthlyInCome, rewardSalary);
        this.doneTaskNumber = doneTaskNumber;
    }

    public float getDoneTaskNumber() {
        return doneTaskNumber;
    }

    public void setDoneTaskNumber(int doneTaskNumber) {
        this.doneTaskNumber = doneTaskNumber;
    }

    public double getAllowance() {
        return calAllowance();
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    private float calWorkingHour() {
        return getTotalMonthWorked() * 160;
    }

    public double calAllowance() {
        int totalMonthWorked = getTotalMonthWorked();

        if (totalMonthWorked >= 36) {
            allowance = 2000000 * getProductivityScore();
        } else {
            allowance = 1200000 * getProductivityScore();
        }
        return allowance;
    }

    @Override
    public double calMonthlyInCome() {
        double monthInCome = (calWorkingHour() * 1500000) + getRewardSalary() + calAllowance();
        return monthInCome;
    }
}
