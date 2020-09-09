package com.twu;

public class adminSelection {
	userIndex1 ui=new userIndex1();
	
	//对用户输入的数字进行判断
	public void whatNumber(int inputNumbers) {
		selectionOperation so=new selectionOperation();
		if(inputNumbers==1) {
			so.adminNumerIs1();
		}else if(inputNumbers==2) {
			so.adminNumerIs2();
		}else if(inputNumbers==3){			
			so.adminNumerIs3();			
		}else {			
			ui.print(); 
		}
	}
}
