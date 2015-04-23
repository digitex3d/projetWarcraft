package implementations;

import java.util.List;
import java.util.Map;
import java.util.Set;

import enums.ECommande;
import enums.ERace;
import enums.EResultat;
import services.IHotelVille;
import services.IMine;
import services.IMoteurJeu;
import services.IVilainService;
import services.IVillageois;
import utils.Utils;

public class MoteurJeuImpl implements IMoteurJeu{
	protected int largeurTerrain;
    protected int hauteurTerrain;
    protected int maxPasJeu;  
    protected int pasJeuCourant;
    protected boolean estFini;
    protected EResultat resultatFinal;
    protected Map<Integer, IMine> numeroesMine;
    protected Map<Integer, IVillageois> numeroesVillageois;
    protected Map<Integer, Integer> posVillageoisX;
    protected Map<Integer, Integer> posVillageoisY;
    protected Map<Integer, Integer> posMineX;
    protected Map<Integer, Integer> posMineY;
    protected int posHoteldeVilleX;
    protected int posHoteldeVilleY;
    protected List<IVillageois> villageois;
    protected List<IMine> mines;
    
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
	public Set<Integer> getNumeroesVillageois() {
		return this.numeroesMine.keySet();
	}

	@Override
	public IVillageois getVillageois(int num) {
		return this.numeroesVillageois.get(num);
	}

	@Override
	public int getPositionVillageoisX(int num) {
		return this.posVillageoisX.get(num);
	}

	@Override
	public int getPositionVillageoisY(int num) {
		return this.posVillageoisY.get(num);
	}

	@Override
	public Set<Integer> getNumeroesMine() {
		return this.numeroesMine.keySet();
	}

	@Override
	public int getPositionMineX(int num) {
		return this.posMineX.get(num);
	}

	@Override
	public int getPositionMineY(int num) {
		return this.posMineY.get(num);
	}

	@Override
	public IHotelVille getHotelDeVille() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPositionHotelVilleX() {
		return this.posHoteldeVilleX;
	}

	@Override
	public int getPositionHotelVilleY() {
		return this.posHoteldeVilleY;
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
		 
		 // Initialisation Hotel de ville
		 this.posHoteldeVilleX = Utils.randInt(0, 51);
		 this.posHoteldeVilleY = Utils.randInt(0, 51);
		 this.hotelDeVille.init(20, 20);
		 
		// Initialisation Villageois
		int poshx = this.posHoteldeVilleX;
		int poshy = this.posHoteldeVilleY;
		for( int numv : this.getNumeroesVillageois()){
			this.villageois.get(numv).init(ERace.HUMAN, 1, 2, 10, 10.0, 100);
			this.posVillageoisX.put(numv, poshx + Utils.randInt(0, 51));
			this.posVillageoisY.put(numv, poshy + Utils.randInt(0, 51));
			
		}
		
		// Initialisation Mines
		for( int numm : this.getNumeroesMine()){
				this.mines.get(numm).init(3,3);
				this.posMineX.put(numm, Utils.randInt(0, this.getLargeurTerrain()));
				this.posMineY.put(numm, Utils.randInt(0, this.getLargeurTerrain()));
					
				}
		 
		 
		 return this;
		 
		                                                
		
	
	}

	@Override
	public void pasJeu(ECommande commande, int numVillageois, int argument) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IMine getMine(int num) {
		// TODO Auto-generated method stub
		return null;
	}

}
