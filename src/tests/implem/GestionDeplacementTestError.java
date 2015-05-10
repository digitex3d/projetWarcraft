package tests.implem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import implemError.GestionDeplacementImplError;
import implementations.GestionDeplacementImpl;
import implementations.MurailleImpl;
import implementations.RouteImpl;
import implementations.TerrainImpl;
import implementations.VillageoisImpl;

import org.junit.Before;
import org.junit.Test;

import services.IHotelVille;
import services.IMine;
import services.IMuraille;
import services.IRoute;
import services.IVillageois;
import tests.AbstractGestionDeplacementTests;
import utils.Utils;
import contracts.GestionDeplacementContract;
import contracts.TerrainContract;
import enums.ERace;
import exceptions.PreconditionError;

public class GestionDeplacementTestError extends AbstractGestionDeplacementTests{
	@Override
	@Before
	public void before() {
		
		List<IMuraille> listeMuraille = new ArrayList<IMuraille>();
		List<IRoute> listeRoute=new   ArrayList<IRoute>();
		List<IMine> listeMine=new   ArrayList<IMine>();
		List<IVillageois> listeVillageois=new   ArrayList<IVillageois>();
		List<IHotelVille> listeHotelVille=new   ArrayList<IHotelVille>();
		terrain = new TerrainImpl();
		
		terrain.bindHDV(listeHotelVille);
		terrain.bindMine(listeMine);
		terrain.bindMur(listeMuraille);
		terrain.bindVill(listeVillageois);
		terrain.bindRoute(listeRoute);
		
		terrain = terrain.init(600, 400);
		
		gd = new GestionDeplacementImpl();

		gd.bindTerrain(terrain);
		gd.init();
	}
	
	@Test
	public void testCalcCheminPre1() {
		// condition initiale : aucune
		
		IVillageois vill = new VillageoisImpl();
		vill = vill.init(0, 0, ERace.ORC, 1, 1, 10, 2, 100);
		
		List<IVillageois> listeVillageois=new ArrayList<IVillageois>();
		listeVillageois.add(vill);
		
		terrain.bindVill(listeVillageois);
		
		
		
	
			
		// opération
		gd.calcChemin(0, 270);

		// oracle
		
		ArrayList<Integer> res_cheminX = 
				new ArrayList<Integer>(Arrays.asList(0,0));
		
		assertPerso("calcChemin, les X du cheminX sont fausses", 
				Utils.areEqualValues(gd.getCheminX(), res_cheminX));
		
		ArrayList<Integer> res_cheminY = 
				new ArrayList<Integer>(Arrays.asList(1,2));
		
		assertPerso("calcChemin, les Y du cheminY sont fausses", 
				Utils.areEqualValues(gd.getCheminY(), res_cheminY));
		
		ArrayList<Integer> res_pointArrive = 
				new ArrayList<Integer>(Arrays.asList(0,2));
		
		assertPerso("calcChemin, le point d'arrivee est faux", 
				Utils.areEqualValues(gd.getPointArrivee(), res_pointArrive));
		
		assertPerso("calcChemin, le premierObstacle est faux", 
				gd.getFirstObstacle()== -1);
		

		assertPerso("calcChemin, estCalcChemin est faux", 
				gd.estCalcChemin() == true);
		
	}
	
	@Test
	public void testCalcCheminPre2() {

		// condition initiale : aucune
		IVillageois vill = new VillageoisImpl();
		vill = vill.init(0, 0, ERace.ORC, 1, 1, 10, 2, 100);
		
		List<IVillageois> listeVillageois=new ArrayList<IVillageois>();
		listeVillageois.add(vill);
		
		terrain.bindVill(listeVillageois);
			
		// opération
		gd.calcChemin(0, 360);

		// oracle
		
		ArrayList<Integer> res_cheminX = 
				new ArrayList<Integer>(Arrays.asList(1,2));
		
		assertPerso("calcChemin, les X du cheminX sont fausses", 
				Utils.areEqualValues(gd.getCheminX(), res_cheminX));
		
		ArrayList<Integer> res_cheminY = 
				new ArrayList<Integer>(Arrays.asList(0,0));
		
		assertPerso("calcChemin, les Y du cheminY sont fausses", 
				Utils.areEqualValues(gd.getCheminY(), res_cheminY));
		
		ArrayList<Integer> res_pointArrive = 
				new ArrayList<Integer>(Arrays.asList(2,0));
		
		assertPerso("calcChemin, le point d'arrivee est faux", 
				Utils.areEqualValues(gd.getPointArrivee(), res_pointArrive));
		
		assertPerso("calcChemin, le premierObstacle est faux", 
				gd.getFirstObstacle()== -1);
		

		assertPerso("calcChemin, estCalcChemin est faux", 
				gd.estCalcChemin() == true);
		


	}		

