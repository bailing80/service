package com.yian.admin.model;

public class SysQRCode extends BaseModel
{
	private Long id;
	
    private String name;

    private byte[] qrcodetext;

    private String createtime;
    
    private String pathqrcode;
    
    private String stats;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getQrcodetext() {
		return qrcodetext;
	}

	public void setQrcodetext(byte[] qrcodetext) {
		this.qrcodetext = qrcodetext;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getPathqrcode() {
		return pathqrcode;
	}

	public void setPathqrcode(String pathqrcode) {
		this.pathqrcode = pathqrcode;
	}

	public String getStats() {
		return stats;
	}

	public void setStats(String stats) {
		this.stats = stats;
	}

	
}