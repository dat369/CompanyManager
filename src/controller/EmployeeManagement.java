package controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.Dev;
import model.Information;
import model.Leader;
import model.Management;
import java.time.LocalDate;

public class EmployeeManagement implements Calculate{
	int cout=0;
    Scanner sc = new Scanner(System.in);
    List<Information> employees = new ArrayList<>();

    public EmployeeManagement() {this.employees = new ArrayList<>();}   
    
    //Nguyen Quang Tuyen
    public void addEmployee() throws IOException {
    	int newRole;
        String newId;
        String newAccount;
        String newWorkStartingDate;
        float productivityScore;
        boolean codeDuplicated = false;

        do {// pattern MNV000 ==> Pattern: "^MNV\\d{3}$"
        	newRole = Inputter.inputInt("Input Role: ", 1, 3);
        	newId = Inputter.inputPattern("Input ID: ", "^MNV\\\\d{3}$");
            codeDuplicated = isIdDuplicated(newId);
            if(codeDuplicated) System.out.println("Code is duplicated!");
        }while (codeDuplicated == true);
        
        //Check format account has first letter and last letter are uppercase
        newAccount = Inputter.inputPattern("Input Account Employee: ", "^[A-Z].*[A-Z]$");
        //Check format date is correct
        do {
            System.out.print("Input Work Starting Date(day/month/year): ");
            newWorkStartingDate = sc.nextLine().trim();
        }while (isValidDate(newWorkStartingDate) == false);
        
        productivityScore = Inputter.inputFloat("Input productivityScore: ", 0, 1.2f);
        
	    switch (newRole) {
	        case 1:
	        	int resolveIssueNumber;
	        	int otherTaskNumber;
	        	
	        	resolveIssueNumber = Inputter.inputIntOverZero("Input Resolve Issue Number: ");
	        	otherTaskNumber = Inputter.inputIntOverZero("Input Other Task Number: ");
	           
	        	Information infor =new Management(newRole, newId, newAccount, newWorkStartingDate, productivityScore, resolveIssueNumber, otherTaskNumber);
	        	employees.add(infor);
	            break;
	        case 2:
	        	int reviewTaskNumber;
	        	int supportTaskNumber;
	        	
	        	reviewTaskNumber = Inputter.inputIntOverZero("Input Review Task Number: ");
	        	supportTaskNumber = Inputter.inputIntOverZero("Input Support Task Number: ");
	        	
	        	Information leader =new Leader(newRole, newId, newAccount, newWorkStartingDate, productivityScore,reviewTaskNumber, supportTaskNumber);
	        	employees.add(leader);
	            break;
	        case 3:
	        	int doneTaskNumber;
	        	doneTaskNumber = Inputter.inputIntOverZero("Input Done Task Number: ");
	        	
	        	Information dev =new Dev(newRole, newId, newAccount, newWorkStartingDate, productivityScore, doneTaskNumber);
	        	employees.add(dev);
	            break;
	    }
		System.out.println("Employee added successfully!");
    }
    
    private boolean isIdDuplicated(String id){
        id = id.trim().toUpperCase();
        return searchId(id) != null;
    }
    
    // Le Duc Manh
	public void updateInformation(){
        if(employees.isEmpty()) System.out.println("Empty list. No update can be performed!");
         else{
        	 String uId = Inputter.inputStr("Input code of updated employee: ");
        	 try {
        		 
                 Information employee = searchId(uId);
                     //Update Information
                 	calMonthlyIncome(employee);
                     calRewardSalary(employee);
                     calAllowance(employee);
                     // Transform form double to string
                     String convertedMonthlyMoney = String.format("%.0f", employee.getMonthlyInCome());
                     System.out.println("Monthly Income: " + convertedMonthlyMoney);
                     System.out.println("Reward Salary: " + employee.getRewardSalary());
                     System.out.println("Allowance: " + employee.getAllowance());
                     System.out.println("Employee information " + uId + " has been updated.");
        	 }catch(NullPointerException e) {
        		 System.out.println("Employee " + uId +" doesn't esixt");
        	 }
            
           }
        
	}
	
