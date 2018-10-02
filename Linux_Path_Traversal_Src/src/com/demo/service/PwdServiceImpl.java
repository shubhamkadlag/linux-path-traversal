package com.demo.service;

import com.demo.dto.Tree;
import com.demo.dto.TreeNode;


/**
 * 
 * Service to Output the path of current directory
 * @author Shubham Kadlag
 *
 */
public class PwdServiceImpl implements Service {
	
	   public String execute(String command, Tree tree){
		   StringBuilder sb=new StringBuilder("");
		   
		   TreeNode temp=tree.getCurr();
		   
		   if(temp.equals(tree.getRoot())) return "PATH: /";
		   
		   
		   while(!temp.equals(tree.getRoot())) {
			   
			   sb.insert(0, "/"+temp.getData());
			   temp=temp.getParent();
			   
		   }
		   
		   sb.insert(0, "PATH: ");
			return sb.toString();
		   }

		   @Override
		   public String getName() {
		      return "pwd";
		   }

}
