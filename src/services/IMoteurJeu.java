package services;

import java.util.Set;

import enums.ECommande;
import enums.EResultat;

public interface IMoteurJeu {
	/* ########### Observators ########### */	
	int 	getLargeurTerrain();
	int		getHauteurTerrain();
	int		getMaxPasJeu();
	int		getPasJeuCourant();
	boolean estFini();
	EResultat resultatFinal();
	
	// \pre resultatFinal(M) require estFini(M)
	Set<Integer> numeroesVillageois();
	
	IVillageois getVillageois(int num);
    // \pre getVillageois(M,num) 
	//	require num ∈ numeroesVillageois(M,num)
	
	int 	getPositionVillageoisX(int num);
	// \pre positionVillageoisX(M,num) require
	//	num ∈ numeroesVillageois(M,num)
	
	int 	getPositionVillageoisY(int num);
	// \pre positionVillageoisY(M,num) require
	//	num ∈ numeroesVillageois(M,num)
	
	Set<Integer> getNumeroesMine();
	IMine getMine();
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
	
	IMine init(int largeur, int hauteur);
	
	/* \post: maxPasJeu(init(l,h,m))=m
	* \post: pasJeuCourant(init(l,h,m))=0
	* \post: largeurTerrain(init(l,h,m))=l
	* \post: hauteurTerrain(init(l,h,m))=h
	* \post: estFini(init(l,h,m))=False

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
	*  
	
	
	
	
	
	
	
	
	
	
	
	void retrait(int s);
	
	// \post:orRestant(retrait(M,s))=orRestant(M)-s 
	// \post: abandonCompteur(retrait(M,s))=abandonCompteur(M)@pre 
	// \post: estAbandonnee(retrait(M,s))= estAbandonnee(V)@pre
	// \post: estLaminee(retrait(M,s))= estLaminee(V)@pre
	 
	// [acceuil]
	// \pre acceuil(M) require ¬abandoned(M)

	void acceuil();

	// \post: 	orRestant(acceuil(M))=orRestant(M)@pre  
	// \post:	abandonCompteur(accueil(M))=0 

	
	// [abandoned]
	// \pre abandoned(M) require ¬acceuil(M)

	void abandoned();

	// \post: orRestant(abandoned(M))=orRestant(M)
	// \post: abandonCompteur(abandoned(M))=abandonCompteur()+1
	

	/* ########### Invariants ########### */
	// \inv:  estLaminee(M) min = orRestant(M) ≤ 0
	// \inv:  estAbandonnee(M) min = abandonCompteur = 51
	// \inv:  0 ≤abandonCompteur(M)≤ 51
}
