package tests.implem;




import implementations.VillageoisImpl;

import org.junit.Before;

import tests.AbstractVillageoisTest;

public class VillageoisTestImpl extends AbstractVillageoisTest{
	@Override
	@Before
	public void before() {
		villageois = new VillageoisImpl();
	}
}
