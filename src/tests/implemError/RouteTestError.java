package tests.implemError;

import implemError.RouteImplError;
import org.junit.Before;
import tests.AbstractRouteTest;
import contracts.RouteContract;

public class RouteTestError   extends AbstractRouteTest {
	@Override
	@Before
	public void before() {
		route = new RouteContract(new RouteImplError());
	}
}
