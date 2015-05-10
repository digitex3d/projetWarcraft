package contracts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import decorators.MoteurJeuDecorator;
import enums.ECommande;
import enums.EEntite;
import enums.ERace;
import enums.EResultat;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;
import services.IHotelVille;
import services.IMine;
import services.IMoteurJeu;
import services.IMuraille;
import services.IVillageois;
import utils.Utils;

public class MoteurJeuContract extends MoteurJeuDecorator{
	public MoteurJeuContract(IMoteurJeu delegate) {
		super(delegate);
	}
	
	public void checkInvariants() {
		// inv: 0 <= pasJeuCourant() <= maxPasJeu()
		if (super.getPasJeuCourant() < 0 || super.getPasJeuCourant() > super.getMaxPasJeu())
			throw new InvariantError("0 <= pasJeuCourant() <= maxPasJeu()");
		
		// inv: estFini() min= \exist x \verify 0 <= x <= terrain().getListeHotelVille().size() &&
		//						getHDV(x).orRestant() >= 1664 || pasJeuCourant() == maxPasJeu()
		if (super.estFini()) {
			int hv1664 = -1;
			for (int x = 0; x < getTerrain().getListeHotelVille().size(); x++)
				if (super.getHotelVille(x).getOrRestant() >= 1664) {
					hv1664 = x;
					break;
				}
			if (hv1664 == -1 && this.getPasJeuCourant() != this.getMaxPasJeu())
				throw new InvariantError("getHDV(x).orRestant() >= 1664 || pasJeuCourant() == maxPasJeu()");
			
			// inv: if \exist x \verify 0 <= x <= terrain().getListeHotelVille().size() && getHDV(x).orRestant() >= 1664 then
			//			if getHDV(x).etatAppartenance() == ORC then resultatFinal() min= ORC_GAGNE
			//			if getHDV(x).etatAppartenance() == HUMAN then resultatFinal() min= HUMAN_GAGNE
			//		else resultatFinal() min= NUL
			if (hv1664 == -1 && super.resultatFinal() != EResultat.NUL)
				throw new InvariantError("resultatFinal() min= NUL");
			if (getHotelVille(hv1664).getEtatAppartenance() == ERace.ORC && super.resultatFinal() != EResultat.ORC_GAGNE)
				throw new InvariantError("if getHDV(x).etatAppartenance() == ORC then resultatFinal() min= ORC_GAGNE");
			if (getHotelVille(hv1664).getEtatAppartenance() == ERace.HUMAN && super.resultatFinal() != EResultat.HUMAN_GAGNE)
				throw new InvariantError("if getHDV(x).etatAppartenance() == HUMAN then resultatFinal() min= HUMAN_GAGNE");
		}
				
		// inv: getVillageois(vill) min= terrain().getListeVillageois().get(vill)
		// inv: getMine(mi) min= terrain().getListeMine().get(mi)
		// inv: getMuraille(mu) min= terrain().getListeMuraille().get(mu)
		// inv: getHDV(hdv) min= terrain().getListeHotelVille().get(hdv)
		
		// inv: peutEntrer(vill, mi) min= distance(vilposX, vilposY, mineCenterX, mineCenterY) <= 51 && ! getMine(mi).estLaminee()
		// inv: peutEntrerHotelVille(vill, hdv) min= distance(vilposX, vilposY, hvCenterX,  hvCenterY)) <= 51 && getVillageois(vill).race() == getHDV(hdv).etatAppartenance() && getVillageois(vill).quantiteOr() > 0
		// inv: peutTaperMuraille(vill,mu) min= distance(vilposX, vilposY, murCenterX, murCenterY) ≤ 51		
	}
		
	
	public IMoteurJeu init(int maxPas) {
    	// pre: maxPas > 0
		if( maxPas <= 0)
			throw new PreconditionError("maxPas > 0");

		super.init(maxPas);
		
		this.checkInvariants();
		
    	// post: maxPasJeu() == maxPas
		if (super.getMaxPasJeu() != maxPas)
			throw new PostconditionError("maxPasJeu() == maxPas");
    	// post: pasJeuCourant() == 0
		if( this.getPasJeuCourant() != 0 )
			throw new PostconditionError("pasJeuCourant() == 0");
		
		return this;
	}
	
