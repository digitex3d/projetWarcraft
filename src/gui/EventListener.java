package gui;

import java.util.HashSet;

import enums.ECommande;
import enums.EEntite;
import services.IHotelVille;
import services.IMine;
import services.IMoteurJeu;
import services.ITerrain;
import services.IVillageois;

public class EventListener {
	IMoteurJeu moteur;
	ITerrain terrain;
	int lastSelectedID;
	ECommande lastCommand;
	int lastArg;
	
	public EventListener(IMoteurJeu moteur) {
		super();
		this.terrain = moteur.getTerrain();
		this.lastSelectedID = -1;
		this.lastCommand = ECommande.RIEN;
		this.lastArg = 360;
		this.moteur = moteur;
	}
	
	public IVillageois getVillageoisAt(int x, int y) {
		for (IVillageois vill : terrain.getListeVillageois())
			if ( x < x + vill.getLargeur() &&  x >= vill.getX() )
				if ( y < y + vill.getHauteur() &&  y >= vill.getY() )
					return vill;
		return null;
	}
	
	public IMine getMineAt(int x, int y) {
		for (IMine mine : terrain.getListeMine())
			if ( x < x + mine.getLargeur() &&  x >= mine.getX() )
				if ( y < y + mine.getHauteur() &&  y >= mine.getY() )
					return mine;
		return null;
	}
	
	public IHotelVille getHDVAt(int x, int y) {
		for (IHotelVille  hdv : terrain.getListeHotelVille())
			if ( x < x + hdv.getLargeur() &&  x >= hdv.getX() )
				if ( y < y + hdv.getHauteur() &&  y >= hdv.getY() )
					return hdv;
		return null;
	}
	
	public void gererClick(int x, int y){
		synchronized (moteur) {
			
		HashSet<EEntite> entiteClique = (HashSet<EEntite>) terrain.getEntiteAt(x, y);
		
		if (entiteClique.contains(EEntite.VILLAGEOIS)){
			
			this.lastSelectedID = terrain.getListeVillageois().indexOf( getVillageoisAt(x, y));
			
		}
		
		if (entiteClique.contains(EEntite.MINE)  && 
				this.lastSelectedID != -1){
	
			
			this.lastCommand = ECommande.ENTRERMINE;
			this.lastArg = terrain.getListeMine().indexOf( getMineAt(x, y));
			
			if(!moteur.peutEntrer(lastSelectedID, lastArg))
				this.lastCommand = ECommande.RIEN;
		}
		
		
		if (entiteClique.contains(EEntite.HDV)  && 
				this.lastSelectedID != -1){

			
			this.lastCommand = ECommande.ENTRERHOTELVILLE;
			this.lastArg = terrain.getListeHotelVille().indexOf( getHDVAt(x, y) );
			if(!this.moteur.peutEntrerHotelVille(lastSelectedID, lastArg))
				this.lastCommand = ECommande.RIEN;
			
		}
		
		if ((entiteClique.contains(EEntite.RIEN) || entiteClique.contains(EEntite.ROUTE)) && 
				this.lastSelectedID != -1){
			IVillageois vill = this.terrain.getListeVillageois().get(lastSelectedID);
			
			this.lastCommand = ECommande.DEPLACER;
			int dX = x- vill.getX();
			int dY = y - vill.getY();
			int angl = (int) Math.toDegrees(Math.atan2(-dX, -dY))+90;  
			this.lastArg =  (angl < 0) ? angl + 360: angl;
	
			System.out.println(this.lastArg);
		}
		
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
