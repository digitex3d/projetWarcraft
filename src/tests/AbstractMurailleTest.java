package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.IMuraille;

public abstract class AbstractMurailleTest extends AbstractAssertion{

	protected IMuraille mur;
	

	@Before
	public abstract void before();

	@After
	public void after() {
		mur = null;
	}


	@Test
	public void testInit1() {
		mur.init(0, 10, 1, 1, 10);

		// oracle
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise x", mur.getX() == 0);
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise y", mur.getY() == 10);
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise largeur", mur.getLargeur() == 1);
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise hauteur", mur.getHauteur() == 1);
		assertPerso("init, La muraille est créé"
				+ " avec des mauvaise  points de vie", mur.getPointsDeVie() == 10);
	}
	
	@Test
	public void testInit2() {
		mur.init(10, 0, 1, 1, 1);
		
		// oracle
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise x", mur.getX() == 10);
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise y", mur.getY() == 0);
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise largeur", mur.getLargeur() == 1);
		assertPerso("init, La muraille est créé"
				+ " avec une mauvaise hauteur", mur.getHauteur() == 1);
		assertPerso("init, La muraille est créé"
				+ " avec un mauvaise  points de vie", mur.getPointsDeVie() == 1);
	}
	
}
