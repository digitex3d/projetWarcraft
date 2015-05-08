package decorators;

import services.IHotelVille;
import services.IMine;
import services.IMoteurJeu;
import services.IMuraille;
import services.ITerrain;
import services.IVillageois;
import enums.ECommande;
import enums.EResultat;

public class MoteurJeuDecorator implements IMoteurJeu{
    private IMoteurJeu delegate;
	
    public MoteurJeuDecorator(IMoteurJeu delegate) {
    	super();
    	this.delegate = delegate;
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
	public IVillageois getVillageois(int vill) {
		return this.delegate.getVillageois(vill);
	}

	@Override
	public IMine getMine(int mi) {
		return this.delegate.getMine(mi);
		
	}

	@Override
	public IHotelVille getHotelVille(int hdv) {
		return this.delegate.getHotelVille(hdv);
	}

	@Override
	public boolean peutEntrer(int vilNum, int minNum) {
		return this.delegate.peutEntrer(vilNum, minNum);
	}

	@Override
	public boolean peutEntrerHotelVille(int vilNum, int hdv) {
		return this.delegate.peutEntrerHotelVille(vilNum, hdv);
	}

	@Override
	public boolean peutTaperMuraille(int vilNum, int mur) {
		return this.delegate.peutTaperMuraille(vilNum, mur);
	}

	@Override
	public IMoteurJeu init(int maxPas) {
		 return this.delegate.init( maxPas);
	}

	@Override
	public void pasJeu(ECommande commande, int vilNum, int arg) {
		this.delegate.pasJeu(commande, vilNum, arg);
		
	}

	@Override
	public ITerrain getTerrain() {
		return this.delegate.getTerrain();
	}

	@Override
	public IMuraille getMuraille(int mu) {
		return this.delegate.getMuraille(mu);
	}

	@Override
	public void bindTerrain(ITerrain terr) {
		this.delegate.bindTerrain(terr);
		
	}
}
