package contracts;

import services.IVillageois;
import decorators.VillageoisDecorator;
import enums.ERace;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;

public class VillageoisContract extends VillageoisDecorator {

	public VillageoisContract(IVillageois delegate) {
		super(delegate);
		
	}
	
	public void checkInvariants() {
		// \inv: estMort(V) min= pointsDeVie(V) ≤ 0
		if (super.estMort() && super.getPointsDeVie() > 0)
			throw new InvariantError("inv: estMort(V) min= pointsDeVie(V) > 0");
	}
	
	public IVillageois init(int x, int y, ERace race, int largeur, int hauteur, int force,
			double vitesse, int pointsDeVie) {
				
		/* 	pre init(race,largeur,hauteur,force,vitesse,pointsVie) 
		* 	require 	largeur%2=1 ∧ 
		* 				hauteur%2=1 ∧
		* 				force > 0	∧
		* 				vitesse > 0 ∧
		* 				pointsVie > 0
		* 				x > 0
		*               y > 0
		*/
		if (largeur % 2 != 1) 
			throw new PreconditionError("inv: largeur%2!=1");
		if (hauteur % 2 != 1) 
			throw new PreconditionError("inv: hauteur%2!=1");
		if (force <= 0)
			throw new PreconditionError("inv: force > 0");
		if (vitesse <= 0)
			throw new PreconditionError("inv: vitesse > 0");
		if (pointsDeVie <= 0) 
			throw new PreconditionError("inv: pointsVie > 0");
		if (vitesse <= 0)
			throw new PreconditionError("inv: x > 0");
		if (pointsDeVie <= 0) 
			throw new PreconditionError("inv: y > 0");
	
		
		// execute fonction
		super.init(race, largeur, hauteur, force, vitesse, pointsDeVie);
		
		/* ######## Verification des postcondition ######### */
		
		// \post: posX(init(x, y, l, h, pv)) = x
		if (super.getX() != x)
			throw new PostconditionError("post: posX(init(x, y, l, h, pv)) = x");
		// \post: posY(init(x, y, l, h, pv)) = y
		if (super.getY() != y)
		
		// \post: race(init(s,l,h,f,v,p))=s
		if( super.getRace() != race )
			throw new PostconditionError("race(init(s,l,h,f,v,p))=s");
		
		
		
		
		// Deuxième check des invariants
		this.checkInvariants();
		
		
		
		return this;
	}
	
	public void retrait(int s){
		/* ######## Premier check des invariants ######### */
		this.checkInvariants();

		/* ######## Verification des préconditions ######### */
	
		// \pre retrait(V,s) require ¬estMort(V) ∧ s>0
		if (super.estMort() || s <= 0 )
			throw new  PreconditionError("retrait(V,s) require ¬estMort(V) ∧ s>0");

		/* ######## 	Sauvegarde contexte  		######### */
		int pointsDeVie_pre = super.getPointsDeVie();
		int quantiteOr_pre = super.getQuantiteOr();
		
		// Execution
		super.retrait(s);

		/* ######## Deuxième check des invariants ######### */
		this.checkInvariants();
		
		/* ######## Verification des postcondition ######### */
		
		// \post: pointsDeVie(retrait(V,s))= pointsDeVie(V)@pre -s
		if( super.getPointsDeVie() != pointsDeVie_pre - s )
			throw new PostconditionError("pointsDeVie(retrait(V,s))= pointsDeVie(V)@pre -s");
		
		// \post: quantiteOr(retrait(V,s))= quantiteOr(V)@pre
		if( super.getQuantiteOr() != quantiteOr_pre )
			throw new PostconditionError("quantiteOr(retrait(V,s))= quantiteOr(V)@pre");
	}
	
	public void chargeOr(int s){
		// Premier check des invariants
		this.checkInvariants();

		/* ######## Verification des préconditions ######### */
		// \pre chargeOr(V,s) require ¬estMort(V) ∧ s>0
		if (super.estMort() || s <= 0)
			throw new PreconditionError("chargeOr(V,s) require ¬estMort(V) ∧ s>0");
		
		//Sauvegarde contexte  
		int pointsDeVie_pre = super.getPointsDeVie();
		int quantiteOr_pre = super.getQuantiteOr();
		
		/* ######## 	Execution  		######### */

		super.chargeOr(s);
		
		// Deuxième check des invariants
		this.checkInvariants();
		
		/* ######## Verification des postcondition ######### */
		// \post: pointsDeVie(chargeOr(V,s))= pointsDeVie(V)@pre
		if (super.getPointsDeVie() != pointsDeVie_pre)
			throw new PostconditionError("pointsDeVie(chargeOr(V,s))= pointsDeVie(V)@pre");
		
		// \post: quantiteOr(chargeOr(V,s))= quantiteOr(V)@pre
		if (super.getQuantiteOr() != quantiteOr_pre+s)
			throw new PostconditionError("quantiteOr(chargeOr(V,s))= quantiteOr(V)@pre+s");
	}
	
	public void dechargeOr(int s){
		// Premier check des invariants
		this.checkInvariants();

		/* ######## Verification des préconditions ######### */
		// \pre deChargeOr(V,s) require ¬estMort(V) ∧ s>0
		if (super.estMort() || s <= 0)
			throw new PreconditionError("deChargeOr(V,s) require ¬estMort(V) ∧ s>0");
		// \pre dechargeOr(V,s) require s <= quantiteOr(V)
		if (s > super.getQuantiteOr())
			throw new PreconditionError("dechargeOr(V,s) require s <= quantiteOr(V)");
		
		//Sauvegarde contexte  
		int pointsDeVie_pre = super.getPointsDeVie();
		int quantiteOr_pre = super.getQuantiteOr();
		
		/* ######## 	Execution  		######### */

		super.dechargeOr(s);
		
		// Deuxième check des invariants
		this.checkInvariants();

		/* ######## Verification des postcondition ######### */
		// \post: pointsDeVie(dechargeOr(V,s))= pointsDeVie(V)@pre
		if (super.getPointsDeVie() != pointsDeVie_pre)
			throw new PostconditionError("pointsDeVie(deChargeOr(V,s))= pointsDeVie(V)@pre");
		
		// \post: quantiteOr(dechargeOr(V,s))= quantiteOr(V)@pre
		if (super.getQuantiteOr() != quantiteOr_pre-s)
			throw new PostconditionError("quantiteOr(deChargeOr(V,s))= quantiteOr(V)@pre-s");
	}
}
