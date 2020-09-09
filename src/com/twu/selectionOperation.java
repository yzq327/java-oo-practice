package com.twu;

import java.util.Arrays;
import java.util.Scanner;

public class selectionOperation {
	static String[] hot = null;//初始化热搜
	static int[] hotSort = null;//热搜排名
	static int[] hotnumbers = null;//热搜票数	
	static int[] hotMoney= null;//每个热搜位置的购买金额
	static int[] superHot=null; //超级热搜
	static int[] costHot=null; //购买热搜
	static inputName iname=new inputName();
	static Scanner scan=new Scanner(System.in);
	static selectionOperation so=new selectionOperation();
	
	/********************用户*********************/
	//当输入数字为1时,查看热搜排行榜
	public void userNumerIs1() {
		if(hot != null && hot.length != 0) {				
			for(int i=0;i<hot.length;i++) {//打印热搜
				System.out.println(hotSort[i]+" "+hot[i]+" "+ hotnumbers[i]);
			}
		}
		iname.userIprint();
	}
	
	//当输入数字为2时，给热搜事件投票
	public void userNumerIs2() {
		System.out.println("请输入你要投票的热搜名称:");
		String inputHot2=scan.next();
		System.out.println("请输入你要投票的热搜票数: (你目前还有："+inputName.voteNumber+"票)");
		int userVote=scan.nextInt();//用户输入票数
		if(userVote<0 || userVote > inputName.voteNumber) {
			System.out.println("投票失败！");
			iname.userIprint();
		}else {
			System.out.println("投票成功！");							
			changeHot(inputHot2,userVote);//对相应的热搜排名进行修改
			iname.userIprint();
		}			
	}
			
	//当输入数字为3时,购买热搜
	public void userNumerIs3() {
		System.out.println("请输入你要购买的热搜名称:");
		String inputHot=scan.next();
		System.out.println("请输入你要购买的热搜排名：");
		int intputMunber=scan.nextInt();
		intputMunber=intputMunber-1;//具体排名
		System.out.println("请输入你要购买的热搜金额：");
		int cost=scan.nextInt();
		//判断购买的金额是否足够
		if(ifEnoughCost(intputMunber,cost)) {
			System.out.println("购买成功！");
			changeCostHot(inputHot,intputMunber,cost);//对相应的热搜排名进行修改
			iname.userIprint();
		}else {
			System.out.println("购买失败");
			iname.userIprint();
		}	
	}
		
	//当输入数字为4时，添加热搜
	public void userNumerIs4() {
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
		superHot=insertInt(superHot,0);//初始化每个都不是超级热搜，当为0时，不是超级热搜，当为1时，是超级热搜	
		costHot=insertInt(costHot,0);//不是购买热搜
		System.out.println("添加成功！");	
		iname.userIprint();	
	}
	
	
	/********************管理员***************************/		
	//当输入数字为1时,查看热搜排行榜
	public void adminNumerIs1() {
		if(hot != null && hot.length != 0) {				
			for(int i=0;i<hot.length;i++) {//打印热搜
				System.out.println(hotSort[i]+" "+hot[i]+" "+ hotnumbers[i]);
			}
		}
		iname.adminPrint();
	}
	
	//当输入数字为2时,添加热搜
	public void adminNumerIs2() {
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
		superHot=insertInt(superHot,0);//不是超级热搜
		costHot=insertInt(costHot,0);//不是购买热搜
		System.out.println("添加成功！");	
		iname.adminPrint();
	}
		
	//当输入数字为3时，添加超级热搜
	public void adminNumerIs3() {
		System.out.println("请输入你要添加的热搜事件的名字:");
		String inputHot=scan.next();
		hot=insertString(hot,inputHot);
		if(hotSort != null && hotSort.length != 0) {
			hotSort=insertInt(hotSort,hotSort.length+1);				
		}else {
			hotSort=insertInt(hotSort,1);				
		}		
		//superHotIndex=(hotSort.length-1);//此时超级热搜的序号		
		hotnumbers=insertInt(hotnumbers,0);//初始化每个热搜的票数为0
		hotMoney=insertInt(hotMoney,0);//初始化每个热搜的购买金额为0		
		superHot=insertInt(superHot,1);//是超级热搜	
		System.out.println("添加成功！");	
		iname.adminPrint();					
	}
			
	
	
