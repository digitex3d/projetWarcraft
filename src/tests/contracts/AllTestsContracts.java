package tests.contracts;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
	MineContractTest.class, 
	MoteurJeuContractTest.class, 
	RouteContractTest.class, 
	VillageoisContractTest.class, 
	MurailleContractTest.class,
	HotelVilleContractTest.class,
	TerrainContractTest.class,
	GestionDeplacementContractTest.class})
public class AllTestsContracts {
}
