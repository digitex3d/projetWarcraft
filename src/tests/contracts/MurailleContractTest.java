package tests.contracts;

import implementations.MurailleImpl;

import org.junit.Before;
import org.junit.Test;

import tests.AbstractMurailleTest;
import contracts.MurailleContract;
import exceptions.PreconditionError;

public class MurailleContractTest   extends AbstractMurailleTest {
	@Override
	@Before
	public void before() {
		hdv = new MurailleContract(new MurailleImpl());
	}

	@Test(expected=PreconditionError.class)
	public void testInitFailPre3() {
		// condition initiale : aucune

		// opération
		hdv.init(-1, 0, 1, 1, 10);

		// oracle
		// Exception PreconditionError
	}

	@Test(expected=PreconditionError.class)
	public void testInitFailPre4() {
		// condition initiale : aucune

		// opération
		hdv.init(10, -1, 1, 1, 10);

		// oracle
		// Exception PreconditionError
	}


	@Test(expected=PreconditionError.class)
	public void testInitFailPre5() {
		// condition initiale : aucune

		// opération
		hdv.init(10, 1, 1,2, 1);

		// oracle
		// Exception PreconditionError
	}

	@Test(expected=PreconditionError.class)
	public void testInitFailPre6() {
		// condition initiale : aucune

		// opération
		hdv.init(10, 1, 2,1, 1);

		// oracle
		// Exception PreconditionError
	}

	@Test(expected=PreconditionError.class)
	public void testInitFailPre7() {
		// condition initiale : aucune

		// opération
		hdv.init(10, 10, 1,1, 0);

		// oracle
		// Exception PreconditionError
	}

	// ################ Retrait ###################

	@Test(expected=PreconditionError.class)
	public void testRetraitFailPre2() {
		// condition initiale : 
		hdv.init(10, 10, 1,1, 100);

		// opération
		hdv.retrait(-5);


		// oracle
		// Exception PreconditionError
	}

	public void testRetraitFailPre3() {
		// condition initiale : 
		hdv.init(10, 10, 1,1, 100);
		hdv.retrait(100);

		// opération
		hdv.retrait(1);

		// oracle
		// Exception PreconditionError
	}

}
