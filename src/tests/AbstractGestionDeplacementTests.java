package tests;


import org.junit.After;
import org.junit.Before;

import services.IGestionDeplacement;
import services.ITerrain;

public abstract class AbstractGestionDeplacementTests extends AbstractAssertion {
	protected IGestionDeplacement gd;
	protected ITerrain terrain;


	@Before
	public abstract void before();

	@After
	public void after() {
		gd = null;
		terrain = null;
		
	}
	


}
