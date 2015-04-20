package contracts;

import services.IMine;
import decorators.MineDecorator;
import exceptions.InvariantError;
import exceptions.PostconditionError;
import exceptions.PreconditionError;

public class MineContract extends MineDecorator {

	public MineContract(IMine delegate) {
		super(delegate);
		
	}
	
	//TODO: Verifier
	public void checkInvariants(){
		 // \inv : estLaminee(M) min = orRestant(M) ≤ 0
		if ( super.estLaminee() )
			if(super.getOrRestant() > 0)
				throw new InvariantError("estLaminee(M) min = orRestant(M) ≤ 0");

		 // \inv : estAbandonnee(M) min = abandonCompteur = 51
		if ( super.estAbandonne())
			if(super.getAbandonCompteur() != 51)
				throw new InvariantError("estAbandonnee(M) min = abandonCompteur = 51");

		// \inv : 0 ≤abandonCompteur(M)≤ 51
		if (  super.getAbandonCompteur() > 51 ||  super.getAbandonCompteur() <0)
				throw new InvariantError("0 ≤abandonCompteur(M)≤ 51");

	}
	
	public IMine init(int largeur, int hauteur ){
		// Premier check des invariants
		this.checkInvariants();

		/* ######## Verification des préconditions ######### */
		/* 	pre init(race,largeur,hauteur,force,vitesse,pointsVie) 
		* 	require 	largeur%2=1 ∧ 
		* 				hauteur%2=1 ∧
		*/
		if( ! ( largeur % 2 == 1 ) ) 
			throw new PreconditionError("inv: largeur%2!=1");
		if( ! ( hauteur % 2 == 1 ) ) 
			throw new PreconditionError("inv: hauteur%2!=1");

		//Sauvegarde contexte  

		/* ######## 	Execution  		######### */
		super.init( largeur, hauteur);

		/* ######## Verification des postcondition ######### */
		// \post :  largeur(init(l,h))=l
		if( super.getLargeur() != largeur)
			throw new PostconditionError("largeur(init(l,h))=l");
		// \post :  hauteur(init(l,h))=h
		if( super.getHauteur() != hauteur)
			throw new PostconditionError("hauteur(init(l,h))=h");
		// \post :  orRestant(init(l,h))=51
		if( super.getOrRestant()!= 51)
			throw new PostconditionError("orRestant(init(l,h))=51");
		// \post :  abandonCompteur(init(l,h))=51
		if( super.getAbandonCompteur() != 51)
			throw new PostconditionError("abandonCompteur(init(l,h))=51");

		// Deuxième check des invariants
		this.checkInvariants();
		
		return this;
		
	}
	
	public void retrait(int s){
		/* ######## Premier check des invariants ######### */
		this.checkInvariants();

		/* ######## Verification des préconditions ######### */
	
		// \pre retrait(M,s) require ¬estLaminee(M) ∧ s>0
		if( super.estLaminee() && s <= 0 )
			throw new  PreconditionError("retrait(M,s) require ¬estLaminee(M) ∧ s>0");

		/* ######## 	Sauvegarde contexte  		######### */
		int abandonCompteur_pre = super.getAbandonCompteur();
		boolean estAbandonne_pre = super.estAbandonne();
		int OrRestant_pre = super.getOrRestant();
		boolean estLaminee_pre = super.estLaminee();
		
		// Execution
		super.retrait(s);
		
		
		/* ######## Verification des postcondition ######### */
		// \post: orRestant(retrait(M,s))=orRestant(M)-s
			if( super.getOrRestant() != OrRestant_pre - s )                                     
				throw new  PostconditionError("orRestant(retrait(M,s))=orRestant(M)-s");  
			
		// \post: abandonCompteur(retrait(M,s))=abandonCompteur(M)@pre
			if( super.getAbandonCompteur() != abandonCompteur_pre )                                     
				throw new  PostconditionError("abandonCompteur(retrait(M,s))=abandonCompteur(M)@pre");  
			
		// \post: estAbandonnee(retrait(M,s))= estAbandonnee(M)@pre 
			if( super.estAbandonne() != estAbandonne_pre )                                     
				throw new  PostconditionError("estAbandonnee(retrait(M,s))= estAbandonnee(M)@pre ");  
		// \post: estLaminee(retrait(M,s))= estLaminee(M)@pre   
			if( super.estLaminee() != estLaminee_pre)                                     
				throw new  PostconditionError("estLaminee(retrait(M,s))= estLaminee(M)@pre");  

		/* ######## Deuxième check des invariants ######### */
		this.checkInvariants();
		
	}
	
	public void acceuil(){
		// Premier check des invariants
		this.checkInvariants();

		/* ######## Verification des préconditions ######### */
		// \pre: pre acceuil(M) require ¬abandoned(M)
		if( super.estAbandonne() ) 
			throw new  PreconditionError("pre acceuil(M) require ¬abandoned(M)");

		//Sauvegarde contexte  

		/* ######## 	Execution  		######### */

		/* ######## Verification des postcondition ######### */

		// Deuxième check des invariants
		this.checkInvariants();
		
	}
	
	
	
	
	

}
