package com.twu;

import java.util.Arrays;
import java.util.Scanner;

public class Users {	
	static Users user=new Users();
	static Scanner scan=new Scanner(System.in);
	static String username=null;
	static int voteNumber;//用户
	static String[] hot = null;//初始化热搜
	static int[] hotSort = null;//热搜排名
	static int[] hotnumbers = null;//热搜票数	
	static int[] hotMoney= null;//每个热搜位置的购买金额
	int superHotIndex=Admin.superHotIndex;//超级热搜的索引号
	
	public void inputname() {
		System.out.println("请输入您的昵称"); 
     	String usersNames=scan.next(); 
     	username = usersNames;	
     	voteNumber=10;//每个用户最开始有10票
     	user.print();
	}	
	
	//用户界面的显示
	public void print() {		
		 System.out.println("你好,"+username+",你可以");
	     System.out.println("1.查看热搜排行榜");
	     System.out.println("2.给热搜事件投票");
	     System.out.println("3.购买热搜");
	     System.out.println("4.添加热搜");
	     System.out.println("5.退出");	     	    
	     int inputNumbers=scan.nextInt();	     
	     user.whatNumber(inputNumbers);	     
	}
	//对用户输入的数字进行判断
	public void whatNumber(int inputNumbers) {
		userIndex1 ui=new userIndex1();		
		
		if(inputNumbers==1) {
			user.numerIs1();			
		}else if(inputNumbers==2) {
			user.numerIs2();
		}else if(inputNumbers==3){	
			user.numerIs3();			
		}else if(inputNumbers==4) {
			user.numerIs4();
		}else {	
			ui.print(); 
		}
		
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

	//对投票后的热搜排名进行修改
	public void changeHot(String inputHot2,int userVote) {			
		String[] newHot = hot;		
		for(int i=0;i<newHot.length;i++) { //查找相应匹配的热搜
			if(newHot[i].equals(inputHot2)) {//当输入的值与热搜可以匹配	
				if(i==superHotIndex) {//当该热搜为超级热搜时
					hotnumbers[i]=hotnumbers[i]+2*userVote;//用户投票数乘以2
				}else {
					hotnumbers[i]=hotnumbers[i]+userVote;//更新该热搜的票数
				}				
			}
		}
		if(hotnumbers.length>1) {//存在多个热搜时
			for(int i=0;i<hotnumbers.length-1;i++) {				
				for(int j=1;j<hotnumbers.length;j++) {//判断票数
					if(hotnumbers[i]<hotnumbers[j]) {//当票数更高的在后面是
						int tempint=hotnumbers[i];
						hotnumbers[i]=hotnumbers[j];//交换票数
						hotnumbers[j]=tempint;
						superHotIndex=j;//更新超级热搜的位置索引
						String tempHot=newHot[i];
						newHot[i]=newHot[j];//交换热搜
						newHot[j]=tempHot;
					}
				}
			}
		}		
		hot=newHot;//更新热搜排行
	}
		
	//判断购买金额是否足够
	public boolean ifEnoughCost(int intputMunber,int cost) {
		boolean result=true;
		if(hotMoney[intputMunber]>cost) {//如果之前购买的金额大于现有输入金额
			result=false;//购买失败
		}
		return result;
	}
	
	//对购买后的热搜排名进行修改
	public void changeCostHot(String inputHot,int intputMunber) {	
		String[] newHot = hot;
		int oldIndex = 0;
		String tempHot;
		int tempInt;
		if(hotMoney[intputMunber]==0) {//这是该热搜被首次购买
			for(int i=0;i<newHot.length;i++) { //查找相应匹配的热搜
				if(newHot[i].equals(inputHot)) {//当输入的值与热搜可以匹配					
					oldIndex=i;//保存该热搜的原来位置
				}
			}
			tempHot=newHot[oldIndex];
			newHot[oldIndex]=newHot[intputMunber];//交换更新热搜的位置
			newHot[intputMunber]=tempHot;
			tempInt=hotnumbers[oldIndex];
			hotnumbers[oldIndex]=hotnumbers[intputMunber];//交换更新热搜的票数
			hotnumbers[intputMunber]=tempInt;
		}else { //该热搜不是首次购买，原来的热搜被挤掉并消失不见
			
			
			
		}
			
					
			hot=newHot;//更新热搜排行
	}
	
	//当输入数字为1时
	public void numerIs1() {
		if(hot != null && hot.length != 0) {				
			for(int i=0;i<hot.length;i++) {//打印热搜
				System.out.println(hotSort[i]+" "+hot[i]+" "+ hotnumbers[i]);
			}
		}
		user.print(); 
	}
	
	//当输入数字为2时
	public void numerIs2() {
		System.out.println("请输入你要投票的热搜名称:");
		String inputHot2=scan.next();
		System.out.println("请输入你要投票的热搜票数: (你目前还有："+voteNumber+"票)");
		int userVote=scan.nextInt();//用户输入票数
		if(userVote<0 || userVote > voteNumber) {
			System.out.println("投票失败！");
			user.print(); 
		}else {
			System.out.println("投票成功！");	
			voteNumber=voteNumber-userVote;//更新可投票的票数				
			changeHot(inputHot2,userVote);//对相应的热搜排名进行修改
			user.print(); 
		}
			
	}
		
	//当输入数字为3时
	public void numerIs3() {
		System.out.println("请输入你要购买的热搜名称:");
		String inputHot=scan.next();
		System.out.println("请输入你要购买的热搜排名：");
		int intputMunber=scan.nextInt();
		intputMunber=intputMunber+1;//具体排名
		System.out.println("请输入你要购买的热搜金额：");
		int cost=scan.nextInt();
		//判断购买的金额是否足够
		if(ifEnoughCost(intputMunber,cost)) {
			System.out.println("购买成功！");
			changeCostHot(inputHot,intputMunber);//对相应的热搜排名进行修改
			user.print(); 
		}else {
			System.out.println("购买失败");
			user.print(); 
		}	
	}
	
	//当输入数字为4时
	public void numerIs4() {
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
		user.print(); 		
	}
}
