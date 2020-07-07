package com.louis.kitty.admin.model;

public class SysHospital extends BaseModel
{
	private Long id;
	
	private String province;
	
	private String district;
	
	private String name;

	private String hospitalAddres;
	
	private String grade;
	
	private String outpatientVolume;
	
	private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}


	public String getHospitalAddres() {
		return hospitalAddres;
	}

	public void setHospitalAddres(String hospitalAddres) {
		this.hospitalAddres = hospitalAddres;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getOutpatientVolume() {
		return outpatientVolume;
	}

	public void setOutpatientVolume(String outpatientVolume) {
		this.outpatientVolume = outpatientVolume;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
 }