package tests;

import static org.junit.Assert.assertTrue;

import com.sun.istack.internal.logging.Logger;


public class AbstractAssertion {

	protected void assertPerso(String message, boolean expression) {
		if(!expression)
			System.out.println(message);
		assertTrue(message, expression);
	}
	
	public Logger getLogger(){
		return  Logger.getLogger( this.getClass() );
		
	}
	
	
}
