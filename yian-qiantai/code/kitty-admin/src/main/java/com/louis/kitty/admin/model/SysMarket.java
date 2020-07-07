package com.louis.kitty.admin.model;

public class SysMarket extends BaseModel
{

	private Long id;
	
	private String activityname;
	
	private String name;

    private String tel;

    private String hospital;

    private String departmentoffices;

    private String address;

    private Integer sysId;

    private String sellname;
    
	private String createtime;

    /**
     * --�����
     */
    private Integer qrcodeId;
    
    private Byte delFlag;
    
    
	public Byte getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Byte delFlag) {
		this.delFlag = delFlag;
	}

    public String getActivityname() {
		return activityname;
	}

	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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




	

}