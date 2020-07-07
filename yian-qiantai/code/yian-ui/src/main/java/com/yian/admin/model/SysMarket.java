package com.yian.admin.model;

public class SysMarket extends BaseModel
{

	private Long id;
	
    private String name;

    private String tel;

    private String hospital;

    private String departmentoffices;

    private String address;

    private Integer sysId;

    private String sellname;
    
    private String createtime;

    /**
     * --活动名称
     */
    private Integer qrcodeId;
    
    private Byte delFlag;
    
    private String activityname;
    
	public Byte getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Byte delFlag) {
		this.delFlag = delFlag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDepartmentoffices() {
		return departmentoffices;
	}

	public void setDepartmentoffices(String departmentoffices) {
		this.departmentoffices = departmentoffices;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getSysId() {
		return sysId;
	}

	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getSellname() {
		return sellname;
	}

	public void setSellname(String sellname) {
		this.sellname = sellname;
	}

	public Integer getQrcodeId() {
		return qrcodeId;
	}

	public void setQrcodeId(Integer qrcodeId) {
		this.qrcodeId = qrcodeId;
	}

	public String getActivityname() {
		return activityname;
	}

	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}


	

}