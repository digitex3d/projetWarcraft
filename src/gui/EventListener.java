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
	ECommande lastCommand;
	int lastArg;
	
	public EventListener(ITerrain terrain) {
		super();
		this.terrain = terrain;
		this.lastSelectedID = -1;
		this.lastCommand = ECommande.RIEN;
		this.lastArg = 360;
	}
	
	public IVillageois getVillageoisAt(int x, int y) {
		for (IVillageois vill : terrain.getListeVillageois())
			if ( x < x + vill.getLargeur() &&  x >= vill.getX() )
				if ( y < y + vill.getHauteur() &&  y >= vill.getY() )
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
			IVillageois vill = this.terrain.getListeVillageois().get(lastSelectedID);
			
			this.lastCommand = ECommande.DEPLACER;
			int dX = vill.getX() - x;
			int dY = vill.getY() - y;
			this.lastArg = (int) Math.toDegrees(Math.atan(dX/dY));
	
			System.out.println(this.lastArg);
		}
		
		
	}
	
	public ECommande getLastCommand(){
		ECommande tmp = this.lastCommand;
		this.lastCommand = ECommande.RIEN;
		return tmp;
		
	}
	
	public int getLastID(){
		return this.lastSelectedID;
	}
	
	public int getLastArg(){
		return this.lastArg;
	}
	
}
