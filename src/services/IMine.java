package services;

import enums.ERace;

public interface IMine extends IEntite {
	/* ########### Observators ########### */	
	int		getOrRestant();
	boolean	estAbandonne();
	boolean	estLaminee();
	int		getAbandonCompteur();
	// pre: ¬estAbandonnee()
	ERace   getEtatAppartenance();

	/* ########### Constructors ########### */		

	/** 
		pre l % 2 = 1 && h % 2 = 1 && x > 0 && y > 0
		post: posx() == x
		post: posy() == y
		post: largeur() == l
		post: hauteur() == h
		post: orRestant() == 51
		post: abandonCompteur() == 51
		post: etatAppartenance() == ORC
	*/
	IMine init(int x, int y, int l, int h);
	
	/* ########### Operators ########### */
	
	/** [retrait]
		pre: ! estLaminee() && s > 0
		post: orRestant() == orRestant()@pre - s 
		post: abandonCompteur() == abandonCompteur()@pre 
		post: etatAppartenance() == etatAppartenance()@pre
	 */
	void retrait(int s);
	
	
	/** [acceuil]
		pre: estAbandonnee() || etatAppartenance() == r
		post: orRestant() == orRestant()@pre  
		post: abandonCompteur() == 0 
		post: etatAppartenance() == race
	*/
	void acceuil(ERace race);

	
	/** [abandoned]
		pre: ! estAbandonne()
		post: orRestant() == orRestant()@pre
		post: abandonCompteur() == abandonCompteur()@pre + 1
		post: etatAppartenance() == etatAppartenance()@pre
	*/
	void abandoned();


	/* ########### Invariants ########### */
	// inv: estLaminee() min= orRestant() <= 0
	// inv: estAbandonnee() min= abandonCompteur == 51
	// inv: 0 <= abandonCompteur() <= 51
	
}
