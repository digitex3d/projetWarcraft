package contracts;

import services.IVillageois;
import decorators.VillageoisDecorator;
import enums.ERace;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;

// TODO: à comppléter
public class VillageoisContract extends VillageoisDecorator {

	public VillageoisContract(IVillageois delegate) {
		super(delegate);
		
	}
	
	public void checkInvariants() {
		// \inv: estMort(V) min= pointsDeVie(V) ≤ 0
		if( super.estMort())
			if(super.getPointsDeVie() > 0 )
				throw new InvariantError("inv: estMort(V) min= pointsDeVie(V) > 0");
		
	}
	
	public IVillageois init(ERace race, int largeur, int hauteur, int force,
			double vitesse, int pointsDeVie) {
		
		// Check inv avant
		this.checkInvariants();
		
		/* 	pre init(race,largeur,hauteur,force,vitesse,pointsVie) 
		* 	require 	largeur%2=1 ∧ 
		* 				hauteur%2=1 ∧
		* 				force, vitesse, pointsVie > 0
		*/
		if( ! ( largeur % 2 == 1 ) ) 
			throw new InvariantError("inv: largeur%2!=1");
		if( ! ( hauteur % 2 == 1 ) ) 
			throw new InvariantError("inv: hauteur%2!=1");
		if( ! ( force > 0 && vitesse > 0 && pointsDeVie > 0 ) ) 
			throw new InvariantError("inv: force, vitesse, pointsVie <= 0");
	
		
		// Premier check des invariants
		this.checkInvariants();
		
		// execute function
		super.init(race, largeur, hauteur, force, vitesse, pointsDeVie);

		// Deuxième check des invariants
		this.checkInvariants();
		
		return this;
	}
	
	public void retrait(int s){
		/* ######## Premier check des invariants ######### */
		this.checkInvariants();

		/* ######## Verification des préconditions ######### */
	
		// \pre retrait(V,s) require ¬estMort(V) ∧ s>0
		if( super.estMort() || super.getPointsDeVie() <= 0 )
			throw new  PreconditionError("retrait(V,s) require ¬estMort(V) ∧ s>0");

		/* ######## 	Sauvegarde contexte  		######### */
		int pointsDeVie_pre = super.getPointsDeVie();
		int quantiteOr_pre = super.getQuantiteOr();
		
		// Execution
		super.retrait(s);
		
		
		/* ######## Verification des postcondition ######### */
		
		// \post: pointsDeVie(retrait(V,s))= pointsDeVie(V)@pre -s
		if( super.getPointsDeVie() != pointsDeVie_pre - s )
			throw new  PostconditionError("pointsDeVie(retrait(V,s))= pointsDeVie(V)@pre -s");
		
		// \post: quantiteOr(retrait(V,s))= quantiteOr(V)@pre
		if( super.getQuantiteOr() != quantiteOr_pre )
			throw new  PostconditionError("quantiteOr(retrait(V,s))= quantiteOr(V)@pre");
		

		/* ######## Deuxième check des invariants ######### */
		this.checkInvariants();
		
		// Deuxième check des invariants
		this.checkInvariants();
		
		
		
	}

}
