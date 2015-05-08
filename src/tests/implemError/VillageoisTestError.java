package tests.implemError;



import implemError.VillageoisImplError;

import org.junit.Before;

import contracts.VillageoisContract;
import tests.AbstractVillageoisTest;

public class VillageoisTestError extends AbstractVillageoisTest{
	@Override
	@Before
	public void before() {
		villageois = new VillageoisContract(new VillageoisImplError());
	}
}
