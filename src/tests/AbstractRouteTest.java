package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.IRoute;

public abstract class AbstractRouteTest extends AbstractAssertion{

	protected IRoute route;
	

	@Before
	public abstract void before();

	@After
	public void after() {
		route = null;
	}
	
	@Test
	public void test0_1() {
		// condition initiale
		
		// opérations
		route = route.init(11, 11,11,11,2);
		
		// oracle
		assertPerso("init, La route est crée avec une mauvaise position x", route.getX() == 11);
		assertPerso("init, La route est crée avec une mauvaise position y", route.getY() == 11);
	    assertPerso("init, La route est crée avec une mauvaise largeur", route.getLargeur() == 11);
	    assertPerso("init, La route est crée avec une mauvaise hauteur", route.getHauteur() == 11);
	    assertPerso("init, La route est crée avec une mauvaise multiplicateur",   route.getMult() == 2 );
	}
}
