package com.demo.exception;

/**
 * A RunTime Exception which is thrown when application does not support the service.
 * i.e any service other than : cd, ls, pwd, mkdir, rm, session.
 * 
 * @author Shubham Kadlag
 *
 */
public class NoSuchServiceFoundException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {		
		return super.getMessage().concat("NoSuchServiceFound. Please check the servicename");
	}
	
	

}
