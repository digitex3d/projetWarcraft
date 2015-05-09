package tests.contracts;


import java.util.ArrayList;
import java.util.List;

import implementations.HotelVilleImpl;
import implementations.MineImpl;
import implementations.TerrainImpl;
import implementations.VillageoisImpl;

import org.junit.Before;

import contracts.HotelVilleContract;
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

public class TerrainContractTest extends AbstractTerrainTest {

	@Override
	@Before
	public void before() {		
		terrain = new TerrainContract(new TerrainImpl());
		
		IVillageois vill = new VillageoisContract(new VillageoisImpl());
		vill.init(40, 40, ERace.ORC, 11, 11, 5, 5, 100);
		List<IVillageois> lv = new ArrayList<IVillageois>();
		lv.add(vill);
		
		IHotelVille hv = new HotelVilleContract(new HotelVilleImpl());
		hv.init(65, 65, 1, 1, 16, ERace.ORC);
		List<IHotelVille> lh = new ArrayList<IHotelVille>();
		lh.add(hv);
		
		IMine mine = new MineContract(new MineImpl());
		mine.init(80, 80, 5, 5);
		List<IMine> lm = new ArrayList<IMine>();
		lm.add(mine);
		
		List<IMuraille> lmu = new ArrayList<IMuraille>();
		List<IRoute> lr = new ArrayList<IRoute>();
		terrain.bindVill(lv);
		terrain.bindHDV(lh);
		terrain.bindMine(lm);
		terrain.bindMur(lmu);
		terrain.bindRoute(lr);
	}
}
