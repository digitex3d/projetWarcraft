package contracts;

import services.IMine;
import decorators.MineDecorator;
import enums.ERace;
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
		// \post: etatAppartenance(init(l, h)) = ORC
		if (super.getEtatAppartenance() != ERace.ORC)
			throw new PostconditionError("etatAppartenance(init(l, h)) = ORC");
		
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
		ERace etatAppartenance_pre = super.getEtatAppartenance();
		
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

		// \post: etatAppartenance(retrait(M,s)) = etatAppartenance(M)@pre
		if (super.getEtatAppartenance() != etatAppartenance_pre)
			throw new PostconditionError("etatAppartenance(retrait(M,s)) = etatAppartenance(M)@pre");
}
	
	//	------------------------------- [ acceuil ] -------------------------------
	public void acceuil(ERace race){
		// Premier check des invariants
		this.checkInvariants();

		/* ######## Verification des préconditions ######### */
		
		// \pre: acceuil(M, r) require estAbandonnee(M) v etatAppartenance(M) = r
		if ( ! (super.estAbandonne() || super.getEtatAppartenance() == race)) 
			throw new PreconditionError("pre acceuil(M, r) require estAbandonnee(M) v etatAppartenance(M) = r");

		/* ######## 	Sauvegarde contexte  		######### */
		int orRestant_pre = super.getOrRestant();
		
		/* ######## 	Execution  		######### */
		super.acceuil(race);
		
		// Deuxième check des invariants
		this.checkInvariants();

		/* ######## Verification des postcondition ######### */
		
		// \post: orRestant(acceuil(M))=orRestant(M)@pre
		if (super.getOrRestant() != orRestant_pre)                                     
			throw new PostconditionError("orRestant(acceuil(M))=orRestant(M)@pre"); 
		
		// \post: abandonCompteur(accueil(M))=0
		if (super.getAbandonCompteur() != 0)                                     
			throw new PostconditionError("abandonCompteur(accueil(M))=0");
		
		// \post: etatAppartenance(accueil(M, r)) = r
		if (super.getEtatAppartenance() != race)
			throw new PostconditionError("etatAppartenance(accueil(M, r)) = r");
	}
	
	// --------------------- [abandoned] -----------------------------
	public void abandoned(){
		// Premier check des invariants
		this.checkInvariants();
		
		/* ######## Verification des préconditions ######### */
		// \pre: abandoned(M) require ¬estAbandonne()
		if (super.estAbandonne())
			throw new PreconditionError(" abandoned(M) require ¬estAbandonne()");

		//Sauvegarde contexte  
		int orRestant_pre = super.getOrRestant();
		int abandonCompteur_pre = super.getAbandonCompteur();
		ERace etatAppartenance_pre = super.getEtatAppartenance();
		
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
		
		// \post: etatAppartenance(abandoned(M)) = etatAppartenance(M)@pre
		if (super.getEtatAppartenance() != etatAppartenance_pre)
			throw new PostconditionError("etatAppartenance(abandoned(M)) = etatAppartenance(M)@pre");
	}
	
	public ERace getEtatAppartenance() {
		// \pre: etatAppartenance(M) require ¬estAbandonnee()
		if (super.estAbandonne())
			throw new PreconditionError("etatAppartenance(M) require ¬estAbandonnee()");
		
		return super.getEtatAppartenance();
	}
}
