package tests.contracts;

import java.util.ArrayList;

import implementations.HotelVilleImpl;
import implementations.MineImpl;
import implementations.MoteurJeuImpl;
import implementations.VillageoisImpl;

import org.junit.Before;
import org.junit.Test;

import contracts.MoteurJeuContract;

import services.IHotelVille;
import services.IMine;
import services.IVillageois;
import tests.AbstractMoteurJeuTest;


import exceptions.PreconditionError;

public class MoteurJeuContractTest extends AbstractMoteurJeuTest {

	@Override
	@Before
	public void before() {
		moteur = new MoteurJeuContract(new MoteurJeuImpl());

		
		IHotelVille hotelVille = new HotelVilleImpl();

		moteur.bind(hotelVille);
		
		ArrayList<IVillageois> villageois =
				 new ArrayList<IVillageois>();
		
		for (int i=0; i < 3; i++)
			villageois.add( new VillageoisImpl());
	
		ArrayList<IMine> mines =
				 new ArrayList<IMine>();
		
		for (int i=0; i < 3; i++)
			mines.add( new MineImpl());
		
		moteur.bind(villageois, mines);

	}
	
	@Test(expected=PreconditionError.class)
	public void testInitFail() {
		// condition initiale : aucune

		// opération
		moteur.init(100, 100, -2 );

		// oracle un message d'erreur est attendu : Cf. @Test(expected=PreconditionError.class)	
	}
	

	

	

}
