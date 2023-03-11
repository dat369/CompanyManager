package controller;

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

public class EmployeeManagement {
	Scanner sc= new Scanner(System.in);
    List<Information> employees= new ArrayList<>();
    
    public EmployeeManagement() {
        this.employees = new ArrayList<>();
    }
    public void addEmployee() {
    	try {
	    	System.out.print("input Role: ");
	    	int role= sc.nextInt();
	    	System.out.print("input id: ");
	    	String id= sc.nextLine();
	    	System.out.print("input accountEmployee: ");
	    	String accountEmployee= sc.nextLine();
	    	System.out.print("input workStartingDate: ");
	    	String workStartingDate= sc.nextLine();
	    		
	    	System.out.print("input productivityScore: ");
	    	float productivityScore= sc.nextFloat();
	    	System.out.print("input monthlyInCome: ");
	    	double monthlyInCome= sc.nextDouble();
	    	System.out.print("input rewardSalary: ");
	    	double rewardSalary= sc.nextDouble();
	        if (!"^MNV\\\\d{3}$".equals(id)) {
	            System.out.println("Invalid Employ ID! Please try again!");
	            return;
	        }
	        if (role == 0|| id == null || accountEmployee.isEmpty()) {
	            System.out.println("Role and Account can not be null or empty! Please try again!");
	            return;
	        }
	        
	        switch (role) {
	            case 1:
	            	System.out.print("input resolveIssueNumber: ");
	            	int resolveIssueNumber= sc.nextInt();
	            	System.out.print("input otherTaskNumber: ");
	            	int otherTaskNumber= sc.nextInt();
	            	employees.add(new Management(role, id, accountEmployee, workStartingDate, productivityScore, monthlyInCome, rewardSalary,resolveIssueNumber, otherTaskNumber));
	                break;
	            case 2:
	            	System.out.print("input reviewTaskNumber: ");
	            	int reviewTaskNumber= sc.nextInt();
	            	System.out.print("input supportTaskNumber: ");
	            	int supportTaskNumber= sc.nextInt();
	            	employees.add(new Leader(role, id, accountEmployee, workStartingDate, productivityScore, monthlyInCome, rewardSalary,reviewTaskNumber, supportTaskNumber));
	                break;
	            case 3:
	            	System.out.print("input doneTaskNumber: ");
	            	int doneTaskNumber= sc.nextInt();
	            	employees.add(new Dev(role, id, accountEmployee, workStartingDate, productivityScore, monthlyInCome, rewardSalary,doneTaskNumber));
	                break;
	        }
	        if (employees != null) {
	            System.out.println("Employee added successfully!");
	        } else {
	            System.out.println("Can not add Employee! Please try again!");
	        }
        } catch (Exception e) {
			// TODO: handle exception
        	System.out.println("you entered the wrong data!");
		}
    }

    public void displayEmployeesByMonthlyIncomeAndAccount() {
        Collections.sort(employees, (o1,o2) -> {
            if (o1.calMonthlyInCome()==o2.calMonthlyInCome()) {
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
                if (o1.getRole()==o2.getRole()) {
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
		String namefile= sc.nextLine();
		
		try {
			File file =new File(namefile);
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			
			for (Information employee : employees) {
				if(employee instanceof Management) {
					Management mn= (Management) employee;
					writer.write(mn.getRole()+","+ mn.getId()+ "," +mn.getAccountEmployee() +","+mn.getWorkStartingDate()+","+mn.getProductivityScore()+","+mn.getMonthlyInCome()+","+mn.getRewardSalary()+","+mn.getResolveIssueNumber()+","+mn.getOtherTaskNumber());
					writer.newLine();
				}if(employee instanceof Leader) {
					Leader ld= (Leader) employee;
					writer.write(ld.getRole()+","+ ld.getId()+ "," +ld.getAccountEmployee() +","+ld.getWorkStartingDate()+","+ld.getProductivityScore()+","+ld.getMonthlyInCome()+","+ld.getRewardSalary()+","+ld.getReviewTaskNumber()+","+ ld.getSupportTaskNumber());
					writer.newLine();
				}if(employee instanceof Dev) {
					Dev de= (Dev) employee;
					writer.write(de.getRole()+","+ de.getId()+ "," +de.getAccountEmployee() +","+de.getWorkStartingDate()+","+de.getProductivityScore()+","+de.getMonthlyInCome()+","+de.getRewardSalary()+","+de.getDoneTaskNumber());
					writer.newLine();
				}
			}
			writer.close();
		}catch (IOException e) {
			System.out.println("Undetected errors occur!");
		}
    }
    public void readfile() {
		System.out.print("nhap ten file: ");
		String namefile= sc.nextLine();
		
		try {
			File tenfile= new File(namefile);
			BufferedReader br = new BufferedReader(new FileReader(tenfile));
			String line;
			while ((line = br.readLine()) != null) {
				String[] employee = line.split(",");
				
				if(employee[0].equals("1")) {
					Information nv= new Management(Integer.parseInt(employee[0]),employee[1], employee[2],employee[3], Float.parseFloat(employee[4]),Double.parseDouble(employee[5]),Double.parseDouble(employee[6]),Integer.parseInt(employee[7]),Integer.parseInt(employee[8]) );
					employees.add(nv);
				}if(employee[0].equals("2")) {
					
					Information nv= new Leader(Integer.parseInt(employee[0]),employee[1], employee[2],employee[3], Float.parseFloat(employee[4]),Double.parseDouble(employee[5]),Double.parseDouble(employee[6]),Integer.parseInt(employee[7]),Integer.parseInt(employee[8]));
					employees.add(nv);
				}if(employee[0].equals("3")) {
					
					Information nv= new Dev(Integer.parseInt(employee[0]),employee[1], employee[2],employee[3], Float.parseFloat(employee[4]),Double.parseDouble(employee[5]),Double.parseDouble(employee[6]),Integer.parseInt(employee[7]));
					employees.add(nv);
				}
			}
			br.close();
		}catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
	}
}

