package com.erestaurant.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRole implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long userRoleID;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private Users user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role_id")
	private Role role;

	public UserRole(){}
	
	public UserRole(Users user, Role role){
		this.user=user;
		this.role=role;
	}

	public long getUserRoleID() {
		return userRoleID;
	}

	public void setUserRoleID(long userRoleID) {
		this.userRoleID = userRoleID;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
