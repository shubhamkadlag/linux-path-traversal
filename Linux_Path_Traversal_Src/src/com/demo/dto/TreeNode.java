package com.demo.dto;


import java.util.ArrayList;

import java.util.List;
import java.util.ListIterator;



/**
 * 
 * @brief Class representation of a Node of a N-ary tree. Consists of String data which holds 
 * current TreeNode name, pointer to parent node and a list of all children.
 * 		 
 * 
 * @author Shubham kadlag
 *
 */
public class TreeNode {



   private String data;

   private List<TreeNode> children;

   private TreeNode parent;




   public TreeNode() {
	   super();
	   children=new ArrayList<>();
	   data="";
	   
   }

   public TreeNode getParent() {
	return parent;
   }




   public void setParent(TreeNode parent) {
	this.parent = parent;
   }

   public TreeNode(String data, TreeNode parent) {
	   
       
	   setParent(parent);
       setData(data);
       children=new ArrayList<>();
   }



   public List<TreeNode> getChildren() {

       return this.children;

   }



   public int getNumberOfChildren() {

       return getChildren().size();

   }



   public boolean hasChildren() {

       return (getNumberOfChildren() > 0 );

   }





   public TreeNode getChild(TreeNode child) {

	   List<TreeNode> children=this.getChildren();
	   
	   for(TreeNode temp:children ) {
		   
		   if(temp.equals(child)) return temp;
		   
	   }
	   return null;

   }

   public void addChild(TreeNode child) {
	   child.setParent(this);
       children.add(child);

   }

   public boolean removeChild(TreeNode child) {
	   
	   List<TreeNode> children=this.getChildren();
	   ListIterator<TreeNode> itr=children.listIterator();
	   while(itr.hasNext()) {
		   
		   if(child.equals(itr.next())) {
			   
			   itr.remove();
			   return true;
		   }
		   
	   }
	   
	   return false;
	      
   }

   public String getData() {

       return this.data;

   }



   public void setData(String data) {

       this.data = data;

   }



   public String toString() {

       return getData().toString();

   }



   public boolean equals(TreeNode node) {

       return node.getData().equals(getData());

   }



   public int hashCode() {

       return getData().hashCode();

   }

}


