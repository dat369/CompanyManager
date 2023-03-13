package view;
import java.io.IOException;
import java.util.Scanner;


import Controller.EmployeeManagement;
public class ComanyManagement extends Menu<String>{
    Scanner sc = new Scanner(System.in);
    static String[] menu ={"Add employees", "Calculate money","Update information","Sort student", "Write into the file", "Load the file","Print all student","Exit the program"};
    EmployeeManagement em = new EmployeeManagement();
    public ComanyManagement() {
        super("Company management", menu);
    }
    @Override
    public void execuse(int n) {
        switch(n){
            case 1:em.addEmployee();break;
            case 2:String id = sc.nextLine();em.updateinformation(id);break;
            case 3:String[] menu3 = {"sort by income and and account","sort by role and ID","exit sorting"}; 
            			@SuppressWarnings("rawtypes") Menu mn = new Menu("Sort student", menu3){
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
                    System.out.println("Undetected errors occur!");
                }}break;
            case 5:em.readfile();
            case 6:em.displayAllEmplyees();
        }
    }
    
}
