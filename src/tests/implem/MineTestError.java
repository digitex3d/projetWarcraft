package tests.implem;



import implemError.MineImplError;
import implementations.MineImpl;

import org.junit.Before;

import contracts.MineContract;
import tests.AbstractMineTest;

public class MineTestError extends AbstractMineTest{
	@Override
	@Before
	public void before() {
		mine = new MineImpl();
	}
}
