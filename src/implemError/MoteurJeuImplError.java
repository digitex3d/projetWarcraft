package implemError;

import java.util.ArrayList;

import enums.ECommande;
import enums.EEntite;
import enums.ERace;
import enums.EResultat;
import services.IGestionDeplacement;
import services.IHotelVille;
import services.IMine;
import services.IMoteurJeu;
import services.IMuraille;
import services.ITerrain;
import services.IVillageois;
import utils.Utils;

public class MoteurJeuImplError implements IMoteurJeu{
    protected int maxPasJeu;  
    protected int pasJeuCourant;
    protected boolean estFini;
    protected EResultat resultatFinal;
    protected ITerrain terrain;
    protected IGestionDeplacement gd;
    
    public MoteurJeuImplError(){}
    
	@Override
	public int getMaxPasJeu() {
		return this.maxPasJeu;
	}

	@Override
	public int getPasJeuCourant() {
		return this.maxPasJeu; // BUG
	}

	@Override
	public boolean estFini() {
		for(IHotelVille hv : this.terrain.getListeHotelVille())
			if( hv.getOrRestant() >= 1664 ) return true;
		
		return this.getPasJeuCourant() == this.getMaxPasJeu();
		
	}

	@Override
	public EResultat resultatFinal() {
		for(IHotelVille hv : this.terrain.getListeHotelVille())
			if( hv.getOrRestant() >= 1664 )
				if( hv.getEtatAppartenance() == ERace.ORC) 
					return EResultat.HUMAN_GAGNE; // BUG
				else
					return EResultat.HUMAN_GAGNE;
		return EResultat.NUL;
	}

	@Override
	public boolean peutEntrer(int numVillageois, int numMine) {
		IVillageois vill = this.getVillageois(numVillageois);
		IMine mine = this.getMine(numMine);
		
		// Si la mine est laminée retourne false
		if( mine.estLaminee() ) return false;
		
		int mineCenterX = (mine.getX() +  mine.getLargeur() / 2 ); // BUG
		int mineCenterY = mine.getY() + ( mine.getHauteur() / 2 );
		
		return Utils.distance(	vill.getX(), 
								vill.getY(),
								mineCenterX, 
								mineCenterY) <= 51;
		
	}

	@Override
	public IMoteurJeu init(int maxPas) {
		if (this.terrain == null)
			throw new Error("Le terrain n'est pas bindé!");
		if (this.gd == null)
			throw new Error("La gestion de déplacement n'est pas bindé!");

		this.maxPasJeu =     maxPas;                            
		this.pasJeuCourant= 0;                             

		return this;

	}
	
	@Override
	public void pasJeu(ECommande command, int numVillageois, int argument) {
		IVillageois selVill = this.terrain.getListeVillageois().get(numVillageois);
		
		// Décrémente la corvée des villageois occupés
		for( IVillageois vill : this.terrain.getListeVillageois()){
						//BUG
					this.terrain.reinsertVillageois(
							this.terrain.getListeVillageois().indexOf(vill));
			
		}
		
		for (int i = 0; i < terrain.getListeMine().size(); i++) {
			IMine mine = getMine(i);
			if ( ! mine.estAbandonne())
				if (command != ECommande.ENTRERMINE || i != argument)
					mine.abandoned();
					
		}
		
		switch(command){
		case DEPLACER:
			this.terrain.removeEntiteAt(EEntite.VILLAGEOIS, selVill.getX(), selVill.getY(), selVill.getLargeur(), selVill.getHauteur());
			gd.calcChemin(numVillageois, argument);
			ArrayList<Integer> pArrive = this.gd.getPointArrivee();
			
			selVill.setXY(pArrive.get(0), pArrive.get(1));
			this.terrain.setEntiteAt(EEntite.VILLAGEOIS, selVill.getX(), selVill.getY(), selVill.getLargeur(), selVill.getHauteur());
			break;
		case ENTRERHOTELVILLE:
			// BUG
			selVill.dechargeOr(selVill.getQuantiteOr());
			break;
		case ENTRERMINE:
			IMine mine = this.terrain.getListeMine().get(argument);
			mine.acceuil(selVill.getRace());
			mine.retrait(1);
			selVill.chargeOr(1);
			this.terrain.removeEntiteAt(EEntite.VILLAGEOIS,
										selVill.getX(), 
										selVill.getY(), 
										selVill.getLargeur(),
										selVill.getHauteur());
			selVill.setCorvee(16, mine.getX(), mine.getY());
			break;
		case TAPERMURAILLE:
			IMuraille mur = this.terrain.getListeMuraille().get(argument);
			mur.retrait(selVill.getForce());
			if(mur.estDetruite())
				this.terrain.removeEntiteAt(	EEntite.MURAILLE, 
												mur.getX(),
												mur.getY(),
												mur.getLargeur(),
												mur.getHauteur());
			
			
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
		return this.terrain;
	}

	@Override
	public IMuraille getMuraille(int mu) {
		return this.terrain.getListeMuraille().get(mu);
	}

	@Override
	public IHotelVille getHotelVille(int hdv) {
		return this.terrain.getListeHotelVille().get(hdv);
	}

	@Override
	public boolean peutEntrerHotelVille(int vilNum, int hdvNum) {
		IVillageois vill = this.getVillageois(vilNum);
		IHotelVille hdv = this.getHotelVille(hdvNum);
		
		// Si la race n'est pas la bonne retourne false
		if( hdv.getEtatAppartenance() != vill.getRace() ) return false;
		
		if (vill.getQuantiteOr() <= 0)
			return false;
		
		int hdvCenterX = hdv.getX() + ( hdv.getLargeur() / 2 );
		int hdvCenterY = hdv.getY() + ( hdv.getHauteur() / 2 );
		
		return Utils.distance(	vill.getX(), 
								vill.getX(),  /// BUG
								hdvCenterX, 
								hdvCenterY) <= 51;
	}

	@Override
	public boolean peutTaperMuraille(int vilNum, int murNum) {
		IVillageois vill = this.getVillageois(vilNum);
		IMuraille mur = this.getMuraille(murNum);
		
		int murCenterX = mur.getX() + ( mur.getLargeur() / 2 );
		int murCenterY = mur.getY() + ( mur.getHauteur() / 2 );
		
		return Utils.distance(	vill.getX(), 
								vill.getY(),
								murCenterX, 
								murCenterY) <= 20; // BUG
	}

	@Override
	public IVillageois getVillageois(int vill) {
		return this.terrain.getListeVillageois().get(vill);
	}

	@Override
	public IMine getMine(int mi) {
		return this.terrain.getListeMine().get(mi);
	}
	
	@Override
	public void bindTerrain(ITerrain terr) {
		this.terrain = terr;
		
	}

	@Override
	public IGestionDeplacement getGestDepl() {
		return this.gd;
	}

	@Override
	public void bindGD(IGestionDeplacement gd) {
		this.gd = gd;
	}

}
