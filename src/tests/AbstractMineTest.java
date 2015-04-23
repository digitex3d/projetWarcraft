package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.IMine;



public abstract class AbstractMineTest extends AbstractAssertion{

	protected IMine mine;
	

	@Before
	public abstract void before();

	@After
	public void after() {
		mine = null;
	}
	
	@Test
	public void test0_1() {
		// condition initiale
		
		// opérations
		mine = mine.init(77, 75);
		
		// oracle
		assertPerso("init, La mine est crée avec une mauvaise largeur", mine.getLargeur() == 77);
		assertPerso("init, La mine est crée avec une mauvaise hauteur", mine.getHauteur() == 75);
	
	}
	

	
	@Test
	public void test1_0() {
		// condition initiale 
		mine.init(77, 75);
		
		// opération
		mine.retrait(1);

		// oracle 
		assertPerso("Valeur de retrait fausse", mine.getOrRestant() == 50);

	}
	
	
	
	
}
