package tests;

import static org.junit.Assert.assertTrue;


public class AbstractAssertion {

	protected void assertPerso(String message, boolean expression) {
		if(!expression){
			System.out.println( "___________________________________________");
			System.out.println( "Class de test:" + this.getClass().getName()) ;
			System.out.println( "Test name:" +  Thread.currentThread().getStackTrace()[2].getMethodName()) ;
			System.out.println( "Assert ===> " + message);
		assertTrue(message, expression);
		}
	}
	

	
	
}
