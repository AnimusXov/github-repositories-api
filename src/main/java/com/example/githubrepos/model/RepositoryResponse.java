package com.example.githubrepos.model;

public class RepositoryResponse {
private String name;
private Owner owner;
private Branch[] branches;

// Constructors, getters, and setters

public String getName(){
	return name;
}

public void setName( String name ){
	this.name = name;
}


public Owner getOwner(){
	return owner;
}

public void setOwner( Owner owner ){
	this.owner = owner;
}

public Branch[] getBranches(){
	return branches;
}

public void setBranches( Branch[] branches ){
	this.branches = branches;
}
}
