package com.demo.service;

import com.demo.dto.Tree;
import com.demo.dto.TreeNode;


/**
 * 
 * Service to Create directories.
 * This service is not enabled to create directories recursively. 
 * Hence, you would have to create a parent directory before creating child directory.
 * 
 * @author Shubham Kadlag
 *
 */
public class MkdirServiceImpl implements Service {
	
	   public String execute(String command, Tree tree){
		   StringBuilder message=new StringBuilder("");
		   
		   command=command.trim();	  
		   
		   String dirtobecreated[] =command.split("\\s+");
		   
		   for(String dir:dirtobecreated) {
			   
			   dir=dir.trim();
			   if(dir.endsWith("/")) {
				   
				   dir=dir.substring(0, dir.length()-1);
				   
			   }
			   
			   if(dir.startsWith("/")	) {
				   dir=dir.substring(1, dir.length());
				   String dirpath[] =dir.split("/");
				   TreeNode temp=tree.getRoot();
				   
				   
				   for(int i=0;i<dirpath.length;i++) {
					   
					 TreeNode nodetobechecked= new TreeNode(dirpath[i],temp);
					 
					 if(temp.getChild(nodetobechecked)==null && i==dirpath.length-1) {
						 
						 temp.addChild(nodetobechecked);
						 //temp=temp.getChild(nodetobechecked);
						 message.append("/"+dir+" created successfully \n");
						 
						 
						 
					 }else if(temp.getChild(nodetobechecked)==null){
						 
						 message.append("Error: /"+dir+" : No such directory found. Please create parent directory first. \n");
						 break;
						 
					 }else  {
						 
						 temp=temp.getChild(nodetobechecked);
						 
						 if(i==dirpath.length-1) { 
							 message.append("/"+dir+" already exists \n");
							 break;
						 }
					 }
					  
				   }
				   
			   }else {
				   
				   String dirpath[] =dir.split("/");
				   TreeNode temp=tree.getCurr();
				   
				   
				   for(int i=0;i<dirpath.length;i++) {
					   
					 TreeNode nodetobechecked= new TreeNode(dirpath[i],temp);
					 
					 if(temp.getChild(nodetobechecked)==null && i==dirpath.length-1) {
						 
						 temp.addChild(nodetobechecked);
						 //temp=temp.getChild(nodetobechecked);
						 message.append(dir+" created successfully \n");
						 
						 
						 
					 }else if(temp.getChild(nodetobechecked)==null){
						 
						 message.append("Error: "+dir+" : No such directory found. Please create parent directory first. \n");
						 break;
						 
					 }else  {
						 
						 temp=temp.getChild(nodetobechecked);
						 
						 if(i==dirpath.length-1)  {
							 
							 message.append(dir+" already exists \n");
							 break;
						 }
					 }
					  
				   }
				   
			   
				   
				   
			   }
			   
		   }  
		   	
		      return message.toString().trim();
		   }

		   @Override
		   public String getName() {
		      return "mkdir";
		   }

}
