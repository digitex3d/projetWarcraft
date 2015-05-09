package gui;

import java.util.HashSet;

import enums.ECommande;
import enums.EEntite;
import services.IMoteurJeu;
import services.ITerrain;
import services.IVillageois;

public class EventListener {
	IMoteurJeu moteur;
	ITerrain terrain;
	int lastSelectedID;
	
	public EventListener(ITerrain terrain, IMoteurJeu moteur) {
		super();
		this.terrain = terrain;
		this.moteur = moteur;
		this.lastSelectedID = -1;
	}
	
	public IVillageois getVillageoisAt(int x, int y) {
		for (IVillageois vill : terrain.getListeVillageois())
			if (vill.getX() >= x &&  x <= vill.getX()+vill.getLargeur() )
				if (vill.getY() <= y && y >= vill.getY()+ vill.getHauteur() )
					return vill;
		return null;
	}
	
	public void gererClick(int x, int y){
		HashSet<EEntite> entiteClique = (HashSet<EEntite>) terrain.getEntiteAt(x, y);
		
		System.out.println( entiteClique.toString() );
		System.out.println(  getVillageoisAt(x, y) );
		
		if (entiteClique.contains(EEntite.VILLAGEOIS)){
			this.lastSelectedID = terrain.getListeVillageois().indexOf( getVillageoisAt(x, y));
			
		}
		
		if (entiteClique.contains(EEntite.RIEN)){
			this.moteur.pasJeu(ECommande.DEPLACER, lastSelectedID, 360);
			
		}
		
		
	}
	
}
