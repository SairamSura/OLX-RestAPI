package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(value="OLX Login DTO")
public class User {
	//@ApiModelProperty(value="User Id")
	//private int id;
	@ApiModelProperty(value="User FirstName")
	private String firstName;
	@ApiModelProperty(value="User LastName")
	private String lastName;
	@ApiModelProperty(value="User UserName")
	private String userName;
	@ApiModelProperty(value="User Password")
	private String password;
	@ApiModelProperty(value="User Email")
	private String email;
	@ApiModelProperty(value="User PhoneNumber")
	private long phone;
	public User(int id, String firstName, String lastName, String userName, String password, String email, long phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}
	public User() {
		super();
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", email=" + email + ", phone=" + phone + "]";
	}
	
	
}
