package net_EXCELK;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id	
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, length=20)
	private String userId;
		
	@Column(nullable=false, length=20)
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
	
	public void update(User newUser) {
		// TODO Auto-generated method stub
		this.passWd = newUser.passWd;
		this.name = newUser.name;
		this.email = newUser.email;
	}	
	
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", passWd=" + passWd + ", name=" + name + ", email=" + email + "]";
	}
	
}
