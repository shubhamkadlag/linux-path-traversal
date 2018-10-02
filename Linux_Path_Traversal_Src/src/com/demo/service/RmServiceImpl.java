package com.demo.service;

import com.demo.dto.Tree;
import com.demo.dto.TreeNode;


/**
 * 
 * Service to remove directories.
 * This service has been enabled to remove parent directory or current directory without any errors.
 * However to see the effect you would have to navigate to root directory.
 * @author Shubham Kadlag
 *
 */

public class RmServiceImpl implements Service {
	
	   public String execute(String command,Tree tree){
		      
			   StringBuilder message=new StringBuilder("");
			   
			   command=command.trim();	  
			   
			   String dirtobedeleted[] =command.split("\\s+");
			   
			   for(String dir:dirtobedeleted) {
				   
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
						 
						 if(temp.getChild(nodetobechecked)!=null && i==dirpath.length-1) {
							
							 temp.removeChild(nodetobechecked);
							
							 //temp=temp.getChild(nodetobechecked);
							 message.append("/"+dir+" deleted successfully \n");
							 
							 
							 
						 }else if(temp.getChild(nodetobechecked)==null){
							 
							 message.append("Error: /"+dir+" : No such directory found \n");
							 break;
							 
						 }else  {
							 
							 temp=temp.getChild(nodetobechecked);
							
						 }
						  
					   }
					   
				   }else {
					   
					   String dirpath[] =dir.split("/");
					   TreeNode temp=tree.getCurr();
					   
					   
					   for(int i=0;i<dirpath.length;i++) {
						   
						 TreeNode nodetobechecked= new TreeNode(dirpath[i],temp);
						 
						 if(temp.getChild(nodetobechecked)!=null && i==dirpath.length-1) {
							 
							 temp.removeChild(nodetobechecked);
								
							 //temp=temp.getChild(nodetobechecked);
							 message.append("/"+dir+" deleted successfully \n");
							 
							 
						 }else if(temp.getChild(nodetobechecked)==null){
							 
							 message.append("Error: "+dir+" : No such directory found \n");
							 break;
							 
						 }else  {
							 
							 temp=temp.getChild(nodetobechecked);
							 
							 /*if(i==dirpath.length-1)  {
								 
								 message.append(dir+" already exists \n");
								 break;
							 }*/
						 }
						  
					   }
				   }
				   
			   }  
			   	
			      return message.toString().trim();
		   }

		   @Override
		   public String getName() {
		      return "rm";
		   }

}
