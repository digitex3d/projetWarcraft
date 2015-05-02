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
	int		getCorvee();
	boolean estOccupe();
	
	/* ########### Constructors ########### */		
	
	/* \pre init(race,largeur,hauteur,force,vitesse,pointsVie) 
	* 	require 	largeur%2=1 ∧ 
	* 				hauteur%2=1 ∧
	* 				force > 0	∧
	* 				vitesse > 0 ∧
	* 				pointsVie > 0
	*/
	// \post: posx(init(x,y,s,l,h,f,v,p))=x
	// \post: posy(init(x,y,s,l,h,f,v,p))=y
	// \post: race(init(x,y,s,l,h,f,v,p))=s
	// \post: largeur(init(x,y,s,l,h,f,v,p))=l
	// \post: hauteur(init(x,y,s,l,h,f,v,p))=h
	// \post: force(init(x,y,s,l,h,f,v,p))=f
	// \post: vitesse(init(x,y,s,l,h,f,v,p))=v
	// \post: pointsDeVie(init(x,y,s,l,h,f,v,p))=p
	// \post: quantiteOr(init(x,y,s,l,h,f,v,p))=0
	// \post: corvee(init(x,y,s,l,h,f,v,p)) = 0
	IVillageois init(int x, int y, ERace race, int largeur, int hauteur, int force, 
			double vitesse, int pointsDeVie);
	
	/* ########### Operators ########### */
	
	// [retrait]
	// \pre retrait(V,s) require ¬estMort(V) ∧ ¬estOccupe(V) ∧ s>0
	// \post: pointsDeVie(retrait(V,s))= pointsDeVie(V)@pre -s
	// \post: quantiteOr(retrait(V,s))= quantiteOr(V)@pre
	// \post: corvee(retrait(V,s)) = corvee(V)
	// \post: posx(retrait(V,s)) = posx(V)	
	// \post: posy(retrait(V,s)) = posy(V)	
	
	void retrait(int s);
	
	// [chargeOr]
	// \pre chargeOr(V,s) require ¬estMort(V) ∧ s>0
	// \post: pointsDeVie(chargeOr(V,s))= pointsDeVie(V)@pre 
	// \post: quantiteOr(chargeOr(V,s))= quantiteOr(V)@pre+s
	// \post: corvee(chargeOr(V,s)) = corvee(V)	
	// \post: posx(chargeOr(V,s)) = posx(V)	
	// \post: posy(chargeOr(V,s)) = posy(V)	

	void chargeOr(int s);

	// [dechargeOr]
	// \pre dechargeOr(V,s) require ¬estMort(V) ∧ s>0 ∧ s <= quantiteOr(V)
	// \post: pointsDeVie(dechargeOr(V,s))= pointsDeVie(V)@pre 
	// \post: quantiteOr(dechargeOr(V,s))= quantiteOr(V)@pre - s
	// \post: corvee(dechargeOr(V,s)) = corvee(V)
	// \post: posx(dechargeOr(V,s)) = posx(V)	
	// \post: posy(dechargeOr(V,s)) = posy(V)	

	void dechargeOr(int s);

	// [setCorvee]
	// \pre setCorvee(V, s) require ¬estMort(V) ∧ ¬estOccupe(V) ∧ s > 0
	// \post: pointsDeVie(setCorvee(V,s)) = pointsDeVie(V)
	// \post: quantiteOr(setCorvee(V,s)) = quantiteOr(V)
	// \post: corvee(setCorvee(V,s)) = s
	// \post: posx(setCorvee(V,s)) = posx(V)	
	// \post: posy(setCorvee(V,s)) = posy(V)	

	void setCorvee(int s);
	
	// [decrCorvee]
	// \pre decrCorvee(V) require ¬estMort(V) ∧ estOccupe(V)
	// \post: pointsDeVie(decrCorvee(V)) = pointsDeVie(V)
	// \post: quantiteOr(decrCorvee(V)) = quantiteOr(V)
	// \post: corvee(decrCorvee(V)) = corvee(V) - 1
	// \post: posx(decrCorvee(V)) = posx(V)	
	// \post: posy(decrCorvee(V)) = posy(V)
	
	void decrCorvee();
	
	/* ########### Invariants ########### */
	//	minimisation
	// \inv: estMort(V) min= pointsDeVie(V) ≤ 0
	// \inv: estOccupe(V) min= corvee > 0
	// \inv: corvee(V) >= 0
}
