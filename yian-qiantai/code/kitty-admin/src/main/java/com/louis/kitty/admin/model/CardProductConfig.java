package com.louis.kitty.admin.model;

public class CardProductConfig extends BaseModel
{
	private Long id;

	private String customercode;
	
	private String productmodel;
	
	private String serialnumber;
	
	private String sellername;
	
	private String purchasedate;
	
	private String appearance;
	
	private String packing;
	
	private String accessories;
	
	private String remarks;
	
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

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getPurchasedate() {
		return purchasedate;
	}

	public void setPurchasedate(String purchasedate) {
		this.purchasedate = purchasedate;
	}

	public String getAppearance() {
		return appearance;
	}

	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}

	public String getPacking() {
		return packing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}

	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}