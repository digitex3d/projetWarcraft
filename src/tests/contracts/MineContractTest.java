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
		mine = new MineContract(new MineImpl());

	}
}
