package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.IMine;



public abstract class AbstractMineTest extends AbstractAssertion{

	protected IMine moteur;
	

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
		moteur = moteur.init(77, 75);
		
		// oracle
		assertPerso("init, La mine est crée avec une mauvaise largeur", moteur.getLargeur() == 77);
		assertPerso("init, La mine est crée avec une mauvaise hauteur", moteur.getHauteur() == 75);
	
	}
	

	
	@Test
	public void test1_0() {
		// condition initiale 
		moteur.init(77, 75);
		
		// opération
		moteur.retrait(1);

		// oracle 
		assertPerso("Valeur de retrait fausse", moteur.getOrRestant() == 50);

	}
	
	
	
	
}
