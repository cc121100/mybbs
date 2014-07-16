package org.mybbs.base.init;

import java.util.Arrays;

public class TestBean {
	
	private String[] strArr;
	
	private String str;
	

	public TestBean() {
	}


	public TestBean(String[] strArr, String str) {
		this.strArr = strArr;
		this.str = str;
	}


	public String[] getStrArr() {
		return strArr;
	}

	public void setStrArr(String[] strArr) {
		this.strArr = strArr;
	}


	public String getStr() {
		return str;
	}


	public void setStr(String str) {
		this.str = str;
	}


	@Override
	public String toString() {
		return "TestBean [strArr=" + Arrays.toString(strArr) + ", str=" + str
				+ "]";
	}
	
	

}
