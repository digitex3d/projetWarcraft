package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import enums.ERace;
import exceptions.ContractError;
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
	public void testInit1() {
		mine.init(0, 10, 1, 1);
		
		// oracle
		assertPerso("init, La mine est crée avec une mauvaise largeur", mine.getX() == 0);
		assertPerso("init, La mine est crée avec une mauvaise largeur", mine.getY() == 10);
		assertPerso("init, La mine est crée avec une mauvaise largeur", mine.getLargeur() == 1);
		assertPerso("init, La mine est crée avec une mauvaise hauteur", mine.getHauteur() == 1);
		assertPerso("init, La mine est crée avec un mauvais orRestant", mine.getOrRestant() == 51);
		assertPerso("init, La mine est crée avec un mauvais abandonCompteur", mine.getHauteur() == 51);	
	}
	
	@Test
	public void testInit2() {
		mine.init(10, 0, 1, 1);
		
		// oracle
		assertPerso("init, La mine est crée avec une mauvaise largeur", mine.getX() == 10);
		assertPerso("init, La mine est crée avec une mauvaise largeur", mine.getY() == 0);
		assertPerso("init, La mine est crée avec une mauvaise largeur", mine.getLargeur() == 1);
		assertPerso("init, La mine est crée avec une mauvaise hauteur", mine.getHauteur() == 1);
		assertPerso("init, La mine est crée avec un mauvais orRestant", mine.getOrRestant() == 51);
		assertPerso("init, La mine est crée avec un mauvais abandonCompteur", mine.getHauteur() == 51);	
	}

	@Test
	public void testInit3() {
		boolean exc = false;
		try {
			mine.init(-1, 10, 1, 1);
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
			mine.init(10, -1, 1, 1);
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
			mine.init(10, 10, 2, 1);
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
			mine.init(10, 10, 1, 2);
		}
		catch (ContractError e) {
			exc = true;
		};
		
		// oracle
		assertPerso("init, Pas d'exception levée", exc);
	}

	@Test
	public void testAccueil1() {
		mine.init(0, 10, 1, 1);
		
		mine.acceuil(ERace.HUMAN);
		
		// oracle
		assertPerso("orRestant modifié après accueil", mine.getOrRestant() == 51);
		assertPerso("mauvais abandonCompteur après accueil", mine.getAbandonCompteur() == 0);
		assertPerso("mauvais etatAppartenance après accueil", mine.getEtatAppartenance() == ERace.HUMAN);
	}
	
	@Test
	public void testAccueil2() {
		mine.init(0, 10, 1, 1);
		
		mine.acceuil(ERace.HUMAN);
		mine.acceuil(ERace.HUMAN);		
		
		// oracle
		assertPerso("orRestant modifié après accueil", mine.getOrRestant() == 51);
		assertPerso("mauvais abandonCompteur après accueil", mine.getAbandonCompteur() == 0);
		assertPerso("mauvais etatAppartenance après accueil", mine.getEtatAppartenance() == ERace.HUMAN);
	}
	
	@Test
	public void testAccueil3() {
		boolean exc = false;
		mine.init(10, 10, 1, 1);
		mine.acceuil(ERace.ORC);
		try {
			mine.acceuil(ERace.HUMAN);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("accueil, Pas d'exception levée", exc);
	}
	
	@Test
	public void testRetrait1() {
		mine.init(10, 10, 1, 1);
		mine.acceuil(ERace.ORC);
		
		mine.retrait(1);		
		
		// oracle
		assertPerso("mauvais orRestant après retrait", mine.getOrRestant() == 50);
		assertPerso("mauvais abandonCompteur après retrait", mine.getAbandonCompteur() == 0);
		assertPerso("mauvais etatAppartenance après retrait", mine.getEtatAppartenance() == ERace.ORC);
	}
	
	@Test
	public void testRetrait2() {
		mine.init(10, 10, 1, 1);
		mine.acceuil(ERace.ORC);
		
		mine.retrait(51);		
		
		// oracle
		assertPerso("mauvais orRestant après retrait", mine.getOrRestant() == 0);
		assertPerso("mauvais abandonCompteur après retrait", mine.getAbandonCompteur() == 0);
		assertPerso("mauvais etatAppartenance après retrait", mine.getEtatAppartenance() == ERace.ORC);
	}
	
	@Test
	public void testRetrait3() {
		mine.init(10, 10, 1, 1);
		mine.acceuil(ERace.ORC);
		
		mine.retrait(5);		
		
		// oracle
		assertPerso("mauvais orRestant après retrait", mine.getOrRestant() == 46);
		assertPerso("mauvais abandonCompteur après retrait", mine.getAbandonCompteur() == 0);
		assertPerso("mauvais etatAppartenance après retrait", mine.getEtatAppartenance() == ERace.ORC);
	}
	
	@Test
	public void testRetrait4() {
		boolean exc = false;
		mine.init(10, 10, 1, 1);
		mine.acceuil(ERace.ORC);
		try {
			mine.retrait(-1);
			
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("retrait, Pas d'exception levée", exc);
	}
	
	@Test
	public void testRetrait5() {
		boolean exc = false;
		mine.init(10, 10, 1, 1);
		mine.acceuil(ERace.ORC);
		try {
			mine.retrait(100);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("retrait, Pas d'exception levée", exc);
	}
	
	@Test
	public void testRetrait6() {
		boolean exc = false;
		mine.init(10, 10, 1, 1);
		try {
			mine.retrait(5);
			
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("retrait, Pas d'exception levée", exc);
	}

	@Test
	public void testAbandoned1() {
		mine.init(10, 10, 1, 1);
		mine.acceuil(ERace.ORC);
		
		mine.abandoned();		
		
		// oracle
		assertPerso("mauvais orRestant après abandoned", mine.getOrRestant() == 51);
		assertPerso("mauvais abandonCompteur après abandoned", mine.getAbandonCompteur() == 1);
		assertPerso("mauvais etatAppartenance après abandoned", mine.getEtatAppartenance() == ERace.ORC);
	}

	@Test
	public void testAbandoned2() {
		boolean exc = false;
		mine.init(10, 10, 1, 1);
		try {
			mine.abandoned();
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("abandoned, Pas d'exception levée", exc);
	}
	
}
