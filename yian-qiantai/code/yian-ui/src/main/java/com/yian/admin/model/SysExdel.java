package com.yian.admin.model;

public class SysExdel extends BaseModel
{

	private Long id;
	
    private String name;

    private String telephone;      
    
    private String toLocation;    

    private String fromLocation;

    private String expressName;

    private String expressNumber;

    private String giftSend;
    
    private String sparam;

    private String sign;

    private String createtime;

    private Byte delFlag;
    
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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSparam() {
		return sparam;
	}

	public void setSparam(String sparam) {
		this.sparam = sparam;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressNumber() {
		return expressNumber;
	}

	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	
	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String getGiftSend() {
		return giftSend;
	}

	public void setGiftSend(String giftSend) {
		this.giftSend = giftSend;
	}
}