	@Test
	public void testCalcCheminPre3() {

		// condition initiale : aucune
		
		IVillageois vill = new VillageoisImpl();
		vill = vill.init(0, 0, ERace.ORC, 1, 1, 10, 2, 100);
		
		List<IVillageois> listeVillageois=new ArrayList<IVillageois>();
		listeVillageois.add(vill);
		
		terrain.bindVill(listeVillageois);
			
		// opération
		gd.calcChemin(0, 0);

		// oracle
		
		ArrayList<Integer> res_cheminX = 
				new ArrayList<Integer>(Arrays.asList(1,2));
		
		assertPerso("calcChemin, les X du cheminX sont fausses", 
				Utils.areEqualValues(gd.getCheminX(), res_cheminX));
		
		ArrayList<Integer> res_cheminY = 
				new ArrayList<Integer>(Arrays.asList(0,0));
		
		assertPerso("calcChemin, les Y du cheminY sont fausses", 
				Utils.areEqualValues(gd.getCheminY(), res_cheminY));
		
		ArrayList<Integer> res_pointArrive = 
				new ArrayList<Integer>(Arrays.asList(2,0));
		
		assertPerso("calcChemin, le point d'arrivee est faux", 
				Utils.areEqualValues(gd.getPointArrivee(), res_pointArrive));
		
		assertPerso("calcChemin, le premierObstacle est faux", 
				gd.getFirstObstacle()== -1);
		

		assertPerso("calcChemin, estCalcChemin est faux", 
				gd.estCalcChemin() == true);
		


	}	
	
	@Test(expected=PreconditionError.class)
	public void testCalcCheminPre4() {

		// condition initiale : aucune
		
		IVillageois vill = new VillageoisImpl();
		vill = vill.init(0, 0, ERace.ORC, 1, 1, 10, 2, 100);
		
		List<IVillageois> listeVillageois=new ArrayList<IVillageois>();
		listeVillageois.add(vill);
		
		terrain.bindVill(listeVillageois);
			
		// opération
		gd.calcChemin(0, -5);

		// oracle
		
		// Exception
		
	}	
	
	@Test(expected=PreconditionError.class)
	public void testCalcCheminPre5() {

		// condition initiale : aucune
		
		IVillageois vill = new VillageoisImpl();
		vill = vill.init(0, 0, ERace.ORC, 1, 1, 10, 2, 100);
		
		List<IVillageois> listeVillageois=new ArrayList<IVillageois>();
		listeVillageois.add(vill);
		
		terrain.bindVill(listeVillageois);
			
		// opération
		gd.calcChemin(0, 370);

		// oracle
		
		// Exception
		
	}		
	
	@Test(expected=PreconditionError.class)
	public void testCalcCheminPre6() {

		// condition initiale : aucune
		
		IVillageois vill = new VillageoisImpl();
		vill = vill.init(0, 0, ERace.ORC, 1, 1, 10, 2, 100);
		
		List<IVillageois> listeVillageois=new ArrayList<IVillageois>();
		listeVillageois.add(vill);
		
		terrain.bindVill(listeVillageois);
			
		// opération
		gd.calcChemin(5, 60);

		// oracle
		
		// Exception
		
	}		
	
	@Test
	public void testCalcChemin8() {

		// condition initiale : aucune
		
		// Ajute d'une muraille au terrain
		IMuraille mur = new MurailleImpl();
		mur = mur.init(2, 0, 1, 1, 100);
		
		List<IMuraille> listeMuraille=new ArrayList<IMuraille>();
		listeMuraille.add(mur);
		
		// Ajute d'un villageois au terrain
		IVillageois vill = new VillageoisImpl();
		vill = vill.init(0, 0, ERace.ORC, 1, 1, 10, 4, 100);
		
		List<IVillageois> listeVillageois=new ArrayList<IVillageois>();
		listeVillageois.add(vill);
		
		terrain.bindMur(listeMuraille);
		
			
		terrain.init(600, 400);
		
		terrain.bindVill(listeVillageois);
		
		// opération
		gd.calcChemin(0, 360);

		// oracle
		ArrayList<Integer> res_cheminX = 
				new ArrayList<Integer>(Arrays.asList(1,2,3,4));
		
		assertPerso("calcChemin, les X du cheminX sont fausses", 
				Utils.areEqualValues(gd.getCheminX(), res_cheminX));
		
		ArrayList<Integer> res_cheminY = 
				new ArrayList<Integer>(Arrays.asList(0,0,0,0));
		
		assertPerso("calcChemin, les Y du cheminY sont fausses", 
				Utils.areEqualValues(gd.getCheminY(), res_cheminY));
		
		ArrayList<Integer> res_pointArrive = 
				new ArrayList<Integer>(Arrays.asList(1,0));
		
		assertPerso("calcChemin, le point d'arrivee est faux", 
				Utils.areEqualValues(gd.getPointArrivee(), res_pointArrive));
		
		assertPerso("calcChemin, le premierObstacle est faux", 
				gd.getFirstObstacle()== 1);
		

		assertPerso("calcChemin, estCalcChemin est faux", 
				gd.estCalcChemin() == true);
		
	}	
	
