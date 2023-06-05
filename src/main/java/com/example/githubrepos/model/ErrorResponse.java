package com.example.githubrepos.model;

public class ErrorResponse{
private int status;
private String message;

public ErrorResponse( int status, String message ){
	this.status = status;
	this.message = message;
}
// getters and setters

public int getStatus(){
	return status;
}

public void setStatus( int status ){
	this.status = status;
}

public String getMessage(){
	return message;
}

public void setMessage( String message ){
	this.message = message;
}
}
