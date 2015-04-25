package main;

import java.util.ArrayList;

import implementations.HotelVilleImpl;
import implementations.MineImpl;
import implementations.MoteurJeuImpl;
import implementations.VillageoisImpl;
import services.IHotelVille;
import services.IMine;
import services.IMoteurJeu;
import services.IVillageois;

import com.sun.java.swing.plaf.windows.resources.windows;

import gui.GuiMainWindow;



public class MainGame {
	public static void main(String[] args) {

		IMoteurJeu moteurJeu = new MoteurJeuImpl();
		
		IHotelVille hotelVille = new HotelVilleImpl();

		moteurJeu.bind(hotelVille);
		
		ArrayList<IVillageois> villageois =
				 new ArrayList<IVillageois>();
		
		for (int i=0; i < 3; i++)
			villageois.add( new VillageoisImpl());
		

		
		ArrayList<IMine> mines =
				 new ArrayList<IMine>();
		
		for (int i=0; i < 3; i++)
			mines.add( new MineImpl());
		
		moteurJeu.bind(villageois, mines);
		
		moteurJeu.init(800, 600, 1000);
		
		GuiMainWindow window = new GuiMainWindow(moteurJeu.getLargeurTerrain(),
				moteurJeu.getHauteurTerrain());
		window.updateMoteur(moteurJeu);
		window.setVisible(true);
		
	}
}
