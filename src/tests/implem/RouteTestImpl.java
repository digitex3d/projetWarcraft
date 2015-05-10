package tests.implem;

import implemError.RouteImplError;
import implementations.RouteImpl;

import org.junit.Before;

import tests.AbstractRouteTest;
import contracts.RouteContract;

public class RouteTestImpl   extends AbstractRouteTest {
	@Override
	@Before
	public void before() {
		route = new RouteImpl();
	}
}
