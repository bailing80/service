package com.louis.kitty.admin.model;

public class CardPersonal extends BaseModel
{	
	private Long id;

	private String customercode;
	
	private String name;
	
	private String sex;

	private String age;
	
	//private String birthday;
	
	//private String height;
	
	//private String weight;
	
	private String telephone;
	
	private String buychannel;

	private String province;
	
	private String city;
	
	private String productmodel;
	
	private String serialnumber;
	
	private String purchasedate;
	
	//private String homephone;

	//private String address;
	
	private String createtime;
	
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBuychannel() {
		return buychannel;
	}

	public void setBuychannel(String buychannel) {
		this.buychannel = buychannel;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProductmodel() {
		return productmodel;
	}

	public void setProductmodel(String productmodel) {
		this.productmodel = productmodel;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getPurchasedate() {
		return purchasedate;
	}

	public void setPurchasedate(String purchasedate) {
		this.purchasedate = purchasedate;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}