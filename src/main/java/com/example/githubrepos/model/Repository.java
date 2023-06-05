package com.example.githubrepos.model;



public class Repository{
private String name;
private Owner owner;
private boolean fork;
private Branch[] branches;

// getters and setters

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

public boolean isFork(){
	return fork;
}

public void setFork( boolean fork ){
	this.fork = fork;
}

public Branch[] getBranches(){
	return branches;
}

public void setBranches( Branch[] branches ){
	this.branches = branches;
}
}
