package com.twu;

import java.util.Scanner;

public class inputName {
	
	Scanner scan=new Scanner(System.in);
	static String username=null;
	static int voteNumber;//用户
	static inputName inputn=new inputName();
	String[][] adminInfo={{"admin","admin123"},{"yzq","yzq327"}};
	static String adminname=null;
	
	//用户的姓名输入
	public void userInputname() {
		System.out.println("请输入您的昵称"); 
     	String usersNames=scan.next(); 
     	username = usersNames;	
     	voteNumber=10;//每个用户最开始有10票
     	inputn.userIprint();
	}	
	
	//管理员的姓名输入
	public void adminInputname() {
		System.out.println("请输入您的昵称:"); 
     	String adminName=scan.next(); 
     	System.out.println("请输入您的密码:");
     	String adminPassword=scan.next();
     	for(int i=0;i<adminInfo.length;i++) {//当账户密码匹配
     		if(adminInfo[i][0].equals(adminName) && adminInfo[i][1].equals(adminPassword)) {
     			adminname = adminName;	     	
     			inputn.adminPrint();
     		} 
     	}    	  	
	}
	
	//用户界面的显示
	public void userIprint() {		
		 System.out.println("你好,"+username+",你可以:");
	     System.out.println("1.查看热搜排行榜");
	     System.out.println("2.给热搜事件投票");
	     System.out.println("3.购买热搜");
	     System.out.println("4.添加热搜");
	     System.out.println("5.退出");	     	    
	     int inputNumbers=scan.nextInt();	
	     userSelection us=new userSelection();
	     us.whatNumber(inputNumbers);	     
	}	
	
	
	//管理员界面的显示
	public void adminPrint() {		
		 System.out.println("你好,"+adminname+",你可以:");
	     System.out.println("1.查看热搜排行榜");	     
	     System.out.println("2.添加热搜");
	     System.out.println("3.添加超级热搜");
	     System.out.println("4.退出");      
	     int inputNumbers=scan.nextInt();
	     adminSelection as=new adminSelection();
	     as.whatNumber(inputNumbers);	     
	}

}
