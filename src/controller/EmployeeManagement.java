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
import model.Student;

public class EmployeeManagement {
	Scanner sc= new Scanner(System.in);
    private List<Information> employees;
    
    public EmployeeManagement() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(int role, String employID, String account, String workStartingDate, int productivityScore,
            int checkType, int otherTaskNumber, int supportTaskNumber, int reviewTaskNumber, int resolveIssueNumber,
            int workingHour) {
        if (!"^MNV\\\\d{3}$".equals(employID)) {
            System.out.println("Invalid Employ ID! Please try again!");
            return;
        }
        if (role == 0|| account == null || account.isEmpty()) {
            System.out.println("Role and Account can not be null or empty! Please try again!");
            return;
        }
        Information employee = null;
        switch (role) {
            case 1:
                employee = new Management( role, id, accountEmployee, workStartingDate, productivityScore, monthlyInCome, rewardSalary,resolveIssueNumber, otherTaskNumber, allowance);
                break;
            case 2:
                employee = new Leader(reviewTaskNumber, supportTaskNumber, allowance, role, id, accountEmployee, workStartingDate, productivityScore, monthlyInCome, rewardSalary,reviewTaskNumber, supportTaskNumber, allowance);
                break;
            case 3:
                employee = new Dev( role, id, accountEmployee, workStartingDate, productivityScore, monthlyInCome, rewardSalary,reviewTaskNumber, supportTaskNumber, allowance);
                break;
        }
        if (employee != null) {
            this.employees.add(employee);
            System.out.println("Employee added successfully!");
        } else {
            System.out.println("Can not add Employee! Please try again!");
        }
    }

    public void displayEmployeesByMonthlyIncomeAndAccount() {
        List<Information> sortedList = new ArrayList<>(this.employees);
        Collections.sort(sortedList, (Information o1, Information o2) -> {
            if (o1.calMonthlyInCome().equals(o2.calMonthlyInCome())) {
                return o1.accountEmployee().compareTo(o2.accountEmployee());
            } else {
                return o1.getRole().compareTo(o2.getRole());
            }
        });
        System.out.println("List of Employees sorted by Account:");
        for (Information e : sortedList) {
            System.out.println(e);
        }
    }

    public void displayEmployeesByRoleAndEmployID() {
        List<Information> sortedList = new ArrayList<>(this.employees);
        Collections.sort(sortedList, new Comparator<Information>() {
            @Override
            public int compare(Information o1, Information o2) {
                if (o1.getRole().equals(o2.getRole())) {
                    return o1.getId().compareTo(o2.getId());
                } else {
                    return o1.getRole().compareTo(o2.getRole());
                }
            }
        });
        System.out.println("List of Employees sorted by Account:");
        for (Information e : sortedList) {
            System.out.println(e);
        }
    }

    public void showEmployeeList(String sortBy, boolean isAscending) {
        List<Employee> employeeList = model.getEmployeeList();
        switch (sortBy) {
            case "monthlyIncome":
                employeeList.sort(Comparator.comparing(Employee::getMonthlyIncome));
                break;
            case "account":
                employeeList.sort(Comparator.comparing(Employee::getAccount));
                break;
            case "role":
                employeeList.sort(Comparator.comparing(Employee::getRole));
                break;
            case "employID":
                employeeList.sort(Comparator.comparing(Employee::getEmployID));
                break;
            default:
                System.out.println("Invalid sort by criteria.");
                return;
        }
        if (!isAscending) {
            Collections.reverse(employeeList);
        }
        System.out.println("Employee List:");
        System.out.printf("%-10s %-15s %-15s %-20s %-20s %-20s %-20s\n",
                "Role", "Employ ID", "Account", "Starting Date", "Productivity Score", "Monthly Income", "Allowance");
        for (Employee employee : employeeList) {
            System.out.printf("%-10s %-15s %-15s %-20s %-20s %-20s %-20s\n",
                    employee.getRole(), employee.getEmployID(), employee.getAccount(), employee.getStartingDate(),
                    employee.getProductivityScore(), employee.getMonthlyIncome(), employee.getAllowance());
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
				if(employee.getRole()==1) {
					writer.write(employee.getRole()+","+ employee.getId()+ "," +employee.getAccountEmployee() +","+employee.getWorkStartingDate()+","+employee.getProductivityScore()+","+employee.getMonthlyInCome()+","+employee.getRewardSalary()+","+employee.getResolveIssueNumber()+","+employee.getOtherTaskNumber()+","employee.getAllowance());
					writer.newLine();
				}if(employee.getRole()==2) {
					writer.write(employee.getRole()+","+ employee.getId()+ "," +employee.getAccountEmployee() +","+employee.getWorkStartingDate()+","+employee.getProductivityScore()+","+employee.getMonthlyInCome()+","+employee.getRewardSalary());
					writer.newLine();
				}if(employee.getRole()==3) {
					writer.write(employee.getRole()+","+ employee.getId()+ "," +employee.getAccountEmployee() +","+employee.getWorkStartingDate()+","+employee.getProductivityScore()+","+employee.getMonthlyInCome()+","+employee.getRewardSalary());
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
					Information nv= new Management(Integer.parseInt(employee[0]),employee[1], employee[2],employee[3], Float.parseFloat(employee[4]),Double.parseDouble(employee[5]),Double.parseDouble(employee[6]) );
					employees.add(nv);
				}if(employee[0].equals("2")) {
					String role= "Leader";
					Information nv= new Leader(Integer.parseInt(employee[0]),employee[1], employee[2],employee[3], Float.parseFloat(employee[4]),Double.parseDouble(employee[5]),Double.parseDouble(employee[6]));
					employees.add(nv);
				}if(employee[0].equals("3")) {
					String role= "Dev";
					Information nv= new Dev(Integer.parseInt(employee[0]),employee[1], employee[2],employee[3], Float.parseFloat(employee[4]),Double.parseDouble(employee[5]),Double.parseDouble(employee[6]));
					employees.add(nv);
				}
			}
			br.close();
		}catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
	}
}

