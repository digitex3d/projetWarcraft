package tests.implemError;

import java.util.ArrayList;
import java.util.List;

import implementations.GestionDeplacementImpl;
import implementations.HotelVilleImpl;
import implementations.MineImpl;
import implementations.MoteurJeuImpl;
import implementations.MurailleImpl;
import implementations.TerrainImpl;
import implementations.VillageoisImpl;

import org.junit.Before;

import contracts.GestionDeplacementContract;
import contracts.HotelVilleContract;
import contracts.MineContract;
import contracts.MoteurJeuContract;
import contracts.MurailleContract;
import contracts.TerrainContract;
import contracts.VillageoisContract;
import enums.ERace;
import services.IGestionDeplacement;
import services.IHotelVille;
import services.IMine;
import services.IMuraille;
import services.IRoute;
import services.ITerrain;
import services.IVillageois;
import tests.AbstractMoteurJeuTest;

public class MoteurJeuTestError extends AbstractMoteurJeuTest {

	@Override
	@Before
	public void before() {
		moteur = new MoteurJeuImplError();
				
		ITerrain terrain = new TerrainContract(new TerrainImpl());
		
		IVillageois vill = new VillageoisContract(new VillageoisImpl());
		vill.init(40, 40, ERace.ORC, 11, 11, 5, 5, 100);
		IVillageois vill2 = new VillageoisContract(new VillageoisImpl());
		vill2.init(5, 589, ERace.HUMAN, 11, 11, 5, 5, 100);
		List<IVillageois> lv = new ArrayList<IVillageois>();
		lv.add(vill);
		lv.add(vill2);
		
		IHotelVille hv = new HotelVilleContract(new HotelVilleImpl());
		hv.init(65, 65, 1, 1, 16, ERace.ORC);
		List<IHotelVille> lh = new ArrayList<IHotelVille>();
		lh.add(hv);

		IHotelVille hv2 = new HotelVilleContract(new HotelVilleImpl());
		hv2.init(5, 580, 1, 1, 16, ERace.HUMAN);
		lh.add(hv2);

		IMine mine = new MineContract(new MineImpl());
		mine.init(60, 60, 3, 3);
		List<IMine> lm = new ArrayList<IMine>();
		lm.add(mine);
		
		List<IMuraille> lmu = new ArrayList<IMuraille>();
		IMuraille mu = new MurailleContract(new MurailleImpl());
		mu.init(20, 20, 5, 5, 10);
		lmu.add(mu);
		List<IRoute> lr = new ArrayList<IRoute>();
		terrain.bindVill(lv);
		terrain.bindHDV(lh);
		terrain.bindMine(lm);
		terrain.bindMur(lmu);
		terrain.bindRoute(lr);
		terrain.init(800, 600);
		moteur.bindTerrain(terrain);

		IGestionDeplacement gd = new GestionDeplacementContract(new GestionDeplacementImpl());
		gd.bindTerrain(terrain);
		gd.init();
		moteur.bindGD(gd);
	}
}
