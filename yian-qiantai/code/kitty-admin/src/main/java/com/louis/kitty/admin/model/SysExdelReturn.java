package com.louis.kitty.admin.model;

public class SysExdelReturn extends BaseModel
{

	private Long id;
	
    private String message;

    private Integer state;      
    
    private Integer statusExp;    

    private String conditionA;

    private Integer ischeck;

    private String com;

    private String nu;
    
    private String data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getStatusExp() {
		return statusExp;
	}

	public void setStatusExp(Integer statusExp) {
		this.statusExp = statusExp;
	}

	public String getConditionA() {
		return conditionA;
	}

	public void setConditionA(String conditionA) {
		this.conditionA = conditionA;
	}

	public Integer getIscheck() {
		return ischeck;
	}

	public void setIscheck(Integer ischeck) {
		this.ischeck = ischeck;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getNu() {
		return nu;
	}

	public void setNu(String nu) {
		this.nu = nu;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}


}