package com.louis.kitty.admin.model;

import java.util.Date;

public class SysEngremark extends BaseModel
{
		
	private String remarks;
	
	private String phone;
	
	private String email;
	

	private Date createtime;
	 
	 
	 
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
	
}