	/********************其余函数****************************/	
	//判断购买金额是否足够
	public boolean ifEnoughCost(int intputMunber,int cost) {
		boolean result;
		if(hotMoney[intputMunber] < cost ) {//如果之前购买的金额大于现有输入金额
			result=true;//购买成功
			
		}else {			
			result=false;//购买失败			
		}
		return result;
	}
	
	//对投票后的热搜排名进行修改
	public void changeHot(String inputHot2,int userVote) {			
		String[] newHot = hot;		
		for(int i=0;i<newHot.length;i++) { //查找相应匹配的热搜
			if(newHot[i].equals(inputHot2)) {//当输入的值与热搜可以匹配				
				if(superHot[i] == 1) {//当该热搜为超级热搜时				
					hotnumbers[i]=hotnumbers[i]+2*userVote;//用户投票数乘以2
					//inputName.voteNumber=inputName.voteNumber-2*userVote;//更新可投票的票数	
				}else {
					hotnumbers[i]=hotnumbers[i]+userVote;//更新该热搜的票数					
				}
				
			}
		}
		inputName.voteNumber=inputName.voteNumber-userVote;//更新可投票的票数	
		if(hotnumbers.length>1) {//存在多个热搜时
			for(int i=0;i<hotnumbers.length-1;i++) {				
				for(int j=i+1;j<hotnumbers.length;j++) {//判断票数
					if(hotnumbers[i]<hotnumbers[j] && costHot[i] !=1) {//当票数更高的在后面，且该位不是购买热搜位时，进行交换
						int tempint=hotnumbers[i];
						hotnumbers[i]=hotnumbers[j];//交换票数
						hotnumbers[j]=tempint;
						//superHotIndex=j;//更新超级热搜的位置索引
						String tempHot=newHot[i];
						newHot[i]=newHot[j];//交换热搜
						newHot[j]=tempHot;	
						int tempSuper=superHot[i];
						superHot[i]=superHot[j];
						superHot[j]=tempSuper;
						//if(superHot[i]==1 || superHot[j]==1) { //此时交换超级热搜的索引
						//	int tempSuper=superHot[i];
						//	superHot[i]=superHot[j];
						//	superHot[j]=tempSuper;
						//}
					}
				}
			}
		}		
		hot=newHot;//更新热搜排行
	}
			
	//对购买后的热搜排名进行修改
	public void changeCostHot(String inputHot,int intputMunber,int cost) {	
		costHot[intputMunber]=1;//购买的热搜位为1
		String[] newHot = hot;
		int oldIndex = 0;			
		for(int i=0;i<newHot.length;i++) { //查找相应匹配的热搜
			if(newHot[i].equals(inputHot)) {//当输入的值与热搜可以匹配					
				oldIndex=i;//保存该热搜的原来位置
			}
		}
		if(hotMoney[intputMunber]== 0) {//将购买位的热搜换为购买的热搜
			String tempHot=newHot[oldIndex];
			newHot[oldIndex]=newHot[intputMunber];//交换更新热搜的位置
			newHot[intputMunber]=tempHot;
			int tempInt=hotnumbers[oldIndex];
			hotnumbers[oldIndex]=hotnumbers[intputMunber];//交换更新热搜的票数
			hotnumbers[intputMunber]=tempInt;
			hotMoney[intputMunber]=cost;//更新该热搜位的购买金额
		/*	
		if(oldIndex > intputMunber) {//如果购买的热搜位在原有位置之前
			for(int i=intputMunber; i<oldIndex;i++) {
				if(i == intputMunber) {//将购买位的热搜换为购买的热搜
					String tempHot=newHot[oldIndex];
					newHot[oldIndex]=newHot[intputMunber];//交换更新热搜的位置
					newHot[intputMunber]=tempHot;
					int tempInt=hotnumbers[oldIndex];
					hotnumbers[oldIndex]=hotnumbers[intputMunber];//交换更新热搜的票数
					hotnumbers[intputMunber]=tempInt;
					}else { //其余为顺位下移						
					}
				}				
			}else(oldIndex < intputMunber)//如果购买的热搜位在原有位置之后			
		*/							
		}else { //该热搜不是首次购买，原来的热搜被挤掉并消失不见	
			for(int i=0;i<newHot.length;i++) { //查找相应匹配的热搜
				if(newHot[i].equals(inputHot)) {//当输入的值与热搜可以匹配					
					oldIndex=i;//保存该热搜的原来位置
				}
			}
			String tempHot=newHot[oldIndex];
			newHot[oldIndex]=newHot[intputMunber];//交换更新热搜的位置
			newHot[intputMunber]=tempHot;
			int tempInt=hotnumbers[oldIndex];
			hotnumbers[oldIndex]=hotnumbers[intputMunber];//交换更新热搜的票数
			hotnumbers[intputMunber]=tempInt;
			hotMoney[intputMunber]=cost;//更新该热搜位的购买金额	 	
			newHot= so.removeString(newHot, oldIndex);//删除原来的购买的热搜
			hotnumbers=so.removeInt(hotnumbers, oldIndex);//删除原来的购买的热搜票数
			hotMoney=so.removeInt(hotMoney, hotMoney.length-1);//删除最后一位的购买热搜金额
		}			
		hot=newHot;//更新热搜排行		
		/*
		if(hotMoney[intputMunber]== 0) {//这是该热搜被首次购买
			if(oldIndex > intputMunber) {//如果购买的热搜位在原有位置之前)
				infrontCostSort(newHot,inputHot, intputMunber);
			}if(oldIndex < intputMunber) {//如果购买的热搜位在原有位置之后)
				afterCostSort(newHot,inputHot, intputMunber);
			}			
			
		*/	
	}
	
