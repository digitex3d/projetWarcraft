package services;


import java.awt.event.MouseEvent;
import java.util.ArrayList;

import enums.ECommande;
import enums.EResultat;
import gui.EEvent;

public interface IMoteurJeu {
	/* ########### Observators ########### */	
	int 	getLargeurTerrain();
	int		getHauteurTerrain();
	int		getMaxPasJeu();
	int		getPasJeuCourant();
	boolean estFini();
	EResultat resultatFinal();
	
	
	IVillageois getVillageois(int num);
    // \pre getVillageois(M,num) 
	//	require num ∈ numeroesVillageois(M,num)
	
	int 	getPositionVillageoisX(int num);
	// \pre positionVillageoisX(M,num) require
	//	num ∈ numeroesVillageois(M,num)
	
	int 	getPositionVillageoisY(int num);
	// \pre positionVillageoisY(M,num) require
	//	num ∈ numeroesVillageois(M,num)
	
	IMine getMine(int num);
	// \pre getMine(M,num) require num ∈ numeroesMine(M,num)
	
	int getPositionMineX(int num);
	// \pre getPositionMineX(M,num) require
	//	num ∈ numeroesMine(M,num)
	
	int getPositionMineY(int num);
	// \pre getPositionMineY(M,num) require
	//	num ∈ numeroesMine(M,num)
	
	IHotelVille getHotelDeVille();
	int getPositionHotelVilleX();
	int getPositionHotelVilleY();
	boolean peutEntrer(int numVillageois, int numMine);
	// \pre peutEntrer(M,numVillageois,numMine) require 
	// numVillageois ∈ numeroesVillageois(M,numVillageois)
	// ∧ numMine ∈ numeroesMine(M,numMine)
	
	boolean peutEntrerHotelVille(int num);
	// \pre peutEntrerHotelVille(M,numVillageois) 
	// require numVillageois ∈ numeroesVillageois(M,numVillageois)
	
    
	/* ########### Constructors ########### */	
	
	// [ init ]	
	// \pre init(largeur,hauteur,maxPas) require 
	// largeur≥ 600 ∧ hauteur≥ 400 ∧ maxPas≥ 0
	
	IMoteurJeu init(int largeur, int hauteur, int maxPasJeu);
	
	// postconditions
	/* maxPasJeu(init(l,h,m))=m
	 * pasJeuCourant(init(l,h,m))=0
	 * largeurTerrain(init(l,h,m))=l
	 * hauteurTerrain(init(l,h,m))=h
	 * estFini(init(l,h,m))=False
	 * 
	 * // Initialisation Hotel de ville
	 * positionHotelVilleX( init(l,h,m) ) <= 51 
	 * positionHotelVilleY( init(l,h,m) ) <= 51 
	 * HotelVille::orRestant( getHotelVille( init(l,h,m) ) ) = 16
	 * 
	 * // Initialisation Villageois
	 * \forall numV \in numeroesVillageois:
	 * 	  positionVillageoisY(M, numV) - positionHotelVilleY(M) <= 51
	 *      	Ʌ  positionVillageoisX(M, numV) - positionHotelVilleX(M) <= 51
	 *  	    Ʌ Villageois::pointsDeVie( getVillageois(M, numV) ) = 100
	 *     	Ʌ Villageois::quantiteOr( getVillageois(M, numV) ) = 0
	 *  		Ʌ Villageois::estMort( getVillageois(M, numV) ) = False
	 * 
	 * // Initialisation Mines
	 * \forall numM \in numeroesMine:
	 * 	  positionMineY(M, numM) <= largeurTerrain
	 *      	Ʌ  positionMineX(M, numM) <= hauteurTerrain
	 * 	     	Ʌ Mine::estAbandonne( getVillageois(M, numV) ) = True
	 */
		
		
		
		

	/* ########### Operators ########### */
	
	// [pasJeu]
	// \pre: pre pasJeu(M,commmand,numVillgeois,argument) require
	//         / ¬estFini(M)
	//         | command=DEPLACER ⇒ 0 ≤argument≤ 360
	//         \ command=ENTRERMINE ⇒
	//         	>		argument∈numeroesMines(M)
	//		   /   		peutEntrer(M,numVillageois,argument)                       
	//         |command=ENTRERHOTELVILLE ⇒ peutEntrerHotelVille(M,numVillageois)
	//         \	
	
	void pasJeu(ECommande commande, int numVillageois, int argument);

	
	/* \post: pasJeuCourant(pasJeu(M,c,numVillageois,arg))=pasJeuCourant(M) +1
	* 	getMine(pasJeu(M,c,numVillageois,arg),numMine) =
	* 							/	Mine::abandoned(getMine(M,numMine)) si c=ENTRERMINE ∨ arg=numMine
	*							>
	* 							\	Mine::acceuil(getMine(M,numMine)) sinon
	* 	getVillageois(pasJeu(M,c,numVillageois,arg),numVillageois) = 
	* 							/	Villageois::dechargeOr(getVillageois(M,numVillageois),
	*									Villageois::quantiteOr(getVillageois(M,numVillageois))) si c=ENTRERHOTELVILLE
	*							getVillageois(M,numVillageois)@pre
	*  getHotelDeVille(pasJeu(M,c,numVillageois,arg),numVillageois) = 
	*  			HotelVille:depot(getVillageois(M,numVillageois),
	*  						Villageois::quantiteOr(getVillageois(M,numVillageois))) si c=ENTRERHOTELVILLE 
	*                       getHotelDeVille(M)@pre 
	*  
	*/
	
	
	//TODO: Specifier
	ArrayList<IVillageois> getListVillageois();
	ArrayList<IMine> getListMines();
	void bind(ArrayList<IVillageois> villageois, ArrayList<IMine> mines);
	void bind(IHotelVille hv);
	public void eventListener(MouseEvent e, EEvent click);

	

}
