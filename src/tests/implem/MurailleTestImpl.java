package tests.implem;

import implemError.MurailleImplError;
import implementations.MurailleImpl;

import org.junit.Before;

import tests.AbstractMurailleTest;
import contracts.MurailleContract;

public class MurailleTestImpl extends AbstractMurailleTest{
	@Override
	@Before
	public void before() {
		mur = new MurailleImpl();
	}
	
	
	
}
