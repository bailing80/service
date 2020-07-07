package com.yian.admin.model;

public class SysDoctor extends BaseModel
{

	private Long id;
	
    private String provincial;

    private String city;

    private String hospitalName;

    private String hospitalAddres;

    private String outpatientVolume;

    private Integer remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvincial() {
		return provincial;
	}

	public void setProvincial(String provincial) {
		this.provincial = provincial;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	


	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalAddres() {
		return hospitalAddres;
	}

	public void setHospitalAddres(String hospitalAddres) {
		this.hospitalAddres = hospitalAddres;
	}

	public String getoutpatientVolume() {
		return outpatientVolume;
	}

	public void setoutpatientVolume(String outpatientVolume) {
		outpatientVolume = outpatientVolume;
	}

	public Integer getRemarks() {
		return remarks;
	}

	public void setRemarks(Integer remarks) {
		this.remarks = remarks;
	}
}