package com.demo.util;

import com.demo.exception.NoSuchServiceFoundException;
import com.demo.service.Service;

/**
 * 
 * Service Locator in order to locate the requested service.
 * Initially tries to get the service from cache, in case service is not present in  cache it looks it up 
 * in InitialContext and also add the service to cache. 
 * 
 * @author Shubham Kadlag
 *
 */
public class ServiceLocator {
	
	   private static Cache cache;

	   static {
	      cache = new Cache();		
	   }

	   public static Service getService(String servicename) throws NoSuchServiceFoundException{

	      Service service = cache.getService(servicename);

	      if(service != null){
	         return service;
	      }

	      InitialContext context = new InitialContext();
	      Service servicefromcontext = (Service)context.lookup(servicename);
	      
	      if(servicefromcontext==null) throw new NoSuchServiceFoundException();
	      
	      cache.addService(servicefromcontext);
	      return servicefromcontext;
	   }
	}
