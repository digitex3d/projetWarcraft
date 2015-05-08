package implementations;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import enums.ECommande;
import enums.EEntite;
import enums.ERace;
import enums.EResultat;
import gui.EEvent;
import services.IGestionDeplacement;
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
		for(IHotelVille hv : this.terrain.getListeHotelVille())
			if( hv.getOrRestant() >= 1664 ) return true;
		
		return this.getPasJeuCourant() == this.getMaxPasJeu();
		
	}

	@Override
	public EResultat resultatFinal() {
		for(IHotelVille hv : this.terrain.getListeHotelVille())
			if( hv.getOrRestant() >= 1664 )
				if( hv.getEtatAppartenance() == ERace.ORC) 
					return EResultat.ORC_GAGNE;
				else
					return EResultat.HUMAN_GAGNE;
		//TODO: Rivedere spec per il caso di parità
		return EResultat.NUL;
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
	public IMoteurJeu init(int maxPas) {
		if (this.terrain == null)
			throw new Error("Le terrain n'est pas bindé!");

		this.maxPasJeu =     maxPas;                            
		this.pasJeuCourant= 0;                             

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
		IVillageois selVill = this.terrain.getListeVillageois().get(numVillageois);
		
		// Décrémente la corvée des villageois occupés
		for( IVillageois vill : this.terrain.getListeVillageois()){
			if(vill.estOccupe()){
				if( vill.getCorvee() == 1 )
					this.terrain.reinsertVillageois(
							this.terrain.getListeVillageois().indexOf(vill));
				else
					vill.decrCorvee();
			}
		}
		
		// On récupère le point d'arrivée
		// TODO: bind?
		IGestionDeplacement gd = new GestionDeplacementImpl();
		
		gd = gd.init(this.terrain, selVill, argument );
		
		ArrayList<Integer> pArrive = gd.getPointArrivee();
		
		switch(commande){
		case DEPLACER:
			selVill.setXY(pArrive.get(0), pArrive.get(1));
			this.terrain.moveVillageoisAt(	numVillageois,
											pArrive.get(0),
											pArrive.get(1));
			break;
		case ENTREHOTELVILLE:
			IHotelVille hdv = this.terrain.getListeHotelVille().get(argument);
			hdv.depot(selVill.getQuantiteOr());
			selVill.dechargeOr(selVill.getQuantiteOr());
			//TODO: peutEntrer à che livello lo verifichiamo?
			break;
		case ENTREMINE:
			IMine mine = this.terrain.getListeMine().get(argument);
			mine.acceuil(selVill.getRace());
			mine.retrait(1);
			selVill.chargeOr(1);
			this.terrain.removeEntiteAt(EEntite.VILLAGEOIS,
										selVill.getX(), 
										selVill.getY(), 
										selVill.getLargeur(),
										selVill.getHauteur());
			selVill.setXY(mine.getX(), mine.getY());
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
		
		int hdvCenterX = hdv.getX() + ( hdv.getLargeur() / 2 );
		int hdvCenterY = hdv.getY() + ( hdv.getHauteur() / 2 );
		
		return Utils.distance(	vill.getX(), 
								vill.getY(),
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
								murCenterY) <= 51;
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

}
