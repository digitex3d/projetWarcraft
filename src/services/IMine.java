package services;

public interface IMine extends IEntite {
	/* ########### Observators ########### */	
	int		getOrRestant();
	boolean	estAbandonne();
	boolean	estLaminee();
	int		getAbandonCompteur();

	/* ########### Constructors ########### */		

	/* \pre init(largeur,hauteur) 
	* 	require 	largeur%2=1 ∧ 
	* 				hauteur%2=1
	*/
	IMine init(int largeur, int hauteur);
	// \post: largeur(init(l,h))=l
	// \post: hauteur(init(l,h))=h
	// \post: orRestant(init(l,h))=51
	// \post: abandonCompteur(init(l,h))=51

	/* ########### Operators ########### */
	
	// [retrait]
	// \pre retrait(M,s) require ¬estLaminee(M) ∧ s>0
	
	void retrait(int s);
	
	// \post:orRestant(retrait(M,s))=orRestant(M)-s 
	// \post: abandonCompteur(retrait(M,s))=abandonCompteur(M)@pre 
	 
	// [acceuil]
	// \pre acceuil(M) require ¬abandoned(M) //bug???

	void acceuil();

	// \post: 	orRestant(acceuil(M))=orRestant(M)@pre  
	// \post:	abandonCompteur(accueil(M))=0 

	
	// [abandoned]
	// \pre abandoned(M) require ¬acceuil(M) //bug???

	void abandoned();

	// \post: orRestant(abandoned(M))=orRestant(M)
	// \post: abandonCompteur(abandoned(M))=abandonCompteur()+1
	

	/* ########### Invariants ########### */
	// \inv:  estLaminee(M) min = orRestant(M) ≤ 0
	// \inv:  estAbandonnee(M) min = abandonCompteur = 51
	// \inv:  0 ≤abandonCompteur(M)≤ 51
	
}
