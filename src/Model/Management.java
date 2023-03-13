package Model;

/**
 *
 * @author Luu Thanh Dat
 */
public class Management extends Information{

    private int resolveIssueNumber;
    private int otherTaskNumber;
    
    public Management() {
    }
    public Management(int role, String id, String accountEmployee, String workStartingDate, float productivityScore, double monthlyInCome, double rewardSalary, double allowance,int resolveIssueNumber, int otherTaskNumber) {
        super(role, id, accountEmployee, workStartingDate, productivityScore, monthlyInCome, rewardSalary, allowance);
        this.resolveIssueNumber = resolveIssueNumber;
        this.otherTaskNumber = otherTaskNumber;
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
    @Override
    public String toString() {
        return super.toString() + ", Resolve issue number: " + resolveIssueNumber + 
                ", Other task number: " + otherTaskNumber;
    }
}