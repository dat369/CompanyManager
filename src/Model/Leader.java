package Model;

import controller.Allowance;

/**
 *
 * @author Luu Thanh Dat
 */
public class Leader extends Information implements Allowance {

    private int reviewTaskNumber;
    private int supportTaskNumber;
    private double allowance;

    public Leader() {
    }

    public Leader( int role, String id, String accountEmployee, String workStartingDate, float productivityScore, double monthlyInCome, double rewardSalary,int reviewTaskNumber, int supportTaskNumber) {
        super(role, id, accountEmployee, workStartingDate, productivityScore, monthlyInCome, rewardSalary);
        this.reviewTaskNumber = reviewTaskNumber;
        this.supportTaskNumber = supportTaskNumber;
    }

    public int getReviewTaskNumber() {
        return reviewTaskNumber;
    }

    public void setReviewTaskNumber(int reviewTaskNumber) {
        this.reviewTaskNumber = reviewTaskNumber;
    }

    public int getSupportTaskNumber() {
        return supportTaskNumber;
    }

    public void setSupportTaskNumber(int supportTaskNumber) {
        this.supportTaskNumber = supportTaskNumber;
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
        double monthInCome = (reviewTaskNumber * 4000000) + (supportTaskNumber * 400000) + getRewardSalary() + calAllowance();
        return monthInCome;
    }
}