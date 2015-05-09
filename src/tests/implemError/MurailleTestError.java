package tests.implemError;

import implemError.MurailleImplError;

import org.junit.Before;

import tests.AbstractMurailleTest;
import contracts.MurailleContract;

public class MurailleTestError extends AbstractMurailleTest{
	@Override
	@Before
	public void before() {
		mur = new MurailleContract(new MurailleImplError());
	}
}
