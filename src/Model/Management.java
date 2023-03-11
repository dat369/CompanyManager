package Model;

import controller.Allowance;

/**
 *
 * @author Luu Thanh Dat
 */
public class Management extends Information implements Allowance {

    private int resolveIssueNumber;
    private int otherTaskNumber;
    private double allowance;

    public Management() {
    }

    public Management(int resolveIssueNumber, int otherTaskNumber, double allowance, String role, String id, String accountEmployee, String workStartingDate, float productivityScore, double monthlyInCome, double rewardSalary) {
        super(role, id, accountEmployee, workStartingDate, productivityScore, monthlyInCome, rewardSalary);
        this.resolveIssueNumber = resolveIssueNumber;
        this.otherTaskNumber = otherTaskNumber;
        this.allowance = allowance;
    }

    public int getResolveIssueNumber() {
        return resolveIssueNumber;
    }

    public void setResolveIssueNumber(int resolveIssueNumber) {
        this.resolveIssueNumber = resolveIssueNumber;
    }

    public int getOtherTaskNumber() {
        return otherTaskNumber;
    }

    public void setOtherTaskNumber(int otherTaskNumber) {
        this.otherTaskNumber = otherTaskNumber;
    }

    public double getAllowance() {
        return calAllowance();
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    @Override
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
        double monthInCome = (resolveIssueNumber * 5000000) + (otherTaskNumber * 500000) + getRewardSalary() + calAllowance();
        return monthInCome;
    }
}