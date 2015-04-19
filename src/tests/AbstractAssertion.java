package tests;

import static org.junit.Assert.assertTrue;


public class AbstractAssertion {
	
	protected void assertPerso(String message, boolean expression) {
		if(!expression)
			System.out.println(message);
		assertTrue(message, expression);
	}
}
