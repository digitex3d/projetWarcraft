package tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import enums.ECommande;
import exceptions.ContractError;
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
	public void testInit1() {
		moteur.init(100);
		
		// oracle
		assertPerso("init, Le moteurJeu est créé avec un mauvais maxPasJeu", moteur.getMaxPasJeu() == 100);
		assertPerso("init, Le terrain est créé avec un mauvais pasJeuCourant", moteur.getPasJeuCourant() == 0);
	}
	
	@Test
	public void testInit2() {
		moteur.init(1);
		
		// oracle
		assertPerso("init, Le moteurJeu est créé avec un mauvais maxPasJeu", moteur.getMaxPasJeu() == 1);
		assertPerso("init, Le terrain est créé avec un mauvais pasJeuCourant", moteur.getPasJeuCourant() == 0);
	}

	@Test
	public void testInit3() {
		boolean exc = false;
		try {
			moteur.init(0);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("init, Pas d'exception levée", exc);
	}

	@Test
	public void testPasJeu1() {
		moteur.init(100);
		
		moteur.pasJeu(ECommande.DEPLACER, 0, 0);
		// oracle
		assertPerso("pasJeu, mauvais pasJeuCourant", moteur.getPasJeuCourant() == 1);
	}


	@Test
	public void testPasJeu2() {
		moteur.init(100);
		
		moteur.pasJeu(ECommande.DEPLACER, 0, 90);
		// oracle
		assertPerso("pasJeu, mauvais pasJeuCourant", moteur.getPasJeuCourant() == 1);
	}


	@Test
	public void testPasJeu3() {
		moteur.init(100);
		
		moteur.pasJeu(ECommande.DEPLACER, 0, 180);
		// oracle
		assertPerso("pasJeu, mauvais pasJeuCourant", moteur.getPasJeuCourant() == 1);
	}

	@Test
	public void testPasJeu4() {
		moteur.init(100);
		
		moteur.pasJeu(ECommande.DEPLACER, 1, 270);
		// oracle
		assertPerso("pasJeu, mauvais pasJeuCourant", moteur.getPasJeuCourant() == 1);
	}

	@Test
	public void testPasJeu5() {
		boolean exc = false;
		moteur.init(100);
		try {
			moteur.pasJeu(ECommande.DEPLACER, 0, -1);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("pasJeu, Pas d'exception levée", exc);
	}
	
	@Test
	public void testPasJeu6() {
		moteur.init(100);
		
		moteur.pasJeu(ECommande.ENTRERMINE, 0, 0);
		// oracle
		assertPerso("pasJeu, mauvais pasJeuCourant", moteur.getPasJeuCourant() == 1);
	}

	@Test
	public void testPasJeu7() {
		boolean exc = false;
		moteur.init(100);
		try {
			moteur.pasJeu(ECommande.ENTRERMINE, 1, 0);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("pasJeu, Pas d'exception levée", exc);
	}

	@Test
	public void testPasJeu8() {
		boolean exc = false;
		moteur.init(100);
			moteur.pasJeu(ECommande.ENTRERMINE, 0, 0);
		try {
			moteur.pasJeu(ECommande.ENTRERMINE, 0, 0);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("pasJeu, Pas d'exception levée", exc);
	}

	@Test
	public void testPasJeu9() {
		boolean exc = false;
		moteur.init(100);
			moteur.pasJeu(ECommande.ENTRERMINE, 0, 0);
		try {
			moteur.pasJeu(ECommande.DEPLACER, 0, 0);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("pasJeu, Pas d'exception levée", exc);
	}

	@Test
	public void testPasJeu10() {
		boolean exc = false;
		moteur.init(100);
			moteur.pasJeu(ECommande.ENTRERMINE, 0, 0);
		try {
			moteur.pasJeu(ECommande.ENTRERHOTELVILLE, 0, 0);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("pasJeu, Pas d'exception levée", exc);
	}

	@Test
	public void testPasJeu11() {
		boolean exc = false;
		moteur.init(100);
			moteur.pasJeu(ECommande.ENTRERMINE, 0, 0);
		try {
			moteur.pasJeu(ECommande.TAPERMURAILLE, 0, 0);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("pasJeu, Pas d'exception levée", exc);
	}

	@Test
	public void testPasJeu12() {
		moteur.init(100);
		moteur.pasJeu(ECommande.ENTRERMINE, 0, 0);

		moteur.pasJeu(ECommande.RIEN, 0, 0);
		// oracle
		assertPerso("pasJeu, mauvais pasJeuCourant", moteur.getPasJeuCourant() == 2);
	}

	@Test
	public void testPasJeu13() {
		moteur.init(100);
		moteur.pasJeu(ECommande.RIEN, 0, 0);

		moteur.pasJeu(ECommande.RIEN, 0, 0);
		// oracle
		assertPerso("pasJeu, mauvais pasJeuCourant", moteur.getPasJeuCourant() == 2);
	}

	@Test
	public void testPasJeu14() {
		moteur.init(100);
		moteur.pasJeu(ECommande.RIEN, 0, 0);

		moteur.pasJeu(ECommande.ENTRERMINE, 0, 0);
		// oracle
		assertPerso("pasJeu, mauvais pasJeuCourant", moteur.getPasJeuCourant() == 2);
	}

	@Test
	public void testPasJeu15() {
		boolean exc = false;
		moteur.init(100);
		moteur.pasJeu(ECommande.RIEN, 0, 0);
		try {
			moteur.pasJeu(ECommande.ENTRERHOTELVILLE, 0, 0);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("pasJeu, Pas d'exception levée", exc);
	}
	
	@Test
	public void testPasJeu16() {
		moteur.init(100);

		moteur.pasJeu(ECommande.TAPERMURAILLE, 0, 0);
		// oracle
		assertPerso("pasJeu, mauvais pasJeuCourant", moteur.getPasJeuCourant() == 1);
	}
	
	@Test
	public void testPasJeu17() {
		moteur.init(100);
		moteur.pasJeu(ECommande.TAPERMURAILLE, 0, 0);

		moteur.pasJeu(ECommande.TAPERMURAILLE, 0, 0);
		// oracle
		assertPerso("pasJeu, mauvais pasJeuCourant", moteur.getPasJeuCourant() == 2);
	}

	@Test
	public void testPasJeu18() {
		moteur.init(100);
		moteur.pasJeu(ECommande.TAPERMURAILLE, 0, 0);

		moteur.pasJeu(ECommande.ENTRERMINE, 0, 0);
		// oracle
		assertPerso("pasJeu, mauvais pasJeuCourant", moteur.getPasJeuCourant() == 2);
	}

	@Test
	public void testPasJeu19() {
		moteur.init(100);
		moteur.pasJeu(ECommande.TAPERMURAILLE, 0, 0);

		moteur.pasJeu(ECommande.RIEN, 0, 0);
		// oracle
		assertPerso("pasJeu, mauvais pasJeuCourant", moteur.getPasJeuCourant() == 2);
	}

	@Test
	public void testPasJeu20() {
		moteur.init(100);
		moteur.pasJeu(ECommande.TAPERMURAILLE, 0, 0);
		moteur.pasJeu(ECommande.TAPERMURAILLE, 0, 0);

		moteur.pasJeu(ECommande.ENTRERMINE, 0, 0);
		// oracle
		assertPerso("pasJeu, mauvais pasJeuCourant", moteur.getPasJeuCourant() == 3);
	}

	@Test
	public void testPasJeu21() {
		boolean exc = false;
		moteur.init(100);
		moteur.pasJeu(ECommande.TAPERMURAILLE, 0, 0);
		moteur.pasJeu(ECommande.TAPERMURAILLE, 0, 0);
		try {
			moteur.pasJeu(ECommande.TAPERMURAILLE, 0, 0);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("pasJeu, Pas d'exception levée", exc);
	}

	@Test
	public void testPasJeu22() {
		moteur.init(100);
		moteur.pasJeu(ECommande.ENTRERMINE, 0, 0);
		for (int i = 1; i <= 16; i++)
			moteur.pasJeu(ECommande.RIEN, 0, 0);

		moteur.pasJeu(ECommande.ENTRERHOTELVILLE, 0, 0);
		// oracle
		assertPerso("pasJeu, mauvais pasJeuCourant", moteur.getPasJeuCourant() == 18);
	}
}
