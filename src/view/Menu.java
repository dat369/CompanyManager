package view;
/**
*
* @author Luu Thanh Dat
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.EmployeeManagement; 
public abstract class Menu<T>{
  
    private String title;
    private EmployeeManagement vs = new EmployeeManagement();
    private ArrayList<String> menu;

    public Menu(String title, String[] menu) {
        this.title = title;
        this.menu = new ArrayList<>();
        this.menu.addAll(Arrays.asList(menu));
    }

    public Menu() {
    }
    public void display(){
        System.out.println(this.title);
        System.out.println("-------------------");
        for(int i = 0; i< this.menu.size(); i++){
            System.out.println((i+1)+ " " + menu.get(i));
        }
        System.out.println("-----------");
    }
    public int enterChoice(){
    	Scanner sc = new Scanner(System.in);
    	try {
    		 System.out.print("Enter the choice: ");
    	        return sc.nextInt();
    	}catch(InputMismatchException e) {
    		System.out.println("Invalid choice! ");
    		return 0;
    	}
    	
       
    }
    public abstract void execuse(int n); 
    public void run(){
        while(true){
            display();
            int n = enterChoice();
            execuse(n);
            if (n == (this.menu.size())){
                break;
            }   
        }
    }
    
}
