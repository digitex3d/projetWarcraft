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
		// \inv: estOccupe(V) min= corvee > 0
		if (super.estOccupe() && super.getCorvee() <= 0)
			throw new InvariantError("inv: estOccupe(V) min= corvee > 0");
	
	}
	
	public IVillageois init(int x, int y, ERace race, int largeur, int hauteur, int force,
			double vitesse, int pointsDeVie) {
				
		/* 	pre init(race,largeur,hauteur,force,vitesse,pointsVie) 
		* 	require 	largeur%2=1 ∧ 
		* 				hauteur%2=1 ∧
		* 				force > 0	∧
		* 				vitesse > 0 ∧
		* 				pointsVie > 0
		* 				x >= 0
		*               y >= 0
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
		if (x < 0)
			throw new PreconditionError("inv: x >= 0");
		if (y < 0) 
			throw new PreconditionError("inv: y >= 0");
	
		
		// execute fonction
		super.init(x,y,race, largeur, hauteur, force, vitesse, pointsDeVie);
		
		this.checkInvariants();
		
		/* ######## Verification des postcondition ######### */
		
		// \post: posx(init(x,y,s,l,h,f,v,p))=x
		if (super.getX() != x)
			throw new PostconditionError("posx(init(x,y,s,l,h,f,v,p))=x");
		// \post: posy(init(x,y,s,l,h,f,v,p))=y
		if (super.getY() != y)
			throw new PostconditionError("posy(init(x,y,s,l,h,f,v,p))=y");
		// \post: race(init(x,y,s,l,h,f,v,p))=s
		if (super.getRace() != race)
			throw new PostconditionError("race(init(x,y,s,l,h,f,v,p))=s");
		// \post: largeur(init(x,y,s,l,h,f,v,p))=l
		if (super.getLargeur() != largeur)
			throw new PostconditionError("largeur(init(x,y,s,l,h,f,v,p))=l");
		// \post: hauteur(init(x,y,s,l,h,f,v,p))=h
		if (super.getHauteur() != hauteur)
			throw new PostconditionError("hauteur(init(x,y,s,l,h,f,v,p))=h");
		// \post:force(init(x,y,s,l,h,f,v,p))=f
		if (super.getForce() != force)
			throw new PostconditionError("force(init(x,y,s,l,h,f,v,p))=f");
		// \post: vitesse(init(x,y,s,l,h,f,v,p))=v
		if (super.getVitesse() != vitesse)
			throw new PostconditionError("vitesse(init(x,y,s,l,h,f,v,p))=v");
		// \post: pointsDeVie(init(x,y,s,l,h,f,v,p))=p
		if (super.getPointsDeVie() != pointsDeVie)
			throw new PostconditionError("pointsDeVie(init(x,y,s,l,h,f,v,p))=p");
		// \post: quantiteOr(init(x,y,s,l,h,f,v,p))=0
		if (super.getQuantiteOr() != 0)
			throw new PostconditionError("quantiteOr(init(x,y,s,l,h,f,v,p))=0");
		// \post: corvee(init(x,y,s,l,h,f,v,p)) = 0
		if (super.getCorvee() != 0)
			throw new PostconditionError("corvee(init(x,y,s,l,h,f,v,p))");
		
		return this;
	}
	
	public void retrait(int s){
		/* ######## Premier check des invariants ######### */
		this.checkInvariants();

		/* ######## Verification des préconditions ######### */
	
		// \pre: !estMort() && !estOccupe(V) && s>0
		if (super.estMort() || super.estOccupe() || s <= 0)
			throw new  PreconditionError("!estMort() && !estOccupe(V) && s>0");

		/* ######## 	Sauvegarde contexte  		######### */
		int pointsDeVie_pre = super.getPointsDeVie();
		int quantiteOr_pre = super.getQuantiteOr();
		int corvee_pre = super.getCorvee();
		int x_pre = super.getX();
		int y_pre = super.getY();
		
		// Execution
		super.retrait(s);

		/* ######## Deuxième check des invariants ######### */
		this.checkInvariants();
		
		/* ######## Verification des postconditions ######### */
		
		// \post: pointsDeVie()= pointsDeVie()@pre -s
		if( super.getPointsDeVie() != pointsDeVie_pre - s )
			throw new PostconditionError("pointsDeVie(retrait(V,s))= pointsDeVie(V)@pre -s");
		
		// \post: quantiteOr()= quantiteOr()@pre
		if( super.getQuantiteOr() != quantiteOr_pre )
			throw new PostconditionError("quantiteOr( )= quantiteOr(V)@pre");
		// \post: corvee() = corvee()@pre
		if (super.getCorvee() != corvee_pre)
			throw new PostconditionError("corvee() = corvee()@pre");
		// \post: posx() = posx()@pre
		if (super.getX() != x_pre)
			throw new PostconditionError("posx() = posx()@pre");
		// \post: posy() = posy()@pre
		if (super.getY() != y_pre)
			throw new PostconditionError("posy() = posy()@pre");
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
		int corvee_pre = super.getCorvee();
		int x_pre = super.getX();
		int y_pre = super.getY();
		
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
		// \post: corvee(chargeOr(V,s)) = corvee(V)
		if (super.getCorvee() != corvee_pre)
			throw new PostconditionError("corvee(chargeOr(V,s)) = corvee(V)");
		// \post: posx(chargeOr(V,s)) = posx(V)
		if (super.getX() != x_pre)
			throw new PostconditionError("posx(chargeOr(V,s)) = posx(V)");
		// \post: posy(chargeOr(V,s)) = posy(V)
		if (super.getY() != y_pre)
			throw new PostconditionError("posy(chargeOr(V,s)) = posy(V)");
	}
	
	public void dechargeOr(int s){
		// Premier check des invariants
		this.checkInvariants();

		/* ######## Verification des préconditions ######### */
		// \pre dechargeOr(V,s) require ¬estMort(V) ∧ s>0
		if (super.estMort() || s <= 0)
			throw new PreconditionError("deChargeOr(V,s) require ¬estMort(V) ∧ s>0");
		// \pre dechargeOr(V,s) require s <= quantiteOr(V)
		if (s > super.getQuantiteOr())
			throw new PreconditionError("dechargeOr(V,s) require s <= quantiteOr(V)");
		
		//Sauvegarde contexte  
		int pointsDeVie_pre = super.getPointsDeVie();
		int quantiteOr_pre = super.getQuantiteOr();
		int corvee_pre = super.getCorvee();
		int x_pre = super.getX();
		int y_pre = super.getY();
		
		/* ######## 	Execution  		######### */

		super.dechargeOr(s);
		
		// Deuxième check des invariants
		this.checkInvariants();

		/* ######## Verification des postcondition ######### */
		// \post: pointsDeVie(dechargeOr(V,s))= pointsDeVie(V)@pre
		if (super.getPointsDeVie() != pointsDeVie_pre)
			throw new PostconditionError("pointsDeVie(dechargeOr(V,s))= pointsDeVie(V)@pre");
		
		// \post: quantiteOr(dechargeOr(V,s))= quantiteOr(V)@pre
		if (super.getQuantiteOr() != quantiteOr_pre-s)
			throw new PostconditionError("quantiteOr(dechargeOr(V,s))= quantiteOr(V)@pre-s");
		// \post: corvee(dechargeOr(V,s)) = corvee(V)
		if (super.getCorvee() != corvee_pre)
			throw new PostconditionError("corvee(dechargeOr(V,s)) = corvee(V)");
		// \post: posx(dechargeOr(V,s)) = posx(V)
		if (super.getX() != x_pre)
			throw new PostconditionError("posx(dechargeOr(V,s)) = posx(V)");
		// \post: posy(dechargeOr(V,s)) = posy(V)
		if (super.getY() != y_pre)
			throw new PostconditionError("posy(dechargeOr(V,s)) = posy(V)");
	}
	
	public void setCorvee(int s, int corveeX, int corveeY) {
		this.checkInvariants();
		
		// \pre !estMort() && !estOccupe() && s > 0 && corveeX >= 0 && corveeY >= 0
		if (super.estMort() || super.estOccupe() || s <= 0 || corveeX < 0 || corveeY < 0)
			throw new PreconditionError("setCorvee(V, s) require ¬estMort(V) ∧ ¬estOccupe(V) ∧ s > 0");
		
		//Sauvegarde contexte  
		int pointsDeVie_pre = super.getPointsDeVie();
		int quantiteOr_pre = super.getQuantiteOr();

		super.setCorvee(s, corveeX, corveeY);
		
		/* ######## Verification des postcondition ######### */
		// \post: pointsDeVie() = pointsDeVie(V)@pre
		if (super.getPointsDeVie() != pointsDeVie_pre)
			throw new PostconditionError("pointsDeVie(setCorvee(V,s))= pointsDeVie(V)@pre");
		
		// \post: quantiteOr() = quantiteOr(V)@pre
		if (super.getQuantiteOr() != quantiteOr_pre)
			throw new PostconditionError("quantiteOr(setCorvee(V,s))= quantiteOr(V)@pre");
		// \post: corvee() = corvee(V)
		if (super.getCorvee() != s)
			throw new PostconditionError("corvee(setCorvee(V,s)) = s");
		// \post: posx() = corveeX
		if (super.getX() != corveeX)
			throw new PostconditionError("posx(setCorvee(V,s)) = posx(V)");
		// \post: posy() = corveeY
		if (super.getY() != corveeY)
			throw new PostconditionError("posy(setCorvee(V,s)) = posy(V)");
		
	}
	
	public void decrCorvee() {
		this.checkInvariants();
		
		// \pre decrCorvee(V) require ¬estMort(V) ∧ estOccupe(V)
		if (super.estMort() || ! super.estOccupe())
			throw new PreconditionError("decrCorvee(V) require ¬estMort(V) ∧ estOccupe(V)");
		
		//Sauvegarde contexte  
		int pointsDeVie_pre = super.getPointsDeVie();
		int quantiteOr_pre = super.getQuantiteOr();
		int corvee_pre = super.getCorvee();
		int x_pre = super.getX();
		int y_pre = super.getY();

		super.decrCorvee();
		
		/* ######## Verification des postcondition ######### */
		// \post: pointsDeVie(decrCorvee(V)) = pointsDeVie(V)
		if (super.getPointsDeVie() != pointsDeVie_pre)
			throw new PostconditionError("pointsDeVie(decrCorvee(V))= pointsDeVie(V)@pre");
		
		// \post: quantiteOr(decrCorvee(V))= quantiteOr(V)@pre
		if (super.getQuantiteOr() != quantiteOr_pre)
			throw new PostconditionError("quantiteOr(decrCorvee(V))= quantiteOr(V)@pre");
		// \post: corvee(decrCorvee(V)) = corvee(V) - 1
		if (super.getCorvee() != corvee_pre - 1)
			throw new PostconditionError("corvee(decrCorvee(V)) = corvee(V)@pre - 1");
		// \post: posx(setCorvee(V,s)) = posx(V)
		if (super.getX() != x_pre)
			throw new PostconditionError("posx(decrCorvee(V)) = posx(V)");
		// \post: posy(setCorvee(V,s)) = posy(V)
		if (super.getY() != y_pre)
			throw new PostconditionError("posy(decrCorvee(V)) = posy(V)");
		
	}

	//	------------------------------- [ setXY ] -------------------------------