	public void pasJeu(ECommande command, int vilNum, int arg) {
		this.checkInvariants();
		
		/**
			pre: ! estFini() &&
				 if command != RIEN then
					! getVillageois(vilNum).estMort() &&
			 	 	! getVillageois(vilNum).estOccupe()
			 	 if command == DEPLACER then
			 	 	0 <= arg <= 360
			 	 if command == ENTRERMINE then
			 	 	peutEntrer(vilNum, arg)
			 	 if command == ENTRERHOTELVILLE then
			 	 	peutEntrerHotelVille(vilNum, arg)
			 	 if command == TAPERMURAILLE then
			 	 	peutTaperMuraille(vilNum, arg) &&
			 	 	! getMuraille(arg).estDetruite()
		 */
		if (super.estFini())
			throw new PreconditionError("! estFini()");
		if (command != ECommande.RIEN) {
			if (getVillageois(vilNum).estMort())
				throw new PreconditionError("getVillageois(vilNum).estMort()");
			if (getVillageois(vilNum).estOccupe())
				throw new PreconditionError("getVillageois(vilNum).estOccupe()");
		}
		if (command == ECommande.DEPLACER)
			if( arg > 360 || arg < 0 )
				throw new PreconditionError("0 <= arg <= 360");
		if (command == ECommande.ENTRERMINE)
			if ( ! peutEntrer(vilNum, arg))
				throw new PreconditionError("peutEntrer(vilNum, arg)");
		if (command == ECommande.ENTRERHOTELVILLE)
			if ( ! peutEntrerHotelVille(vilNum, arg))
				throw new PreconditionError("peutEntrerHotelVille(vilNum, arg)");
		if (command == ECommande.TAPERMURAILLE)
			if ( ! peutTaperMuraille(vilNum, arg) || getMuraille(arg).estDetruite())
				throw new PreconditionError("peutTaperMuraille(vilNum, arg)");
		
		int pasJeuCourant_pre = this.getPasJeuCourant();
		
		ArrayList<HashMap<String, Object>> villageois_pre = new ArrayList<HashMap<String, Object>>();
		for (IVillageois vill : getTerrain().getListeVillageois())
			villageois_pre.add(Utils.getServiceAtPre(vill));
		
		ArrayList<HashMap<String, Object>> mine_pre = new ArrayList<HashMap<String, Object>>();
		for (IMine mine : getTerrain().getListeMine())
			mine_pre.add(Utils.getServiceAtPre(mine));
		
		ArrayList<HashMap<String, Object>> hdv_pre = new ArrayList<HashMap<String,Object>>();
		for (IHotelVille hdv : getTerrain().getListeHotelVille())
			hdv_pre.add(Utils.getServiceAtPre(hdv));
			
		HashMap<String, Object> mur_pre = null;
		if (command == ECommande.TAPERMURAILLE)
			mur_pre = Utils.getServiceAtPre(getTerrain().getListeMuraille().get(arg));
		
		super.pasJeu(command, vilNum, arg);
		
		this.checkInvariants();
		
		// post: pasJeuCourant() == pasJeuCourant@pre + 1
		if (super.getPasJeuCourant() != pasJeuCourant_pre + 1)
			throw new PostconditionError("pasJeuCourant() == pasJeuCourant@pre + 1");
		
		/**
		 post: \forall x \in [0, terrain().getListeVillageois().size()[, 
					if getVillageois(x)@pre.estOccupe() then
						getVillageois(x) == getVillageois(x)@pre.decrCorvee())
					if getVillageois(x)@pre.corvee() == 1 then
					 	terrain() == terrain()@pre.reinsertVillageois(x)
		 */
		for (int i = 0; i < getTerrain().getListeVillageois().size(); i++) {
			IVillageois vill = getVillageois(i);
			Boolean vill_pre_occ = (Boolean) villageois_pre.get(i).get("estOccupe");
			Integer vill_pre_corvee = (Integer) villageois_pre.get(i).get("getCorvee");
			
			if (vill_pre_occ && vill.getCorvee() != vill_pre_corvee - 1)
				throw new PostconditionError("getVillageois(x) == getVillageois(x)@pre.decrCorvee())");
			if (vill_pre_corvee == 1)
				for (int x = vill.getX(); x < vill.getLargeur(); x++)
					for (int y = vill.getY(); y < vill.getHauteur(); y++)
						if ( ! getTerrain().getEntiteAt(x, y).contains(EEntite.VILLAGEOIS))
							throw new PostconditionError("terrain() == terrain()@pre.reinsertVillageois(x)");
		}
		/**
		 post: \forall x \in [0, terrain().getListeMine().size()[, 
		 			if ! getMine(x)@pre.estAbandonee() then
		 				if command != ENTRERMINE || x != arg then
		 					getMine(x) == getMine(x)@pre.abandoned()
		 */
		for (int i = 0; i < getTerrain().getListeMine().size(); i++) {
			IMine mine = getMine(i);
			Boolean mine_pre_ab = (Boolean) mine_pre.get(i).get("estAbandonne");
			Integer mine_pre_abcpt = (Integer) mine_pre.get(i).get("getAbandonCompteur");
			if ( ! mine_pre_ab && (command != ECommande.ENTRERMINE || i != arg)) {
				if (mine.getAbandonCompteur() != mine_pre_abcpt + 1)
					throw new PostconditionError("getMine(x) == getMine(x)@pre.abandoned()");
			}
		}
		
		/**
		 Soit pArrivee := gestDepl().calcChemin(vilNum, arg).getPointArrivee()
		 Soit Villpre := getVillageois(vilNum)@pre
		 if command == DEPLACER then
		 	getVillageois(vilNum) == Villpre.setXY(pArrivee.get(0), pArrivee.get(1)) && 
			terrain() = terrain()@pre.removeEntiteAt(VILLAGEOIS, Villpre.posx(), Villpre.posy(), Villpre.largeur(), Villpre.hauteur())
									 .setEntiteAt(VILLAGEOIS, pArrivee.get(0), pArrivee.get(1), Villpost.largeur(), Villpost.hauteur())
		 */
		if (command == ECommande.DEPLACER) {
			List<Integer> pArrivee = super.getGestDepl().getPointArrivee();
			int vilx = getVillageois(vilNum).getX();
			int vily = getVillageois(vilNum).getY();
			if (vilx != pArrivee.get(0) || vily != pArrivee.get(1))
				throw new PostconditionError("getVillageois(vilNum) == Villpre.setXY(pArrivee.get(0), pArrivee.get(1))");
			if ( ! checkDeplacement((int) villageois_pre.get(vilNum).get("getX"), (int) villageois_pre.get(vilNum).get("getY"), vilx, vily, getVillageois(vilNum).getLargeur(), getVillageois(vilNum).getHauteur()))
				throw new PostconditionError("terrain() = terrain()@pre.removeEntiteAt(VILLAGEOIS, Villpre.posx(), Villpre.posy(), Villpre.largeur(), Villpre.hauteur())");
			for (int x = vilx; x < vilx + getVillageois(vilNum).getLargeur(); x++)
				for (int y = vily; y < vily + getVillageois(vilNum).getHauteur(); y++)
					if ( ! getTerrain().getEntiteAt(x, y).contains(EEntite.VILLAGEOIS))
						throw new PostconditionError("terrain()@pre.setEntiteAt(VILLAGEOIS, pArrivee.get(0), pArrivee.get(1), Villpost.largeur(), Villpost.hauteur())");
		}
		
		/**
		 if command == ENTRERHOTELVILLE then
		 	getHDV(arg) == getHDV(arg)@pre.depot(Villpre.quantiteOr()) && 
			getVillageois(vilNum) == Villpre.dechargeOr(Villpre.quantiteOr())
		 */
		
		if (command == ECommande.ENTRERHOTELVILLE) {
			if (getHotelVille(arg).getOrRestant() != ((int)hdv_pre.get(arg).get("getOrRestant") + (int)villageois_pre.get(vilNum).get("getQuantiteOr")))
				throw new PostconditionError("getHDV(arg) == getHDV(arg)@pre.depot(Villpre.quantiteOr())");
			if (getVillageois(vilNum).getQuantiteOr() != 0)
				throw new PostconditionError("getVillageois(vilNum) == Villpre.dechargeOr(Villpre.quantiteOr())");
		}
		
		/**
		 if command == ENTRERMINE then
		 	getMine(arg) == getMine(arg)@pre.accueil().retrait() &&
			getVillageois(vilNum) == Villpre.setCorvee(16).chargerOr(1) &&
			terrain() == terrain()@pre.removeEntiteAt(EEntite.VILLAGEOIS, Villpre.posx(), Villpre.posy(), Villpre.largeur(), Villpre.hauteur()) &&
			getVillageois(vilNum).posx() = getMine(arg).posx() && 
			getVillageois(vilNum).posy() = getMine(arg).posy()
		 */
		
		if (command == ECommande.ENTRERMINE) {
			if (getMine(arg).getOrRestant() != (int) mine_pre.get(arg).get("getOrRestant") - 1 || getMine(arg).getEtatAppartenance() != (ERace) mine_pre.get(arg).get("getEtatAppartenance"))
				throw new PostconditionError("getMine(arg) == getMine(arg)@pre.accueil().retrait()");
			if (getVillageois(vilNum).getCorvee() != 16 || getVillageois(vilNum).getQuantiteOr() != (int) villageois_pre.get(vilNum).get("getQuantiteOr") + 1)
				throw new PostconditionError("getVillageois(vilNum) == Villpre.setCorvee(16).chargerOr(1)");
			int vilprex = (int) villageois_pre.get(vilNum).get("getX");
			int vilprey = (int) villageois_pre.get(vilNum).get("getY");
			for (int x = vilprex; x < vilprex + getVillageois(vilNum).getLargeur(); x++)
				for (int y = vilprey; y < vilprey + getVillageois(vilNum).getHauteur(); y++)
					if (getTerrain().getEntiteAt(x, y).contains(EEntite.VILLAGEOIS))
						throw new PostconditionError("terrain() == terrain()@pre.removeEntiteAt(EEntite.VILLAGEOIS, Villpre.posx(), Villpre.posy(), Villpre.largeur(), Villpre.hauteur())");
			if (getVillageois(vilNum).getX() != getMine(arg).getX())
				throw new PostconditionError("getVillageois(vilNum).posx() = getMine(arg).posx()");
			if (getVillageois(vilNum).getY() != getMine(arg).getY())
				throw new PostconditionError("getVillageois(vilNum).posy() = getMine(arg).posy()");
		}
		
		/**
		 Soit Mur := getMuraille(arg)@pre
		 if command == TAPERMURAILLE then
		 	getMuraille(arg) == Mur.retrait(Villpre.force()) &&
			if getMuraille(arg).estDetruite() then
				terrain() == terrain()@pre.removeEntiteAt(EEntite.MURAILLE, Mur.posx(), Mur.posy(), Mur.largeur(), Mur.hauteur())
		 */
		if (command == ECommande.TAPERMURAILLE) {
			IMuraille mur = getMuraille(arg);
			if (mur.getPointsDeVie() != (int)mur_pre.get("getPointsDeVie") - getVillageois(vilNum).getForce())
				throw new PostconditionError("getMuraille(arg) == Mur.retrait(Villpre.force())");
			if (mur.estDetruite())
				for (int x = mur.getX(); x < mur.getX() + mur.getLargeur(); x++)
					for (int y = mur.getY(); y < mur.getY() + mur.getHauteur(); y++)
						if (getTerrain().getEntiteAt(x, y).contains(EEntite.MURAILLE))
							throw new PostconditionError("terrain() == terrain()@pre.removeEntiteAt(EEntite.MURAILLE, Mur.posx(), Mur.posy(), Mur.largeur(), Mur.hauteur())");
		}
	}
	
