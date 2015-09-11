package com.youniforever.shop.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class AccountDto {
	
	@Data
	@Getter
	@Setter
	public static class Create {
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NotBlank @Size(min = 5)
		private String username;
		
		@NotBlank @Size(min = 5)
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
	
	@Data
	@Getter
	@Setter
	public static class Response {
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String username;
		private String email;
		private String fullName;
		
		@Temporal(TemporalType.TIMESTAMP)
		private Date joined;
		
		@Temporal(TemporalType.TIMESTAMP)
		private Date updated;

		@Override
		public String toString() {
			return "Create [id=" + id + ", username=" + username + ", email=" + email
					+ ", fullName=" + fullName + ", joined=" + joined + ", updated=" + updated + "]";
		}
	}
}
