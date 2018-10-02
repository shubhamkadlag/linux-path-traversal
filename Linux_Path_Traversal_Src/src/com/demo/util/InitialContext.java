package com.demo.util;


import java.util.HashMap;

import java.util.Map;

import com.demo.service.CdServiceImpl;
import com.demo.service.LsServiceImpl;
import com.demo.service.MkdirServiceImpl;
import com.demo.service.PwdServiceImpl;
import com.demo.service.RmServiceImpl;
import com.demo.service.Service;
import com.demo.service.SessionServiceImpl;

/**
 * 
 * Initial Context which holds all the supported services in a HashMap.
 * 
 * @author Shubham Kadlag
 *
 */
public class InitialContext {
	
	
	Map<String, Service> servicebyname;
	
	public InitialContext() {
		
		servicebyname=new HashMap<>();		
		servicebyname.put("cd",new CdServiceImpl());
		servicebyname.put("mkdir",new MkdirServiceImpl());
		servicebyname.put("rm",new RmServiceImpl());
		servicebyname.put("pwd",new PwdServiceImpl());
		servicebyname.put("ls",new LsServiceImpl());
		servicebyname.put("session",new SessionServiceImpl());
		
	}
	
	
	
	   public Object lookup(String servicename){	   
	      
	      return servicebyname.get(servicename);
	      
	   }
}
