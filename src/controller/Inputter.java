package controller;
import java.util.Scanner;

public class Inputter {
	static Scanner sc = new Scanner(System.in);
	// Get an integer between min...max
	public static int inputInt(String msg, int min, int max){
        if(min > max){
            int t = min;
            min = max;
            max = t;
        }

        int data;

        do{
            System.out.print(msg);
            //Change string to int
            data = Integer.parseInt(sc.nextLine());
        }while (data < min || data > max);

        return data;
    }
	
	public static int inputIntOverZero(String msg){
        int data;

        do{
            System.out.print(msg);
            //Change string to int
            data = Integer.parseInt(sc.nextLine());
        }while (data < 0);

        return data;
    }
	
	public static int inputFloat(String msg, float min, float d){
        if(min > d){
            float t = min;
            min = d;
            d = t;
        }

        int data;

        do{
            System.out.print(msg);
            //Change string to float
            data = Integer.parseInt(sc.nextLine());
        }while (data < min || data > d);

        return data;
    }

    public static String inputStr(String msg){
        System.out.print(msg);
        // trim() to remove all " " in string
        String data = sc.nextLine().trim();
        return data;
    }
    // Get non-blank string
    public static String inputNonBlankStr(String msg){
        String data;

        do{
            System.out.print(msg);
            data = sc.nextLine().trim();
        }while (data.length() == 0);

        return  data;
    }
    //Get string follow pattern
    public static String inputPattern(String msg, String pattern){
        String data;

        do {
            System.out.print(msg);
            data = sc.nextLine().trim();
        }while (!data.matches(pattern));
        return data;
    }
}
