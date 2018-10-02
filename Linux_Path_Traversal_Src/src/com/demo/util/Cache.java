package com.demo.util;

import java.util.ArrayList;
import java.util.List;

import com.demo.service.Service;

/**
 * Taking into account the extensibility of the application, in order to provide better
 * the Cache class stores the List of services which are  called at least once. However the class does
 * not include method to limit the size of List , which is important once the total number of services is large.
 * 
 * ServiceLocator initially tries to get the service from cache before looking up in InitialContext.
 * 
 * @author Shubham Kadlag
 *
 */
public class Cache {

   private List<Service> services;

   public Cache(){
      services = new ArrayList<Service>();
   }

   public Service getService(String serviceName){
   
	   
	   
      for (Service service : services) {
         if(service.getName().equalsIgnoreCase(serviceName)){
           
            return service;
         }
      }
      return null;
   }

   public void addService(Service newService){
      boolean exists = false;
      
      for (Service service : services) {
         if(service.getName().equalsIgnoreCase(newService.getName())){
            exists = true;
         }
      }
      if(!exists){
         services.add(newService);
      }
   }
}