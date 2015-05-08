package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import enums.ERace;
import exceptions.ContractError;
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
	public void testInit1() {
		villageois.init(0, 10, ERace.ORC, 1, 1, 1, 1, 1);
		
		// oracle
		assertPerso("init, Le villageois est créé avec une mauvaise x", villageois.getX() == 0);
		assertPerso("init, Le villageois est créé avec une mauvaise y", villageois.getY() == 10);
		assertPerso("init, Le villageois est créé avec une mauvaise race", villageois.getRace() == ERace.ORC);
		assertPerso("init, Le villageois est créé avec une mauvaise largeur", villageois.getLargeur() == 1);
		assertPerso("init, Le villageois est créé avec une mauvaise hauteur", villageois.getHauteur() == 1);
		assertPerso("init, Le villageois est créé avec une mauvaise force", villageois.getForce() == 1);
		assertPerso("init, Le villageois est créé avec une mauvaise vitesse", villageois.getVitesse() == 1);	
		assertPerso("init, Le villageois est créé avec une mauvaise quantiteOr", villageois.getQuantiteOr() == 0);	
		assertPerso("init, Le villageois est créé avec un mauvais pointsDeVie", villageois.getPointsDeVie() == 1);	
	}
	
	@Test
	public void testInit2() {
		villageois.init(10, 0, ERace.ORC, 1, 1, 1, 1, 1);
		
		// oracle
		assertPerso("init, Le villageois est créé avec une mauvaise x", villageois.getX() == 0);
		assertPerso("init, Le villageois est créé avec une mauvaise y", villageois.getY() == 10);
		assertPerso("init, Le villageois est créé avec une mauvaise race", villageois.getRace() == ERace.ORC);
		assertPerso("init, Le villageois est créé avec une mauvaise largeur", villageois.getLargeur() == 1);
		assertPerso("init, Le villageois est créé avec une mauvaise hauteur", villageois.getHauteur() == 1);
		assertPerso("init, Le villageois est créé avec une mauvaise force", villageois.getForce() == 1);
		assertPerso("init, Le villageois est créé avec une mauvaise vitesse", villageois.getVitesse() == 1);	
		assertPerso("init, Le villageois est créé avec une mauvaise quantiteOr", villageois.getQuantiteOr() == 0);	
		assertPerso("init, Le villageois est créé avec un mauvais pointsDeVie", villageois.getPointsDeVie() == 1);	
	}

	@Test
	public void testInit3() {
		boolean exc = false;
		try {
			villageois.init(-1, 10, ERace.ORC, 1, 1, 1, 1, 1);
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
			villageois.init(10, -1, ERace.ORC, 1, 1, 1, 1, 1);
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
			villageois.init(10, 10, ERace.ORC, 2, 1, 1, 1, 1);
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
			villageois.init(10, 10, ERace.ORC, 1, 2, 1, 1, 1);
		}
		catch (ContractError e) {
			exc = true;
		};
		
		// oracle
		assertPerso("init, Pas d'exception levée", exc);
	}

	@Test
	public void testInit7() {
		boolean exc = false;
		try {
			villageois.init(10, 10, ERace.ORC, 1, 1, 0, 1, 1);
		}
		catch (ContractError e) {
			exc = true;
		};
		
		// oracle
		assertPerso("init, Pas d'exception levée", exc);
	}

	@Test
	public void testInit8() {
		boolean exc = false;
		try {
			villageois.init(10, 10, ERace.ORC, 1, 1, 1, 0, 1);
		}
		catch (ContractError e) {
			exc = true;
		};
		
		// oracle
		assertPerso("init, Pas d'exception levée", exc);
	}

	@Test
	public void testInit9() {
		boolean exc = false;
		try {
			villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 0);
		}
		catch (ContractError e) {
			exc = true;
		};
		
		// oracle
		assertPerso("init, Pas d'exception levée", exc);
	}

	@Test
	public void testSetXY1() {
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		
		villageois.setXY(0, 10);
		
		// oracle
		assertPerso("mauvaise x", villageois.getX() == 0);
		assertPerso("mauvaise y", villageois.getY() == 10);
		assertPerso("mauvaise corvee", villageois.getCorvee() == 0);
		assertPerso("mauvaise quantiteOr", villageois.getQuantiteOr() == 0);	
		assertPerso("mauvais pointsDeVie", villageois.getPointsDeVie() == 1);	
	}

	@Test
	public void testSetXY2() {
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		
		villageois.setXY(10, 0);
		
		// oracle
		assertPerso("mauvaise x", villageois.getX() == 10);
		assertPerso("mauvaise y", villageois.getY() == 0);
		assertPerso("mauvaise corvee", villageois.getCorvee() == 0);
		assertPerso("mauvaise quantiteOr", villageois.getQuantiteOr() == 0);	
		assertPerso("mauvais pointsDeVie", villageois.getPointsDeVie() == 1);	
	}

	@Test
	public void testSetXY3() {
		boolean exc = false;
			villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		try {
			villageois.setXY(-1, 10);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("setXY, Pas d'exception levée", exc);
	}

	@Test
	public void testSetXY4() {
		boolean exc = false;
			villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		try {
			villageois.setXY(10, -1);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("setXY, Pas d'exception levée", exc);
	}
	
	@Test
	public void testRetrait1() {
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		
		villageois.retrait(1);		
		
		// oracle
		assertPerso("mauvaise x", villageois.getX() == 10);
		assertPerso("mauvaise y", villageois.getY() == 0);
		assertPerso("mauvaise corvee", villageois.getCorvee() == 0);
		assertPerso("mauvaise quantiteOr", villageois.getQuantiteOr() == 0);	
		assertPerso("mauvais pointsDeVie", villageois.getPointsDeVie() == 0);	
	}
	
	@Test
	public void testRetrait2() {
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		
		villageois.retrait(10);		
		
		// oracle
		assertPerso("mauvaise x", villageois.getX() == 10);
		assertPerso("mauvaise y", villageois.getY() == 0);
		assertPerso("mauvaise corvee", villageois.getCorvee() == 0);
		assertPerso("mauvaise quantiteOr", villageois.getQuantiteOr() == 0);	
		assertPerso("mauvais pointsDeVie", villageois.getPointsDeVie() == -9);	
	}
	
	@Test
	public void testRetrait3() {
		boolean exc = false;
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		try {
			villageois.retrait(0);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("retrait, Pas d'exception levée", exc);
	}
	
	@Test
	public void testRetrait4() {
		boolean exc = false;
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		villageois.retrait(1);
		try {
			villageois.retrait(1);
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
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		villageois.setCorvee(1, 10, 10);
		try {
			villageois.retrait(1);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("retrait, Pas d'exception levée", exc);
	}
	
	@Test
	public void testChargeOr1() {
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		
		villageois.chargeOr(1);		
		
		// oracle
		assertPerso("mauvaise x", villageois.getX() == 10);
		assertPerso("mauvaise y", villageois.getY() == 10);
		assertPerso("mauvaise corvee", villageois.getCorvee() == 0);
		assertPerso("mauvaise quantiteOr", villageois.getQuantiteOr() == 1);	
		assertPerso("mauvais pointsDeVie", villageois.getPointsDeVie() == 1);	
	}
	
	@Test
	public void testChargeOr2() {
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		
		villageois.chargeOr(10);		
		
		// oracle
		assertPerso("mauvaise x", villageois.getX() == 10);
		assertPerso("mauvaise y", villageois.getY() == 10);
		assertPerso("mauvaise corvee", villageois.getCorvee() == 0);
		assertPerso("mauvaise quantiteOr", villageois.getQuantiteOr() == 1);	
		assertPerso("mauvais pointsDeVie", villageois.getPointsDeVie() == 1);	
	}

	@Test
	public void testChargeOr3() {
		boolean exc = false;
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		villageois.retrait(1);
		try {
			villageois.chargeOr(10);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("chargeOr, Pas d'exception levée", exc);
	}

	@Test
	public void testDechargeOr1() {
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		villageois.chargeOr(1);
		villageois.dechargeOr(1);		
		
		// oracle
		assertPerso("mauvaise x", villageois.getX() == 10);
		assertPerso("mauvaise y", villageois.getY() == 10);
		assertPerso("mauvaise corvee", villageois.getCorvee() == 0);
		assertPerso("mauvaise quantiteOr", villageois.getQuantiteOr() == 0);	
		assertPerso("mauvais pointsDeVie", villageois.getPointsDeVie() == 1);	
	}

	@Test
	public void testDechargeOr2() {
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		villageois.chargeOr(10);
		villageois.dechargeOr(8);		
		
		// oracle
		assertPerso("mauvaise x", villageois.getX() == 10);
		assertPerso("mauvaise y", villageois.getY() == 10);
		assertPerso("mauvaise corvee", villageois.getCorvee() == 0);
		assertPerso("mauvaise quantiteOr", villageois.getQuantiteOr() == 2);	
		assertPerso("mauvais pointsDeVie", villageois.getPointsDeVie() == 1);	
	}

	@Test
	public void testDechargeOr3() {
		boolean exc = false;
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		try {
			villageois.dechargeOr(8);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("dechargeOr, Pas d'exception levée", exc);
	}
	
	@Test
	public void testDechargeOr4() {
		boolean exc = false;
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		villageois.chargeOr(1);
		try {
			villageois.dechargeOr(0);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("dechargeOr, Pas d'exception levée", exc);
	}
	
	@Test
	public void testDechargeOr5() {
		boolean exc = false;
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		villageois.retrait(10);
		try {
			villageois.dechargeOr(4);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("dechargeOr, Pas d'exception levée", exc);
	}

	@Test
	public void testSetCorvee1() {
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		villageois.setCorvee(1, 12, 12);		
		
		// oracle
		assertPerso("mauvaise x", villageois.getX() == 12);
		assertPerso("mauvaise y", villageois.getY() == 12);
		assertPerso("mauvaise corvee", villageois.getCorvee() == 1);
		assertPerso("mauvaise quantiteOr", villageois.getQuantiteOr() == 0);	
		assertPerso("mauvais pointsDeVie", villageois.getPointsDeVie() == 1);	
	}
	
	@Test
	public void testSetCorvee2() {
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		villageois.setCorvee(10, 12, 12);		
		
		// oracle
		assertPerso("mauvaise x", villageois.getX() == 12);
		assertPerso("mauvaise y", villageois.getY() == 12);
		assertPerso("mauvaise corvee", villageois.getCorvee() == 10);
		assertPerso("mauvaise quantiteOr", villageois.getQuantiteOr() == 0);	
		assertPerso("mauvais pointsDeVie", villageois.getPointsDeVie() == 1);	
	}
	
	@Test
	public void testSetCorvee3() {
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		villageois.setCorvee(10, 0, 12);		
		
		// oracle
		assertPerso("mauvaise x", villageois.getX() == 0);
		assertPerso("mauvaise y", villageois.getY() == 12);
		assertPerso("mauvaise corvee", villageois.getCorvee() == 10);
		assertPerso("mauvaise quantiteOr", villageois.getQuantiteOr() == 0);	
		assertPerso("mauvais pointsDeVie", villageois.getPointsDeVie() == 1);	
	}
	
	@Test
	public void testSetCorvee4() {
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		villageois.setCorvee(10, 12, 0);		
		
		// oracle
		assertPerso("mauvaise x", villageois.getX() == 12);
		assertPerso("mauvaise y", villageois.getY() == 0);
		assertPerso("mauvaise corvee", villageois.getCorvee() == 10);
		assertPerso("mauvaise quantiteOr", villageois.getQuantiteOr() == 0);	
		assertPerso("mauvais pointsDeVie", villageois.getPointsDeVie() == 1);	
	}
	
	@Test
	public void testSetCorvee5() {
		boolean exc = false;
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		try {
			villageois.setCorvee(0, 10, 10);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("setCorvee, Pas d'exception levée", exc);
	}
	
	@Test
	public void testSetCorvee6() {
		boolean exc = false;
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		try {
			villageois.setCorvee(10, -1, 10);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("setCorvee, Pas d'exception levée", exc);
	}
	
	@Test
	public void testSetCorvee7() {
		boolean exc = false;
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		try {
			villageois.setCorvee(10, 10, -1);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("setCorvee, Pas d'exception levée", exc);
	}
	
	@Test
	public void testSetCorvee8() {
		boolean exc = false;
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		villageois.retrait(1);
		try {
			villageois.setCorvee(10, 10, 10);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("setCorvee, Pas d'exception levée", exc);
	}
	
	@Test
	public void testSetCorvee9() {
		boolean exc = false;
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		villageois.setCorvee(1, 10, 10);
		try {
			villageois.setCorvee(10, 10, 10);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("setCorvee, Pas d'exception levée", exc);
	}
	
	@Test
	public void testDecrCorvee1() {
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		villageois.setCorvee(1, 10, 10);		
		villageois.decrCorvee();
		// oracle
		assertPerso("mauvaise x", villageois.getX() == 10);
		assertPerso("mauvaise y", villageois.getY() == 10);
		assertPerso("mauvaise corvee", villageois.getCorvee() == 0);
		assertPerso("mauvaise quantiteOr", villageois.getQuantiteOr() == 0);	
		assertPerso("mauvais pointsDeVie", villageois.getPointsDeVie() == 1);	
	}
	
	@Test
	public void testDecrCorvee2() {
		boolean exc = false;
		villageois.init(10, 10, ERace.ORC, 1, 1, 1, 1, 1);
		try {
			villageois.decrCorvee();
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("decrCorvee, Pas d'exception levée", exc);
	}
}
