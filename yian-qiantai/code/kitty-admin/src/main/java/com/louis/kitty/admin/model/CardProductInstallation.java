package com.louis.kitty.admin.model;

public class CardProductInstallation extends BaseModel
{
	private Long id;

	private String customercode;
	
	private String requirements;
	
	private String selftest;
	
	private String usertraining;
	
	private String logoplacement;
	
	private String installconclusion;
	
	private String installer;
	
	private String installdate;
	
	private String acceptor;
	
	private String acceptdate;
	
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

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public String getSelftest() {
		return selftest;
	}

	public void setSelftest(String selftest) {
		this.selftest = selftest;
	}

	public String getUsertraining() {
		return usertraining;
	}

	public void setUsertraining(String usertraining) {
		this.usertraining = usertraining;
	}

	public String getLogoplacement() {
		return logoplacement;
	}

	public void setLogoplacement(String logoplacement) {
		this.logoplacement = logoplacement;
	}

	public String getInstallconclusion() {
		return installconclusion;
	}

	public void setInstallconclusion(String installconclusion) {
		this.installconclusion = installconclusion;
	}

	public String getInstaller() {
		return installer;
	}

	public void setInstaller(String installer) {
		this.installer = installer;
	}

	public String getInstalldate() {
		return installdate;
	}

	public void setInstalldate(String installdate) {
		this.installdate = installdate;
	}

	public String getAcceptor() {
		return acceptor;
	}

	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}

	public String getAcceptdate() {
		return acceptdate;
	}

	public void setAcceptdate(String acceptdate) {
		this.acceptdate = acceptdate;
	}
}