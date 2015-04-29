package tests.contracts;

import implementations.RouteImpl;

import org.junit.Before;
import org.junit.Test;

import tests.AbstractRouteTest;

import contracts.RouteContract;
import exceptions.PreconditionError;

public class RouteContractTest   extends AbstractRouteTest {
	@Override
	@Before
	public void before() {
		route = new RouteContract(new RouteImpl());

	}
	
	@Test(expected=PreconditionError.class)
	public void testInitFail() {
		// condition initiale : aucune

		// opération
		route.init(11, 11, 11, 11, -2 );

		// oracle un message d'erreur est attendu : Cf. @Test(expected=PreconditionError.class)	
	}
}
