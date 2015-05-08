package contracts;

import implementations.TerrainImpl;
import implementations.VillageoisImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

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
import services.ITerrain;
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
		// inv: peutEntrerHotelVille(vill, hdv) min= distance(vilposX, vilposY, hvCenterX,  hvCenterY)) <= 51 && getVillageois(vill).race() == getHDV(hdv).etatAppartenance()
		// inv: peutTaperMuraille(vill,mu) min= distance(vilposX, vilposY, murCenterX, murCenterY) ≤ 51		
	}
		
	
	public IMoteurJeu init(ITerrain terrain, int maxPas) {
    	// pre: maxPas > 0
		if( maxPas <= 0)
			throw new PreconditionError("maxPas > 0");

		super.init(terrain, maxPas);
		
		this.checkInvariants();
		
    	//post: terrain() == terrain
		if (super.getTerrain() != terrain)
			throw new PostconditionError("terrain() == terrain");
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
			pre: ! getVillageois(vilNum).estMort() &&
			 	 ! getVillageois(vilNum).estOccupe() && ! estFini() &&
			 	 if command == DEPLACER then
			 	 	0 <= arg <= 360
			 	 if command == ENTRERMINE then
			 	 	peutEntrer(vilNum, arg)
			 	 if command == ENTRERHOTELVILLE then
			 	 	peutEntrerHotelVille(vilNum, arg)
			 	 if command == TAPERMURAILLE then
			 	 	peutTaperMuraille(vilNum, arg)
		 */
		if (getVillageois(vilNum).estMort())
			throw new PreconditionError("getVillageois(vilNum).estMort()");
		if (getVillageois(vilNum).estOccupe())
			throw new PreconditionError("getVillageois(vilNum).estOccupe()");
		if (super.estFini())
			throw new PreconditionError("! estFini()");
		if (command == ECommande.DEPLACER)
			if( arg > 360 || arg < 0 )
				throw new PreconditionError("0 <= arg <= 360");
		if (command == ECommande.ENTREMINE)
			if ( ! peutEntrer(vilNum, arg))
				throw new PreconditionError("peutEntrer(vilNum, arg)");
		if (command == ECommande.ENTREHOTELVILLE)
			if ( ! peutEntrerHotelVille(vilNum, arg))
				throw new PreconditionError("peutEntrerHotelVille(vilNum, arg)");
		if (command == ECommande.TAPERMURAILLE)
			if ( ! peutTaperMuraille(vilNum, arg))
				throw new PreconditionError("peutTaperMuraille(vilNum, arg)");
		
		int pasJeuCourant_pre = this.getPasJeuCourant();
		
		ArrayList<HashMap<String, Object>> villageois_pre = new ArrayList<HashMap<String, Object>>();
		for (IVillageois vill : getTerrain().getListeVillageois()) {
			villageois_pre.add(Utils.getServiceAtPre(vill));
		}
		
		ArrayList<HashMap<String, Object>> mine_pre = new ArrayList<HashMap<String, Object>>();
		for (IMine mine : getTerrain().getListeMine()) {
			mine_pre.add(Utils.getServiceAtPre(mine));
		}
			
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
			Integer vill_pre_corvee = (Integer) villageois_pre.get(i).get("corvee");
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
			Boolean mine_pre_ab = (Boolean) mine_pre.get(i).get("estAbandonee");
			Integer mine_pre_abcpt = (Integer) mine_pre.get(i).get("estAbandonee");
			if (mine_pre_ab && (command != ECommande.ENTREMINE || i != arg)) {
				if (mine.getAbandonCompteur() != mine_pre_abcpt + 1)
					throw new PostconditionError("getMine(x) == getMine(x)@pre.abandoned()");
			}
		}
		
	}		
}