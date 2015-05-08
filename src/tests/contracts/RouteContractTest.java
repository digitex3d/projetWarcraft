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
}
