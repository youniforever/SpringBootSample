package com.youniforever.shop.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
public class Account {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	private String email;
	private String fullName;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date joined;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	@Override
	public String toString() {
		return "Create [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", fullName=" + fullName + ", joined=" + joined + ", updated=" + updated + "]";
	}
}
