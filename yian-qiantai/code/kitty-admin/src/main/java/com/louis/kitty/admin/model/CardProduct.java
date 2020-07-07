package com.louis.kitty.admin.model;

public class CardProduct extends BaseModel
{
	
	private Long id;
	
	private String productmodel;
	
	private String ceset;
	
	private String serialnumber;
	
	private  String model;
	
	private String expiration;
	
	private String manmade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductmodel() {
		return productmodel;
	}

	public void setProductmodel(String productmodel) {
		this.productmodel = productmodel;
	}

	public String getCeset() {
		return ceset;
	}

	public void setCeset(String ceset) {
		this.ceset = ceset;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public String getManmade() {
		return manmade;
	}

	public void setManmade(String manmade) {
		this.manmade = manmade;
	}
	
	
}
