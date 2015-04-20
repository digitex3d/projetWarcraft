package services;

import enums.ERace;



public interface IVillageois {

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
	IVillageois init(ERace race, int largeur, int hauteur, int force, 
			double vitesse, int pointsDeVie);
	
	/* \pre init(race,largeur,hauteur,force,vitesse,pointsVie) 
	* 	require 	largeur%2=1 ∧ 
	* 				hauteur%2=1 ∧
	* 				force, vitesse, pointsVie > 0
	*/

	
	
	/* ########### Operators ########### */
	
	// [retrait]
	// \pre retrait(V,s) require ¬estMort(V) ∧ s>0
	
	void retrait(int s);
	
	// \post: pointsDeVie(retrait(V,s))= pointsDeVie(V)@pre -s
	// \post: quantiteOr(retrait(V,s))= quantiteOr(V)@pre
	// \post: estMort(retrait(V,s))= estMort(V)@pre
	 
	// [chargeOr]
	// \pre chargeOr(V,s) require ¬estMort(V) ∧ s>0

	void chargeOr(int s);

	// \post: pointsDeVie(chargeOr(V,s))= pointsDeVie(V)@pre -s
	// \post: quantiteOr(chargeOr(V,s))= quantiteOr(V)@pre
	// \post: estMort(chargeOr(V,s))= estMort(V)@pre
	
	// [dechargeOr]
	// \pre retrait(V,s) require ¬estMort(V) ∧ s>0

	void dechargeOr(int s);

	// \post: pointsDeVie(retrait(V,s))= pointsDeVie(V)@pre -s
	// \post: quantiteOr(retrait(V,s))= quantiteOr(V)@pre
	// \post: estMort(dechargeOr(V,s))= estMort(V)@pre

	/* ########### Invariants ########### */
	//	minimisation
	// \inv: estMort(V) min= pointsDeVie(V) ≤ 0
	
}
