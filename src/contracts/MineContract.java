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
	
	public void checkInvariants(){
		 // \inv : estLaminee(M) min = orRestant(M) ≤ 0
		if (super.estLaminee() && super.getOrRestant() > 0)
			throw new InvariantError("estLaminee(M) min = orRestant(M) ≤ 0");

		 // \inv : estAbandonnee(M) min = abandonCompteur = 51
		if (super.estAbandonne() && super.getAbandonCompteur() != 51)
			throw new InvariantError("estAbandonnee(M) min = abandonCompteur = 51");

		// \inv : 0 ≤abandonCompteur(M)≤ 51
		if (super.getAbandonCompteur() > 51 || super.getAbandonCompteur() < 0)
			throw new InvariantError("0 ≤abandonCompteur(M)≤ 51");
	}
	
	public IMine init(int largeur, int hauteur ){
		/* ######## Verification des préconditions ######### */
		/* \pre init(largeur,hauteur) 
		* 	require 	largeur%2=1 ∧ 
		* 				hauteur%2=1
		*/
		if (largeur % 2 != 1) 
			throw new PreconditionError("pre: largeur%2!=1");
		if (hauteur % 2 != 1) 
			throw new PreconditionError("pre: hauteur%2!=1");

		//Sauvegarde contexte  

		/* ######## 	Execution  		######### */
		super.init(largeur, hauteur);

		// Deuxième check des invariants
		this.checkInvariants();
		
		/* ######## Verification des postcondition ######### */
		// \post :  largeur(init(l,h))=l
		if( super.getLargeur() != largeur)
			throw new PostconditionError("largeur(init(l,h))=l");
		// \post :  hauteur(init(l,h))=h
		if( super.getHauteur() != hauteur)
			throw new PostconditionError("hauteur(init(l,h))=h");
		// \post :  orRestant(init(l,h))=51
		if( super.getOrRestant() != 51)
			throw new PostconditionError("orRestant(init(l,h))=51");
		// \post :  abandonCompteur(init(l,h))=51
		if( super.getAbandonCompteur() != 51)
			throw new PostconditionError("abandonCompteur(init(l,h))=51");
		
		return this;
	}
	
	public void retrait(int s){
		/* ######## Premier check des invariants ######### */
		this.checkInvariants();

		/* ######## Verification des préconditions ######### */
	
		// \pre retrait(M,s) require ¬estLaminee(M) ∧ s>0
		if( super.estLaminee() || s <= 0)
			throw new PreconditionError("retrait(M,s) require ¬estLaminee(M) ∧ s>0");

		/* ######## 	Sauvegarde contexte  		######### */
		int abandonCompteur_pre = super.getAbandonCompteur();
		int OrRestant_pre = super.getOrRestant();
		
		// Execution
		super.retrait(s);
		
		/* ######## Deuxième check des invariants ######### */
		this.checkInvariants();
		
		/* ######## Verification des postcondition ######### */
		// \post: orRestant(retrait(M,s))=orRestant(M)-s
			if (super.getOrRestant() != OrRestant_pre - s)                                     
				throw new PostconditionError("orRestant(retrait(M,s))=orRestant(M)-s");  
			
		// \post: abandonCompteur(retrait(M,s))=abandonCompteur(M)@pre
			if (super.getAbandonCompteur() != abandonCompteur_pre)                                     
				throw new PostconditionError("abandonCompteur(retrait(M,s))=abandonCompteur(M)@pre");  
	}
	
	//	------------------------------- [ acceuil ] -------------------------------
	public void acceuil(){
		// Premier check des invariants
		this.checkInvariants();

		/* ######## Verification des préconditions ######### */
		
		/* bug ??? 
		// \pre: pre acceuil(M) require ¬abandoned(M)
		if (super.estAbandonne()) 
			throw new PreconditionError("pre acceuil(M) require ¬abandoned(M)");
*/
		/* ######## 	Sauvegarde contexte  		######### */
		int orRestant_pre = super.getOrRestant();
		
		/* ######## 	Execution  		######### */
		super.acceuil();
		
		// Deuxième check des invariants
		this.checkInvariants();

		/* ######## Verification des postcondition ######### */
		
		// \pre: orRestant(acceuil(M))=orRestant(M)@pre
		if (super.getOrRestant() != orRestant_pre)                                     
			throw new PostconditionError("orRestant(acceuil(M))=orRestant(M)@pre"); 
		
		// \pre: abandonCompteur(accueil(M))=0
		if (super.getAbandonCompteur() != 0)                                     
			throw new PostconditionError("abandonCompteur(accueil(M))=0"); 
	}
	
	// --------------------- [abandoned] -----------------------------
	public void abandoned(){
		// Premier check des invariants
		this.checkInvariants();
		
		/* ######## Verification des préconditions ######### */
		/* bug???
		// \pre: abandoned(M) require ¬acceuil(M)
		if( ! super.estAbandonne() )
			throw new PreconditionError(" abandoned(M) require ¬estAbandonne()");
		*/
		//Sauvegarde contexte  
		int orRestant_pre = super.getOrRestant();
		int abandonCompteur_pre = super.getAbandonCompteur();
		
		/* ######## 	Execution  		######### */
		super.abandoned();
		
		// Deuxième check des invariants
		this.checkInvariants();
		
		/* ######## Verification des postcondition ######### */
		// \pre: orRestant(acceuil(M))=orRestant(M)@pre
		if (super.getOrRestant() != orRestant_pre)                                     
			throw new PostconditionError("orRestant(acceuil(M))=orRestant(M)@pre"); 

		// \post: abandonCompteur(abandoned(M))=abandonCompteur()+1	
		if (super.getAbandonCompteur() != abandonCompteur_pre + 1)                                     
				throw new PostconditionError("abandonCompteur(abandoned(M))=abandonCompteur()+1"); 
	}
}
