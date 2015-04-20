package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.istack.internal.logging.Logger;

import enums.ERace;
import services.IVillageois;



public abstract class AbstractVillageoisTest extends AbstractAssertion{


	protected IVillageois villageois;
	


	@Before
	public abstract void before();

	@After
	public void after() {
		villageois = null;
	}
	
	@Test
	public void test0_1() {
		// condition initiale
		
		// opérations
		villageois.init(ERace.HUMAN, 77, 75,10,10.0,10 );
		
		// oracle
		assertPerso("init, Le villageois est crée avec une mauvaise race", villageois.getRace() == ERace.HUMAN);
		assertPerso("init, Le bloc est crée avec une mauvaise largeur", villageois.getLargeur() == 77);
		assertPerso("init, Le bloc est crée avec une mauvaise hauteur", villageois.getHauteur() == 75);
		assertPerso("init, Le bloc est crée avec une mauvaise force", villageois.getForce() == 10);
		assertPerso("init, Le bloc est crée avec une mauvaise vitesse", villageois.getForce() == 10.0);
		assertPerso("init, Le bloc est crée avec des mauvais points de vie", villageois.getVitesse() == 10);
	}
	
	@Test
	public void test0_2() {
		// condition initiale 
		villageois.init(ERace.HUMAN, 77, 75,10,10.0,10 );
		
		// opération
		villageois.retrait(5);

		// oracle 
		assertPerso("Valeur de retrait fausse", villageois.getPointsDeVie() == 5);
		assertPerso("La quantité d'or a changé", villageois.getQuantiteOr() == 0);
	}
	
	
	
	
}
