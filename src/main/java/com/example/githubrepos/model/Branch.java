package com.example.githubrepos.model;

public class Branch {
private String name;
private Commit LastCommitSha;

// Constructors, getters, and setters

public Commit getCommit(){
	return LastCommitSha;
}

public void setCommit( Commit commit ){
	this.LastCommitSha = commit;
}


// getters and setters

public String getName(){
	return name;
}

public void setName( String name ){
	this.name = name;
}


}
