package net_EXCELK;

public class Users {
	private String userId;
	private String passWd;
	private String name;
	private String email;
	
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", passWd=" + passWd + ", name=" + name + ", email=" + email + "]";
	}	
	
}
