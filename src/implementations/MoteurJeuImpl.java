package implementations;

import java.util.ArrayList;

import java.util.Set;

import enums.ECommande;
import enums.ERace;
import enums.EResultat;
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
		 this.hotelDeVille.setX(Utils.randInt(0, 51));
		 this.hotelDeVille.setY(Utils.randInt(0, 51));
		 this.hotelDeVille.init(20, 20);
		 
		// Initialisation Villageois
		int poshx = this.hotelDeVille.getX();
		int poshy = this.hotelDeVille.getY();
		for( IVillageois villageois : this.villageois){
			villageois.init(ERace.HUMAN, 1, 2, 10, 10.0, 100);
			villageois.setX(poshx + Utils.randInt(0, 51));
			villageois.setY(poshy + Utils.randInt(0, 51));
			
		}

		// Initialisation Mines
		for( IMine mine: this.mines){
			mine.init(3,3);
			mine.setX(Utils.randInt(0, this.getLargeurTerrain()));
			mine.setY(Utils.randInt(0, this.getLargeurTerrain()));

		}

		 
		 return this;
		                                                
	}

	@Override
	public void pasJeu(ECommande commande, int numVillageois, int argument) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void bind(ArrayList<IVillageois> villageois,
			ArrayList<IMine> mines) {
		this.villageois = villageois;
		this.mines = mines;
	}



	@Override
	public Set<Integer> getNumeroesMine() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void bind(IHotelVille hv) {
		this.hotelDeVille = hv;
		
	}


}
