package com.demo.cookiesandsessions.model;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserInfoDTO {
	
	@NotBlank(message="* Your Name should be mandatory")
	@Size(min=5,max=15,message="* Your Name should be between 5 to 15 characters")
	private String yourName="Varun";
	
	@NotBlank(message="* Crush Name should be mandatory")
	@Size(min=5,max=15,message="* Crush Name should be between 5 to 15 characters")
	private String crushName="Aliya";
	
	private boolean termsAndConditions;
	
	public String getYourName() {
		return yourName;
	}
	public void setYourName(String yourName) {
		this.yourName = yourName;
	}
	public String getCrushName() {
		return crushName;
	}
	public void setCrushName(String crushName) {
		this.crushName = crushName;
	}
	@AssertTrue(message="* You have to agree to use our app")
	public boolean isTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(boolean termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
	@Override
	public String toString() {
		return "UserInfoDTO [yourName=" + yourName + ", crushName=" + crushName + "]";
	}
	

}
