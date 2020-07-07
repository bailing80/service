package com.louis.kitty.admin.model;

public class CardPersonalRecord extends BaseModel
{	
	private Long id;

	private String customercode;
	
	private String hospital;
	
	private String room;
	
	private String breathetype;
	
	private String othersA;
	
	private String psgresult;
	
	private String saturation;
	
	private String complication;
	
	private String othersB;
	
	private String parameters;
	
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

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getBreathetype() {
		return breathetype;
	}

	public void setBreathetype(String breathetype) {
		this.breathetype = breathetype;
	}

	public String getOthersA() {
		return othersA;
	}

	public void setOthersA(String othersA) {
		this.othersA = othersA;
	}

	public String getPsgresult() {
		return psgresult;
	}

	public void setPsgresult(String psgresult) {
		this.psgresult = psgresult;
	}

	public String getSaturation() {
		return saturation;
	}

	public void setSaturation(String saturation) {
		this.saturation = saturation;
	}

	public String getComplication() {
		return complication;
	}

	public void setComplication(String complication) {
		this.complication = complication;
	}

	public String getOthersB() {
		return othersB;
	}

	public void setOthersB(String othersB) {
		this.othersB = othersB;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
}