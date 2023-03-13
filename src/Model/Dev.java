package Model;


public class Dev extends Information {
    private int doneTaskNumber;

    public Dev( int role, String id, String accountEmployee, String workStartingDate, float productivityScore, double monthlyInCome, double rewardSalary, double allowance,int doneTaskNumber) {
        super(role, id, accountEmployee, workStartingDate, productivityScore, monthlyInCome, rewardSalary, allowance);
        this.doneTaskNumber = doneTaskNumber;
    }

   
    public Dev() {
    }
    public int getDoneTaskNumber() {
        return doneTaskNumber;
    }
    public void setDoneTaskNumber(int doneTaskNumber) {
        this.doneTaskNumber = doneTaskNumber;
    }
    @Override
    public String toString() {
        return super.toString() + ", Done task number: " + doneTaskNumber;
    }
}