package com.louis.kitty.admin.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自动生成客户编码
 * @author hgliu
 * @date October 14, 2019
 */
public class CustomerCodeUtil {
	private String customercode;
	
	private static int numberCUS = 1;
	private static int numberCTS = 1;
	private static final String HOSPITAL = "HOSPITAL";
	private static final String PERSONAL = "PERSONAL";
	
	//医院编码规则：CUS-YYYYMMDD-number(三位数)
	//个人编码规则：CTS-YYYYMMDD-number(三位数)
	public String getLatestCustomerCode(String tag) {
		
		Date now=new Date();
		SimpleDateFormat f=new SimpleDateFormat("yyyyMMdd");
		String date = f.format(now);
		if(tag.equals(HOSPITAL))
		{
			this.customercode = "CUS-";
			this.customercode += date;
			this.customercode += '-';
			if(this.numberCUS < 10)
			{
				this.customercode += "00";
				this.customercode += Integer.toString(this.numberCUS);
			}
			else if(this.numberCUS < 100)
			{
				this.customercode += "0";
				this.customercode += Integer.toString(this.numberCUS);
			}
			else {
				this.customercode += Integer.toString(this.numberCUS);
			}
			numberCUS ++;	
		}
		else if(tag.equals(PERSONAL))
		{
			this.customercode = "CTS-";
			this.customercode += date;
			this.customercode += '-';
			if(this.numberCTS < 10)
			{
				this.customercode += "00";
				this.customercode += Integer.toString(this.numberCTS);
			}
			else if(this.numberCTS < 100)
			{
				this.customercode += "0";
				this.customercode += Integer.toString(this.numberCTS);
			}
			else {
				this.customercode += Integer.toString(this.numberCTS);
			}
			numberCTS ++;	
		}

		return this.customercode;
	}
}
