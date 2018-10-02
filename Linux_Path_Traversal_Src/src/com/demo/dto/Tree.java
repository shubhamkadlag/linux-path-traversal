package com.demo.dto;

 

/**
 * @brief  A N-ary tree implemntation to store the directory Structure.
 * 		   It consists of two pointers, one pointing to the root treenode and
 * 		   another to the current treenode i.e current directory.
 * 
 * 
 * @author Shubham Kadlag
 * 
 * @date 23-09-2018
 *
 */
public class Tree {

     

    private TreeNode root;

    private TreeNode curr;

    /**
     * Default Constructor to create the root Treenode and point current Treenode to root.
     */
    public Tree() {

        super();
        root=new TreeNode("/",new TreeNode());
        curr=root;

    }
 
 
 
    /**
     * @return root treenode
     */
    public TreeNode getRoot() {
 
        return this.root;
 
    }
 
 
 
    /**
     * @param set root
     */
    public void setRoot(TreeNode root) {
 
        this.root = root;
 
    }
 
    /**
     * @return current treenode 
     */
    public TreeNode getCurr() {
    	 
        return this.curr;
 
    }
 
 
 
    /**
     * @param set current treenode i.e directory
     */
    public void setCurr(TreeNode curr) {
 
        this.curr = curr;
 
    }
 
 
    
 
 
 
    /**
     * @return true if root is null or else false
     */
    public boolean isEmpty() {
 
        return (root == null);
 
    }
 
 
}
