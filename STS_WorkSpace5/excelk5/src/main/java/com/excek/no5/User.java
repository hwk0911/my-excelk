package com.excek.no5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(nullable = false, length = 20, unique = true, name = "USER_ID")
	private String userId;

	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "EMAIL")
	private String email;

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean matchPassword(String newPassword) {
		if (newPassword == null) {
			return false;
		}

		return newPassword.equals(password);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void update(User newUser) {
		this.password = newUser.password;
		this.email = newUser.email;
		this.name = newUser.name;
	}

	public boolean matchId(Long newId) {
		if (newId == null) {
			return false;
		}

		return newId.equals(id);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
	
	public String getUserId () {
		return this.userId;
	}

}