	@Test
	public void testCalcChemin9() {

		// condition initiale : aucune
		
		// Ajute d'une muraille au terrain
		IMuraille mur = new MurailleImpl();
		mur = mur.init(1, 0, 1, 1, 100);
		
		List<IMuraille> listeMuraille=new ArrayList<IMuraille>();
		listeMuraille.add(mur);
		
		// Ajute d'un villageois au terrain
		IVillageois vill = new VillageoisImpl();
		vill = vill.init(0, 0, ERace.ORC, 1, 1, 10, 4, 100);
		
		List<IVillageois> listeVillageois=new ArrayList<IVillageois>();
		listeVillageois.add(vill);
		
		terrain.bindMur(listeMuraille);
		
			
		terrain.init(600, 400);
		
		terrain.bindVill(listeVillageois);
		
		// opération
		gd.calcChemin(0, 360);

		// oracle
		ArrayList<Integer> res_cheminX = 
				new ArrayList<Integer>(Arrays.asList(1,2,3,4));
		
		assertPerso("calcChemin, les X du cheminX sont fausses", 
				Utils.areEqualValues(gd.getCheminX(), res_cheminX));
		
		ArrayList<Integer> res_cheminY = 
				new ArrayList<Integer>(Arrays.asList(0,0,0,0));
		
		assertPerso("calcChemin, les Y du cheminY sont fausses", 
				Utils.areEqualValues(gd.getCheminY(), res_cheminY));
		
		ArrayList<Integer> res_pointArrive = 
				new ArrayList<Integer>(Arrays.asList(0,0));
		
		assertPerso("calcChemin, le point d'arrivee est faux", 
				Utils.areEqualValues(gd.getPointArrivee(), res_pointArrive));
		
		assertPerso("calcChemin, le premierObstacle est faux", 
				gd.getFirstObstacle()== 0);
		

		assertPerso("calcChemin, estCalcChemin est faux", 
				gd.estCalcChemin() == true);
		
	}		

	@Test
	public void testCalcChemin10() {

		// condition initiale : aucune
		
		// Ajute d'une muraille au terrain
		IRoute route = new RouteImpl();
		route = route.init(2, 0, 1, 1, 1);
		
		List<IRoute> listeRoute =new ArrayList<IRoute>();
		listeRoute.add(route);
		
		// Ajute d'un villageois au terrain
		IVillageois vill = new VillageoisImpl();
		vill = vill.init(0, 0, ERace.ORC, 1, 1, 10, 4, 100);
		
		List<IVillageois> listeVillageois=new ArrayList<IVillageois>();
		listeVillageois.add(vill);
		
		terrain.bindRoute(listeRoute);
		
			
		terrain.init(600, 400);
		
		terrain.bindVill(listeVillageois);
		
		// opération
		gd.calcChemin(0, 360);

		// oracle
		ArrayList<Integer> res_cheminX = 
				new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
		
		assertPerso("calcChemin, les X du cheminX sont fausses", 
				Utils.areEqualValues(gd.getCheminX(), res_cheminX));
		
		ArrayList<Integer> res_cheminY = 
				new ArrayList<Integer>(Arrays.asList(0,0,0,0,0));
		
		assertPerso("calcChemin, les Y du cheminY sont fausses", 
				Utils.areEqualValues(gd.getCheminY(), res_cheminY));
		
		ArrayList<Integer> res_pointArrive = 
				new ArrayList<Integer>(Arrays.asList(5,0));
		
		assertPerso("calcChemin, le point d'arrivee est faux", 
				Utils.areEqualValues(gd.getPointArrivee(), res_pointArrive));
		
		assertPerso("calcChemin, le premierObstacle est faux", 
				gd.getFirstObstacle()== -1);
		

		assertPerso("calcChemin, estCalcChemin est faux", 
				gd.estCalcChemin() == true);
		
	}		
	
	
}
