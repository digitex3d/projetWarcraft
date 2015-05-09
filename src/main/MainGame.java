package main;

import java.util.ArrayList;

import implementations.GestionDeplacementImpl;
import implementations.HotelVilleImpl;
import implementations.MineImpl;
import implementations.MoteurJeuImpl;
import implementations.MurailleImpl;
import implementations.RouteImpl;
import implementations.TerrainImpl;
import implementations.VillageoisImpl;
import services.IGestionDeplacement;
import services.IHotelVille;
import services.IMine;
import services.IMoteurJeu;
import services.IMuraille;
import services.IRoute;
import services.ITerrain;
import services.IVillageois;
import utils.Utils;
import enums.ERace;
import gui.EventListener;
import gui.GuiMainWindow;
import gui.Terrain;



public class MainGame {
	public static void main(String[] args) {
		final int winLargeur = 600;
		final int winHauteur = 400;
		
		// init HotelVille
		IHotelVille hvORC = new HotelVilleImpl();
		hvORC = hvORC.init(50, 50, 20, 20, 16, ERace.ORC);
		
		IHotelVille hvHumain =  new HotelVilleImpl();
		hvHumain = hvHumain.init(550, 350, 20, 20, 16, ERace.HUMAN);
		
		ArrayList<IHotelVille> hVListe = new ArrayList<IHotelVille>();
		hVListe.add(hvHumain);
		hVListe.add(hvORC);
		
		ArrayList<IVillageois> villageoisListe = new ArrayList<IVillageois>();
		
		// Init Villageois Humains
		int poshx = hvHumain.getX();
		int poshy = hvHumain.getY();
		for (int i = 0; i < 3; i++) {
			IVillageois vill = new VillageoisImpl();
			vill.init(poshx + Utils.randInt(10, 30) , 
					  poshy + Utils.randInt(10, 30),
					ERace.HUMAN, 10, 10, 10, 4.0, 100);
			villageoisListe.add(vill);
			
		}		
		
		// Init Villageois Orcs
		poshx = hvORC.getX();
		poshy = hvORC.getY();
		for (int i = 0; i < 3; i++) {
			IVillageois vill = new VillageoisImpl();
			vill.init(poshx + Utils.randInt(10, 30) , 
					  poshy + Utils.randInt(10, 30),
					ERace.ORC, 10, 10, 10, 4.0, 100);
			villageoisListe.add(vill);
		}		
		 

		// Initialisation Mines
		ArrayList<IMine> minesListe = new ArrayList<IMine>();

		for (int i = 0; i < 8; i++) {
			IMine mine = new MineImpl();


			mine.init(Utils.randInt(0, winLargeur), Utils.randInt(0, winHauteur), 12, 12);
			minesListe.add(mine);
		}

		// Initialisation Murailles
		ArrayList<IMuraille> muraillesListe = new ArrayList<IMuraille>();

		for (int i = 0; i < 15; i++) {
			IMuraille mur = new MurailleImpl();
			mur.init(Utils.randInt(0, winLargeur), Utils.randInt(0, winHauteur), 8, 8, 100);
			muraillesListe.add(mur);
		}
		// Initialisation Routes
		ArrayList<IRoute> routesListe = new ArrayList<IRoute>();

		for (int i = 0; i < 20; i++) {
			IRoute route = new RouteImpl();
			route.init(Utils.randInt(0, winLargeur), Utils.randInt(0, winHauteur), 4, 16,1);
			routesListe.add(route);
		}

		// init terrain
		ITerrain terrain = new TerrainImpl();
		
		terrain.bindHDV(hVListe);
		terrain.bindMine(minesListe);
		terrain.bindMur(muraillesListe);
		terrain.bindVill(villageoisListe);
		terrain.bindRoute(routesListe);
		
		terrain.init(winLargeur+100, winHauteur+100);
		
		IMoteurJeu moteurJeu = new MoteurJeuImpl();
		
		moteurJeu.bindTerrain(terrain);
		
		IGestionDeplacement gd = new GestionDeplacementImpl();
		gd.bindTerrain(terrain);
		gd = gd.init();
		
		moteurJeu.bindGD(gd);
		moteurJeu.init(10);
		
		EventListener listener = new EventListener(terrain, moteurJeu);
		
	


		
		
		
		GuiMainWindow window = new GuiMainWindow(terrain, listener);

		window.updateTerrain(terrain);
		window.setVisible(true);
		
	}
}
