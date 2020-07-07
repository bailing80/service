package com.louis.kitty.admin.model;

public class SysClientAndExpress //extends BaseModel
{
	private Long id;
	
	private String activityname;
	
    private String name;

    private String telephone;

    private String toLocation;
    
    private String fromLocation;

	private String expressName;
    
    private String expressNumber;

	private String giftname;

	private String giftSend;
    
    private String sendtime;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	
	public String getExpressName()
	{
		return expressName;
	}
	
	public void setExpressName(String expressName)
	{
		this.expressName = expressName;
	}
	
	public String getExpressNumber()
	{
		return expressNumber;
	}
	
	public void setExpressNumber(String expressNumber)
	{
		this.expressNumber = expressNumber;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	
    
    public String getGiftname() {
		return giftname;
	}

	public void setGiftname(String giftname) {
		this.giftname = giftname;
	}

	public String getGiftSend() {
		return giftSend;
	}

	public void setGiftSend(String giftSend) {
		this.giftSend = giftSend;
	}
    
    public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

}