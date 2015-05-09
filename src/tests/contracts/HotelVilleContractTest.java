package tests.contracts;

import implementations.HotelVilleImpl;

import org.junit.Before;
import org.junit.Test;

import tests.AbstractHotelVilleTest;
import contracts.HotelVilleContract;
import enums.ERace;
import exceptions.PreconditionError;

public class HotelVilleContractTest   extends AbstractHotelVilleTest {
	@Override
	@Before
	public void before() {
		hdv = new HotelVilleContract(new HotelVilleImpl());
	}

	@Test(expected=PreconditionError.class)
	public void testInitFailPre3() {
		// condition initiale : aucune

		// opération
		hdv.init(10, 0, 2, 1, 0, ERace.ORC);

		// oracle
		// Exception PreconditionError
	}

	@Test(expected=PreconditionError.class)
	public void testInitFailPre4() {
		// condition initiale : aucune

		// opération
		hdv.init(10, 0, 1, 2, 0, ERace.ORC);

		// oracle
		// Exception PreconditionError
	}


	@Test(expected=PreconditionError.class)
	public void testInitFailPre5() {
		// condition initiale : aucune

		// opération
		hdv.init(-1, 0, 1, 2, 0, ERace.ORC);

		// oracle
		// Exception PreconditionError
	}

	@Test(expected=PreconditionError.class)
	public void testInitFailPre6() {
		// condition initiale : aucune

		// opération
		hdv.init(10, -1, 1,2, 0, ERace.ORC);

		// oracle
		// Exception PreconditionError
	}

	@Test(expected=PreconditionError.class)
	public void testInitFailPre7() {
		// condition initiale : aucune

		// opération
		hdv.init(10, 0, 1,2, -1, ERace.ORC);

		// oracle
		// Exception PreconditionError
	}

	// ################ Depot ###################

	@Test(expected=PreconditionError.class)
	public void testDepotFailPre3() {
		// condition initiale : 
		hdv.init(10, 10, 1,1, 10, ERace.ORC);

		// opération
		hdv.depot(0);


		// oracle
		// Exception PreconditionError
	}


}
