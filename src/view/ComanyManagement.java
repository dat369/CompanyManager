package view;
/**
*
* @author Le Duc Manh
*/
import java.io.IOException;
import java.util.Scanner;
import controller.EmployeeManagement;


public class ComanyManagement extends Menu<String>{
    Scanner sc = new Scanner(System.in);
    static String[] menu ={"Add employees","","Update information","Sort employee", "Write into the file", "Load the file","Print all employees","Exit the program"};
    EmployeeManagement em = new EmployeeManagement();
    public ComanyManagement() {
        super("Company management", menu);
    }
    @Override
    public void execuse(int n) {
        switch(n){
            case 1:
            	try {
            		em.addEmployee();
            	} catch (IOException e) {
            		// TODO Auto-generated catch block
            		e.printStackTrace();
            	}
            	break;
            case 2: em.updateInformation();break;
            case 3: String[] menu3 = {"Sort by income and and account","Sort by role and ID","Exit sorting"}; 
            			 Menu mn = new Menu("Sort employees", menu3){
                        @Override
                        public void execuse(int n) {
                            switch(n){
                                case 1:em.displayEmployeesByMonthlyIncomeAndAccount(); break;
                                case 2:em.displayEmployeesByRoleAndEmployID(); break;}
                        }  
                    };mn.run();break;
            case 4:{
                try {
                    em.pwFile();
                } catch (IOException ex) {
                    System.out.println("Undetected errors occur!");
                }}break;
            case 5:
            	em.readFile();
            	break;
            case 6:
            	em.displayAllEmplyees();
            	break;
            case 7:em.getWorkingMonth("13/06/2017");
        }
    }
    
}
