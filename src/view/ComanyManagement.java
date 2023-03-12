
package view;
import controller.EmployeeManagement;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ComanyManagement extends Menu<String>{
    static String[] menu ={"add employees", "show all employees","sort student", "write into the file", "load the file","exit the program"};
    EmployeeManagement em = new EmployeeManagement();
    public ComanyManagement() {
        super("Company management", menu);
    }
    @Override
    public void execuse(int n) {
        switch(n){
            case 1:em.addEmployee();break;
            case 2:em.displayAllEmplyees();break;
            case 3:String[] menu2 = {"sort by income and and account","sort by role and ID","exit sorting"}; Menu mn = new Menu("Sort student", menu2) {
                        @Override
                        public void execuse(int n) {
                            switch(n){
                                case 1:em.displayEmployeesByMonthlyIncomeAndAccount(); break;
                                case 2:em.displayEmployeesByRoleAndEmployID(); break;}
                        }  
                    };mn.run();break;
            case 4:{
                try {
                    em.pwfile();
                } catch (IOException ex) {
                    Logger.getLogger(ComanyManagement.class.getName()).log(Level.SEVERE, null, ex);
                }}break;
            case 5:em.readfile();break;       
        }
    }
    
}

