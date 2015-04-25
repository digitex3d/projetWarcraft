package decorators;

import java.util.ArrayList;

import services.IHotelVille;
import services.IMine;
import services.IMoteurJeu;
import services.IVillageois;
import utils.Utils;
import enums.ECommande;
import enums.ERace;
import enums.EResultat;
import implementations.MoteurJeuImpl;

public class MoteurJeuDecorator extends MoteurJeuImpl{
    protected IMoteurJeu delegate;
	
    public MoteurJeuDecorator(IMoteurJeu delegate) {
    	super();
    	this.delegate = delegate;
    }    


	@Override
	public int getLargeurTerrain() {
		return this.getLargeurTerrain();
	}

	@Override
	public int getHauteurTerrain() {
		return this.delegate.getHauteurTerrain();
	}

	@Override
	public int getMaxPasJeu() {
		return this.delegate.getMaxPasJeu();
	}

	@Override
	public int getPasJeuCourant() {
		return this.delegate.getPasJeuCourant();
	}

	@Override
	public boolean estFini() {
		return this.delegate.estFini();
	}

	@Override
	public EResultat resultatFinal() {
		return this.delegate.resultatFinal();
	}

	@Override
	public ArrayList<IVillageois> getListVillageois() {
		return this.delegate.getListVillageois();
	}

	@Override
	public IVillageois getVillageois(int num) {
		return this.delegate.getVillageois(num);
	}

	@Override
	public int getPositionVillageoisX(int num) {
		return this.delegate.getPositionVillageoisY(num);
	}

	@Override
	public int getPositionVillageoisY(int num) {
		return this.delegate.getPositionVillageoisY(num);
	}

	@Override
	public ArrayList<IMine>  getListMines() {
		return this.delegate.getListMines();
	}

	@Override
	public IMine getMine(int num) {
		return this.delegate.getMine(num);
		
	}
	
	@Override
	public int getPositionMineX(int num) {
		return this.delegate.getPositionMineX(num);
	}

	@Override
	public int getPositionMineY(int num) {
		return this.delegate.getPositionMineY(num);
	}

	@Override
	public IHotelVille getHotelDeVille() {
		return this.delegate.getHotelDeVille();
	}

	@Override
	public int getPositionHotelVilleX() {
		return this.delegate.getPositionHotelVilleX();
	}

	@Override
	public int getPositionHotelVilleY() {
		return this.delegate.getPositionHotelVilleY();
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
		
		 
		 return this.delegate.init(largeur, hauteur, maxPasJeu);
		                                                
	}

	@Override
	public void pasJeu(ECommande commande, int numVillageois, int argument) {
		this.delegate.pasJeu(commande, numVillageois, argument);
		
	}
	
	@Override
	public void bind(ArrayList<IVillageois> villageois, ArrayList<IMine> mine){
		this.delegate.bind(villageois, mine);
	}

	@Override
	public void bind(IHotelVille hv) {
		this.delegate.bind(hv);
		
	}


}
