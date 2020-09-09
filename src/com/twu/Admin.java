package com.twu;

import java.util.Scanner;

public class Admin {
	static String adminname=null;
	static Scanner scan=new Scanner(System.in);
	static userIndex1 ui=new userIndex1();
	static Admin admin=new Admin();
	String[] hot = Users.hot;//初始化热搜
	int[] hotSort = Users.hotSort;//热搜排名
	int[] hotnumbers = Users.hotnumbers;//热搜票数
	int[] hotMoney= Users.hotMoney;//每个热搜位置的购买金额
	static String[][] adminInfo={{"admin","admin123"},{"yzq","yzq327"}};
	static int superHotIndex;//超级热搜的索引号
	
	public void inputname() {
		System.out.println("请输入您的昵称:"); 
     	String adminName=scan.next(); 
     	System.out.println("请输入您的密码:");
     	String adminPassword=scan.next();
     	for(int i=0;i<adminInfo.length;i++) {//当账户密码匹配
     		if(adminInfo[i][0].equals(adminName) && adminInfo[i][1].equals(adminPassword)) {
     			adminname = adminName;	     	
     	     	admin.print();
     		} 
     	}    	  	
	}	
	
	//管理员界面的显示
	public void print() {		
		 System.out.println("你好,"+adminname+",你可以:");
	     System.out.println("1.查看热搜排行榜");	     
	     System.out.println("2.添加热搜");
	     System.out.println("3.添加超级热搜");
	     System.out.println("4.退出");      
	     int inputNumbers=scan.nextInt();
	     admin.whatNumber(inputNumbers);	     
	}
	//对用户输入的数字进行判断
	public void whatNumber(int inputNumbers) {
		if(inputNumbers==1) {
			admin.numerIs1();
		}else if(inputNumbers==2) {
			admin.numerIs2();
		}else if(inputNumbers==3){			
			admin.numerIs3();			
		}else {			
			ui.print(); 
		}
		
	}
	
	//当输入数字为1时
	public void numerIs1() {
		if(hot != null && hot.length != 0) {				
			for(int i=0;i<hot.length;i++) {//打印热搜
				System.out.println(hotSort[i]+" "+hot[i]+" "+ hotnumbers[i]);
			}
		}
		admin.print(); 
	}
	
	//当输入数字为2时
	public void numerIs2() {
		System.out.println("请输入你要添加的热搜事件:");
		String inputHot=scan.next();
		hot=insertString(hot,inputHot);
		if(hotSort != null && hotSort.length != 0) {
			hotSort=insertInt(hotSort,hotSort.length+1);				
		}else {
			hotSort=insertInt(hotSort,1);				
		}
		hotnumbers=insertInt(hotnumbers,0);//初始化每个热搜的票数为0
		hotMoney=insertInt(hotMoney,0);//初始化每个热搜的购买金额为0			
		System.out.println("添加成功！");	
	//  System.out.println("此时的热搜有："+hot.length+"条");
		admin.print(); 	
				
	}
	
	//当输入数字为3时
	public void numerIs3() {
		System.out.println("请输入你要添加的热搜事件的名字:");
		String inputHot=scan.next();
		hot=insertString(hot,inputHot);
		if(hotSort != null && hotSort.length != 0) {
			hotSort=insertInt(hotSort,hotSort.length+1);				
		}else {
			hotSort=insertInt(hotSort,1);				
		}
		superHotIndex=hotSort.length;//此时超级热搜的序号
		hotnumbers=insertInt(hotnumbers,0);//初始化每个热搜的票数为0
		hotMoney=insertInt(hotMoney,0);//初始化每个热搜的购买金额为0			
		System.out.println("添加成功！");	
		admin.print(); 	
					
	}
	
	// 往字符串数组追加新数据
	private String[] insertString(String[] hot, String inputHot) {
			// TODO Auto-generated method stub
				int size=0;
				if(hot != null && hot.length != 0) {
					size = hot.length; // 获取原数组长度
				}
			
		        int newSize = size + 1; // 原数组长度加上追加的数据的总长度
		 
		        // 新建临时字符串数组
		        String[] tmp = new String[newSize];
		        // 先遍历将原来的字符串数组数据添加到临时字符串数组
		        for (int i = 0; i < size; i++) {
		            tmp[i] = hot[i];
		        }
		        // 在末尾添加上需要追加的数据	        
		        tmp[size]=inputHot;
		        return tmp; // 返回拼接完成的字符串数组
		}
		
	// 往整数数组追加新数据
	private int[] insertInt(int[] hotnumber, int number) {
			// TODO Auto-generated method stub
			int size=0;
			if(hotnumber != null && hotnumber.length != 0) {
				size = hotnumber.length; // 获取原数组长度
			}
			    
		        int newSize = size + 1; // 原数组长度加上追加的数据的总长度
		 
		        // 新建临时整数数组
		        int[] tmp = new int[newSize];
		        // 先遍历将原来的字符串数组数据添加到临时整数数组
		        for (int i = 0; i < size; i++) {
		            tmp[i] = hotnumber[i];
		        }
		        // 在末尾添加上需要追加的数据	        
		        tmp[size]=number;
		        return tmp; // 返回拼接完成的整数数组
		}

			

	
}
