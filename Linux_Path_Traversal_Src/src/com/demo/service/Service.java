package com.demo.service;

import com.demo.dto.Tree;

/**
 * Service interface consisting two abstract methods.
 *  One for returning Service Name and other to execute a service.
 * @author Shubham Kadlag
 */
public interface Service {
	
	   public String getName();
	   
	   public String execute(String command, Tree tree);
	   
}