package com.twu;

public class userSelection {
	
	userIndex1 ui=new userIndex1();
	
	//对用户输入的数字进行判断
	public void whatNumber(int inputNumbers) {
		userIndex1 ui=new userIndex1();		
		selectionOperation so=new selectionOperation();
		if(inputNumbers==1) {
			so.userNumerIs1();			
		}else if(inputNumbers==2) {
			so.userNumerIs2();	
		}else if(inputNumbers==3){	
			so.userNumerIs3();			
		}else if(inputNumbers==4) {
			so.userNumerIs4();	
		}else {	
			ui.print(); 
		}
			
	}

}
