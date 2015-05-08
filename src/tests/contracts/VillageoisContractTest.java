package tests.contracts;

import implementations.VillageoisImpl;

import org.junit.Before;
import org.junit.Test;

import contracts.VillageoisContract;
import enums.ERace;

import tests.AbstractVillageoisTest;
import exceptions.PreconditionError;

public class VillageoisContractTest extends AbstractVillageoisTest {

	@Override
	@Before
	public void before() {
		villageois = new VillageoisContract(new VillageoisImpl());

	}
}
