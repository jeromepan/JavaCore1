
package Bankers_Algorithm;

import java.util.Scanner;
public class main {
	
public static void main(String []args) {
        System.out.println("initialize");
        bankers tb = new bankers();
        boolean flag = true;
        while (flag) {
            System.out.println("1.safe test ");
            System.out.println("2.avoidance and deadlock test(Banker's Algorithm )");
            System.out.println("3.end");
            System.out.println("----------------------------------------------------------");
            System.out.println("please choose:");
            Scanner input = new Scanner(System.in);
            int num = input.nextInt();
            switch (num) {
            case 1:
                tb.Security_check();
                flag = true;
                break;
            case 2:
                tb.checkRequest();//deadlock test
                 
                flag = true;
                break;
            case 3:
                System.out.println("Bye");
                flag = false;
                break;
            }
        }
    }
}