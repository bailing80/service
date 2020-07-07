package com.louis.kitty.admin.model;





public class CardXuliehao extends BaseModel
{
	
	private Long id;
	
	private String prodmodel;
	
	private String ceset;
	
	private String serial;

	private String model;
	
	private String expiration;
	
	private String manmade;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getCeset() {
		return ceset;
	}

	public void setCeset(String ceset) {
		this.ceset = ceset;
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

	public String getProdmodel() {
		return prodmodel;
	}

	public void setProdmodel(String prodmodel) {
		this.prodmodel = prodmodel;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	
	
}
