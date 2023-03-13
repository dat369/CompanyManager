
package view;
/**
AUTHOR : LE DUC MANH
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Controller.EmployeeManagement; 
public abstract class Menu<T>{
    Scanner sc = new Scanner(System.in);
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
        System.out.print("Enter the choice: ");
        return sc.nextInt();
    }
    public abstract void execuse(int n); 
    public void run(){
        while(true){
            vs.readfile();
            display();
            int n = enterChoice();
            execuse(n);
            if (n == (this.menu.size())){
                break;
            }   
        }
    }
    
}
