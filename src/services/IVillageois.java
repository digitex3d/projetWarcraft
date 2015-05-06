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
	
	/* init : int x int x ERace × int × int × int × double × int → [Villageois]
	* 	\pre: 	largeur % 2=1 && 
	*       	hauteur % 2=1 &&
	*       	force > 0 && 
	*     		vitesse > 0 && 
	* 			pointsVie > 0 &&
	* 			x >= 0 &&
	*		 	y >= 0
	*/

	IVillageois init(int x, int y, ERace race, int largeur, int hauteur, int force, 
			double vitesse, int pointsDeVie);
	
	/* ########### Operators ########### */
	
	//	[setXY]
	//	\pre: x >= 0 &&  
	//  \pre: y >= 0
	
	void setXY(int x, int y);
	
	//  \post: pointsDeVie(setX(x))== pointsDeVie()@pre
	//	\post: quantiteOr(setX(x)) == quantiteOr()@pre
	//	\post: corvee(setX(x)) == corvee()@pre
	//	\post: posx(setX(x)) == x	
	//	\post: posy(setX(x)) == y	
	 
	
	// [retrait]
	// \pre: !estMort() && !estOccupe() && s>0
	
	void retrait(int s);
	
	// \post: pointsDeVie() == pointsDeVie()@pre - s
	// \post: quantiteOr() == quantiteOr()@pre
	// \post: corvee() == corvee()@pre
	// \post: posx() == posx()@pre
	// \post: posy() == posy()@pre	

	// [chargeOr]
	// \pre: !estMort(V) && s>0
	
	void chargeOr(int s);
	
	// \post: pointsDeVie() == pointsDeVie()@pre
	// \post: quantiteOr() == quantiteOr()@pre + s       
	// \post: corvee() == corvee()@pre               
	// \post: posx() == posx()@pre                   
	// \post: posy() == posy()@pre	                

	

	// [dechargeOr]
	// \pre: !estMort() && s>0 && s <= quantiteOr()
	
	void dechargeOr(int s);
	
	// \post: pointsDeVie() == pointsDeVie()@pre   	
	// \post: quantiteOr() == quantiteOr()@pre - s 	
	// \post: corvee() == corvee()@pre             	
	// \post: posx() == posx()@pre                 	
	// \post: posy() == posy()@pre	               	

	// [setCorvee]
	// \pre: !estMort(V) && !estOccupe(V) && s > 0 && corveeX >= 
	// 			and corveeY >= 0
	
	void setCorvee(int s, int corveeX, int corveeY);
	
	// \post: pointsDeVie() == pointsDeVie()@pre   
	// \post: quantiteOr() == quantiteOr()@pre 
	// \post: corvee() == s          
	// \post: posx() == corveeX                 
	// \post: posy() == corveeY	               


	
	// [decrCorvee]
	// \pre: !estMort() && !estOccupe()
	
	void decrCorvee();
	
	// \post: pointsDeVie() == pointsDeVie()@pre 
	// \post: quantiteOr() == quantiteOr()@pre   
	// \post: corvee() == corvee()@pre -1                     
	// \post: posx() == posx()@pre                  
	// \post: posy() == posy()@pre	                 
	
	
	/* ########### Invariants ########### */
	// \inv: estMort(V) min= pointsDeVie(V) ≤ 0
	// \inv: estOccupe(V) min= corvee > 0

}
