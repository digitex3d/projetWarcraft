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
import gui.ThreadListener;
import services.IEntite;


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
		IVillageois vill = new VillageoisImpl();
		vill.init(poshx + 30 , 
				  poshy ,
				ERace.HUMAN, 10, 10, 10, 10.0, 100);
		villageoisListe.add(vill);	
		
		// Init Villageois Orcs
		poshx = hvORC.getX();
		poshy = hvORC.getY();
		
		vill = new VillageoisImpl();
		vill.init(poshx + 30 , 
				  poshy ,
				ERace.ORC, 10, 10, 10, 10.0, 100);
		villageoisListe.add(vill);
			
		 

		// Initialisation Mines
		ArrayList<IMine> minesListe = new ArrayList<IMine>();

	
		IMine mine = new MineImpl();

		mine.init(300, 100, 12, 12);
		minesListe.add(mine);
		
		mine = new MineImpl();
		
		mine.init(160, 100, 12, 12);
		minesListe.add(mine);

		// Initialisation Murailles
		ArrayList<IMuraille> muraillesListe = new ArrayList<IMuraille>();

		
		IMuraille mur = new MurailleImpl();
		mur.init(200,300, 8, 8, 100);
		muraillesListe.add(mur);
	
		mur = new MurailleImpl();
		mur.init(100,250, 8, 8, 100);
		muraillesListe.add(mur);
	
		
		// Initialisation Routes
		ArrayList<IRoute> routesListe = new ArrayList<IRoute>();

	
		IRoute route = new RouteImpl();
		route.init(150, 150, 4, 16,1);
		routesListe.add(route);
		
		route = new RouteImpl();
		route.init(60, 100, 4, 16,1);
		routesListe.add(route);
		

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
		moteurJeu.init(1000);
		
		EventListener listener = new EventListener(terrain, moteurJeu);
		
		ThreadListener tlist = new ThreadListener(listener, terrain);
		tlist.start();
		
		
		while (!moteurJeu.estFini())
		{
			try {
				
				synchronized (moteurJeu) {

					moteurJeu.pasJeu(listener.getLastCommand(), 
							listener.getLastID(), 
							listener.getLastArg());
				}
		
				tlist.updateTerrain();
				System.out.println("PasDeJeu:" + moteurJeu.getPasJeuCourant());
				System.out.println("Or Orc:" + moteurJeu.getHotelVille(0).getOrRestant());
				System.out.println("Or Humains:" + moteurJeu.getHotelVille(1).getOrRestant());
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(moteurJeu.resultatFinal());
		
	
		
	}
	
	
}
