package implementations;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import enums.ECommande;
import enums.ERace;
import enums.EResultat;
import gui.EEvent;
import gui.GuiMainWindow;
import services.IHotelVille;
import services.IMine;
import services.IMoteurJeu;
import services.IMuraille;
import services.ITerrain;
import services.IVillageois;
import utils.Utils;

public class MoteurJeuImpl implements IMoteurJeu{
    protected int maxPasJeu;  
    protected int pasJeuCourant;
    protected boolean estFini;
    protected EResultat resultatFinal;
    protected ITerrain terrain;
    
    public MoteurJeuImpl(){}
    

	@Override
	public int getMaxPasJeu() {
		return this.maxPasJeu;
	}

	@Override
	public int getPasJeuCourant() {
		return this.pasJeuCourant;
	}

	@Override
	public boolean estFini() {
		return this.estFini;
	}

	@Override
	public EResultat resultatFinal() {
		return this.resultatFinal;
	}


	@Override
	public boolean peutEntrer(int numVillageois, int numMine) {
		IVillageois vill = this.getVillageois(numVillageois);
		IMine mine = this.getMine(numMine);
		
		// Si la mine est laminée retourne false
		if( mine.estLaminee() ) return false;
		
		int mineCenterX = mine.getX() + ( mine.getLargeur() / 2 );
		int mineCenterY = mine.getY() + ( mine.getHauteur() / 2 );
		
		return Utils.distance(	vill.getX(), 
								vill.getY(),
								mineCenterX, 
								mineCenterY) <= 51;
		
	}

	@Override
	public IMoteurJeu init(ITerrain terrain, int maxPas) {
		this.terrain = terrain;
		this.maxPasJeu =     maxPas;                            
		this.pasJeuCourant= 0;                             

		//TODO: à faire dans l'impl de terrain
////		if (this.hotelDeVille == null)
////			throw new Error("Vous devez binder le Hotel de ville avant de faire appel a init");
////		if (this.villageois == null)
////			throw new Error("Vous devez binder les villageois avent de faire appel a init");
////		if (this.mines == null)
////			throw new Error("Vous devez binder les Mines  ville avant de faire appel a init");
//
//		// Initialisation Hotel de ville
//		int hvx = Utils.randInt(0, 51);
//		int hvy = Utils.randInt(0, 51);
//		this.hotelDeVille.init(hvx,hvy, 20, 20, 16);
//
//		// Initialisation Villageois
//		int poshx = this.hotelDeVille.getX();
//		int poshy = this.hotelDeVille.getY();
//		for( IVillageois villageois : this.villageois){
//			int vx = poshx + Utils.randInt(0, 51);
//			int vy = poshy + Utils.randInt(0, 51);
//			villageois.init(vx,vy,ERace.HUMAN, 4, 4, 10, 10.0, 100);
//
//
//		}
//
//		// Initialisation Mines
//		for( IMine mine: this.mines){
//			mine.init(7,7);
//			mine.setX(Utils.randInt(0, this.getLargeurTerrain()));
//			mine.setY(Utils.randInt(0, this.getLargeurTerrain()));
//
//		}

		 
		 return this;
		                                                
	}

	public void eventListener(MouseEvent e, EEvent click){
		switch( click ){
		case CLICK:
			this.pasJeu( ECommande.DEPLACER, 0, 90);
			break;
		}
	}
	
	@Override
	public void pasJeu(ECommande commande, int numVillageois, int argument) {
		switch(commande){
		case DEPLACER:
			break;
		case ENTREHOTELVILLE:
			break;
		case ENTREMINE:
			break;
		case RIEN:
			break;
		default:
			break;
		}
		
		this.pasJeuCourant += 1;
		
	}

	@Override
	public ITerrain getTerrain() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public IMuraille getMuraille(int mu) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public IHotelVille getHotelVille(int hdv) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean peutEntrerHotelVille(int vilNum, int hdvNum) {
		IVillageois vill = this.getVillageois(vilNum);
		IHotelVille hdv = this.getHotelVille(hdvNum);
		
		// Si la race n'est pas la bonne retourne false
		if( hdv.getEtatAppartenance() != vill.getRace() ) return false;
		
		int hdvCenterX = hdv.getX() + ( hdv.getLargeur() / 2 );
		int hdvCenterY = hdv.getY() + ( hdv.getHauteur() / 2 );
		
		return Utils.distance(	vill.getX(), 
								vill.getY(),
								hdvCenterX, 
								hdvCenterY) <= 51;
	}



	@Override
	public boolean peutTaperMuraille(int vilNum, int mur) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public IVillageois getVillageois(int vill) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IMine getMine(int mi) {
		// TODO Auto-generated method stub
		return null;
	}





}
