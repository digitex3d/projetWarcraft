package tests.implem;


import java.util.ArrayList;
import java.util.List;

import implemError.TerrainImplError;
import implementations.MineImpl;
import implementations.TerrainImpl;
import implementations.VillageoisImpl;

import org.junit.Before;

import contracts.MineContract;
import contracts.TerrainContract;
import contracts.VillageoisContract;
import enums.ERace;
import services.IHotelVille;
import services.IMine;
import services.IMuraille;
import services.IRoute;
import services.IVillageois;
import tests.AbstractTerrainTest;

public class TerrainTestError extends AbstractTerrainTest {

	@Override
	@Before
	public void before() {
		terrain = new TerrainImpl();

		IVillageois vill = new VillageoisImpl();
		vill.init(40, 40, ERace.ORC, 11, 11, 5, 5, 5);
		List<IVillageois> lv = new ArrayList<IVillageois>();
		List<IHotelVille> lh = new ArrayList<IHotelVille>();
		
		IMine mine = new MineImpl();
		mine.init(80, 80, 5, 5);
		List<IMine> lm = new ArrayList<IMine>();
		List<IMuraille> lmu = new ArrayList<IMuraille>();
		List<IRoute> lr = new ArrayList<IRoute>();
		lv.add(vill);
		terrain.bindVill(lv);
		terrain.bindHDV(lh);
		terrain.bindMine(lm);
		terrain.bindMur(lmu);
		terrain.bindRoute(lr);
	}
}
