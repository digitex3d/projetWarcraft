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
import services.IVillageois;
import utils.Utils;

public class MoteurJeuImpl implements IMoteurJeu{
	protected int largeurTerrain;
    protected int hauteurTerrain;
    protected int maxPasJeu;  
    protected int pasJeuCourant;
    protected boolean estFini;
    protected EResultat resultatFinal;
    protected ArrayList<IVillageois> villageois;
    protected ArrayList<IMine> mines;
    protected GuiMainWindow gui;
    
    protected IHotelVille hotelDeVille;
    
    public MoteurJeuImpl(){}
    


	@Override
	public int getLargeurTerrain() {
		return this.largeurTerrain;
	}

	@Override
	public int getHauteurTerrain() {
		return this.hauteurTerrain;
	}

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
	public ArrayList<IVillageois> getListVillageois() {
		return this.villageois;
	}

	@Override
	public IVillageois getVillageois(int num) {
		return this.villageois.get(num);
	}

	@Override
	public int getPositionVillageoisX(int num) {
		return this.getVillageois(num).getX();
	}

	@Override
	public int getPositionVillageoisY(int num) {
		return this.getVillageois(num).getY();
	}

	@Override
	public ArrayList<IMine>  getListMines() {
		return this.mines;
	}

	@Override
	public IMine getMine(int num) {
		return this.mines.get(num);
		
	}
	
	@Override
	public int getPositionMineX(int num) {
		return this.getMine(num).getX();
	}

	@Override
	public int getPositionMineY(int num) {
		return this.getMine(num).getY();
	}

	@Override
	public IHotelVille getHotelDeVille() {
		return this.hotelDeVille;
	}

	@Override
	public int getPositionHotelVilleX() {
		return this.hotelDeVille.getX();
	}

	@Override
	public int getPositionHotelVilleY() {
		return this.hotelDeVille.getY();
	}

	@Override
	public boolean peutEntrer(int numVillageois, int numMine) {
		// TODO implementer
		return true;
	}

	@Override
	public boolean peutEntrerHotelVille(int num) {
		// TODO implementer
		return true;
	}

	@Override
	public IMoteurJeu init(int largeur, int hauteur, int maxPasJeu) {
		 this.largeurTerrain = largeur ;                       
		 this.hauteurTerrain = hauteur;                   
		 this.maxPasJeu =     maxPasJeu;                            
		 this.pasJeuCourant= 0;                             
		 this.estFini = false;                               
		
		 if (this.hotelDeVille == null)
				throw new Error("Vous devez binder le Hotel de ville avant de faire appel a init");
		 if (this.villageois == null)
				throw new Error("Vous devez binder les villageois avent de faire appel a init");
		 if (this.mines == null)
				throw new Error("Vous devez binder les Mines  ville avant de faire appel a init");
		 
		 // Initialisation Hotel de ville
		 int hvx = Utils.randInt(0, 51);
		 int hvy = Utils.randInt(0, 51);
		 this.hotelDeVille.init(hvx,hvy, 20, 20, 16);
		 
		// Initialisation Villageois
		int poshx = this.hotelDeVille.getX();
		int poshy = this.hotelDeVille.getY();
		for( IVillageois villageois : this.villageois){
			int vx = poshx + Utils.randInt(0, 51);
			int vy = poshy + Utils.randInt(0, 51);
			villageois.init(vx,vy,ERace.HUMAN, 4, 4, 10, 10.0, 100);

			
		}

		// Initialisation Mines
		for( IMine mine: this.mines){
			mine.init(7,7);
			mine.setX(Utils.randInt(0, this.getLargeurTerrain()));
			mine.setY(Utils.randInt(0, this.getLargeurTerrain()));

		}

		 
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
			IVillageois v = this.getVillageois(numVillageois);
			v.setX( (int ) (v.getX() + v.getVitesse()* Math.cos(argument)));
			v.setY( (int ) (v.getY() + v.getVitesse()* Math.sin(argument)));
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
		
		this.updateGui(this);
	}
	
	private void updateGui(MoteurJeuImpl moteurJeuImpl) {
		this.gui.updateMoteur(moteurJeuImpl);
		
	}

	public void bindWindow(GuiMainWindow gui){
		this.gui = gui;
	}


	@Override
	public void bind(ArrayList<IVillageois> villageois,
			ArrayList<IMine> mines) {
		this.villageois = villageois;
		this.mines = mines;
	}

	@Override
	public void bind(IHotelVille hv) {
		this.hotelDeVille = hv;
		
	}


}