public void setXY(int x, int y){
	// Premier check des invariants
	this.checkInvariants();
	
	/* ######## Verification des préconditions ######### */
	
	// \pre: x >= 0 && y >= 0
	if ( ! ( x >= 0 && y >= 0)) 
		throw new PreconditionError(" x >= 0 && y >= 0");
	
	/* ######## 	Sauvegarde contexte  		######### */
	int pointsDeVie_pre = super.getPointsDeVie();
	int quantiteOr_pre = super.getQuantiteOr();
	int corvee_pre = super.getCorvee();

	/* ######## 	Execution  		######### */
	super.setXY(x,y);
	
	// Deuxième check des invariants
	this.checkInvariants();
	
	/* ######## Verification des postcondition ######### */
	// \post: pointsDeVie(decrCorvee(V)) = pointsDeVie(V)
	if (super.getPointsDeVie() != pointsDeVie_pre)
		throw new PostconditionError("pointsDeVie(decrCorvee(V))= pointsDeVie(V)@pre");
	
	// \post: quantiteOr(decrCorvee(V))= quantiteOr(V)@pre
	if (super.getQuantiteOr() != quantiteOr_pre)
		throw new PostconditionError("quantiteOr(decrCorvee(V))= quantiteOr(V)@pre");
	// \post: corvee(decrCorvee(V)) = corvee(V) - 1
	if (super.getCorvee() != corvee_pre)
		throw new PostconditionError("corvee(decrCorvee(V)) = corvee(V)@pre");
	// \post: posx(setCorvee(V,s)) = posx(V)
	if (super.getX() != x)
		throw new PostconditionError("posx() = x");
	// \post: posy(setCorvee(V,s)) = posy(V)
	if (super.getY() != y)
		throw new PostconditionError("posy() = y");
	
	
	}
}
