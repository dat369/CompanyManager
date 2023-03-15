package model;

/**
 *
 * @author Luu Thanh Dat
 */
public class Leader extends Information{

    private int reviewTaskNumber;
    private int supportTaskNumber;

    public Leader( int role, String id, String accountEmployee, String workStartingDate, float productivityScore,int reviewTaskNumber, int supportTaskNumber) {
        super(role, id, accountEmployee, workStartingDate, productivityScore);
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
    
    @Override
    public String toString() {
        return super.toString() + ", Review task number: " + reviewTaskNumber + 
                ", Support task number: " + supportTaskNumber;
    }
}