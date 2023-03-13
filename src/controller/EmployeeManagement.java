package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import Model.Dev;
import Model.Information;
import Model.Leader;
import Model.Management;
import java.time.LocalDate;


public class EmployeeManagement implements Calculate{

    Scanner sc = new Scanner(System.in);
    List<Information> employees = new ArrayList<>();

    public EmployeeManagement() {
        this.employees = new ArrayList<>();
    }   
    public int getWorkingMonth(String workday) {
        String[] dateSplits =workday.split("/");
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
    public int getWorkingHour(String workday){
        return getWorkingMonth(workday) * 160;
    }
    @Override
    public void calAllowance(Information obj) { 
            if(obj instanceof Management){
                    Management management= (Management) obj;
                    if (getWorkingMonth(management.getWorkStartingDate())>= 36) {
                        management.setAllowance( 2000000 * management.getProductivityScore());
                    } else {
                        management.setAllowance(1200000 * management.getProductivityScore());
                    }
            }else if(obj instanceof Leader){
                Leader lead = (Leader) obj;
                if (getWorkingMonth(lead.getWorkStartingDate())>= 36) {
                    lead.setAllowance( 2000000 * lead.getProductivityScore());
                } else {
                    lead.setAllowance(1200000 * lead.getProductivityScore());
                }
            }else{
                System.out.println("there is no employee");
            }
    }
    @Override
    public void calMonthlyIncome(Information obj) {
        if(obj instanceof Management){
            Management management= (Management) obj;
            double MonthlyIncome= (management.getResolveIssueNumber()*5000000+management.getOtherTaskNumber()*5000000+management.getRewardSalary()+ management.getAllowance());
            management.setMonthlyInCome(MonthlyIncome);
        }if(obj instanceof Leader){
            Leader leader= (Leader) obj;
            double MonthlyIncome= (leader.getReviewTaskNumber()*4000000+leader.getSupportTaskNumber()*400000+leader.getRewardSalary()+ leader.getAllowance());
            leader.setMonthlyInCome(MonthlyIncome);
        }if(obj instanceof Dev){
            Dev dev= (Dev) obj;           
            double MonthlyIncome = getWorkingHour(dev.getWorkStartingDate())*1500000 + dev.getRewardSalary() + dev.getAllowance() ;
            dev.setMonthlyInCome(MonthlyIncome);
        }
    }
    public void calRewardSalary(Information obj){
        obj.setRewardSalary(obj.getProductivityScore() * 3000000);
    }
    public void updateinformation(String id){
        for (Information employee : employees) {
            if(employee.getId().equals(id)){
                calAllowance(employee);
                calRewardSalary(employee);
                calAllowance(employee);
                System.out.println(employee.getMonthlyInCome());
                System.out.println(employee.getRewardSalary());
                System.out.println(employee.getAllowance());
            }else{
                System.out.println("the employees don't exist");
            }
        }
    }    
    public int check(int role, String id, String accountEmployee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(id)) {
                System.out.println("ID already exists!");
                return 1;
            }
        }
        if (!id.matches("^MNV\\d{3}$")) { 
        	 System.out.println("Invalid Employ ID! Please try again!");
             return 1;
        }else if (role <= 0 || role >=4 || id == null || accountEmployee.isEmpty()) {
            System.out.println("Role and Account can not be null or empty! Please try again!");
            return 1;
        }else return 0;
    }
    public void addEmployee() {
        try {
	    	System.out.print("input Role: ");
	    	int role= sc.nextInt();
	    	System.out.print("input id: ");
	    	String id= sc.nextLine();
	    	sc.nextLine();
	    	System.out.print("input accountEmployee: ");
	    	String accountEmployee= sc.nextLine();
	    	
	    	if(check(role, id,accountEmployee)== 0) {
		    	System.out.print("input workStartingDate: ");
		    	String workStartingDate= sc.nextLine();
		    	System.out.print("input productivityScore: ");
		    	float productivityScore= sc.nextFloat();
		        
		        switch (role) {
		            case 1:
		            	System.out.print("input resolveIssueNumber: ");
		            	int resolveIssueNumber= sc.nextInt();
		            	System.out.print("input otherTaskNumber: ");
		            	int otherTaskNumber= sc.nextInt();
                                employees.add(new Management(role, id, accountEmployee, workStartingDate, productivityScore,employees.get(employees.size()).getMonthlyInCome(),employees.get(employees.size()).getRewardSalary(),employees.get(employees.size()).getMonthlyInCome(), resolveIssueNumber, otherTaskNumber));
		                break;
		            case 2:
		            	System.out.print("input reviewTaskNumber: ");
		            	int reviewTaskNumber= sc.nextInt();
		            	System.out.print("input supportTaskNumber: ");
		            	int supportTaskNumber= sc.nextInt();
                                employees.add(new Leader(role, id, accountEmployee, workStartingDate, productivityScore, employees.get(employees.size()).getMonthlyInCome(),employees.get(employees.size()).getRewardSalary(),employees.get(employees.size()).getMonthlyInCome(), reviewTaskNumber, supportTaskNumber));
		                break;
		            case 3:
		            	System.out.print("input doneTaskNumber: ");
		            	int doneTaskNumber= sc.nextInt();
                                employees.add(new Dev(role, id, accountEmployee, workStartingDate, productivityScore,employees.get(employees.size()).getMonthlyInCome(),employees.get(employees.size()).getRewardSalary(),employees.get(employees.size()).getMonthlyInCome(), doneTaskNumber));
		                break;
		        }
	        }
	        if (employees.size() !=0) {
	            System.out.println("Employee added successfully!");
	        } else {
	            System.out.println("Can not add Employee! Please try again!");
	        }
        } catch (Exception e) {
			// TODO: handle exception
        	System.out.println("you entered the wrong data!");
		}
    }
    public void displayAllEmplyees() {
        if (employees.size() == 0) {
            System.out.println("List is empty");
        } else {
            for (int i = 0; i < employees.size(); i++) {
                System.out.println(employees.get(i).toString());

            }
        }

    }   
    public void displayEmployeesByMonthlyIncomeAndAccount() {
        Collections.sort(employees, (o1, o2) -> {
            if (o1.getMonthlyInCome() == o2.getMonthlyInCome()) {
                return o1.getAccountEmployee().compareTo(o2.getAccountEmployee());
            } else {
                return Double.compare(o1.getMonthlyInCome(), o2.getMonthlyInCome());
            }
        });
        System.out.println("List of Employees sorted by Account:");
        for (Information e : employees) {
            System.out.println(e);
        }
    }
    public void displayEmployeesByRoleAndEmployID() {
        Collections.sort(employees, new Comparator<Information>() {
            @Override
            public int compare(Information o1, Information o2) {
                if (o1.getRole() == o2.getRole()) {
                    return o1.getId().compareTo(o2.getId());
                } else {
                    return Integer.compare(o1.getRole(), o2.getRole());
                }
            }
        });
        System.out.println("List of Employees sorted by Account:");
        for (Information e : employees) {
            System.out.println(e);
        }
    }
    public void pwfile() throws IOException {
        System.out.print("nhap ten file: ");
        String namefile = sc.nextLine();

        try {
            File file = new File(namefile);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            for (Information employee : employees) {
                if (employee instanceof Management) {
                    Management mn = (Management) employee;
                    writer.write(mn.getRole() + "," + mn.getId() + "," + mn.getAccountEmployee() + "," + mn.getWorkStartingDate() + "," + mn.getProductivityScore() + "," + mn.getMonthlyInCome() + "," + mn.getRewardSalary() + "," + mn.getResolveIssueNumber() + "," + mn.getOtherTaskNumber());
                    writer.newLine();
                }
                if (employee instanceof Leader) {
                    Leader ld = (Leader) employee;
                    writer.write(ld.getRole() + "," + ld.getId() + "," + ld.getAccountEmployee() + "," + ld.getWorkStartingDate() + "," + ld.getProductivityScore() + "," + ld.getMonthlyInCome() + "," + ld.getRewardSalary() + "," + ld.getReviewTaskNumber() + "," + ld.getSupportTaskNumber());
                    writer.newLine();
                }
                if (employee instanceof Dev) {
                    Dev de = (Dev) employee;
                    writer.write(de.getRole() + "," + de.getId() + "," + de.getAccountEmployee() + "," + de.getWorkStartingDate() + "," + de.getProductivityScore() + "," + de.getMonthlyInCome() + "," + de.getRewardSalary() + "," + de.getDoneTaskNumber());
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Undetected errors occur!");
        }
    }
    public void readfile() {
        try {
            File tenfile = new File("C:\\Users\\oteee\\Documents\\NetBeansProjects\\workshop\\Assigment\\PRO.txt");
            BufferedReader br = new BufferedReader(new FileReader(tenfile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] employee = line.split(",");

                if (employee[0].equals("1")) {
                    Information nv = new Management(Integer.parseInt(employee[0]), employee[1], employee[2], employee[3], Float.parseFloat(employee[4]), Double.parseDouble(employee[5]), Double.parseDouble(employee[6]),Double.parseDouble(employee[7]), Integer.parseInt(employee[8]), Integer.parseInt(employee[9]));
                    employees.add(nv);
                }
                if (employee[0].equals("2")) {

                    Information nv = new Leader(Integer.parseInt(employee[0]), employee[1], employee[2], employee[3], Float.parseFloat(employee[4]), Double.parseDouble(employee[5]), Double.parseDouble(employee[6]),Double.parseDouble(employee[7]), Integer.parseInt(employee[8]), Integer.parseInt(employee[9]));
                    employees.add(nv);
                }
                if (employee[0].equals("3")) {

                    Information nv = new Dev(Integer.parseInt(employee[0]), employee[1], employee[2], employee[3], Float.parseFloat(employee[4]), Double.parseDouble(employee[5]), Double.parseDouble(employee[6]),Double.parseDouble(employee[7]), Integer.parseInt(employee[8]));
                    employees.add(nv);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