	public Information searchId(String id){
        id = id.trim().toUpperCase();
        // size of ArrayList
        for (int i = 0; i < employees.size(); i++) // Linear search is use
            if(employees.get(i).getId().equals(id)) return employees.get(i);

        return null; // not found
    }
    
    // Luu Thanh Dat
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
    
    // Le Duc Manh
    public int getWorkingHour(String workday){
        return getWorkingMonth(workday) * 160;
    }
    
    // Le Duc Manh
    @Override
    public void calAllowance(Information obj) { 
            if(obj instanceof Management){
                    Management management= (Management) obj;
                    if (getWorkingMonth(management.getWorkStartingDate()) >= 36) {
                    	double Allowance=  2000000 * management.getProductivityScore();
                        management.setAllowance(Allowance);
                    } else {
                    	double Allowance= 1200000 * management.getProductivityScore();
                        management.setAllowance(Allowance);
                    }
            }else if(obj instanceof Leader){
                Leader lead = (Leader) obj;
                if (getWorkingMonth(lead.getWorkStartingDate()) >= 36) {
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
    
    // Le Duc Manh
    @Override
    public void calMonthlyIncome(Information obj) {
        calRewardSalary(obj);
        calAllowance(obj);
        if(obj instanceof Management){
            Management management = (Management) obj;
            double MonthlyIncome = ((management.getResolveIssueNumber() * 5000000) + (management.getOtherTaskNumber() * 500000) + management.getRewardSalary()+ management.getAllowance());
            management.setMonthlyInCome(MonthlyIncome);
        }else if(obj instanceof Leader){
            Leader leader= (Leader) obj;
            double MonthlyIncome = (leader.getReviewTaskNumber() * 4000000 + leader.getSupportTaskNumber()*400000 + leader.getRewardSalary()+ leader.getAllowance());
            leader.setMonthlyInCome(MonthlyIncome);
        }else if(obj instanceof Dev){
            Dev dev= (Dev) obj;           
            double MonthlyIncome = getWorkingHour(dev.getWorkStartingDate()) * 1500000 + dev.getRewardSalary() + dev.getAllowance() ;
            dev.setMonthlyInCome(MonthlyIncome);
        }
    }
    
    // Le Duc Manh
    public void calRewardSalary(Information obj){
    	double RewardSalary = obj.getProductivityScore() * 3000000;
        obj.setRewardSalary(RewardSalary);
    }
    
    //Nguyen Quang Tuyen
    
    //Tran Tien Dat
    public void displayAllEmplyees() {
        if (employees.size() == 0) {
            System.out.println("List is empty!");
        } else {
            for (int i = 0; i < employees.size(); i++) {
                System.out.println(employees.get(i).toString());
            }
        }
    }
    
    //Nguyen Quang Tuyen
    public void displayEmployeesByMonthlyIncomeAndAccount() {
        Collections.sort(employees, (o1, o2) -> {
            if (o1.getMonthlyInCome() == o2.getMonthlyInCome()) {
                return o1.getAccountEmployee().compareTo(o2.getAccountEmployee());
            } else {
                return Double.compare( o2.getMonthlyInCome(),o1.getMonthlyInCome());
            }
        });
        System.out.println("List of Employees sorted by Account:");
        for (Information e : employees) {
            System.out.println(e);
        }
    }
    
    //Nguyen Quang Tuyen
    public void displayEmployeesByRoleAndEmployID() {
        Collections.sort(employees, new Comparator<Information>() {
            @Override
            public int compare(Information o1, Information o2) {
                if (o1.getRole() == o2.getRole()) {
                    return o1.getId().compareTo(o2.getId());
                } else {
                    return Integer.compare(o2.getRole(), o1.getRole());
                }
            }
        });
        System.out.println("List of Employees sorted by Account:");
        for (Information e : employees) {
            System.out.println(e);
        }
    }
    
    //Tran tien dat
    public void pwFile() throws IOException {
        try {
            File file = new File("C:\\Users\\ACER\\eclipse-workspace\\AssignmentPRO\\Assignment.txt");
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
                    writer.write(ld.getRole() + "," + ld.getId() + "," + ld.getAccountEmployee() + "," + ld.getWorkStartingDate() + "," + ld.getProductivityScore() + ","+ld.getReviewTaskNumber()+ "," + ld.getSupportTaskNumber());
                    writer.newLine();
                }
                if (employee instanceof Dev) {
                    Dev de = (Dev) employee; 
                    writer.write(de.getRole() + "," + de.getId() + "," + de.getAccountEmployee() + "," + de.getWorkStartingDate() + "," + de.getProductivityScore() + "," + de.getDoneTaskNumber());
                    writer.newLine();
              }
            }
            System.out.println("Save Successed");
            writer.close();
        } catch (IOException e) {
            System.out.println("Undetected errors occur!");
        }
    }
    //Tran Tien Dat
    public boolean checkFile(String line) {
    	String[] implement = line.split(",");
    	if(!implement[0].equals("1") && !implement[0].equals("2") && !implement[0].equals("3")){
    		System.out.print("data of role in file is wrong!");
    		return false;
    	}else if(!implement[1].matches("^MNV\\d{3}$"))	{
    		System.out.print("data of id in file is wrong!");
    		return false;
    	}else if(implement[2].isEmpty()) {
    		System.out.print("data of Account in file is wrong!");
    		return false;
    	}else if(isValidDate(implement[3]) == false) {
    		System.out.print("data of Work Starting Date in file is wrong!");
    		return false;
    	}
    	if(implement[0].equals("1")) {
    		if(implement[4].isEmpty() || implement[5].isEmpty() || implement[6].isEmpty()) {
    			System.out.print("not enough data in file !");
    			return false;
    		}
    	}if(implement[0].equals("2")) {
    		if(implement[4].isEmpty() || implement[5].isEmpty() || implement[6].isEmpty()) {
    			System.out.print("not enough data in file !");
    			return false;
    		}
    	}if(implement[0].equals("3")) {
    		if(implement[4].isEmpty() || implement[5].isEmpty()) {
    			System.out.print("not enough data in file !");
    			return false;
    		}
    	}
		return true;
    }
    
    public void readFile() {
    	int i=0;
        try {
        	cout+=1;
            if(cout==1) {
	            File tenfile =new File("C:\\Users\\ACER\\eclipse-workspace\\AssignmentPRO\\PRO.txt");
	            BufferedReader br = new BufferedReader(new FileReader(tenfile));
	            String line;
	            while ((line = br.readLine()) != null) {
	            	++i;
	            	String[] implement = line.split(",");
	            	if(line.trim().isEmpty()) {
		            	System.out.println("Employee " +i+ " is empty");
	            	}else if(checkFile(line)==true) {
	            		
				                if (implement[0].equals("1")) {
				                    Information nv = new Management(Integer.parseInt(implement[0]), implement[1], implement[2], implement[3], Float.parseFloat(implement[4]),  Integer.parseInt(implement[5]), Integer.parseInt(implement[6]));
				                    employees.add(nv);
				                }
				                if (implement[0].equals("2")) {
				
				                    Information nv = new Leader(Integer.parseInt(implement[0]), implement[1], implement[2], implement[3], Float.parseFloat(implement[4]), Integer.parseInt(implement[5]), Integer.parseInt(implement[6]));
				                    employees.add(nv);
				                }
				                if (implement[0].equals("3")) {
				
				                    Information nv = new Dev(Integer.parseInt(implement[0]), implement[1], implement[2], implement[3], Float.parseFloat(implement[4]),  Integer.parseInt(implement[5]));
				                    employees.add(nv);
				                }   
	            	}else System.out.println(": Employee "+i);	
	            }System.out.println("Load Successed");
	            br.close();
            }else System.out.println("file shoulded load 1 time!");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    
    // Check date input is valid format and is over current date
    public boolean isValidDate(String dateStr) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false); // disable lenient parsing
        try {
            Date date = df.parse(dateStr);
            // Check if the date is not over the current day
            Date today = new Date();
            if (date.after(today)) {
                return false;
            }
            return true;
        } catch (java.text.ParseException e) {
            return false;
        }
    }
}
