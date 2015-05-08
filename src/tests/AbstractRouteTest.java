package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import enums.ERace;
import exceptions.ContractError;
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
	public void testInit1() {
		route.init(0, 10, 1, 1, 1);
		
		// oracle
		assertPerso("init, La route est crée avec une mauvaise largeur", route.getX() == 0);
		assertPerso("init, La route est crée avec une mauvaise largeur", route.getY() == 10);
		assertPerso("init, La route est crée avec une mauvaise largeur", route.getLargeur() == 1);
		assertPerso("init, La route est crée avec une mauvaise hauteur", route.getHauteur() == 1);
		assertPerso("init, La route est crée avec un mauvais bonusVitesse", route.getBonusVitesse() == 1);
	}
	
	@Test
	public void testInit2() {
		route.init(10, 0, 1, 1, 1);
		
		// oracle
		assertPerso("init, La route est crée avec une mauvaise largeur", route.getX() == 10);
		assertPerso("init, La route est crée avec une mauvaise largeur", route.getY() == 0);
		assertPerso("init, La route est crée avec une mauvaise largeur", route.getLargeur() == 1);
		assertPerso("init, La route est crée avec une mauvaise hauteur", route.getHauteur() == 1);
		assertPerso("init, La route est crée avec un mauvais bonusVitesse", route.getBonusVitesse() == 1);
	}

	@Test
	public void testInit3() {
		boolean exc = false;
		try {
			route.init(-1, 10, 1, 1, 1);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("init, Pas d'exception levée", exc);
	}
	
	@Test
	public void testInit4() {
		boolean exc = false;
		try {
			route.init(10, -1, 1, 1, 1);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("init, Pas d'exception levée", exc);
	}

	@Test
	public void testInit5() {
		boolean exc = false;
		try {
			route.init(10, 10, 2, 1, 1);
		}
		catch (ContractError e) {
			exc = true;
		};
		
		// oracle
		assertPerso("init, Pas d'exception levée", exc);
	}

	@Test
	public void testInit6() {
		boolean exc = false;
		try {
			route.init(10, 10, 1, 2, 1);
		}
		catch (ContractError e) {
			exc = true;
		};
		
		// oracle
		assertPerso("init, Pas d'exception levée", exc);
	}

	@Test
	public void testInit7() {
		route.init(10, 10, 1, 1, 1);
		
		// oracle
		assertPerso("init, La route est crée avec une mauvaise largeur", route.getX() == 10);
		assertPerso("init, La route est crée avec une mauvaise largeur", route.getY() == 10);
		assertPerso("init, La route est crée avec une mauvaise largeur", route.getLargeur() == 1);
		assertPerso("init, La route est crée avec une mauvaise hauteur", route.getHauteur() == 1);
		assertPerso("init, La route est crée avec un mauvais bonusVitesse", route.getBonusVitesse() == 1);
	}
	
	@Test
	public void testInit8() {
		boolean exc = false;
		try {
			route.init(10, 10, 1, 1, 0);
		}
		catch (ContractError e) {
			exc = true;
		};
		
		// oracle
		assertPerso("init, Pas d'exception levée", exc);
	}

}
