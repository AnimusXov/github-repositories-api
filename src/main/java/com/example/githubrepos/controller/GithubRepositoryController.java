package com.example.githubrepos.controller;

import com.example.githubrepos.model.Branch;
import com.example.githubrepos.model.ErrorResponse;
import com.example.githubrepos.model.Repository;
import com.example.githubrepos.model.RepositoryResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class GithubRepositoryController{

private static final String GITHUB_API_BASE_URL = "https://api.github.com";

@GetMapping (value = "/repositories/{username}", produces = {MediaType.APPLICATION_JSON_VALUE,
		MediaType.APPLICATION_XML_VALUE})
public ResponseEntity<?> getRepositories( @PathVariable String username,
                                          @RequestHeader ("Accept") String acceptHeader ){
	HttpHeaders headers = new HttpHeaders();
	headers.set("Accept", acceptHeader);
	
	RestTemplate restTemplate = new RestTemplate();
	String apiUrl = GITHUB_API_BASE_URL + "/users/" + username + "/repos";
	
	try {
		Repository[] repositories = restTemplate.getForObject(apiUrl, Repository[].class, headers);
		
		List<RepositoryResponse> filteredRepositories = new ArrayList<>();
		
		if (MediaType.APPLICATION_XML_VALUE.equals(acceptHeader)) {
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Unsupported media type");
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
		}
		
		for ( Repository repository : Objects.requireNonNull(repositories) ) {
			if ( !repository.isFork() ) {
				String repoFullName = username + "/" + repository.getName();
				String branchesUrl = "https://api.github.com/repos/" + repoFullName + "/branches";
				HttpHeaders branchesHeaders = new HttpHeaders();
				branchesHeaders.set("Accept", acceptHeader);
				
				try {
					ResponseEntity<Branch[]> branchesResponse = restTemplate.exchange(branchesUrl, HttpMethod.GET,
							new HttpEntity<>(branchesHeaders), Branch[].class);
					
					if ( branchesResponse.getStatusCode() == HttpStatus.OK ) {
						Branch[] branches = branchesResponse.getBody();
						
						repository.setBranches(branches);
					} else {
						// Handle the case when the branches API request is not successful
						repository.setBranches(new Branch[0]);
					}
					RepositoryResponse repositoryResponse = new RepositoryResponse();
					repositoryResponse.setName(repository.getName());
					repositoryResponse.setOwner(repository.getOwner());
					repositoryResponse.setBranches(repository.getBranches());
					filteredRepositories.add(repositoryResponse);
				} catch ( HttpClientErrorException.NotFound ex ) {
					// Ignore the NotFound exception when retrieving branches
					repository.setBranches(new Branch[0]);
					RepositoryResponse repositoryResponse = new RepositoryResponse();
					repositoryResponse.setName(repository.getName());
					repositoryResponse.setOwner(repository.getOwner());
					repositoryResponse.setBranches(repository.getBranches());
					filteredRepositories.add(repositoryResponse);
				}
			}
		}
		
		

		
		return ResponseEntity.ok(filteredRepositories);
		
	} catch ( HttpClientErrorException.NotFound ex ) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User not found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	} catch ( HttpClientErrorException.NotAcceptable ex ) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Not acceptable");
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponse);
	} catch ( Exception ex ) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal server " + "error");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
}

}

