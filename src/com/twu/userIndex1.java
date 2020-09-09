package com.twu;

import java.util.Scanner;

public class userIndex1 {
	public void print() {
		 Main main=new Main();   
		 Scanner scan=new Scanner(System.in);  
		 System.out.println("欢迎来到热搜排行榜，你是？");
	     System.out.println("1.用户");
	     System.out.println("2.管理员");
	     System.out.println("3.退出");   
	     int userNumber=scan.nextInt();
         main.inputNumber(userNumber);
	}	
}
