package com.erestaurant.model;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String authoriy;
	
	public Authority(String authority){
		this.authoriy = authority;
	}

	@Override
	public String getAuthority() {
		return authoriy;
	}

}
