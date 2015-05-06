package services;

import java.util.List;

public interface ITerrain {
	/* ########### Observators ########### */	
	int getLargeur();
	int getHauteur();
	List<IMuraille> getListeMuraille();
	List<IRoute> getListeRoute();
	List<IVillageois> getListeVillageois();
	List<IMine> getListeMine();
	List<IHotelVille> getListeHotelVille();
	// pre: x >= 0 && y >= 0 && x + l < largeur() && y + h < hauteur()
	boolean estFranchissable(int x, int y, int l, int h);
	
}
