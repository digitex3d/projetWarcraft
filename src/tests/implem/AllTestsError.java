package tests.implem;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
	MineTestError.class, 
	RouteTestError.class, 
	VillageoisTestError.class, 
	//MoteurJeuTestError.class, 
	TerrainTestError.class,
	MurailleTestError.class,
	HotelVilleTestError.class,
	GestionDeplacementTestError.class})
public class AllTestsError {
}
