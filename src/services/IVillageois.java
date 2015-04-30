package services;

import enums.ERace;



public interface IVillageois extends IEntite{

	/* ########### Observators ########### */	
	ERace 	getRace();
	int 	getLargeur();
	int		getHauteur();
	int		getForce();
	double	getVitesse();
	int 	getPointsDeVie();
	int		getQuantiteOr();
	boolean	estMort();
	
	/* ########### Constructors ########### */		
	
	/* \pre init(race,largeur,hauteur,force,vitesse,pointsVie) 
	* 	require 	largeur%2=1 ∧ 
	* 				hauteur%2=1 ∧
	* 				force > 0	∧
	* 				vitesse > 0 ∧
	* 				pointsVie > 0
	*/
	IVillageois init(int x, int y, ERace race, int largeur, int hauteur, int force, 
			double vitesse, int pointsDeVie);
	


	
	
	/* ########### Operators ########### */
	
	// [retrait]
	// \pre retrait(V,s) require ¬estMort(V) ∧ s>0
	
	void retrait(int s);
	
	// \post: pointsDeVie(retrait(V,s))= pointsDeVie(V)@pre -s
	// \post: quantiteOr(retrait(V,s))= quantiteOr(V)@pre
	 
	// [chargeOr]
	// \pre chargeOr(V,s) require ¬estMort(V) ∧ s>0

	void chargeOr(int s);

	// \post: pointsDeVie(chargeOr(V,s))= pointsDeVie(V)@pre 
	// \post: quantiteOr(chargeOr(V,s))= quantiteOr(V)@pre+s
	
	// [dechargeOr]
	// \pre dechargeOr(V,s) require ¬estMort(V) ∧ s>0 ∧ s <= quantiteOr(V)

	void dechargeOr(int s);

	// \post: pointsDeVie(dechargeOr(V,s))= pointsDeVie(V)@pre 
	// \post: quantiteOr(dechargeOr(V,s))= quantiteOr(V)@pre - s

	/* ########### Invariants ########### */
	//	minimisation
	// \inv: estMort(V) min= pointsDeVie(V) ≤ 0
	
}
