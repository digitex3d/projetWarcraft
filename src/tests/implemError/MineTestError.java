package tests.implemError;



import implemError.MineImplError;

import org.junit.Before;

import contracts.MineContract;
import tests.AbstractMineTest;

public class MineTestError extends AbstractMineTest{
	@Override
	@Before
	public void before() {
		mine = new MineContract(new MineImplError());
	}
}