	//如果购买的热搜位在原有位置之前
	public static void infrontCostSort(String[] newHot,String inputHot,int intputMunber) {
		int len=hot.length;
		int i=len-1;
		int costHot=0;//买的热搜
		int costHotVote=0;
		while(i>=0 ) {
			if(!newHot[i].equals(inputHot)) {
				if(i==intputMunber) {
					if( costHot > 0 ) {
						newHot[i+costHot] = newHot[i];
						hotnumbers[i+costHot] = hotnumbers[i];
						costHot--;
					}else {
						newHot[i]=inputHot;
						hotnumbers[i+costHot] =costHotVote;
						i--;
					}							
				}else {
					newHot[i+costHot] = newHot[i];
					hotnumbers[i+costHot] = hotnumbers[i];
					i--;
				}							
			}else {	
				costHotVote=hotnumbers[i];
				costHot++;
				i--;
			}
		}		
		
	}
	
	//如果购买的热搜位在原有位置之后
	public static void afterCostSort(String[] newHot,String inputHot,int intputMunber) {
		int len=hot.length;
		int i=0;
		int costHot=0;//买的热搜
		int costHotVote=0;
		while(i < len ) {
			if(!newHot[i].equals(inputHot)) {
				if(i==intputMunber) {
					if( costHot > 0 ) {
						newHot[i-costHot] = newHot[i];
						hotnumbers[i-costHot] = hotnumbers[i];
						costHot--;
					}else {
						newHot[i]=inputHot;
						hotnumbers[i-costHot] =costHotVote;
						i++;
					}							
				}else {
					newHot[i-costHot] = newHot[i];
					hotnumbers[i-costHot] = hotnumbers[i];
					i++;
				}							
			}else {	
				costHotVote=hotnumbers[i];
				costHot++;
				i++;
			}
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
	
	//删除被覆盖的购买热搜
	private static String[] removeString(String[] src,int index) {
        int size = src.length; //
        int numMoved = size - index - 1;//计算预删除元素右边的元素个数
        String[] desc = Arrays.copyOf(src, size - 1);//复制成新数组，去掉尾部元素
        System.arraycopy(src, index+1, desc, index, numMoved);//复制的起始位置确定好，将源数组的右边的元素贴到新数组中。
        return desc;//返回新数组
    }
	
	//删除被覆盖的购买热搜
	private static int[] removeInt(int[] src,int index) {
	    int size = src.length; //
	    int numMoved = size - index - 1;//计算预删除元素右边的元素个数
	    int[] desc = Arrays.copyOf(src, size - 1);//复制成新数组，去掉尾部元素
	    System.arraycopy(src, index+1, desc, index, numMoved);//复制的起始位置确定好，将源数组的右边的元素贴到新数组中。
	    return desc;//返回新数组
	}
}
