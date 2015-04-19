package tests.contracts;

import implementations.VillageoisImpl;

import org.junit.Before;

import contracts.VillageoisContract;

import tests.AbstractVillageoisTest;

public class VillageoisContractTest extends AbstractVillageoisTest {

	@Override
	@Before
	public void before() {
		villageois = new VillageoisContract(new VillageoisImpl());

	}

}
