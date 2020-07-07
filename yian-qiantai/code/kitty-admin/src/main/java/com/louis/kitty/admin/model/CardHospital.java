package com.louis.kitty.admin.model;

public class CardHospital extends BaseModel
{
	private Long id;
	
	private String customercode;
	
	private String name;

    private String address;

    private String contacts;

    private String officephone;

    private String telephone;
    
    private String office;
    
    private String officer;
    
	private String createtime;
	
	private String serialnumber;

	
	
	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomercode() {
		return customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getOfficephone() {
		return officephone;
	}

	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getOfficer() {
		return officer;
	}

	public void setOfficer(String officer) {
		this.officer = officer;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}