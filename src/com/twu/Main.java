package com.twu;

import java.util.Scanner;

public class Main {
	static Scanner scan=new Scanner(System.in);   
	static userIndex1 ui=new userIndex1();
	
    public static void main(String[] args) {
    	  ui.print();    		
      
    }
    
    public void inputNumber(int userNumber) {
    	inputName inputn=new inputName();
    	 if(userNumber==1) {         	        	
         	//Users user=new Users();	         	
         	//user.inputname();  
    		inputn.userInputname();
         	
         }else if(userNumber==2) {
            //Admin admin=new Admin();          	
         	//admin.inputname();
        	 inputn.adminInputname();
         	
         }else if(userNumber==3){
         	ui.print();
         }else {
         	System.out.println("请从数字1,2,3中选择输入一个");
         	ui.print();
         }
     }


}
