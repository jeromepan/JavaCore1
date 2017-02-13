package Bankers_Algorithm;

import java.util.Scanner;

                   
    public class bankers {
        int m; 
        int n; 
        int[][] max;
        int[][] allocation;
        int[][] need;
        int[] available;
        int[] p;
        boolean[] finish; //true describes yes, false means no.
        Scanner input = new Scanner(System.in);
         
        //initialize
        public bankers() { 
             System.out.println("Input the numbers of processing in OS (m)");
             m = input.nextInt();
             System.out.println("Input the numbers of sources types in OS (n)");
             n = input.nextInt();
             max = new int[m][n];
             allocation = new int[m][n];
             need = new int[m][n];
             available = new int[n];
             finish = new boolean[m];
              
             System.out.println("-Input the max numbers of  "+m+" Row and "+n+" column-");
             for (int i=0;i<max.length;i++) {  
             System.out.println("Input the p("+i+")processing Max:");
                 for(int j=0;j<max[i].length;j++){
                    max[i][j] = input.nextInt();
                 }
             }
             
             System.out.println("-Input the numbers of "+m+" Row "+n+"column-  ");
             for (int i=0;i<allocation.length;i++) {
             System.out.println("Input the p("+i+") processing's Alloction:");
                 for (int j=0;j<allocation[i].length;j++) {
                    allocation[i][j] = input.nextInt();
                 }
             }
             for (int i=0;i<need.length;i++) { 
                 for(int j=0;j<need[i].length;j++){
                    need[i][j] = max[i][j] - allocation[i][j];
                 }
             }
             
             System.out.println("Input the numbers of Avallable：");
             for (int i=0;i<n;i++) {
                 available[i] = input.nextInt();
             }
             
             System.out.println("Initialize as: ");
             print();
        }
         
        //show the allocated 
         public void print() {
             System.out.println("----------------------- sources allocated ---------------------------");
             System.out.println("\tMax\tAllocation\tNeed\tAvalable");
            // System.out.println("\tA B C\tA B C\t\tA B C\tA B C");
             for (int i=0;i<m;i++) {
                 System.out.print("P("+i+"): ");
                 System.out.print(" ");
                     for (int j=0;j<n;j++) {
                         if(max[i][j]>9){
                             System.out.print(" "+max[i][j]+" ");
                         }
                         System.out.print(max[i][j]+" ");	
                     }
                     System.out.print("\t");
                     for (int j=0;j<n;j++) {
                         if(allocation[i][j]>9){
                             System.out.print(" "+allocation[i][j]+" ");
                         }
                           System.out.print(allocation[i][j]+" ");
                     }
                     System.out.print("\t\t ");
                     for (int j=0;j<n;j++) {
                         if(need[i][j]>9){
                             System.out.print(" "+need[i][j]+" ");
                         }
                           System.out.print(need[i][j]+" ");
                     }
                     System.out.print("\t");
                     if (i==0) {
                        for (int j=0;j<n;j++) {
                            if(available[j]>9){
                                 System.out.print(" "+available[j]+" ");
                            }
                          System.out.print(available[j]+" ");
                        }
                     }
                     System.out.println();
             }  	
             System.out.println("------------------------------------------");
         }
         
         //check safe 
        public boolean Security_check() {
            int[] work = new int[n];
            for (int i=0;i<n;i++) {
                work[i] = available[i];
            }
            finish = new boolean[m];
            for (int i = 0; i < m; i++) {
                finish[i] = false;
            }
 
            int num = 0;
            int num1 = 0;
            int count = 0;
            int count1 = 0;
            p = new int[m];//find safe
             
            while (num1<m) {
                for (int i=0;i<m;i++) {
                    if (finish[i] == false) {
                        for (int j=0;j<n;j++) {
                            if (need[i][j] <= work[j]) {
                                num++;
                            }
                        }
                        if (num == n) {
                            for (int k=0;k<n;k++) {
                                work[k] = work[k] + allocation[i][k];
                            }
                            finish[i] = true;
                            p[count++] = i;
                        }
                    }
                    num = 0;
                }
                num1++;
            }
 
            //record
            for (int i=0;i<m;i++) {
                if (finish[i] == true) {
                    count1++;
                }
            }
            if (count1 == m) {
                System.out.println("there is a safe state :");
                for (int i=0;i<m;i++) {
                    if (i != m-1) {
                        System.out.print("P"+p[i]+"-");
                    } else {
                        System.out.println("P"+p[i]);
                    }
                }
                System.out.println("----------------------------------------------------");
                return true;
            } else {;
                System.out.println("Cannot find the safe array, System is not safe: ");
                return false;
            }
        }
//insert the processing
        
        public void checkRequest() {
            int process = 0;// record the No. of processing
            int count2 = 0;
            boolean flag = true;// test range of original process
            System.out.println("Input the process of requret  :");
            while (flag) {
                process = input.nextInt();
                if (process > m) {
                    flag = true;
                    System.out.println("Out of range");
                } else {
                    flag = false;
 
                }
            }
            System.out.println("the "+process+" process has a request");;
            int[] request = new int[n];
            System.out.println("Input the request state :");
            for (int i=0;i<n;i++) {
                request[i] = input.nextInt();
            }
            // check 
            for (int i=0;i<n;i++) {
                if (request[i] <= need[process][i] && request[i] <= available[i]) {
                    count2++;
                }
            }
 
            if (count2==n) {//try to allocate
                for (int j=0;j<n;j++) {
                    allocation[process][j] += request[j];
                    need[process][j] -= request[j];
                    available[j] -= request[j];
                }
                System.out.println("allocate-------->");
                print();
                System.out.println("safe test");
                flag = Security_check();
                 
                if (flag==false) {  
                    for (int j=0;j<n;j++) {
                        allocation[process][j] -= request[j]; 
                        need[process][j] += request[j];
                        available[j] += request[j];
                    }
                 
                     
                }else{
                    for (int j=0;j<n;j++) {
                         
                        available[j]+=allocation[process][j];
                    }
                    System.out.println("----------------callback resoures------------------");
                    print();
                }
            } else {
                System.out.println("cannot allocate");
                 
                 
            }
        }
    }