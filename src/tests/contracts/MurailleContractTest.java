package tests.contracts;

import implementations.MurailleImpl;

import org.junit.Before;
import org.junit.Test;

import tests.AbstractMurailleTest;
import contracts.MurailleContract;
import exceptions.ContractError;
import exceptions.PreconditionError;

public class MurailleContractTest   extends AbstractMurailleTest {
	@Override
	@Before
	public void before() {
		mur = new MurailleContract(new MurailleImpl());
	}
	
	@Test(expected=PreconditionError.class)
	public void testInit4() {
		boolean exc = false;
		try {
			mur.init(10, -1, 1, 1, 1);
		}
		catch (ContractError e) {
			exc = true;
		}
		
		// oracle
		assertPerso("init, Pas d'exception levée", exc);
	}
}
