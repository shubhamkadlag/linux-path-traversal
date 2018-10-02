package com.demo.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.demo.console.JavaConsole;
import com.demo.dto.Tree;
import com.demo.exception.NoSuchServiceFoundException;
import com.demo.util.ServiceLocator;

/**
 * @brief Application class.
 * 
 * @author Shubham Kadlag
 *
 * @date 23-09-2018 
 * 
 */
public class ConsoleRunner {

	private static boolean fContinue = true;
	private static JavaConsole console;	
	private static Tree tree;
	
	

	/**
	 * @brief the program entry point
	 * @param args the command line arguments
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		console = new JavaConsole();
		
		
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
		tree=new Tree();


		while(fContinue)
		{
			System.out.print("$ ");
						
			String command=br.readLine();
			
			if(command==null) {
				System.out.println("");
				continue;
			}
			
			
			command=sanitize(command);		
			
			if(command.trim().isEmpty()) {
			

				System.out.println("");
				continue;
			
			}
			
			
			command=command.trim();
			
			
			if("exit".equals(command)) break;
			
			String[] splitcommand=command.split("\\s+", 2);
			
			try {
				String commandarg=splitcommand.length>1?splitcommand[1]:"";
				String output=ServiceLocator.getService(splitcommand[0]).execute(commandarg,tree);
				if("Session Cleared Successfully".equals(output)) console.clear();
				System.out.println("\n"+output);
				
			}catch(NoSuchServiceFoundException e){
				System.out.println("\n ERR: CANNOT RECOGNIZE INPUT.");
			}
					
			
			
		}
		console.clear();
		System.exit(0);
		
	}
	
	
	
	/**
	 * Method to sanitize command line breaks, any other escape sequence and 
	 * any non-alphanumeric characters except backslash(/) as well replace multiple backslash with single one. 
	 * 
	 * @param command
	 * @return sanitized command
	 */
	private static String sanitize(String command) {
		
		command=command.replaceAll("\b", "");
		command=command.replaceAll("\t", "");
		command=command.replaceAll("\n", "");
		command=command.replaceAll("\f", "");
		command=command.replaceAll("\r", "");
		command=command.replaceAll("\\s+", " ");
		
		
		command=command.replaceAll("[^a-zA-Z0-9\\s/]", "");
		
		command=command.replaceAll("/+", "/");
		return command;
	}
}
