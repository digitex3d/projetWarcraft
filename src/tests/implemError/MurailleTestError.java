package tests.implemError;

import implemError.MurailleImplError;

import org.junit.Before;
import org.junit.Test;

import tests.AbstractMurailleTest;
import contracts.MurailleContract;
import exceptions.PreconditionError;

public class MurailleTestError extends AbstractMurailleTest{
	@Override
	@Before
	public void before() {
		mur = new MurailleContract(new MurailleImplError());
	}
	
	@Test(expected=PreconditionError.class)
	public void testInitFailPre3() {
		// condition initiale : aucune

		// opération
		mur.init(-1, 0, 1, 1, 10);

		// oracle
		// Exception PreconditionError
	}

	@Test(expected=PreconditionError.class)
	public void testInitFailPre4() {
		// condition initiale : aucune

		// opération
		mur.init(10, -1, 1, 1, 10);

		// oracle
		// Exception PreconditionError
	}


	@Test(expected=PreconditionError.class)
	public void testInitFailPre5() {
		// condition initiale : aucune

		// opération
		mur.init(10, 1, 1,2, 1);

		// oracle
		// Exception PreconditionError
	}

	@Test(expected=PreconditionError.class)
	public void testInitFailPre6() {
		// condition initiale : aucune

		// opération
		mur.init(10, 1, 2,1, 1);

		// oracle
		// Exception PreconditionError
	}

	@Test(expected=PreconditionError.class)
	public void testInitFailPre7() {
		// condition initiale : aucune

		// opération
		mur.init(10, 10, 1,1, 0);

		// oracle
		// Exception PreconditionError
	}

	// ################ Retrait ###################

	@Test(expected=PreconditionError.class)
	public void testRetraitFailPre2() {
		// condition initiale : 
		mur.init(10, 10, 1,1, 100);

		// opération
		mur.retrait(-5);


		// oracle
		// Exception PreconditionError
	}

	public void testRetraitFailPre3() {
		// condition initiale : 
		mur.init(10, 10, 1,1, 100);
		mur.retrait(100);

		// opération
		mur.retrait(1);

		// oracle
		// Exception PreconditionError
	}
	
}
