package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import enums.ERace;
import services.IHotelVille;

public abstract class AbstractHotelVilleTest extends AbstractAssertion{

	protected IHotelVille hdv;


	@Before
	public abstract void before();

	@After
	public void after() {
		hdv = null;
	}


	@Test
	public void testInit1() {
		hdv.init(0, 10, 1, 1, 10, ERace.ORC);

		// oracle
		assertPerso("init, le hotel de ville est créé"
				+ " avec une mauvaise x", hdv.getX() == 0);
		assertPerso("init, le hotel de ville est créé"
				+ " avec une mauvaise y", hdv.getY() == 10);
		assertPerso("init, le hotel de ville est créé"
				+ " avec une mauvaise largeur", hdv.getLargeur() == 1);
		assertPerso("init, le hotel de ville est créé"
				+ " avec une mauvaise hauteur", hdv.getHauteur() == 1);
		assertPerso("init, le hotel de ville est créé"
				+ " avec une mauvaise quantité d'or", 
				hdv.getOrRestant() == 10);
		assertPerso("init, le hotel de ville est créé"
				+ " avec un mauvais etat d'appertenance", 
				hdv.getEtatAppartenance() == ERace.ORC);
	}

	@Test
	
	public void testInit2() {
		hdv.init(10, 0, 1, 1, 0, ERace.ORC);

		// oracle
		assertPerso("init, le hotel de ville est créé"
				+ " avec une mauvaise x", hdv.getX() == 10);
		assertPerso("init, le hotel de ville est créé"
				+ " avec une mauvaise y", hdv.getY() == 0);
		assertPerso("init, le hotel de ville est créé"
				+ " avec une mauvaise largeur", hdv.getLargeur() == 1);
		assertPerso("init, le hotel de ville est créé"
				+ " avec une mauvaise hauteur", hdv.getHauteur() == 1);
		assertPerso("init, le hotel de ville est créé"
				+ " avec une mauvaise quantité d'or", 
				hdv.getOrRestant() == 0);
		assertPerso("init, le hotel de ville est créé"
				+ " avec un mauvais etat d'appertenance", 
				hdv.getEtatAppartenance() == ERace.ORC);
	}

	// ################ Depot ###################
	public void testDepotPre1() {
		// condition initiale : 
		hdv.init(10, 10, 1,1, 10, ERace.ORC);

		// opération
		hdv.depot(5);

		// oracle
		assertPerso("depot, contient "
				+ " une mauvaise quantité d'or", 
				hdv.getOrRestant() == 15);
	}
	
	public void testDepotPre2() {
		// condition initiale : 
		hdv.init(10, 10, 1,1, 10, ERace.ORC);

		// opération
		hdv.depot(1);

		// oracle
		assertPerso("depot, contient "
				+ " une mauvaise quantité d'or", 
				hdv.getOrRestant() == 11);
	}
	
	

}
