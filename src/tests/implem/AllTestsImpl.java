package tests.implem;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
	MineTestImpl.class, 
	RouteTestImpl.class, 
	VillageoisTestImpl.class, 
	//MoteurJeuTestError.class, 
	TerrainTestImpl.class,
	MurailleTestImpl.class,
	HotelVilleTestImpl.class,
	GestionDeplacementTestImpl.class})
public class AllTestsImpl {
}
