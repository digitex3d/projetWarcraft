package tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.IMoteurJeu;

public abstract class AbstractMoteurJeuTest extends AbstractAssertion{
	protected IMoteurJeu moteur;
	

	@Before
	public abstract void before();

	@After
	public void after() {
		moteur = null;
	}
	
	@Test
	public void test0_1() {
		// condition initiale
		
		// opérations
		moteur.init(800, 600, 100);
		
		// oracle
		assertPerso("init, La mine est crée avec une mauvaise largeur", moteur.getLargeurTerrain() == 800);
		assertPerso("init, La mine est crée avec une mauvaise hauteur", moteur.getHauteurTerrain() == 600);
		assertPerso("init, La mine est crée avec une mauvaise hauteur", moteur.getMaxPasJeu() == 100);
	
	}
	

}
