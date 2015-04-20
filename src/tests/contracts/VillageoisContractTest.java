package tests.contracts;

import implementations.VillageoisImpl;

import org.junit.Before;
import org.junit.Test;

import contracts.VillageoisContract;
import enums.ERace;

import tests.AbstractVillageoisTest;
import exceptions.PostconditionError;
import exceptions.PreconditionError;

public class VillageoisContractTest extends AbstractVillageoisTest {

	@Override
	@Before
	public void before() {
		villageois = new VillageoisContract(new VillageoisImpl());

	}
	
	@Test(expected=PreconditionError.class)
	public void testInitFail() {
		// condition initiale : aucune

		// opération
		villageois.init(ERace.HUMAN, 78, 75,10,10.0,10 );

		// oracle un message d'erreur est attendu : Cf. @Test(expected=PreconditionError.class)	
	}
	
	@Test(expected=PreconditionError.class)
	public void testInitFail2() {
		// condition initiale : aucune

		// opération
		villageois.init(ERace.HUMAN, 77, 76,10,10.0,10 );

		// oracle un message d'erreur est attendu : Cf. @Test(expected=PreconditionError.class)	
	}
	
	@Test(expected=PreconditionError.class)
	public void testInitFail3() {
		// condition initiale : aucune

		// opération
		villageois.init(ERace.HUMAN, 78, 75,10,10.0,10 );

		// oracle un message d'erreur est attendu : Cf. @Test(expected=PreconditionError.class)	
	}
	
	@Test(expected=PreconditionError.class)
	public void testDeChargeOrFail() {
		// condition initiale : aucune
		villageois.init(ERace.HUMAN, 78, 75,10,10.0,10 );
		
		// opération
		villageois.dechargeOr(10);

		// oracle un message d'erreur est attendu : Cf. @Test(expected=PreconditionError.class)	
	}
	
	
	

}
