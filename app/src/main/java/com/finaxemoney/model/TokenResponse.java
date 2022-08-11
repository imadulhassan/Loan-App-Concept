package com.finaxemoney.model;

import com.google.gson.annotations.SerializedName;

public class TokenResponse {

	@SerializedName("cftoken")
	private String cftoken;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setCftoken(String cftoken){
		this.cftoken = cftoken;
	}

	public String getCftoken(){
		return cftoken;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"TokenResponse{" + 
			"cftoken = '" + cftoken + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}