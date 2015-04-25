package tests.implemError;



import implemError.MineImplError;

import org.junit.Before;

import tests.AbstractMineTest;

public class MineTestError extends AbstractMineTest{
	@Override
	@Before
	public void before() {
		moteur = new MineImplError();
	}
}