	private boolean checkDeplacement(int xo, int yo, int xn, int yn, int l, int h) {
		int startx = 0, endx = 0;
		int starty = 0, endy = 0;
		boolean testx = xo == xn , testy = yo == yn;
		
		if ( ! testx && ! testy)
			return true;
		
		if (xo < xn) {
			startx = xo;
			endx = (xo + l < xn) ? xo + l : xn;
		}
		else {
			startx = (xn + l > xo) ? xn + l : xo;
			endx = xo + l;
		}

		if (yo <= yn) {
			starty = yo;
			endy = (yo + l < yn) ? yo + l : yn;
		}
		else {
			starty = (yn + l > yo) ? yn + l : yo;
			endy = yo + l;
		}
		
		if ( ! testy) {
			for (int x = startx; x < endx; x++)
				for (int y = yo; y < yo + h; y++)
					if (getTerrain().getEntiteAt(x, y).contains(EEntite.VILLAGEOIS))
						return false;
		}
		else if ( ! testx) {
			for (int x = xo; x < xo + l; x++)
				for (int y = starty; y < endy; y++)
					if (getTerrain().getEntiteAt(x, y).contains(EEntite.VILLAGEOIS))
						return false;
		}
		else
			for (int x = startx; x < endx; x++)
				for (int y = starty; y < endy; y++)
					if (getTerrain().getEntiteAt(x, y).contains(EEntite.VILLAGEOIS))
						return false;
		return true;
	}
}