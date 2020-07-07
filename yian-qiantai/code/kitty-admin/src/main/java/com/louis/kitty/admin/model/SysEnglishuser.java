package com.louis.kitty.admin.model;


import java.util.Date;

public class SysEnglishuser extends BaseModel
{

	//private Integer id;

	private String phone;

	private String email;

	private String password;

	private String hospital;

	private String identificationnumber;

	private String name;

	private String diccode;
	
    private String salt;
    
    private Byte status;
	   
    private Date createtime;

	

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getIdentificationnumber() {
		return identificationnumber;
	}

	public void setIdentificationnumber(String identificationnumber) {
		this.identificationnumber = identificationnumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiccode() {
		return diccode;
	}

	public void setDiccode(String diccode) {
		this.diccode = diccode;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
    
    

	
}
