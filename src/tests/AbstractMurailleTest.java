package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.IMuraille;

public abstract class AbstractMurailleTest extends AbstractAssertion{

	protected IMuraille hdv;


	@Before
	public abstract void before();

	@After
	public void after() {
		hdv = null;
	}


	@Test
	public void testInit1() {
		hdv.init(0, 10, 1, 1, 10);

		// oracle
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise x", hdv.getX() == 0);
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise y", hdv.getY() == 10);
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise largeur", hdv.getLargeur() == 1);
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise hauteur", hdv.getHauteur() == 1);
		assertPerso("init, La muraille est créé"
				+ " avec des mauvaise  points de vie", hdv.getPointsDeVie() == 10);
	}

	@Test
	public void testInit2() {
		hdv.init(10, 0, 1, 1, 1);

		// oracle
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise x", hdv.getX() == 10);
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise y", hdv.getY() == 0);
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise largeur", hdv.getLargeur() == 1);
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise hauteur", hdv.getHauteur() == 1);
		assertPerso("init, La muraille est créé"
				+ " avec un mauvaise  points de vie", hdv.getPointsDeVie() == 1);
	}

	public void testRetraitPre1() {
		// condition initiale : 
		hdv.init(10, 10, 1,1, 100);

		// opération
		hdv.retrait(5);

		// oracle
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise x", hdv.getX() == 10);
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise y", hdv.getY() == 10);
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise largeur", hdv.getLargeur() == 1);
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise hauteur", hdv.getHauteur() == 1);
		assertPerso("init, La muraille est créé"
				+ " avec un mauvaise  points de vie", hdv.getPointsDeVie() == 95);
	}

}
