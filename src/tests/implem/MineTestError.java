package tests.implem;




import implementations.MineImpl;

import org.junit.Before;


import tests.AbstractMineTest;

public class MineTestError extends AbstractMineTest{
	@Override
	@Before
	public void before() {
		mine = new MineImpl();
	}
}
