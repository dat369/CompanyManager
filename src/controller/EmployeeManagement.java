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
                    	double Allowance=  2000000 * management.getProductivityScore();
                        management.setAllowance(Allowance);
                    } else {
                    	double Allowance= 1200000 * management.getProductivityScore();
                        management.setAllowance(Allowance);
                    }
            }else if(obj instanceof Leader){
                Leader lead = (Leader) obj;
                if (getWorkingMonth(lead.getWorkStartingDate())>= 36) {
                	double Allowance= 2000000 * lead.getProductivityScore();
                    lead.setAllowance(Allowance);
                } else {
                	double Allowance=1200000 * lead.getProductivityScore();
                    lead.setAllowance(Allowance);
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
    	double RewardSalary=obj.getProductivityScore() * 3000000;
        obj.setRewardSalary(RewardSalary);
    }
    public void updateinformation(String id){
    	int count = 0;
        for (Information employee : employees) {
            if(employee.getId().equals(id)){
            	count++;
	        	calRewardSalary(employee);
	        	calAllowance(employee);
	        	calMonthlyIncome(employee);
                System.out.println("MonthlyInCome: "+employee.getMonthlyInCome());
                System.out.println("RewardSalary: "+employee.getRewardSalary());
                System.out.println("Allowance: "+employee.getAllowance());
                break;
            }
        }
        if(count ==0) {
        	System.out.println("the employees don't exist");
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
    public void addEmployee() throws IOException {
        System.out.print("input Role: ");
		int role= sc.nextInt();
		sc.nextLine();
		System.out.print("input id: ");
		String id= sc.nextLine();
		
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
		        	Information infor =new Management(role, id, accountEmployee, workStartingDate, productivityScore, resolveIssueNumber, otherTaskNumber);
		        	employees.add(infor);
		            break;
		        case 2:
		        	System.out.print("input reviewTaskNumber: ");
		        	int reviewTaskNumber= sc.nextInt();
		        	System.out.print("input supportTaskNumber: ");
		        	int supportTaskNumber= sc.nextInt();
		        	Information ld =new Leader(role, id, accountEmployee, workStartingDate, productivityScore,reviewTaskNumber, supportTaskNumber);
		        	employees.add(ld);
		            break;
		        case 3:
		        	System.out.print("input doneTaskNumber: ");
		        	int doneTaskNumber= sc.nextInt();
		        	Information dev =new Dev(role, id, accountEmployee, workStartingDate, productivityScore, doneTaskNumber);
		        	employees.add(dev);
		            break;
		    }
		}
		if (employees.size() !=0) {
		    System.out.println("Employee added successfully!");
		} else {
		    System.out.println("Can not add Employee! Please try again!");
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
        

        try {
            File file = new File("C:\\Users\\ACER\\eclipse-workspace\\AssignmentPRO\\PRO.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            for (Information employee : employees) {
                if (employee instanceof Management) {
                    Management mn = (Management) employee;
                    writer.write(mn.getRole() + "," + mn.getId() + "," + mn.getAccountEmployee() + "," + mn.getWorkStartingDate() +  "," + mn.getResolveIssueNumber() + "," + mn.getOtherTaskNumber());
                    writer.newLine();
                }
                if (employee instanceof Leader) {
                    Leader ld = (Leader) employee;
                    writer.write(ld.getRole() + "," + ld.getId() + "," + ld.getAccountEmployee() + "," + ld.getWorkStartingDate() + "," + ld.getProductivityScore() + "," + ld.getSupportTaskNumber());
                    writer.newLine();
                }
                if (employee instanceof Dev) {
                    Dev de = (Dev) employee;
                    writer.write(de.getRole() + "," + de.getId() + "," + de.getAccountEmployee() + "," + de.getWorkStartingDate() + "," + de.getProductivityScore() + "," + de.getDoneTaskNumber());
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
            File tenfile = new File("C:\\Users\\ACER\\eclipse-workspace\\AssignmentPRO\\PRO.txt");
            BufferedReader br = new BufferedReader(new FileReader(tenfile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] employee = line.split(",");

                if (employee[0].equals("1")) {
                    Information nv = new Management(Integer.parseInt(employee[0]), employee[1], employee[2], employee[3], Float.parseFloat(employee[4]),  Integer.parseInt(employee[5]), Integer.parseInt(employee[6]));
                    employees.add(nv);
                }
                if (employee[0].equals("2")) {

                    Information nv = new Leader(Integer.parseInt(employee[0]), employee[1], employee[2], employee[3], Float.parseFloat(employee[4]), Integer.parseInt(employee[5]), Integer.parseInt(employee[6]));
                    employees.add(nv);
                }
                if (employee[0].equals("3")) {

                    Information nv = new Dev(Integer.parseInt(employee[0]), employee[1], employee[2], employee[3], Float.parseFloat(employee[4]),  Integer.parseInt(employee[5]));
                    employees.add(nv);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
