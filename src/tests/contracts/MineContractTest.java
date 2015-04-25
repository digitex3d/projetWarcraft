package tests.contracts;

import implementations.MineImpl;
import implementations.VillageoisImpl;

import org.junit.Before;
import org.junit.Test;

import contracts.MineContract;
import contracts.VillageoisContract;
import enums.ERace;

import services.IMine;
import tests.AbstractMineTest;
import tests.AbstractVillageoisTest;
import exceptions.InvariantError;
import exceptions.PreconditionError;

public class MineContractTest extends AbstractMineTest {

	@Override
	@Before
	public void before() {
		moteur = new MineContract(new MineImpl());

	}
	
	@Test(expected=PreconditionError.class)
	public void testInitFail() {
		// condition initiale : aucune

		// opération
		moteur.init(78, 75 );

		// oracle un message d'erreur est attendu : Cf. @Test(expected=PreconditionError.class)	
	}
	
	@Test(expected=PreconditionError.class)
	public void testInitFail2() {
		// condition initiale : aucune

		// opération
		moteur.init(77, 76);

		// oracle un message d'erreur est attendu : Cf. @Test(expected=PreconditionError.class)	
	}
	
	
	

	

}
